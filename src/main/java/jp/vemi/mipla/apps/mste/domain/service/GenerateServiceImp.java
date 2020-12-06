/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mipla.apps.mste.domain.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import groovy.lang.Tuple2;
import jp.vemi.framework.exeption.MessagingException;
import jp.vemi.framework.util.InstanceUtil;
import jp.vemi.mipla.apps.mste.domain.dto.GenerateParameter;
import jp.vemi.mipla.apps.mste.domain.dto.GenerateResult;
import jp.vemi.mipla.foundation.abst.dao.entity.FileManagement;
import jp.vemi.mipla.foundation.abst.dao.repository.FileManagementRepository;
import jp.vemi.mipla.foundation.feature.files.service.FileRegisterService;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;
import jp.vemi.ste.domain.context.SteContext;
import jp.vemi.ste.domain.engine.LogicTemplateEngine;
import jp.vemi.ste.domain.engine.StructureReader;

/**
 * {@link GenerateService} の具象です。
 */
@Service
@Transactional
public class GenerateServiceImp implements GenerateService{

    @Autowired
    protected FileRegisterService fileRegisterService;
    /** {@link FileManagementRepository} */
    @Autowired
    protected FileManagementRepository fileManagementRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse<GenerateResult> invoke(ApiRequest<GenerateParameter> parameter) {

        ApiResponse<GenerateResult> resp = ApiResponse.<GenerateResult>builder().build();

        List<Tuple2<String, String>> retItems = Lists.newArrayList();
        parameter.getModel().params.stream().forEach(item -> {

            Map<String, Object> once = InstanceUtil.forceCast(item.get("content"));

            // FIXME ステンシル定義からファイルIDタイプのものを取得する
            Object fileIdObj = once.get("cdmaster");
            if (null != fileIdObj) {
                String fileId = fileIdObj.toString();
                if (false == StringUtils.isEmpty(fileId)) {
                    try {
                        once.putAll(file(fileId, resp));
                    } catch(Throwable e) {
                        e.printStackTrace();
                        resp.errs.add(e.getLocalizedMessage());
                        return;
                    }
                }
            }
            List<String> errs = validate(once);
            if(false == CollectionUtils.isEmpty(errs)) {
                // has err
                resp.errs.addAll(errs);
                return;
            }

            // create.
            String filePath;
            try{
                filePath = LogicTemplateEngine.create(
                    SteContext.newSteContext(once)).execute();
            } catch(MessagingException e) {
                e.printStackTrace();
                resp.errs.addAll(e.messages);
                return;
            } catch(Throwable e) {
                e.printStackTrace();
                resp.errs.add(e.getLocalizedMessage());
                return;
            }

            // register fileitem.
            Tuple2<String, String> file = fileRegisterService.register(new File(filePath));

            retItems.add(file);

        });

        resp.setModel(GenerateResult.builder().files(retItems).build());

        return resp;

    }

    protected Map<String, Object> file(final String fileId, ApiResponse<GenerateResult> resp) {

        Map<String, Object> once = Maps.newHashMap();
        FileManagement item;
        try {
          item = fileManagementRepository.findById(fileId).get();
        } catch (NoSuchElementException e) {
          e.printStackTrace();
          resp.infos.add("ファイルが見つかりません。ファイル管理ID：" + fileId);
          return once;
        }
  
        // file record is null.
        if (null == item) {
          resp.infos.add("ファイルが見つかりません。ファイル管理ID：" + fileId);
          return once;
        }



        StructureReader sreader = new StructureReader();
        Map<String, List<Map<String, Object>>> tac = sreader.read(item.getFilePath());
        for (Map.Entry<String, List<Map<String, Object>>> entry : tac.entrySet()) {
            once.put(entry.getKey(), entry.getValue());
        }
    
        return once;
    }
    /**
     * validate. <br/>
     * @param param パラメータ
     * @return メッセージ一覧
     */
    public List<String> validate(Map<String, Object> param) {

        List<String> valids = Lists.newArrayList();

        if (StringUtils.isEmpty(param.get("stencilCanonicalName")) || "*".equals(param.get("stencilCanonicalName"))) {
            valids.add("ステンシルが指定されていません。");
        }

        if (StringUtils.isEmpty(param.get("serialNo")) || "*".equals(param.get("serialNo"))) {
            valids.add("シリアルが指定されていません。");
        }

        return valids;
    }
}

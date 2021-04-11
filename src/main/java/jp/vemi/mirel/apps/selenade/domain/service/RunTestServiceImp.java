/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.domain.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.annotation.Generated;

import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.ConstructorException;

import jp.vemi.framework.exeption.MirelApplicationException;
import jp.vemi.framework.exeption.MirelSystemException;
import jp.vemi.framework.util.FileUtil;
import jp.vemi.framework.util.StorageUtil;
import jp.vemi.mirel.apps.selenade.agent.SelenideAgent;
import jp.vemi.mirel.apps.selenade.domain.dto.RunTestParameter;
import jp.vemi.mirel.apps.selenade.domain.dto.RunTestResult;
import jp.vemi.mirel.apps.selenade.dto.yml.ArActivity;
import jp.vemi.mirel.apps.selenade.dto.yml.ArSelenadePage;
import jp.vemi.mirel.foundation.abst.dao.entity.FileManagement;
import jp.vemi.mirel.foundation.abst.dao.repository.FileManagementRepository;
import jp.vemi.mirel.foundation.feature.files.service.FileDownloadService;
import jp.vemi.mirel.foundation.web.api.dto.ApiRequest;
import jp.vemi.mirel.foundation.web.api.dto.ApiResponse;

/**
 * {@link RunTestService テスト実行} の具象です。
 */
@Service
@Transactional
@Generated(value = {"jp.vemi.ste.domain.engine.TemplateEngineProcessor"}, comments = "Generated from /mirel/service:191207A")
public class RunTestServiceImp implements RunTestService {


    /** {@link FileManagementRepository} */
    @Autowired
    protected FileManagementRepository fileManagementRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse<RunTestResult> invoke(ApiRequest<RunTestParameter> parameter) {

        ApiResponse<RunTestResult> resp = ApiResponse.<RunTestResult>builder().build();

        resp.setModel(RunTestResult.builder().build());
        exec(parameter, resp);
        return resp;

    }

    protected void exec(ApiRequest<RunTestParameter> parameter, ApiResponse<RunTestResult> resp) {

        String appKey = (String)parameter.getModel().params.get(0).get("key");
        List<File> files = FileUtil.getFiles(StorageUtil.getFile("apps/apprunner/defs/" + appKey));
        for (File file : files) {
            if (false == file.isDirectory()) {
                continue;
            }

            String fileName = file.getName();
            if (fileName.startsWith("activity") && fileName.endsWith(".yml"))  {
                // activity.
                ArActivity activity = getYaml(file, ArActivity.class);
                continue;
            }

            if (file.getName().endsWith("page") && fileName.endsWith(".yml")) {
                // page.
                ArSelenadePage page = getYaml(file, ArSelenadePage.class);
                continue;
            }
        }
    }

    /**
     * Get object from yaml file.<br/>
     * @param <T>
     * @param file
     * @param clazz
     * @return
     */
    protected <T> T getYaml(File file, Class<T> clazz) {
        T object;
        try(InputStream stream = new FileSystemResource(file).getInputStream()) {
            object = new Yaml(new Constructor(clazz)).loadAs(stream, clazz);
        } catch (final ConstructorException e) {
            e.printStackTrace();
            String msg = "yamlの読込でエラーが発生しました。";
            if(isDebugMode()) {
                msg += "debug log:" + e.getLocalizedMessage();
            }
            throw new MirelApplicationException(msg, e);
        } catch (final IOException e) {
            e.printStackTrace();
            throw new MirelSystemException("yamlの読込で入出力エラーが発生しました。", e);
        }
        return object;
    }
    protected boolean isDebugMode() {
        return true;
    }

}

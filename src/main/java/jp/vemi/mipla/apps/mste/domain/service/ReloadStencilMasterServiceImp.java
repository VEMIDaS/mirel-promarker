/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mipla.apps.mste.domain.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import jp.vemi.mipla.apps.mste.domain.dao.entity.MsteStencil;
import jp.vemi.mipla.apps.mste.domain.dao.repository.MsteStencilRepository;
import jp.vemi.mipla.apps.mste.domain.dto.ReloadStencilMasterParameter;
import jp.vemi.mipla.apps.mste.domain.dto.ReloadStencilMasterResult;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;
import jp.vemi.ste.domain.dto.yml.StencilSettingsYml;
import jp.vemi.ste.domain.dto.yml.StencilSettingsYml.Stencil.Config;
import jp.vemi.ste.domain.engine.LogicTemplateEngine;

/**
 * {@link ReloadStencilMasterService ステンシルマスタの更新} の具象です。
 */
@Service
@Transactional
public class ReloadStencilMasterServiceImp implements ReloadStencilMasterService{

    /** {@link MsteStencilRepository ステンシルマスタ} */
    @Autowired
    protected MsteStencilRepository stencilRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse<ReloadStencilMasterResult> invoke(ApiRequest<ReloadStencilMasterParameter> parameter) {

        ApiResponse<ReloadStencilMasterResult> resp = ApiResponse.<ReloadStencilMasterResult>builder().build();

        // TODO implementation.
        resp.setModel(ReloadStencilMasterResult.builder().build());

        return resp;

    }

    /**
     * read from stencil directory.
     */
    protected void read() {
        String dir = StorageUtil.getBaseDir() + LogicTemplateEngine.getStencilMasterStorageDir();
        List<String> files = FileUtil.findByFileName(dir, "stencil-settings.yml");

        for (String fileName : files) {
            StencilSettingsYml settings = readYaml(new File(fileName));
            Config cfg = settings.getStencil().getConfig();

            MsteStencil stencil = new MsteStencil();
            stencil.setStencilCd(cfg.getId());
            stencil.setStencilName(cfg.getName());
            stencil.setItemKind("2");
            stencil.setSort(1);
            stencilRepository.save(stencil);
            System.out.println(
                    cfg.getId() + "/" + cfg.getSerial() + ":" + cfg.getName() + "（" + cfg.getDescription() + "）");
        }
    }

    protected StencilSettingsYml readYaml (File file) {
        StencilSettingsYml settings = null;
        try(InputStream stream = new FileSystemResource(file).getInputStream()) {
            settings = new Yaml(new Constructor(StencilSettingsYml.class))
                .loadAs(stream, StencilSettingsYml.class);
        } catch (final ConstructorException e) {
            e.printStackTrace();
            String msg = "yamlの読込でエラーが発生しました。";
            throw new MirelApplicationException(msg, e);
        } catch (final IOException e) {
            e.printStackTrace();
            throw new MirelSystemException("yamlの読込で入出力エラーが発生しました。", e);
        }
        return settings;
    }
}

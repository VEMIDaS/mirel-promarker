/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.mirel.mipla.apps.mste.domain.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.mirel.mipla.apps.mste.domain.dto.ReloadStencilMasterParameter;
import jp.mirel.mipla.apps.mste.domain.dto.ReloadStencilMasterResult;
import jp.mirel.mipla.apps.mste.domain.service.ReloadStencilMasterService;
import jp.mirel.mipla.foundation.web.api.dto.ApiRequest;
import jp.mirel.mipla.foundation.web.api.dto.ApiResponse;

/**
 * {@link ReloadStencilMasterService ステンシルマスタの更新} の具象です。
 */
@Service
@Transactional
public class ReloadStencilMasterServiceImpl implements ReloadStencilMasterService{

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
}

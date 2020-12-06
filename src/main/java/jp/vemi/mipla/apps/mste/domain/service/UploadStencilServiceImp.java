/*
 * Copyright(c) 2015-2019 mirelplatform.
 */
package jp.vemi.mipla.apps.mste.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.vemi.mipla.apps.mste.domain.dto.UploadStencilParameter;
import jp.vemi.mipla.apps.mste.domain.dto.UploadStencilResult;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 * {@link UploadStencilService} の具象です。
 */
@Service
@Transactional
public class UploadStencilServiceImp implements UploadStencilService{

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse<UploadStencilResult> invoke(ApiRequest<UploadStencilParameter> parameter) {

        ApiResponse<UploadStencilResult> resp = ApiResponse.<UploadStencilResult>builder().build();

        // TODO concrete.
        resp.setModel(UploadStencilResult.builder().build());

        return resp;

    }
}


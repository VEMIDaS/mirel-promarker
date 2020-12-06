/*
 * Copyright(c) 2015-2019 mirelplatform.
 */
package jp.vemi.mipla.apps.mste.domain.api;

import java.util.Map;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.vemi.mipla.apps.mste.domain.dto.UploadStencilParameter;
import jp.vemi.mipla.apps.mste.domain.service.UploadStencilService;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 *  .<br/>
 */
@Service
public class UploadStencilApi {

    @Autowired
    protected UploadStencilService service;

    public ApiResponse<?> service(Map<String, Object> request) {

        ApiRequest<UploadStencilParameter> apireq = ApiRequest.<UploadStencilParameter>builder().model(
            UploadStencilParameter.builder().params(Lists.newArrayList(request)).build()).build();

        ApiResponse<?> response = service.invoke(apireq);
        return response;
    }

}


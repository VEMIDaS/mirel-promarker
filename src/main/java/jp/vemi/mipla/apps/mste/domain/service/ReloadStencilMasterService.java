/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mipla.apps.mste.domain.service;

import jp.vemi.mipla.apps.mste.domain.dto.ReloadStencilMasterParameter;
import jp.vemi.mipla.apps.mste.domain.dto.ReloadStencilMasterResult;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 * MSTE 生成のサービスです。 .<br/>
 */
public interface ReloadStencilMasterService {

    /**
     * invoke.<br/>
     * @param parameter
     * @return result
     */
    public abstract ApiResponse<ReloadStencilMasterResult> invoke(ApiRequest<ReloadStencilMasterParameter> parameter);
}

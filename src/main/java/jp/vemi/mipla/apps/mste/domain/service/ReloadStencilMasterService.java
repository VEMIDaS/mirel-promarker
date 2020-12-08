/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.mirel.mipla.apps.mste.domain.service;

import jp.mirel.mipla.apps.mste.domain.dto.ReloadStencilMasterParameter;
import jp.mirel.mipla.apps.mste.domain.dto.ReloadStencilMasterResult;
import jp.mirel.mipla.foundation.web.api.dto.ApiRequest;
import jp.mirel.mipla.foundation.web.api.dto.ApiResponse;

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

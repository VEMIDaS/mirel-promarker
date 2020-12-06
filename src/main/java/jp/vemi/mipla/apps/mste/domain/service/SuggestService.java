/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mipla.apps.mste.domain.service;

import jp.vemi.mipla.apps.mste.domain.dto.SuggestParameter;
import jp.vemi.mipla.apps.mste.domain.dto.SuggestResult;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 * MSTEサジェスト .<br/>
 */
public interface SuggestService {

    /**
     * invoke.<br/>
     * @param parameter {@link ApiRequest<SuggestParameter>}
     * @return {@link ApiResponse<SuggestResult>}
     */
    public abstract ApiResponse<SuggestResult> invoke(ApiRequest<SuggestParameter> parameter);

}

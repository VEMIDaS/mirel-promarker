/*
 * Copyright(c) 2015-2021 mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.domain.service;

import jp.vemi.mirel.apps.selenade.domain.dto.RunTestParameter;
import jp.vemi.mirel.apps.selenade.domain.dto.RunTestResult;
import jp.vemi.mirel.foundation.web.api.dto.ApiRequest;
import jp.vemi.mirel.foundation.web.api.dto.ApiResponse;

/**
 * MSTE 生成のサービスです。 .<br/>
 */
public interface RunTestService {

    /**
     * invoke.<br/>
     * @param parameter
     * @return result
     */
    public abstract ApiResponse<RunTestResult> invoke(ApiRequest<RunTestParameter> parameter);
}

/*
 * Copyright(c) 2015-2021 mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.vemi.mirel.apps.selenade.domain.dto.RunTestParameter;
import jp.vemi.mirel.apps.selenade.domain.dto.RunTestResult;
import jp.vemi.mirel.apps.selenade.domain.service.RunTestService;
import jp.vemi.mirel.foundation.web.api.dto.ApiRequest;
import jp.vemi.mirel.foundation.web.api.dto.ApiResponse;

/**
 * {@link RunTestService テスト実行} の具象です。
 */
@Service
@Transactional
public class RunTestServiceImp implements RunTestService{

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse<RunTestResult> invoke(ApiRequest<RunTestParameter> parameter) {

        ApiResponse<RunTestResult> resp = ApiResponse.<RunTestResult>builder().build();

        // TODO implementation.
        resp.setModel(RunTestResult.builder().build());

        return resp;

    }
}

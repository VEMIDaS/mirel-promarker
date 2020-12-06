/*
 * Copyright(c) 2015-2019 mirelplatform.
 */
package jp.vemi.mipla.foundation.abst.service;

import jp.vemi.mipla.foundation.abst.dto.ServiceParameter;
import jp.vemi.mipla.foundation.abst.dto.ServiceResult;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 * service
 */
public abstract interface AbstractService<PARAM extends ServiceParameter, RESULT extends ServiceResult> {

  /**
   * invoke.<br/>
   * 
   * @param parameter
   * @return result
   */
  public abstract ApiResponse<RESULT> invoke(ApiRequest<PARAM> parameter);

}

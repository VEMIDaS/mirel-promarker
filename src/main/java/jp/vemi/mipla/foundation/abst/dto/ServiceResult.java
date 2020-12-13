/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mipla.foundation.abst.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ServiceResult {
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
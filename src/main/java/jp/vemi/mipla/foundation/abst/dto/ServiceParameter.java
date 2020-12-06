/*
 * Copyright(c) 2015-2019 mirelplatform.
 */
package jp.vemi.mipla.foundation.abst.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ServiceParameter {

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
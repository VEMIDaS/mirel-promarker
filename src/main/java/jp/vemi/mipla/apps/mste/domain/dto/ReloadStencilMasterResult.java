/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.mirel.mipla.apps.mste.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * ステンシルマスタの更新 結果クラス.<br/>
 */
@Setter
@Getter
@Builder
public class ReloadStencilMasterResult {


  /**
  * {@inheritDoc}
  */
  @Override
  public String toString() {
      return ToStringBuilder.reflectionToString(this);
  }

}

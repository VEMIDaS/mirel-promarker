/*
 * Copyright(c) 2019 mirelplatform All Right Reserved.
 */
package jp.vemi.mipla.apps.mste.domain.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import groovy.lang.Tuple2;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 生成結果
 */
@Setter
@Getter
@Builder
public class GenerateResult {

  /** files */
  // ファイイルID・ファイル名
  public List<Tuple2<String, String>> files;

  /**
  * {@inheritDoc}
  */
  @Override
  public String toString() {
      return ToStringBuilder.reflectionToString(this);
  }

}

/*
 * Copyright(c) 2015-2021 mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.domain.dto;

import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * テスト実行 パラメータクラス.<br/>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated(value = {"1.0"}, comments = "Generated from /mirel/service")
public class RunTestParameter {
  /**
   * params
   */
  public List<Map<String, Object>> params;
}
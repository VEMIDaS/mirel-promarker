/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Scenario.<br/>
 */
@Getter
@Setter
@NoArgsConstructor
public class ArScenario {

    String id;
    Map<String, ArUsecase> usecases = Maps.newLinkedHashMap();
}

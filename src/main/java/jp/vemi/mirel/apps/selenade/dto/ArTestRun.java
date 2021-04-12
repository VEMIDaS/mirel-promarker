/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto;

import java.util.Map;

import com.google.common.collect.Maps;

import jp.vemi.mirel.apps.selenade.dto.yml.ArUsecase;
import jp.vemi.mirel.apps.selenade.dto.yml.ArScenario;
import jp.vemi.mirel.apps.selenade.dto.yml.ArSelenadePage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Scenario.<br/>
 */
@Getter
@Setter
@NoArgsConstructor
public class ArTestRun {

    /** scenario. */
    Map<String, ArScenario> scenarios = Maps.newLinkedHashMap();

    /** activity. */
    Map<String, ArUsecase> usecases = Maps.newLinkedHashMap();
    /** page. */
    Map<String, ArSelenadePage> pages = Maps.newLinkedHashMap();

    /**
     * Add usecase.<br/>
     * @param usecase
     */
    public void addUsecase(ArUsecase usecase) {
        this.usecases.put(usecase.getId(),usecase);
    }

    /**
     * Add page.<br/>
     * @param page
     */
    public void addPage(ArSelenadePage page) {
        this.pages.put(page.getId(), page);
    }

    /**
     * Add scenario.<br/>
     * @param scenario
     */
    public void addScenario(ArScenario scenario) {
        this.scenarios.put(scenario.getId(), scenario);
    }
}

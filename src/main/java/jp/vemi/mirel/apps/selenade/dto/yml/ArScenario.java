/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;
import java.util.Map;

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

    Scenario scenario;
    Data data;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Scenario {
        String id;
        String name;
        String note;
        List<Usecase> usecase;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Data {
        String id;
        List<DataVariable> variable;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Usecase {
        String id;
        String name;
        Integer sort;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class DataVariable {
        String id;
        String name;
        String type;
        String defaultValue;
        List<Map<String, Object>> valuePattern;
    }
}

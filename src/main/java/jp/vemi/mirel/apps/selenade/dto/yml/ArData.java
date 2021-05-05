/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data.
 */
@Getter
@Setter
@NoArgsConstructor
public class ArData {

    DataTemplate dataTemplate;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class DataTemplate {
        String id;
        List<ValiableItems> variable;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ValiableItems {
        String id;
        String name;
        String type;
        String defaultValue;
        List<Map<String, Object>> defaultValueVariation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

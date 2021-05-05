/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Usecase.
 */
@Getter
@Setter
@NoArgsConstructor
public class ArUsecase {

    Usecase usecase;
    List<UsecaseGroup> usecaseGroup;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Usecase {
        String id;
        String name;
        String note;
        List<Step> step = Lists.newArrayList();
    }
    /**
     * Operation.<br/>
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Step {
        String id;
        String name;
        String note;
        Plugin plugin;

        public boolean isSelenade() {
            if (null == this.plugin) {
                return false;
            }
            
            if (null == this.plugin.selenade) {
                return false;
            }

            return true;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UsecaseGroup {
        String id;
        String name;
        List<Usecase> usecase;
    }

    @Getter
    @Setter
    public static class Plugin {
        Selenade selenade;
    }

    @Getter
    @Setter
    public static class Selenade {
        String pageId;
        List<Action> action;
    }

    @Getter
    @Setter
    public static class Action {
        String id;
        String api;
        String value;
        String actionTemplate;
        Map<String, Object> parameter;
    }
}

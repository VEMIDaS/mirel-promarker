/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArSelenadePage {
    String id;
    String name;
    String appId;
    String contextPath;
    String notes;
    List<Action> actions;

    @Getter
    @Setter
    public static class Action {
        String id;
        String name;
        String type;
        String locator;
        Boolean ignoreIfNotFound;
        List<String> includeTags;
    }

}

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

    Page page;

    @Getter
    @Setter
    public static class Page {
        String id;
        String name;
        String appId;
        String contextPath;
        String note;
        List<Action> action;
    }

    @Getter
    @Setter
    public static class Action {
        String id;
        String name;
        String type;
        String value;
        String locator;
        Boolean ignoreIfNotFound;
        List<String> includeTag;
    }

}

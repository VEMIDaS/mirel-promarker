/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Usecase.
 */
@Getter
@Setter
public class ArUsecase {
    String id;
    String name;
    String notes;
    List<Operation> operations;

    /**
     * Operation.<br/>
     */
    @Getter
    @Setter
    public static class Operation {
        String id;
        String pageId;
        String actionId;
    }
}

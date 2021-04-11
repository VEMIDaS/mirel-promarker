/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArActivity {
    String id;
    String name;
    String notes;
    List<Operation> operations;

    @Getter
    @Setter
    static class Operation {
        String id;
        String pageObject;
        String action;
    }
}

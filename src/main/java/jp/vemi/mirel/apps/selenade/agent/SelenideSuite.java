/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.agent;


import java.util.List;
import java.util.Map;

import com.codeborne.selenide.SelenideConfig;
import com.google.common.collect.Maps;

import org.apache.commons.compress.utils.Lists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Selenade の Suite を管理するオブジェクトです。
 */
public class SelenideSuite {

    final SelenideConfig config;
    final Map<String, Scenario> scenarios = Maps.newLinkedHashMap();

    public SelenideSuite(SelenideConfig config) {
        this.config = config;
    }

    /**
     * 抽象.<br/>
     */
    protected abstract static class AbstractInSuite {
        Map<String, Object> bind;
        boolean screenShot;
        public void screenShot() {
            this.screenShot = true;
        }
        public boolean isScreenshot() {
            return false;
        }
        public boolean validate() {
            return true;
        }
    }

    /**
     * シナリオ.<br/>
     */
    @Setter
    @Getter
    @AllArgsConstructor
    public static class Scenario extends AbstractInSuite {
    }

    /**
     * ユースケース.<br/>
     */
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Usecase extends AbstractInSuite {
        List<Action> actions = Lists.newArrayList();
    }

    /**
     * アクション.<br/>
     */
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Action extends AbstractInSuite {
        ActionType actionType;
        Map<String, Object> actionParameter;

        public enum ActionType {
            /** 開く */
            OPEN(1),
            /** タイトル */
            TITLE(0),
            /** FIND */
            FIND(-1),
            /** FINDALL */
            FINDALL(-1),
            /** クリック */
            CLICK(0),
            /**  */
            GET_SELECTED_RADIO(1),
            /** QUERY */
            QUERY(1),
            ;

            Integer argNum;

            ActionType(Integer argNum) {
                this.argNum = argNum;
            }
        }
    }


}

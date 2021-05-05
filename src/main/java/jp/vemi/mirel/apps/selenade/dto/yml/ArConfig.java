/*
 * Copyright(c) 2015-2021 vemi/mirelplatform.
 */
package jp.vemi.mirel.apps.selenade.dto.yml;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data.
 */
@Getter
@Setter
@NoArgsConstructor
public class ArConfig {

    Config config;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Config {
        String id;
        /** environment */
        List<Environment> environment;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Environment {
        String id;
        List<Server> server;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Server {
        String id;
        String url;
    }

}

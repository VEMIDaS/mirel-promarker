package jp.vemi.mipla;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "mipla2.config")
public class Mipla2ConfigProperties {

    /** ストレージディレクトリ */
    private String storageDir;

}

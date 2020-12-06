/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mipla.foundation.feature.files.service;

import java.io.File;
import groovy.lang.Tuple2;

/**
 * ファイル登録サービス .<br/>
 */
public interface FileRegisterService {

  public Tuple2<String, String> register(File file);
}

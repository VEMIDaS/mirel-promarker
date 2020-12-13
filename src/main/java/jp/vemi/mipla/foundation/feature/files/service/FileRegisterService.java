/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mipla.foundation.feature.files.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import groovy.lang.Tuple2;

/**
 * ファイル登録サービス .<br/>
 */
public interface FileRegisterService {

  public Tuple2<String, String> register(File file, boolean isZip);

  public Tuple2<String, String> register(MultipartFile multipartFile);
}

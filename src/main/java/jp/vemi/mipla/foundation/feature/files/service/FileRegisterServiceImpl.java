/*
 * Copyright(c) 2019 mirelplatform All right reserved.
 */
package jp.vemi.mirel.foundation.feature.files.service;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import groovy.lang.Tuple2;
import jp.vemi.framework.util.DateUtil;
import jp.vemi.framework.util.FileUtil;
import jp.vemi.framework.util.StorageUtil;
import jp.vemi.mirel.foundation.abst.dao.entity.FileManagement;
import jp.vemi.mirel.foundation.abst.dao.repository.FileManagementRepository;

/**
 * {@link FileRegisterService} の具象です。 .<br/>
 */
@Service
public class FileRegisterServiceImpl implements FileRegisterService {

  @Autowired
  protected FileManagementRepository fileManagementRepository;

  protected static final String ATCH_FILE_NAME = "__file";

  @Override
  public Tuple2<String, String> register(MultipartFile multipartFile) {
    String temporaryUuid = UUID.randomUUID().toString();
    File temporary = new File(System.getProperty("java.io.tmpdir") + "/ProMarker/" + temporaryUuid);
    FileUtil.transfer(multipartFile, temporary, ATCH_FILE_NAME);
    return register(temporary, false, multipartFile.getOriginalFilename());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Tuple2<String, String> register(File srcFile, boolean isZip) {
    return register(srcFile, isZip, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Tuple2<String, String> register(File srcFile, boolean isZip, String fileName) {

    String uuid = UUID.randomUUID().toString();

    String dest = getSaveDir(uuid);
    File destFile = new File(dest);

    if (destFile.exists()) {
      // error... files exists already...
    }

    if (isZip) {
      if (false == FileUtil.zip(srcFile, dest, ATCH_FILE_NAME)) {
        // error... failed archive file...
      }
      fileName = srcFile.getName() + ".zip";
    } else {
      FileUtil.copy(srcFile, destFile);
    }

    if (StringUtils.isEmpty(fileName)) {
      fileName = ATCH_FILE_NAME;
    }

    // create entity.
    FileManagement fileManagement = new FileManagement();
    fileManagement.fileId = uuid;
    fileManagement.fileName = fileName;
    fileManagement.filePath = dest + "\\" + ATCH_FILE_NAME;
    fileManagement.expireDate = DateUtils.addDays(new Date(), defaultExpireTerms());
    fileManagementRepository.save(fileManagement);
    return new Tuple2<>(uuid, fileName);

  }

  /**
   * save
   */
  protected String getSaveDir(String uuid) {

    // validate.
    Assert.notNull(uuid, "uuid must not be null.");

    // y&m
    Date date = new Date();
    String y = DateUtil.toString(date, "yy");
    String m = DateUtil.toString(date, "MM");

    // concatenate.
    return StringUtils.joinWith("\\", StorageUtil.getBaseDir(), defaultAppDir(), y, m, uuid);

  }

  protected String defaultAppDir() {
    return "foundation/filemanagement";
  }

  protected int defaultExpireTerms() {
    return 3;
  }
}

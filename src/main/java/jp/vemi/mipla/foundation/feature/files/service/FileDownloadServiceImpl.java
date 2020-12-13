/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mipla.foundation.feature.files.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groovy.lang.Tuple3;
import jp.vemi.mipla.foundation.abst.dao.entity.FileManagement;
import jp.vemi.mipla.foundation.abst.dao.repository.FileManagementRepository;
import jp.vemi.mipla.foundation.feature.files.dto.FileDownloadParameter;
import jp.vemi.mipla.foundation.feature.files.dto.FileDownloadResult;
import jp.vemi.mipla.foundation.web.api.dto.ApiRequest;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 * {@link FileDownloadService} の具象です。 .<br/>
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

  /** {@link FileManagementRepository} */
  @Autowired
  protected FileManagementRepository fileManagementRepository;

  protected Date getToday() {
    return new Date();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ApiResponse<FileDownloadResult> invoke(ApiRequest<FileDownloadParameter> parameter) {

    ApiResponse<FileDownloadResult> resp = new ApiResponse<>();
    resp.setModel(new FileDownloadResult());

    final Date today = getToday();
    final List<FileManagement> fmItems = Lists.newArrayList();

    parameter.getModel().getFileIds().forEach(fileId -> {

      FileManagement item;
      try {
        item = fileManagementRepository.findById(fileId).get();
      } catch (NoSuchElementException e) {
        e.printStackTrace();
        resp.errs.add("ファイルが見つかりません。ファイル管理ID：" + fileId);
        return;
      }

      // file record is null.
      if (null == item) {
        resp.errs.add("ファイルが見つかりません。ファイル管理ID：" + fileId);
        return;
      }

      // file expired.
      if (null != item.getExpireDate() && today.after(item.getExpireDate())) {
        resp.errs.add("ファイルは有効期間を過ぎました。ファイル名：" + item.fileName);
        return;
      }

      // file deleted.
      if (item.getDeleteFlag()) {
        resp.errs.add("ファイルは既に削除されています。ファイル名：" + item.fileName);
        return;
      }

      // ok.
      fmItems.add(item);
    });

    // file not found.
    if (fmItems.isEmpty()) {
      resp.errs.add("取得可能なファイルがありませんでした。");
      return resp;
    }

    // transfer to File model
    fmItems.forEach(item -> {

      Path path = Paths.get(item.getFilePath());

      // file not exists
      if (false == path.toFile().exists()) {
        resp.errs.add("ファイルがストレージから取得できみせんでした。ファイル名：" + item.fileName);
        return;
      }

      // ok.
      resp.model.paths.add(new Tuple3<String, String, Path>(item.getFileId(), item.getFileName(), path));
    });

    return resp;
  }

}

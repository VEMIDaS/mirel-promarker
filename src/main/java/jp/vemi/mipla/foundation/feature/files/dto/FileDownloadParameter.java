/*
 * Copyright(c) 2015-2019 mirelplatform.
 */
package jp.vemi.mipla.foundation.feature.files.dto;

import java.util.List;

import jp.vemi.mipla.foundation.abst.dto.ServiceParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@link FileDownloadParameter}
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDownloadParameter extends ServiceParameter {

  public List<String> fileIds;

  public String user;

}

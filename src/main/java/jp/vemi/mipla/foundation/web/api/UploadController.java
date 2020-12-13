/*
 * Copyright(c) 2015-2020 mirelplatform.
 */
package jp.vemi.mipla.foundation.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import groovy.lang.Tuple2;
import jp.vemi.mipla.foundation.feature.files.dto.FileUploadResult;
import jp.vemi.mipla.foundation.feature.files.service.FileRegisterService;
import jp.vemi.mipla.foundation.web.api.dto.ApiResponse;

/**
 * アップロードコントローラ.<br/>
 */
@RestController
public class UploadController {

    /** {@link FileRegisterService サービス} */
    @Autowired
    protected FileRegisterService service;

    /**
     * post. <br/>
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "commons/upload")
    public ResponseEntity<ApiResponse<FileUploadResult>> index(
            @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            // return
        }

        Tuple2<String, String> ret = service.register(multipartFile);
        FileUploadResult fileUploadResult = new FileUploadResult();
        fileUploadResult.uuid = ret.getFirst();
        fileUploadResult.fileName = ret.getSecond();
        ResponseEntity<ApiResponse<FileUploadResult>> rentity = new ResponseEntity<>(
                new ApiResponse<>(fileUploadResult), HttpStatus.OK);
        return rentity;
    }

}

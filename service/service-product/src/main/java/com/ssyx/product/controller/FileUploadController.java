package com.ssyx.product.controller;

import com.ssyx.common.result.Result;
import com.ssyx.product.service.FileUploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author zhan_py
 * @Date 2024/2/6 11:26
 */
@RestController
@RequestMapping("/admin/product")
@ApiOperation("文件上传接口")
@CrossOrigin
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;

    @ApiOperation("图片上传")
    @PostMapping("/fileUpload")
    public Result<String> fileUpload(MultipartFile file){
        String url = fileUploadService.uploadFile(file);
        return Result.success(url);
    }

}

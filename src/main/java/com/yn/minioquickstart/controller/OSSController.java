package com.yn.minioquickstart.controller;

import com.yn.minioquickstart.api.CommonResult;
import com.yn.minioquickstart.config.MinioConfig;
import com.yn.minioquickstart.dto.SysUploadFileDto;
import com.yn.minioquickstart.service.SysUploadFileService;
import com.yn.minioquickstart.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping("/oss")
public class OSSController {
    @Resource
    private MinioUtils minioUtils;

    @Resource
    private MinioConfig minioConfig;

    @Resource
    private SysUploadFileService sysUploadFileService;
    /**
     * 文件上传
     *
     * @param file
     */
    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String bucketName) {
        try {
            //文件名
            String fileName = file.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(fileName, ".");
            //类型
            String contentType = file.getContentType();
            minioUtils.uploadFile(ObjectUtils.isEmpty(bucketName)?minioConfig.getBucketName():bucketName, file, newFileName, contentType);

            //保存文件信息
            SysUploadFileDto sysUploadFileDto = new SysUploadFileDto();
            sysUploadFileDto.setFileName(fileName);
            sysUploadFileDto.setFileId(newFileName);
            sysUploadFileDto.setBucketName(ObjectUtils.isEmpty(bucketName)?minioConfig.getBucketName():bucketName);
            sysUploadFileService.saveSysUploadFile(sysUploadFileDto);
            return CommonResult.success();
        } catch (Exception e) {
            log.error("上传失败");
            return CommonResult.failed();
        }
    }
    /**
     * 删除
     *
     * @param fileName
     */
    @DeleteMapping("/")
    public void delete(@RequestParam("fileName") String fileName) {
        minioUtils.removeFile(minioConfig.getBucketName(), fileName);
    }

    /**
     * 获取文件信息
     *
     * @param fileName
     * @return
     */
    @GetMapping("/info")
    public String getFileStatusInfo(@RequestParam("fileName") String fileName) {
        return minioUtils.getFileStatusInfo(minioConfig.getBucketName(), fileName);
    }

    /**
     * 获取文件外链
     *
     * @param fileName
     * @return
     */
    @GetMapping("/url")
    public String getPresignedObjectUrl(@RequestParam("fileName") String fileName) {
        return minioUtils.getPresignedObjectUrl(minioConfig.getBucketName(), fileName);
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        try {
            InputStream fileInputStream = minioUtils.getObject(minioConfig.getBucketName(), fileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载失败");
        }
    }
}

package com.yn.minioquickstart.dto;

import lombok.Data;

@Data
public class SysUploadFileDto {
    private String fileName;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 桶名
     */
    private String bucketName;
}

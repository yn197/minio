package com.yn.minioquickstart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yn.minioquickstart.domian.SysUploadFile;
import com.yn.minioquickstart.dto.SysUploadFileDto;

public interface SysUploadFileService extends IService<SysUploadFile> {

    /**
     * 保存文件信息
     */
    void saveSysUploadFile(SysUploadFileDto sysUploadFileDto);

}

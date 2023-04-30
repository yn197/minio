package com.yn.minioquickstart.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yn.minioquickstart.domian.SysUploadFile;
import com.yn.minioquickstart.dto.SysUploadFileDto;
import com.yn.minioquickstart.mapper.SysUploadFileMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SysUploadFileServiceImpl extends ServiceImpl<SysUploadFileMapper, SysUploadFile> implements SysUploadFileService {
    @Override
    public void saveSysUploadFile(SysUploadFileDto sysUploadFileDto) {
        SysUploadFile sysUploadFile = new SysUploadFile();
        BeanUtils.copyProperties(sysUploadFileDto,sysUploadFile);
        this.save(sysUploadFile);
    }
}

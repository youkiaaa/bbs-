package com.atyume.modules.system.service.impl;

import com.atyume.modules.base.service.impl.BaseService;
import com.atyume.modules.system.mapper.LogMapper;
import com.atyume.modules.system.po.Log;
import com.atyume.modules.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl extends BaseService<Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> serarchDesc(String execDesc) {
        System.out.println("log searching:"+execDesc);
        System.out.println("*********************************************************************");
        return logMapper.searchInDesc(execDesc);
    }
}

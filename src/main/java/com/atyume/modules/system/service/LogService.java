package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.po.Log;

import java.util.List;

public interface LogService extends IService<Log> {
    List<Log> serarchDesc(String execDesc);
}

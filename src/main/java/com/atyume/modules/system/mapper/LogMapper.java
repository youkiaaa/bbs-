package com.atyume.modules.system.mapper;

import com.atyume.modules.system.po.Log;
import com.atyume.core.utils.MyMapper;

import java.util.List;

public interface LogMapper extends MyMapper<Log> {
    List<Log> searchInDesc(String exexDesc);
}
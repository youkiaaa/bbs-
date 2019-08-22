package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.po.Avator;

import java.util.List;

public interface AvatorService extends IService<Avator> {
    List<String> selectPath(Long userId);
    String selectPathByActive(Long userId);
    boolean queryImg(Long userid, String picPath);
    boolean updateImg(Long userid,String picPath);
}

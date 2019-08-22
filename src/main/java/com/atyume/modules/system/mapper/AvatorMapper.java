package com.atyume.modules.system.mapper;

import com.atyume.core.utils.MyMapper;
import com.atyume.modules.system.po.Avator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AvatorMapper extends MyMapper<Avator> {
    String SelectPicPathByActive(Long userid);
    List<String> SelectPicPath(Long userid);
    boolean queryImg(Long userid,String picPath,int active);
    boolean setActiveZero(Long userid);
    boolean updateImg(Long userid,String picPath);
    int countAvatarById(Long userid);
}
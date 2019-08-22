package com.atyume.modules.system.service.impl;

import com.atyume.modules.base.service.impl.BaseService;
import com.atyume.modules.system.mapper.AvatorMapper;
import com.atyume.modules.system.po.Avator;
import com.atyume.modules.system.service.AvatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvatorServiceImpl extends BaseService<Avator> implements AvatorService {
    @Autowired
    private AvatorMapper avatorMapper;
    @Override
    public String selectPathByActive(Long userId) {
        String picPath = avatorMapper.SelectPicPathByActive(userId);
        return picPath;
    }

    public List<String> selectPath(Long userId) {
        List<String> picList=avatorMapper.SelectPicPath(userId);
        if(picList.size()==0){
            System.out.println("i am service and picPath size = 0");
        }
        return avatorMapper.SelectPicPath(userId);
    }

    /*本地上传*/
    @Override
    public boolean queryImg(Long userid, String picPath) {
        int active=1;
        if(avatorMapper.countAvatarById(userid)==0){
            return avatorMapper.queryImg(userid,picPath,active);
        }
        return avatorMapper.setActiveZero(userid) && avatorMapper.queryImg(userid,picPath,active);
    }

    public boolean updateImg(Long userid, String picPath){
        return avatorMapper.setActiveZero(userid) && avatorMapper.updateImg(userid,picPath);
    }
}

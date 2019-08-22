package com.atyume.modules.system.service.impl;

import com.atyume.modules.base.service.impl.BaseService;
import com.atyume.modules.system.mapper.GroupMapper;
import com.atyume.modules.system.po.Group;
import com.atyume.modules.system.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseService<Group> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;
}

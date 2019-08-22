package com.atyume.modules.system.mapper;

import com.atyume.modules.system.po.Organization;
import com.atyume.core.utils.MyMapper;

public interface OrganizationMapper extends MyMapper<Organization> {

    int updateSalefParentIds(String makeSelfAsParentIds);

}
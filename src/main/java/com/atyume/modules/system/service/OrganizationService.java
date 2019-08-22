package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.dto.TreeDto;
import com.atyume.modules.system.po.Organization;

import java.util.List;

public interface OrganizationService extends IService<Organization> {

    void createOrganization(Organization organization);

    List<TreeDto> queryOrgTree(Long pId);

    List<Organization> queryAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}

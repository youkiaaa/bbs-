package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.dto.ResourceDto;
import com.atyume.modules.system.po.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceService extends IService<Resource> {

    void createResource(Resource resource);

    Set<String> queryPermissions(Set<Long> resourceIds);

    List<ResourceDto> findMenus(Set<String> permissions);

}

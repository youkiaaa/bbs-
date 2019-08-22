package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.po.Role;

import java.util.Set;

public interface RoleService extends IService<Role> {

    Set<String> queryRoles(Long... roleIds);

    Set<String> queryPermissions(Long[] roleIds);
}

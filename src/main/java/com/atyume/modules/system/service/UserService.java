package com.atyume.modules.system.service;

import com.atyume.core.exception.BizException;
import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.po.User;

import java.util.Set;

public interface UserService extends IService<User> {

    void createUser(User user) throws BizException;

    void changePassword(Long userId, String newPassword);

    Set<String> queryRoles(String username);

    Set<String> queryPermissions(String username);

    Long queryId(String username);

    String selectName(Long id);

    void updateMoneyById(Double money, Long id);      //充值余额

    void modifyMoneyById(Double money, Long id);          //消费余额
}
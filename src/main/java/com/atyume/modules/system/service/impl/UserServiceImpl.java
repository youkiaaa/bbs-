package com.atyume.modules.system.service.impl;

import com.atyume.core.exception.BizException;
import com.atyume.core.utils.ResultCodeEnum;
import com.atyume.modules.base.service.impl.BaseService;
import com.atyume.modules.system.mapper.UserMapper;
import com.atyume.modules.system.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.atyume.modules.system.service.PasswordHelper;
import com.atyume.modules.system.service.RoleService;
import com.atyume.modules.system.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    @Transactional
    public void createUser(User user) {
        User u = userMapper.selectOne(new User().setUsername(user.getUsername()));
        if (u != null) {
            throw new BizException(ResultCodeEnum.FAILED_USER_ALREADY_EXIST);
        }
        // 加密密码
        passwordHelper.encryptPassword(user);
        userMapper.insertSelective(user);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Set<String> queryRoles(String username) {
        User user = userMapper.selectOne(new User().setUsername(username));
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.queryRoles(
                Arrays.asList(user.getRoleIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList()).toArray(new Long[0])
        );
    }

    @Override
    public Set<String> queryPermissions(String username) {
        User user = userMapper.selectOne(new User().setUsername(username));
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.queryPermissions(
                Arrays.asList(user.getRoleIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList()).toArray(new Long[0])
        );
    }

    @Override
    public Long queryId(String username) {
        User user=userMapper.selectIdByName(username);
        System.out.println("US's username:"+username+"userid:"+user.getId()+"username:"+user.getUsername());
        return user.getId();
    }

    @Override
    public String selectName(Long id) {
        return userMapper.selectNameById(id);
    }

    @Override
    public void updateMoneyById(Double money, Long id) {
        userMapper.updateMoneyById(money, id);
    }

    @Override
    public void modifyMoneyById(Double money, Long id) {
        userMapper.modifyMoneyById(money,id);
    }

}

package com.atyume.modules.system.mapper;

import com.atyume.modules.system.po.User;
import com.atyume.core.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {
    User selectIdByName(String username);
    String selectNameById(Long id);
    void updateMoneyById(Double money,Long id);
    void modifyMoneyById(Double money,Long id);
}
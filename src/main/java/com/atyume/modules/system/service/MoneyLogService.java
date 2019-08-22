package com.atyume.modules.system.service;

import com.atyume.modules.base.service.IService;
import com.atyume.modules.system.po.MoneyLog;

public interface MoneyLogService extends IService<MoneyLog> {
    public void transfer(Long posuser,Long quauser,Double money);
}

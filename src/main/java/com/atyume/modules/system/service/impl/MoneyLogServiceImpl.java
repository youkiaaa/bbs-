package com.atyume.modules.system.service.impl;

import com.atyume.modules.base.service.impl.BaseService;
import com.atyume.modules.system.mapper.MoneyLogMapper;
import com.atyume.modules.system.mapper.UserMapper;
import com.atyume.modules.system.po.MoneyLog;
import com.atyume.modules.system.service.MoneyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class MoneyLogServiceImpl extends BaseService<MoneyLog> implements MoneyLogService {
    @Autowired
    private MoneyLogMapper moneyLogMapper;
    @Autowired
    private UserMapper userMapper;

    private TransactionTemplate transactionTemplate;

    /**
     * @param posuser
     *            :转出账号
     * @param quauser
     *            :转入账号
     * @param money
     *            :转账金额
     */
    @Override
    @Transactional
    public void transfer(final Long posuser, final Long quauser, final Double money) {

        // 未经事务控制的业务处理操作，如果过程中出异常，则导致前面的操作能完成，后面的不能，即转账成功但未收到转账款

        /*transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {*/
                    userMapper.updateMoneyById(money,quauser);
//                    int i=1/0;
                    userMapper.modifyMoneyById(money,posuser);
        /*    }
        });*/
    }

}

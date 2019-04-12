package com.lovejava.service.impl;

import com.lovejava.dao.AccountDao;
import com.lovejava.domain.Account;
import com.lovejava.service.AccountService;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:tianyao
 * @date:2019-04-12 10:54
 */
@Service(value = "accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier(value = "accountDao")
    private AccountDao accountDao;


    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    @Transactional(value = "txManage",propagation = Propagation.REQUIRED)
    public void transfer(String sourceName, String targeName, Float money) {
        //1.根据名称查询两个账户
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targeName);
        //2.修改两个账户的金额
        source.setMoney(source.getMoney() - money);//转出账户减钱
        target.setMoney(target.getMoney() + money);//转入账户加钱
        //3.更新两个账户
        accountDao.updateAccount(source);
        //int i=10/0;
        accountDao.updateAccount(target);
    }
}

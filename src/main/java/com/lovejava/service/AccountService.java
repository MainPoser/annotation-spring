package com.lovejava.service;

import com.lovejava.domain.Account;

/**
 * @author:tianyao
 * @date:2019-04-12 10:54
 * 业务层接口类
 */
public interface AccountService {
    /**
     * 根据id查询账户信息
     *
     * @param id
     * @return
     */
    Account findAccountById(Integer id);//查

    /**
     * 转账
     *
     * @param sourceName 转出账户名称
     * @param targeName  转入账户名称
     * @param money      转账金额
     */
    void transfer(String sourceName, String targeName, Float money);//增删改
}

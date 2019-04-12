package com.lovejava.dao.impl;

import com.lovejava.dao.AccountDao;
import com.lovejava.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:tianyao
 * @date:2019-04-12 9:52
 */
@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {
    //把jdbcTemplate作为成员变量
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @Override
    public Account findAccountById(Integer id) {
        List<Account> list = jdbcTemplate.query("select * from account where id = ? ", new BeanPropertyRowMapper<>(Account.class), id);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 根据名称查询用户信息
     *
     * @param name
     * @return
     */
    @Override
    public Account findAccountByName(String name) {
        List<Account> list = jdbcTemplate.query("select * from account where name = ? ", new BeanPropertyRowMapper<>(Account.class), name);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException("结果集不唯一，不是只有一个账户对象");
        }
        return list.get(0);
    }

    /**
     * 更新用户信息
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set money = ? where id = ? ", account.getMoney(), account.getId());
    }
}

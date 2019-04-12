package com.lovejava;

import com.lovejava.config.SpringConfig;
import com.lovejava.dao.AccountDao;
import com.lovejava.domain.Account;
import com.lovejava.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author:tianyao
 * @date:2019-04-12 9:45
 */
public class SpringJdbcTemplate {
    @Test
    public void test01(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springconfig.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        Account accountById = accountDao.findAccountById(2);

        System.out.println(accountById);
    }
    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService service = (AccountService) applicationContext.getBean("accountService");
        service.transfer("aaa","bbb",100f);
    }
}

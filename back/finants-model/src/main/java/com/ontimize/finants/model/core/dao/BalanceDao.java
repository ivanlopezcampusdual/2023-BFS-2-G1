package com.ontimize.finants.model.core.dao;

import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Lazy
@Repository("BalanceDao")
public class BalanceDao extends OntimizeJdbcDaoSupport {
    public static final String ATTR_BALANCE = "balance";
    public static final String ATTR_USER_ = "USER_";
    public static final String  QUERY_BALANCE = "balanceCurrentMonth";

    public BigDecimal getBalance(BigDecimal totalIncomes,BigDecimal totalExpenses ){
        return  totalIncomes.subtract(totalExpenses);
    }


}

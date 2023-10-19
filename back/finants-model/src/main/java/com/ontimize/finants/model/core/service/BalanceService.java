package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IBalanceService;
import com.ontimize.finants.model.core.dao.BalanceDao;
import com.ontimize.finants.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Lazy
@Service("BalanceService")
public class BalanceService implements IBalanceService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    private final BalanceDao balanceDao;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @Autowired
    public BalanceService( BalanceDao balanceDao, ExpenseService expenseService, IncomeService incomeService ){
        this.balanceDao = balanceDao;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    @Override
    public EntityResult balanceQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        BigDecimal balance = BigDecimal.ZERO;;
        balance = getBalance(keyMap, attrList);
        EntityResult balanceResult = getEntityResult(balance);
        return balanceResult;
    }

    private BigDecimal getBalance(Map<String, Object> keyMap, List<String> attrList) {
        BigDecimal balance;
        balance = this.balanceDao.getBalance(this.getTotalIncomesCurrentMonth(),this.getTotalExpensesCurrentMonth());
        return balance;
    }

    @NotNull
    private EntityResult getEntityResult(BigDecimal balance) {
        EntityResult balanceResult = new EntityResultMapImpl();
        ArrayList<Object> listUser = new ArrayList<>();
        ArrayList<Object> listBalance = new ArrayList<>();
        listUser.add(daoHelper.getUser().getUsername());
        listBalance.add(balance);
        balanceResult.put(UserDao.USER_, listUser );
        balanceResult.put(BalanceDao.ATTR_BALANCE, listBalance);
        return balanceResult;
    }

    private BigDecimal getTotalIncomesCurrentMonth(  ) {
        Map<String, Object> keyMap = new HashMap<>();
        List<String> attrList = new ArrayList<>();
        keyMap.put(UserDao.USER_, this.daoHelper.getUser().getUsername());
        keyMap.put("IN_MONTH", LocalDate.now().getMonthValue());
        keyMap.put("IN_YEAR", LocalDate.now().getYear());
        attrList.add("IN_TOTAL_AMOUNT");
        attrList.add("user_");
        EntityResult totalIncomesForCurrentMonth = incomeService.totalIncomeForCurrentMonth(keyMap,attrList);
        List<Object> totalIncomes = (List<Object>) totalIncomesForCurrentMonth.get("IN_TOTAL_AMOUNT");
        return totalIncomes != null? new BigDecimal(String.valueOf(totalIncomes.get(0))): new BigDecimal("0");
    }

    private BigDecimal getTotalExpensesCurrentMonth() {
        Map<String, Object> keyMap = new HashMap<>();
        List<String> attrList = new ArrayList<>();
        keyMap.put(UserDao.USER_, this.daoHelper.getUser().getUsername());
        keyMap.put("EX_MONTH", LocalDate.now().getMonthValue());
        keyMap.put("EX_YEAR", LocalDate.now().getYear());
        attrList.add("EX_TOTAL_AMOUNT");
        attrList.add("user_");
        EntityResult totalExpensesForCurrentMonth = expenseService.totalExpensesForCurrentMounth(keyMap,attrList);
        List<Object> balanceColumnExpenses = (List<Object>) totalExpensesForCurrentMonth.get("EX_TOTAL_AMOUNT");
        return balanceColumnExpenses != null? new BigDecimal(String.valueOf(balanceColumnExpenses.get(0))): new BigDecimal("0");
    }


}

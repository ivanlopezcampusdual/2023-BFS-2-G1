package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IMovementService;
import com.ontimize.finants.model.core.dao.MovementDao;
import com.ontimize.jee.common.db.NullValue;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Lazy
@Service("MovementService")
public class MovementService implements IMovementService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private MovementDao movementDao;

    @Override
    public EntityResult movementQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String, Object> keyMapFilterUser = new HashMap<>(keyMap);
        keyMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMapFilterUser, attrList);
    }

    @Override
    public EntityResult movementInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> attrMapFilterUser = new HashMap<>(attrMap);
        attrMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.insert(this.movementDao, attrMapFilterUser);
    }

    @Override
    public EntityResult movementUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        Map<String, Object>attrMapFilterUser = new HashMap<>(attrMap);
        attrMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.update(this.movementDao, attrMapFilterUser, keyMap);
    }

    @Override
    public EntityResult movementDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.movementDao, keyMap);
    }

    @Override
    public EntityResult totalMovementsForCurrentMonth(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_SUM_AMOUNT_FOR_MONTH );
    }


    @Override
    public EntityResult totalIncomesForCurrentMonth(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String, Object> filterDatekeyMap = new HashMap<>(keyMap);
        filterDatekeyMap.put(MovementDao.ATTR_MOV_MONTH, LocalDate.now().getMonthValue());
        filterDatekeyMap.put(MovementDao.ATTR_MOV_YEAR, LocalDate.now().getYear());
        return this.daoHelper.query(this.movementDao, filterDatekeyMap, attrList, MovementDao.QUERY_SUM_INCOMES_AMOUNT_FOR_MONTH);
    }
    @Override
    public EntityResult totalExpensesForCurrentMonth(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String, Object> filterDatekeyMap = new HashMap<>(keyMap);
        filterDatekeyMap.put(MovementDao.ATTR_MOV_MONTH, LocalDate.now().getMonthValue());
        filterDatekeyMap.put(MovementDao.ATTR_MOV_YEAR, LocalDate.now().getYear());
        return this.daoHelper.query(this.movementDao, filterDatekeyMap, attrList, MovementDao.QUERY_SUM_EXPENSES_AMOUNT_FOR_MONTH);
    }

    public EntityResult insertExpenseInsert (Map<String,Object> attrMap) throws OntimizeJEERuntimeException{
        Map<String, Object> attrMapForThisQuery =  new HashMap<>(attrMap);
        Float movAmount = (Float) attrMapForThisQuery.get(MovementDao.ATTR_MOV_AMOUNT);
        attrMapForThisQuery.put(MovementDao.ATTR_MOV_AMOUNT, changeSignMovAmount(movAmount));
        return movementInsert(attrMapForThisQuery);
    }

    @Override
    public EntityResult totalExpensesAmountDayQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String,Object> keyMapFilterUser = new HashMap<>(keyMap);
        keyMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMapFilterUser, attrList, MovementDao.QUERY_TOTAL_EXPENSES_AMOUNT_DAY);
    }

    @Override
    public EntityResult totalIncomesAmountDayQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String,Object> keyMapFilterUser = new HashMap<>(keyMap);
        keyMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMapFilterUser, attrList, MovementDao.QUERY_TOTAL_INCOMES_AMOUNT_DAY);
    }

    @Override
    public EntityResult expensesForCategoriesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String,Object> keyMapFilterUser = new HashMap<>(keyMap);
        keyMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMapFilterUser, attrList, MovementDao.QUERY_EXPENSES_FOR_CATEGORIES);
    }

    @Override
    public EntityResult incomesForCategoriesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String,Object> keyMapFilterUser = new HashMap<>(keyMap);
        keyMapFilterUser.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMapFilterUser, attrList, MovementDao.QUERY_INCOMES_FOR_CATEGORIES);
    }

    @Override
    public EntityResult balanceQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        EntityResult resultBalance = this.movementQuery(keyMap, attrList);
        List<BigDecimal> listMovAmount = (List<BigDecimal>) resultBalance.get(MovementDao.ATTR_MOV_AMOUNT);
        BigDecimal balance = calcBalance(listMovAmount);
        setResultBalance(resultBalance, balance);
        return resultBalance;
    }

    private void setResultBalance(EntityResult resultBalance, BigDecimal balance) {
        List<Object> balanceTotal  = new ArrayList<>();
        List<Object> user  = new ArrayList<>();
        user.add(this.daoHelper.getUser().getUsername());
        balanceTotal.add(balance);
        resultBalance.clear();
        resultBalance.put(MovementDao.ATTR_BALANCE, balanceTotal);
        resultBalance.put(MovementDao.ATTR_USER_, user);
    }

    @NotNull
    private static BigDecimal calcBalance(List<BigDecimal> listMovAmount)  {
        if(listMovAmount == null || listMovAmount.isEmpty()){
            return BigDecimal.ZERO;
        }else{
            return listMovAmount.stream()
                    .reduce(BigDecimal.ZERO,BigDecimal::add );
        }
    }

    @Override
    public EntityResult expensesForCategoriesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        Map<String, Object> attrMapForThisQuery =  new HashMap<>(attrMap);
        Float movAmount = (Float) attrMapForThisQuery.get(MovementDao.ATTR_MOV_AMOUNT);
        if(movAmount != null) {
            Double movAmountSignNegative = changeSignMovAmount(movAmount);
            attrMapForThisQuery.put(MovementDao.ATTR_MOV_AMOUNT, movAmountSignNegative);
        }
        Object groupId = changeFromNullValueToNull(attrMapForThisQuery);
        attrMapForThisQuery.put(MovementDao.ATTR_GR_ID, groupId);
        return this.movementUpdate(attrMapForThisQuery,keyMap);
    }

    private static boolean isIdGroupNull(Map<String, Object> attrMapForThisQuery) {
        return attrMapForThisQuery.get(MovementDao.ATTR_GR_ID) instanceof NullValue;
    }
    private static Object changeFromNullValueToNull(Map<String, Object> attrMapForThisQuery){
      return (MovementService.isIdGroupNull(attrMapForThisQuery))? null : attrMapForThisQuery.get(MovementDao.ATTR_GR_ID);
    }

    private static Double changeSignMovAmount(Float movAmount) {
          double movAmountCastAndSignNegative = movAmount *= -1;
          return movAmountCastAndSignNegative;
    }

    @Override
    public EntityResult expensesForCategoriesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.movementDelete(keyMap);
    }

    @Override
    public EntityResult incomesForCategoriesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        Map<String, Object> attrMapForThisQuery =  new HashMap<>(attrMap);
        Object groupId = changeFromNullValueToNull(attrMapForThisQuery);
        attrMapForThisQuery.put(MovementDao.ATTR_GR_ID, groupId);
        return this.movementUpdate(attrMapForThisQuery,keyMap);
    }

    @Override
    public EntityResult incomesForCategoriesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.movementDelete(keyMap);
    }

}

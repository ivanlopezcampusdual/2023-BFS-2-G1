package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IMovementService;
import com.ontimize.finants.model.core.dao.ExpenseDao;
import com.ontimize.finants.model.core.dao.MovementDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Lazy
@Service("MovementService")
public class MovementService implements IMovementService {
    @Autowired
    private MovementDao movementDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult movementQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(MovementDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMap, attrList);
    }

    @Override
    public EntityResult movementInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        attrMap.put(MovementDao.ATTR_USER_, this.daoHelper.getUser().getUsername());
        return this.daoHelper.insert(this.movementDao, attrMap);
    }

    @Override
    public EntityResult movementUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        keyMap.put(MovementDao.ATTR_USER_, this.daoHelper.getUser().getUsername());
        return this.daoHelper.update(this.movementDao, attrMap, keyMap);
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
        keyMap.put(MovementDao.ATTR_MOV_MONTH, LocalDate.now().getMonthValue());
        keyMap.put(MovementDao.ATTR_MOV_YEAR, LocalDate.now().getYear());
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_SUM_INCOMES_AMOUNT_FOR_MONTH);
    }
    @Override
    public EntityResult totalExpensesForCurrentMonth(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(MovementDao.ATTR_MOV_MONTH, LocalDate.now().getMonthValue());
        keyMap.put(MovementDao.ATTR_MOV_YEAR, LocalDate.now().getYear());
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_SUM_EXPENSES_AMOUNT_FOR_MONTH);
    }

    public EntityResult insertExpenseInsert (Map<String,Object> attrMap) throws OntimizeJEERuntimeException{
        Float movAmount = (Float)attrMap.get(MovementDao.ATTR_MOV_AMOUNT);
        Double expenseAmount = movAmount.doubleValue() * -1;
        attrMap.put(MovementDao.ATTR_MOV_AMOUNT, expenseAmount);
        return movementInsert(attrMap);
    }

    @Override
    public EntityResult totalExpensesAmountDayQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(ExpenseDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_TOTAL_EXPENSES_AMOUNT_DAY);
    }

    @Override
    public EntityResult totalIncomesAmountDayQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(ExpenseDao.ATTR_USER_, daoHelper.getUser().getUsername());
        EntityResult totalIncomesAmountDay = this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_TOTAL_INCOMES_AMOUNT_DAY);
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_TOTAL_INCOMES_AMOUNT_DAY);
    }

    @Override
    public EntityResult expensesForCategoriesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(ExpenseDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_EXPENSES_FOR_CATEGORIES);

    }

    @Override
    public EntityResult incomesForCategoriesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(ExpenseDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.movementDao, keyMap, attrList, MovementDao.QUERY_INCOMES_FOR_CATEGORIES);
    }


}

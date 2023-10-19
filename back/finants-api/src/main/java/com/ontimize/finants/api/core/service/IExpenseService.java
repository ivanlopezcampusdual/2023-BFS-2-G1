package com.ontimize.finants.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;


public interface IExpenseService {
    public EntityResult expenseQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult expenseInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult expenseUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult expenseDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult totalAmountDayQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public  EntityResult totalExpensesForCurrentMounth(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

}

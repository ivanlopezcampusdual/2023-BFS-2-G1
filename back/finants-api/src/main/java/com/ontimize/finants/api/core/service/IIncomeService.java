package com.ontimize.finants.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;


public interface IIncomeService {
    public EntityResult incomeQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult incomeInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult incomeUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult incomeDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}

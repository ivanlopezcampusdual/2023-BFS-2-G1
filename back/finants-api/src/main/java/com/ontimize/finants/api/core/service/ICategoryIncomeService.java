package com.ontimize.finants.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface ICategoryIncomeService {
    public EntityResult categoryIncomeQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult categoryIncomeInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult categoryIncomeUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult categoryIncomeDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}

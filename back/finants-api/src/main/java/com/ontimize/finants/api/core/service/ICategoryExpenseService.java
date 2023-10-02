package com.ontimize.finants.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface ICategoryExpenseService {
    public EntityResult categoryExpenseQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult categoryExpenseInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult categoryExpenseUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult categoryExpenseDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}

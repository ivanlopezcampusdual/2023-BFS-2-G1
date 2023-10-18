package com.ontimize.finants.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IGroupService {
    public EntityResult groupQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult groupInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult groupUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult groupDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}

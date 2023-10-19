package com.ontimize.finants.model.core.service;


import com.ontimize.finants.api.core.service.ICategoryExpenseService;
import com.ontimize.finants.model.core.dao.CategoryExpenseDao;
import com.ontimize.finants.model.core.dao.ExpenseDao;
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
@Service("CategoryExpenseService")

public class CategoryExpenseService implements ICategoryExpenseService {
    @Autowired
    private CategoryExpenseDao categoryExpenseDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult categoryExpenseQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.categoryExpenseDao, keyMap, attrList);
    }

    @Override
    public EntityResult categoryExpenseInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.categoryExpenseDao, attrMap);
    }

    @Override
    public EntityResult categoryExpenseUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.categoryExpenseDao, attrMap, keyMap);
    }

    @Override
    public EntityResult categoryExpenseDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.categoryExpenseDao, keyMap);
    }
}

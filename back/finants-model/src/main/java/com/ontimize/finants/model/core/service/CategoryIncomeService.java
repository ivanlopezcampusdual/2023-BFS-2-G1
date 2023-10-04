package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.ICategoryIncomeService;
import com.ontimize.finants.model.core.dao.CategoryIncomeDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("CategoryIncomeService")
public class CategoryIncomeService implements ICategoryIncomeService {
    @Autowired
    private CategoryIncomeDao categoryIncomeDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult categoryIncomeQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.categoryIncomeDao, keyMap, attrList);
    }

    @Override
    public EntityResult categoryIncomeInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.categoryIncomeDao, attrMap);
    }

    @Override
    public EntityResult categoryIncomeUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.categoryIncomeDao, attrMap, keyMap);
    }

    @Override
    public EntityResult categoryIncomeDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.categoryIncomeDao, keyMap);
    }
}

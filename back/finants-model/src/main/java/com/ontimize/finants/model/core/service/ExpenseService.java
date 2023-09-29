package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IExpenseService;
import com.ontimize.finants.model.core.dao.ExpenseDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Lazy
@Service("ExpenseService")

public class ExpenseService implements IExpenseService {

    @Autowired
    private ExpenseDao expenseDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult expenseQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.expenseDao, keyMap, attrList);
    }

    @Override
    public EntityResult expenseInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.expenseDao, attrMap);
    }

    @Override
    public EntityResult expenseUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.expenseDao, attrMap, keyMap);
    }

    @Override
    public EntityResult expenseDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.expenseDao, keyMap);
    }
}

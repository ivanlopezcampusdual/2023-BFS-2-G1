package com.ontimize.finants.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("ExpenseDao")
@ConfigurationFile(configurationFile = "dao/ExpenseDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ExpenseDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_EX_ID = "ex_id";
    public static final String ATTR_EX_CONCEPT = "ex_concept";
    public static final String ATTR_EX_AMOUNT = "ex_amount";
    public static final String ATTR_EX_DATE = "ex_date";
    public static final String ATTR_CA_EX_ID = "ca_ex_id";
}

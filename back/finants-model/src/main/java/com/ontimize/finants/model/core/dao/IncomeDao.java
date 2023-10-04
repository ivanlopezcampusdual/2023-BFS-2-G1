package com.ontimize.finants.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("IncomeDao")
@ConfigurationFile(configurationFile = "dao/IncomeDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class IncomeDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_IN_ID = "IN_ID";
    public static final String ATTR_IN_CONCEPT = "IN_CONCEPT";
    public static final String ATTR_IN_AMOUNT = "IN_AMOUNT";
    public static final String ATTR_IN_DATE = "IN_DATE";
    public static final String ATTR_CA_IN_ID = "CA_IN_ID";
}

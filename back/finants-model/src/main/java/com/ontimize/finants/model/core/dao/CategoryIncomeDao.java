package com.ontimize.finants.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("CategoryIncomeDao.xml")
@Lazy
@ConfigurationFile(configurationFile = "dao/CategoryIncomeDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class CategoryIncomeDao extends OntimizeJdbcDaoSupport {
    public static final String ATTR_CA_IN_ID = "CA_IN_ID";
    public static final String ATTR_CA_IN_NAME = "CA_IN_NAME";
}

package com.ontimize.finants.model.core.dao;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;


@Repository("CategoryExpenseDao.xml")
@Lazy
@ConfigurationFile(configurationFile = "dao/CategoryExpenseDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class CategoryExpenseDao extends  OntimizeJdbcDaoSupport{

    public static final String ATTR_CA_EX_ID = "CA_EX_ID";
    public static final String ATTR_CA_EX_NAME = "CA_EX_NAME";

}

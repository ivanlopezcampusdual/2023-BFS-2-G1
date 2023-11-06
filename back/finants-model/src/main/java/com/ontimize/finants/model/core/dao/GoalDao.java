package com.ontimize.finants.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("GoalDao")
@ConfigurationFile(configurationFile = "dao/GoalDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class GoalDao extends OntimizeJdbcDaoSupport {
    public static final String ATTR_ID = "GO_ID";
    public static final String ATTR_USER_ = "USER_";
    public static final String ATTR_GO_NAME = "GO_NAME";
    public static final String ATTR_GO_CA_ID = "GO_CA_ID";
    public static final String ATTR_GO_AMOUNT = "GO_AMOUNT";
}

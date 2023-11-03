package com.ontimize.finants.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository("GroupDao")
@ConfigurationFile(configurationFile = "dao/GroupDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class GroupDao extends OntimizeJdbcDaoSupport {
    public static final String ATTR_GR_ID = "GR_ID";
    public static final String ATTR_GR_NAME = "GR_NAME";
    public static final String ATTR_USER_ = "USER_";
    public static final String ATTR_GR_CREATION_DATE = "GR_CREATION_DATE";
    public static final String ATTR_GR_CHOOSE_MEMBERS = "GR_CHOOSE_MEMBERS";
    public static final String ATTR_MEMBER_BALANCE = "MEMBER_BALANCE";
    public static final String QUERY_GET_GROUPS_BY_MEMBER = "getGroupsByMember";
    public static final String QUERY_GET_GROUP_MEMBERS = "getGroupMembers";
    public static final String QUERY_GET_GROUP_MEMBERS_WITH_BALANCE = "getGroupMembersWithBalance";
    public static final String QUERY_GET_GROUP_MOVEMENTS = "getGroupMovements";
}

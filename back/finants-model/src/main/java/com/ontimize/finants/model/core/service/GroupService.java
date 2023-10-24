package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IGroupService;
import com.ontimize.finants.model.core.dao.GroupDao;
import com.ontimize.finants.model.core.dao.MemberGroupDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("GroupService")
public class GroupService implements IGroupService {

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    private final MemberGroupService memberGroupService;

    public GroupService(MemberGroupService memberGroupService) {
        this.memberGroupService = memberGroupService;
    }

    @Override
    public EntityResult groupQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.groupDao, keyMap, attrList);
    }

    @Override
    public EntityResult groupInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Object localDate = attrMap.get(GroupDao.ATTR_GR_CREATION_DATE);
        if( localDate == null){
            attrMap.put(GroupDao.ATTR_GR_CREATION_DATE, LocalDate.now());
        }
        EntityResult result = this.daoHelper.insert(this.groupDao, attrMap);

        /*METER USUARIO EN GRUPO*/
        Object lista = result.get(GroupDao.ATTR_GR_ID);
        Integer groupId = (Integer)lista;

        for (Object o : (List<Object>)attrMap.get(GroupDao.ATTR_GR_CHOOSE_MEMBERS)){
            Map<String, Object> attrMapMembers = new HashMap<>();
            attrMapMembers.put(MemberGroupDao.ATTR_GR_ID, groupId);
            attrMapMembers.put(MemberGroupDao.ATTR_USER_, o);
            attrMapMembers.put(MemberGroupDao.ATTR_IS_ADMIN, false);

            this.memberGroupService.memberGroupInsert(attrMapMembers);
        }

        return result;
    }

    @Override
    public EntityResult groupUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.groupDao, attrMap, keyMap);
    }

    @Override
    public EntityResult groupDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.groupDao, keyMap);
    }

    @Override
    public EntityResult getGroupMembersQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.groupDao, keyMap, attrList, GroupDao.QUERY_GET_GROUP_MEMBERS);
    }
}

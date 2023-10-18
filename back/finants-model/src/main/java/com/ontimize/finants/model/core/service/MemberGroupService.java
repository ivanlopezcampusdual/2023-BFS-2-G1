package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IMemberGroupService;
import com.ontimize.finants.model.core.dao.MemberGroupDao;
import com.ontimize.finants.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("MemberGroupService")
public class MemberGroupService implements IMemberGroupService {

    @Autowired
    private MemberGroupDao memberGroupDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult memberGroupQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(UserDao.USER_,daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.memberGroupDao, keyMap, attrList);
    }

    @Override
    public EntityResult memberGroupInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        attrMap.put(UserDao.USER_,daoHelper.getUser().getUsername());
        return this.daoHelper.insert(this.memberGroupDao, attrMap);
    }

    @Override
    public EntityResult memberGroupUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        attrMap.put(UserDao.USER_,daoHelper.getUser().getUsername());
        return this.daoHelper.update(this.memberGroupDao, attrMap, keyMap);
    }

    @Override
    public EntityResult memberGroupDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.memberGroupDao, keyMap);
    }
}

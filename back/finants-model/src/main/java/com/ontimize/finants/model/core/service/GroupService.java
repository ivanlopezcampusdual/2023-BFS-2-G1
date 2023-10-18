package com.ontimize.finants.model.core.service;
import com.ontimize.finants.api.core.service.IGroupService;
import com.ontimize.finants.model.core.dao.GroupDao;
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
@Service("GroupService")
public class GroupService implements IGroupService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult groupQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        keyMap.put(UserDao.USER_,daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.groupDao, keyMap, attrList);
    }

    @Override
    public EntityResult groupInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        attrMap.put(UserDao.USER_,daoHelper.getUser().getUsername());
        return this.daoHelper.insert(this.groupDao, attrMap);
    }

    @Override
    public EntityResult groupUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        attrMap.put(UserDao.USER_,daoHelper.getUser().getUsername());
        return this.daoHelper.update(this.groupDao, attrMap, keyMap);
    }

    @Override
    public EntityResult groupDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.groupDao, keyMap);
    }
}

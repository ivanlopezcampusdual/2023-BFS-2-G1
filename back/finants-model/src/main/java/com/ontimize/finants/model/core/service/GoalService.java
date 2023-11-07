package com.ontimize.finants.model.core.service;

import com.ontimize.finants.api.core.service.IGoalService;
import com.ontimize.finants.model.core.dao.GoalDao;
import com.ontimize.finants.model.core.dao.MovementDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("GoalService")
public class GoalService implements IGoalService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private GoalDao goalDao;

    @Override
    public EntityResult goalQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.goalDao, keyMap, attrList);
    }

    @Override
    public EntityResult goalInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.goalDao,attrMap);
    }

    @Override
    public EntityResult goalUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.goalDao,attrMap, keyMap);
    }

    @Override
    public EntityResult goalDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.goalDao, keyMap);
    }

    @Override
    public EntityResult getGoalsCategoryWithNameQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Map<String, Object> keyMapFilterUser = new HashMap<>(keyMap);
        keyMapFilterUser.put(GoalDao.ATTR_USER_, daoHelper.getUser().getUsername());
        return this.daoHelper.query(this.goalDao, keyMapFilterUser, attrList,GoalDao.QUERY_GET_GOALS_CATEGORY_WITH_NAME);

    }

}

package com.ontimize.finants.model.core.service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ontimize.finants.api.core.service.IUserService;
import com.ontimize.finants.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;


@Lazy
@Service("UserService")
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;

	public void loginQuery(Map<?, ?> key, List<?> attr) {
	}

	//Sample to permission
	//@Secured({ PermissionsProviderSecured.SECURED })
	public EntityResult userQuery(Map<?, ?> keyMap, List<?> attrList) {
		return this.daoHelper.query(userDao, keyMap, attrList);
	}

	public EntityResult userInsert(Map<?, ?> attrMap) {
		return this.daoHelper.insert(userDao, attrMap);
	}

	public EntityResult userUpdate(Map<?, ?> attrMap, Map<?, ?> keyMap) {
		return this.daoHelper.update(userDao, attrMap, keyMap);
	}

	public EntityResult userDelete(Map<?, ?> keyMap) {
		Map<Object, Object> attrMap = new HashMap<>();
		attrMap.put("user_down_date", new Timestamp(Calendar.getInstance().getTimeInMillis()));
		return this.daoHelper.update(this.userDao, attrMap, keyMap);
	}



	@Override
	public EntityResult balanceQuery(Map<String, Object> keyMap, List<String> attrList) {
		//1) Recogemos los EntityResult del sumatorio de ingresos y gastos
		EntityResult totalExpensesForCurrentMonth = this.getTotalExpensesForCurrentMounth(keyMap,attrList);
		EntityResult totalIncomesForCurrentMonth = this.getTotalIncomesForCurrentMounth(keyMap,attrList);
		//2) Accedemos a los valores
		List<Object> userColumnExpenses = (List<Object>) totalExpensesForCurrentMonth.get("user_");
		List<Object> balanceColumnExpenses = (List<Object>) totalExpensesForCurrentMonth.get("balance");

		List<Object> userColumnIncomes = (List<Object>) totalIncomesForCurrentMonth.get("user_");
		List<Object> balanceColumnIncomes = (List<Object>) totalIncomesForCurrentMonth.get("balance");
		//3) Instanciamos BigDecimal
		BigDecimal totalExpenses = BigDecimal.ZERO;
		BigDecimal totalIncomes = BigDecimal.ZERO;

		//4)Comprobamos que tengamos el usuario y el total de ingresos y gastos y extraemos el valor
		if (userColumnExpenses != null && balanceColumnExpenses != null) {
				BigDecimal expense = new BigDecimal(String.valueOf(balanceColumnExpenses.get(0)));
				totalExpenses = totalExpenses.add(expense);
		}

		if (userColumnIncomes != null && balanceColumnIncomes != null) {
				BigDecimal income = new BigDecimal(String.valueOf(balanceColumnIncomes.get(0)));
				totalIncomes = totalIncomes.add(income);
		}

		//5) Recuperamos los valores del contexto de la autenticación
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//6) realizamos la operación de resta para obtener el balance
		BigDecimal balance = totalIncomes.subtract(totalExpenses);

		//7) Como tenemos que devolver un EntityResult y no hay forma de instanciarlo ya que es abstracto lo clonamos
		//lo limpiamos y le añadimos nuestras clave valor
		EntityResult entityResult = totalExpensesForCurrentMonth.clone();
		entityResult.clear();
		entityResult.put("user_", Collections.singletonList(authentication.getName()));
		entityResult.put("balance", Collections.singletonList(balance));
		return entityResult  ;
	}

	private  EntityResult getTotalExpensesForCurrentMounth(Map<String, Object> keyMap, List<String> attrList){
		keyMap.clear();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		keyMap.put(UserDao.USER_, authentication.getName());
		keyMap.put("EX_MONTH", LocalDate.now().getMonthValue());
		keyMap.put("EX_YEAR", LocalDate.now().getYear());
		return this.daoHelper.query(this.userDao, keyMap, attrList,"getTotalExpensesForCurrentMounth" );
	}
	private  EntityResult getTotalIncomesForCurrentMounth(Map<String, Object> keyMap, List<String> attrList){
		keyMap.clear();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		keyMap.put(UserDao.USER_, authentication.getName());
		keyMap.put("IN_MONTH", LocalDate.now().getMonthValue());
		keyMap.put("IN_YEAR", LocalDate.now().getYear());
		return this.daoHelper.query(this.userDao, keyMap, attrList,"getTotalIncomesForCurrentMounth" );
	}

}

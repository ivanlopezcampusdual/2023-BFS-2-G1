package com.ontimize.finants.ws.core.rest;

import com.ontimize.finants.api.core.service.ICategoryExpenseService;
import com.ontimize.finants.api.core.service.ICategoryIncomeService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoryIncomes")
public class CategoryIncomeRestController extends ORestController<ICategoryIncomeService> {

    @Autowired
    private ICategoryIncomeService categoryIncomeService;
    @Override
    public ICategoryIncomeService getService() {
        return this.categoryIncomeService;
    }
}

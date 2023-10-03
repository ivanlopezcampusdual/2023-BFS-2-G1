package com.ontimize.finants.ws.core.rest;

import com.ontimize.finants.api.core.service.IIncomeService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incomes")
public class IncomeRestController extends ORestController<IIncomeService> {
    @Autowired
    private IIncomeService incomeService;
    @Override
    public IIncomeService getService() {
        return this.incomeService;
    }
}

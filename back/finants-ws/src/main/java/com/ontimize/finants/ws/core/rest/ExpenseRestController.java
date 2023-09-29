package com.ontimize.finants.ws.core.rest;

import com.ontimize.finants.api.core.service.IExpenseService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseRestController extends ORestController<IExpenseService> {
    @Autowired
    private IExpenseService expenseService;
    @Override
    public IExpenseService getService() {
        return this.expenseService;
    }
}

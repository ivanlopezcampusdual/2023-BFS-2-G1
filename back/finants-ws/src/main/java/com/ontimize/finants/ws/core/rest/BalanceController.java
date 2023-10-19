package com.ontimize.finants.ws.core.rest;

import com.ontimize.finants.api.core.service.IBalanceService;
import com.ontimize.finants.api.core.service.IExpenseService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
public class BalanceController extends ORestController<IBalanceService> {
    @Autowired
    private IBalanceService iBalanceService;
    @Override
    public IBalanceService getService() {
        return this.iBalanceService;
    }
}

package com.ontimize.finants.ws.core.rest;


import com.ontimize.finants.api.core.service.ICategoryExpenseService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoryExpenses")
public class CategoryExpenseRestController extends ORestController<ICategoryExpenseService> {
    @Autowired
    private ICategoryExpenseService categoryExpenseService;
    @Override
    public ICategoryExpenseService getService(){
        return  this.categoryExpenseService;
    }
}

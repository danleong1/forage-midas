package com.jpmc.midascore.controller;

import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.service.BalanceQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    private final BalanceQueryService balanceQuery;

    public BalanceController(BalanceQueryService balanceQuery) {
        this.balanceQuery = balanceQuery;
    }

    @GetMapping("/balance")
    public Balance getBalance(@RequestParam("userId") long userId) {
        return new Balance(balanceQuery.getBalanceOrZero(userId));
    }
}

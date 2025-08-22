package com.jpmc.midascore.service;

import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceQueryService {
    private final UserRepository users;

    public BalanceQueryService(UserRepository users) {
        this.users = users;
    }

    @Transactional(readOnly = true)
    public float getBalanceOrZero(long id) {
        if (users.findById(id) != null) {
            return users.findById(id).getBalance();
        }
        else {
            return 0f;
        }
    }
}

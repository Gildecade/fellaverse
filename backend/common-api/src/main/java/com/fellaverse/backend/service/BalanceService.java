package com.fellaverse.backend.service;

public interface BalanceService {
    Boolean checkBalance(Long userId, Float price);

    Boolean updateBalance(Long userId, Float price);
}

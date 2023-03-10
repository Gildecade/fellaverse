package com.fellaverse.backend.service;

import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    private UserRepository userRepository;

    /***
     * check account of the user, if the balance is enough to buy
     * @param userId user who's buying the product
     * @param price the price of the product
     * @return true if balance is enough
     */
    @Override
    public Boolean checkBalance(Long userId, Float price) {
        return userRepository.findUserWallet(userId) >= price;
    }

    @Override
    @Transactional
    public Boolean updateBalance(Long userId, Float price) {
        Float wallet = userRepository.lockUserWallet(userId);
        if (wallet + price < 0) {
            return false;
        }
        return (userRepository.updateUserWallet(userId, wallet + price) == 1);
    }
}

package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            return user.getWallet() >= price;
        }
        return false;
    }

    @Override
    public Boolean updateBalance(Long userId, Float price) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            user.setWallet(user.getWallet() + price);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}

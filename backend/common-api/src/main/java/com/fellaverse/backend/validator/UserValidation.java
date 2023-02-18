package com.fellaverse.backend.validator;

import com.fellaverse.backend.annotation.NonConflictUser;
import com.fellaverse.backend.annotation.UniqueUser;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Predicate;

public class UserValidation<T extends Annotation> implements ConstraintValidator<T, User> {

    protected Predicate<User> predicate = c -> true;

    @Autowired
    protected UserRepository userRepository;

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return userRepository == null || predicate.test(user);
    }

    /**
     * validate whether user info is unique
     * find if username or email is existed in db
     */
    public static class UniqueUserValidator extends UserValidation<UniqueUser> {
        @Override
        public void initialize(UniqueUser uniqueUser) {
            predicate = c -> !userRepository.existsByUsernameOrEmail(c.getUsername(), c.getEmail());
        }
    }

    /**
     * validate whether user's modified info conflicts with others
     * check if username or email is not existed in db or
     * user information is not a conflict if it only repeats itself
     */
    public static class NonConflictUserValidator extends UserValidation<NonConflictUser> {
        @Override
        public void initialize(NonConflictUser nonConflictUser) {
            predicate = c -> {
                List<User> userList = userRepository.findByUsernameOrEmail(c.getUsername(), c.getEmail());
                return userList.isEmpty() || (userList.size() == 1 && userList.iterator().next().getId().equals(c.getId()));
            };
        }
    }
}

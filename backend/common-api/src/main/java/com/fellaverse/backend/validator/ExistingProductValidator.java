package com.fellaverse.backend.validator;

import com.fellaverse.backend.annotation.ExistingProduct;
import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.repository.LimitedProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Predicate;

public class ExistingProductValidator implements ConstraintValidator<ExistingProduct, LimitedProduct> {

    private Predicate<LimitedProduct> predicate = c -> true;
    @Autowired
    private LimitedProductRepository repository;

    @Override
    public void initialize(ExistingProduct constraintAnnotation) {
        predicate = c -> repository.existsById(c.getId());
    }

    @Override
    public boolean isValid(LimitedProduct value, ConstraintValidatorContext context) {
        return repository == null || predicate.test(value);
    }
}

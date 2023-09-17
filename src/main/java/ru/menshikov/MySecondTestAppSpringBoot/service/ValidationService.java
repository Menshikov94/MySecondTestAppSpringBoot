package ru.menshikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.menshikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService {
void isValid(BindingResult bindingResult) throws ValidationFailedException;
}


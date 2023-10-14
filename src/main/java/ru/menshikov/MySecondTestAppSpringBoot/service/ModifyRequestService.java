package ru.menshikov.MySecondTestAppSpringBoot.service;


import org.springframework.stereotype.Service;
import ru.menshikov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}

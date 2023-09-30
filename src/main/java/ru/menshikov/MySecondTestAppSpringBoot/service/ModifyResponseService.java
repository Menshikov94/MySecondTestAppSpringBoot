package ru.menshikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.menshikov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}

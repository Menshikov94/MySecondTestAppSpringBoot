package ru.menshikov.MySecondTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.menshikov.MySecondTestAppSpringBoot.model.Request;

@Slf4j
@Service
public class ModifySystemNameRequestService implements ModifyRequestService{
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ModifySystemNameRequestService.class);
    @Override
    public void modify(Request request) {
        request.setSource("Service 2");
        // Измерить время между получением запроса и модификацией
        long startTime = System.currentTimeMillis();

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8090/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
        // Измерить время после отправки модифицированного запроса
        long endTime = System.currentTimeMillis();

        // Вывести разницу времени в лог
        logger.info("Время выполнения модификации запроса: {} мс", endTime - startTime);

        // Вывести сообщение в лог
        logger.info("Сообщение обработано. Ответ отправлен.");
    }
}
package ru.menshikov.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.menshikov.MySecondTestAppSpringBoot.model.Response;

import java.util.UUID;

@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService
        implements ModifyResponseService{
    @Override
    public Response modify(Response response) {

        UUID uuid = UUID.randomUUID();

        response.setOpperationUid(uuid.toString());

        return response;
    }
}
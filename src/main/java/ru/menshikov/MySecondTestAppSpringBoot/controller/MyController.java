package ru.menshikov.MySecondTestAppSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.menshikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.menshikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.menshikov.MySecondTestAppSpringBoot.model.*;
import ru.menshikov.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.menshikov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.menshikov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.menshikov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    private final ModifyRequestService modifyRequestService;
    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService, ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                            BindingResult bindingResult) {
        log.info("request: {}", request);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .opperationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            // Проверяем, равен ли uid 123
            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException("Unsupported uid");
            }

            validationService.isValid(bindingResult);
            log.info("Validations passed successfully");
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPROT_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSAPPORTED);
            log.error("Unsupported code exception occurred: {}", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("Validation failed: {}", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Unknown exception occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        log.info("Response: {}", response);
        modifyRequestService.modify(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

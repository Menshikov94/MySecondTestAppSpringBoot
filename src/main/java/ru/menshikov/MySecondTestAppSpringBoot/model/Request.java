package ru.menshikov.MySecondTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Size(max = 32, message = "uid должен содержать не более 32 символов")
    private String uid;

    @Size(max = 32, message = "operationUid должен содержать не более 32 символов")
    private String operationUid;

    private String systemName;

    @NotBlank(message = "systemTime не может быть пустым")
    private String systemTime;

    private String source;

    @Min(value = 1, message = "communicationId должен быть больше или равен 1")
    @Max(value = 100000, message = "communicationId должен быть меньше или равен 100000")
    private int communicationId;

    private int templateId;

    private int productCode;

    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                        ", systemName='" + systemName + '\'' +
                        ", systemTime='" + systemTime + '\'' +
                        ", source='" + source + '\'' +
                        ", communicationId=" + communicationId +
                        ", templateId=" + templateId +
                        ", productCode=" + productCode +
                        ", smsCode=" + smsCode +
                        '}';
    }
}
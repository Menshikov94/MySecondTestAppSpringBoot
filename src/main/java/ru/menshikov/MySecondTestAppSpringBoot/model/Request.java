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
    //Уникальный идентификатор пользователя
    @Size(max = 32, message = "uid должен содержать не более 32 символов")
    private String uid;
    //Уникальный идентификатор операции
    @Size(max = 32, message = "operationUid должен содержать не более 32 символов")
    private String operationUid;
    //Название системы
    private Systems systemName;
    //Время в системе
    @NotBlank(message = "systemTime не может быть пустым")
    private String systemTime;
    //Источник запроса
    private String source;
    //Позиция сотрудника
    private Positions position;
    //Зарплата сотрудника
    private Double salary;
    //Бонус сотрудника
    private Double bonus;
    //Количество рабочих дней
    private Integer workDays;

    //Идентификатор коммуникации
    @Min(value = 1, message = "communicationId должен быть больше или равен 1")
    @Max(value = 100000, message = "communicationId должен быть меньше или равен 100000")
    private int communicationId;

    //Идентификатор шаблона
    private int templateId;
    //Код продукта
    private int productCode;
    //SMS код
    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position" + position +
                ", salary" + salary +
                ", bonus" + bonus +
                ", workDays" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
    public enum Systems {
        ERP("Enterprise Resource Planning"),
        CRM("Customer Relationship Management"),
        WMS("Warehouse Management System");

        private final String systemName;

        Systems(String systemName) {
            this.systemName = systemName;
        }

        public String getSystemName() {
            return systemName;
        }

        @Override
        public String toString() {
            return systemName;
        }
    }
}

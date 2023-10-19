package ru.menshikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.menshikov.MySecondTestAppSpringBoot.model.Positions;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service

public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = isLeapYear() ? 366 : 365;
        double annualBonus = salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
        return annualBonus;
    }

    private boolean isLeapYear() {
        int year = GregorianCalendar.getInstance().get(Calendar.YEAR);
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
    @Override
    public double calculateManagers(Positions positions, double salary, double bonus, int workDays) {
        if (positions.isManager()) {
            int daysInQuarter = determineDaysInQuarter();
            double annualBonus = salary * bonus * daysInQuarter * positions.getPositionCoefficient() / workDays;
            return annualBonus;
        } else {
            return 0.0;
        }
    }
    private int determineDaysInQuarter() {

        int currentMonth = new GregorianCalendar().get(GregorianCalendar.MONTH);
        return switch (currentMonth) {
            case 0, 1, 2 -> 90;
            case 3, 4, 5 -> 91;
            case 6, 7, 8 -> 92;
            case 9, 10, 11 -> 92;
            default -> 0;
        };
    }
    }

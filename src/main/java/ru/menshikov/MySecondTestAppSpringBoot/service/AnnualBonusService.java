package ru.menshikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.menshikov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
    double calculateManagers(Positions positions, double salary, double bonus, int workDays);

}

package ru.menshikov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.menshikov.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        // given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        // when
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);
        // then
        double expected = 360493.8271684938;
        assertThat(result).isEqualTo(expected);
    }
}
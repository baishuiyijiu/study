package com.xxx.xxx.service;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.domain.PersonRequest;
import com.xxx.xxx.utils.SalaryCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.omg.CORBA.PolicyError;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SalaryCalculator.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Test
    public void should_find_person_none() {
        //given
        //when
        //then
        assertThat(personService.find(new PersonRequest("xxxx")))
                .isEqualToComparingFieldByField(new Person("None", "None", BigDecimal.ZERO));

    }

    @Test
    public void should_find_person() {
        //given
        PowerMockito.mockStatic(SalaryCalculator.class);

        //when
        PowerMockito.when(SalaryCalculator.calculate(BigDecimal.TEN)).thenReturn(BigDecimal.valueOf(20));

        //then
        assertThat(personService.find(new PersonRequest("James"))).
                isEqualToComparingFieldByField(new Person("Merson", "James", BigDecimal.valueOf(20)));
    }
}
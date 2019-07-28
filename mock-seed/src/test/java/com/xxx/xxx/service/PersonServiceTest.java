package com.xxx.xxx.service;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.domain.PersonRequest;
import com.xxx.xxx.utils.SalaryCalculator;
import com.xxx.xxx.utils.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {SalaryCalculator.class, TimeUnit.class})
@SuppressStaticInitializationFor("com.xxx.xxx.utils.SalaryCalculator")
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
        PowerMockito.mockStatic(TimeUnit.class);

        //when
        PowerMockito.when(SalaryCalculator.calculate(any())).thenReturn(BigDecimal.valueOf(20));

        //then
        assertThat(personService.find(new PersonRequest("James"))).
                isEqualToComparingFieldByField(new Person("Merson", "James", BigDecimal.valueOf(20)));
    }
}
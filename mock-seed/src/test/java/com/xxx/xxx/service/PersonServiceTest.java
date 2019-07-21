package com.xxx.xxx.service;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.domain.PersonRequest;
import com.xxx.xxx.utils.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(PowerMockRunner.class)
@PrepareForTest(TimeUnit.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Test
    public void should_find_person_none() {
        //given
        PersonRequest request = new PersonRequest("xxxx");

        //when
        Person person = personService.find(request);

        //then
        assertThat(person).isEqualToComparingFieldByField(new Person("None", "None", BigDecimal.ZERO));

    }

    @Test
    public void should_find_person() {
        //given
        PersonRequest request = new PersonRequest("James");
        PowerMockito.mockStatic(TimeUnit.class);
        PowerMockito.doNothing().when(TimeUnit.class);

        //when
        Person person = personService.find(request);

        //then
        assertThat(person).isEqualToComparingFieldByField(new Person("Merson", "James", new BigDecimal(20)));
    }
}
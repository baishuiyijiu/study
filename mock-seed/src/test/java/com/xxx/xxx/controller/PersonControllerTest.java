package com.xxx.xxx.controller;

import com.xxx.xxx.domain.Person;
import com.xxx.xxx.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;
    @Mock
    private PersonService personService;

    @Test
    public void should_get_name() {
        //given
        given(personService.find(argThat(person->person.getName().equals("James")))).willReturn(new Person("Merson",  "James", BigDecimal.TEN));

        //then
        String name = personController.getName("James");

        // then
        assertThat(name).isEqualTo("Merson,James");

    }
}
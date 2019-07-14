package com.lilihuang;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    private StringUtils stringUtils;

    @Before
    public void setup() {
        stringUtils = new StringUtils("test");
    }

    @Test
    public void should_is_empty_with_null() {
        //given
        //when
        //then
        assertThat(stringUtils.isEmpty(null)).isTrue();
    }


    @Test
    public void should_is_empty_with_length_equal_zero() {
        //given
        //when
        //then
        assertThat(stringUtils.isEmpty("")).isTrue();
    }

    @Test
    public void should_is_blank_with_null() {
        //given
        //when
        //then
        assertThat(stringUtils.isBlank(null)).isTrue();
    }

    @Test
    public void should_is_blank_with_length_equal_zero() {
        //given
        //when
        //then
        assertThat(stringUtils.isBlank("")).isTrue();
    }

    @Test
    public void should_is_blank_with_length_not_equal_zero() {
        //given
        //when
        //then
        assertThat(stringUtils.isBlank(" ")).isTrue();
    }

    @Test
    public void should_is_not_blank_with_length_not_equal_zero() {
        //given
        //when
        //then
        assertThat(stringUtils.isBlank("d")).isFalse();
    }

    @Test
    public void should_is_not_alpha_with_null() {
        //given
        //when
        //then
        assertThat(stringUtils.isAlpha(null)).isFalse();
    }

    @Test
    public void should_is_not_alpha_with_string() {
        //given
        //when
        //then
        assertThat(stringUtils.isAlpha(" ")).isFalse();
    }

    @Test
    public void should_is_alpha_with_null() {
        //given
        //when
        //then
        assertThat(stringUtils.isAlpha("ff")).isTrue();
    }
}
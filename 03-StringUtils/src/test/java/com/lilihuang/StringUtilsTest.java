package com.lilihuang;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringUtilsTest {

    private StringUtils stringUtils;

    @Before
    public void setup() {
        stringUtils = new StringUtils("test");
    }

    @Test
    public void should_is_empty_with_null() {
        //given
        CharSequence test = null;

        //when
        boolean flag = stringUtils.isEmpty(test);

        //then
        Assert.assertTrue(flag);
    }


    @Test
    public void should_is_empty_with_length_equal_zero() {
        //given
        CharSequence test = "";

        //when
        boolean flag = stringUtils.isEmpty(test);


        //then
        Assert.assertTrue(flag);
    }

    @Test
    public void should_is_blank_with_null() {
        //given
        CharSequence test = null;

        //when
        boolean flag = stringUtils.isBlank(test);

        //then
        Assert.assertTrue(flag);
    }

    @Test
    public void should_is_blank_with_length_equal_zero() {
        //given
        CharSequence test = "";

        //when
        boolean flag = stringUtils.isBlank(test);

        //then
        Assert.assertTrue(flag);
    }

    @Test
    public void should_is_blank_with_length_not_equal_zero() {
        //given
        CharSequence test = " ";

        //when
        boolean flag = stringUtils.isBlank(test);

        //then
        Assert.assertTrue(flag);
    }

    @Test
    public void should_is_not_blank_with_length_not_equal_zero() {
        //given
        CharSequence test = "d";

        //when
        boolean flag = stringUtils.isBlank(test);

        //then
        Assert.assertFalse(flag);
    }

    @Test
    public void should_is_not_alpha_with_null() {
        //given
        CharSequence test = null;

        //when
        boolean flag = stringUtils.isAlpha(test);

        //then
        Assert.assertFalse(flag);
    }

    @Test
    public void should_is_not_alpha_with_string() {
        //given
        CharSequence test = " ";

        //when
        boolean flag = stringUtils.isAlpha(test);

        //then
        Assert.assertFalse(flag);
    }

    @Test
    public void should_is_alpha_with_null() {
        //given
        CharSequence test = "ff";

        //when
        boolean flag = stringUtils.isAlpha(test);

        //then
        Assert.assertTrue(flag);
    }
}
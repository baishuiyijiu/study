package com.lilihuang;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class LocaleUtilsTest {
    private LocaleUtils localeUtils;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        localeUtils = new LocaleUtils();
    }

    @Test
    public void should_is_null() {
        //given
        //when
        //then
        assertThat(localeUtils.toLocale(null)).isEqualTo(null);
    }

    @Test
    public void should_is_empty() {
        //given
        //when
        //then
        assertThat(localeUtils.toLocale("")).isEqualToComparingFieldByField(new Locale("", ""));
    }

    @Test
    public void should_throw_exception_with_illegal_char() {
        //given
        String str = "test#";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_throw_exception_with_one_length() {
        //given
        String str = "a";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_throw_exception_with_illegal_start_and_length() {
        //given
        String str = "_a";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_throw_exception_with_illegal_char1() {
        //given
        String str = "_aA";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_throw_exception_with_illegal_char2() {
        //given
        String str = "_Aa";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_is_locale_start_with_three_length() {
        //given
        String str = "_AA";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("", str.substring(1, 3)));
    }

    @Test
    public void should_throw_exception_with_length_four() {
        //given
        String str = "_AAa";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_throw_exception_with_third_is_illegal() {
        //given
        String str = "_AAAA";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_is_locale_with_upper_char() {
        //given
        String str = "_AA_D";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("", str.substring(1, 3), str.substring(4)));
    }

    @Test
    public void should_is_locale_with_lower_char() {
        //given
        String str = "_AA_a";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("", str.substring(1, 3), str.substring(4)));
    }

    @Test
    public void should_is_locale_with_numeric() {
        //given
        String str = "_AA_11";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("", str.substring(1, 3), str.substring(4)));
    }

    @Test
    public void should_is_ISO639_with_two_length() {
        //given
        String str = "aa";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(str));
    }

    @Test
    public void should_is_not_ISO639_with_two_length() {
        //given
        String str = "aA";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_is_not_ISO639_with_four_length() {
        //given
        String str = "aaaa";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_is_ISO639_with_three_length() {
        //given
        String str = "aaa";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(str));
    }

    @Test
    public void should_segments_length_is_two_and_has_ISO639_and_ISO3366() {
        //given
        String str = "aa_BB";
        String language = "AA";
        String country = "BB";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(language, country));
    }

    @Test
    public void should_thrown_exception_with_illegal_length_when_two_segment() {
        //given
        String str = "aa_BBB";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }


    @Test
    public void should_segments_length_is_two_and_has_ISO639_and_ISO3366_numeric() {
        //given
        String str = "aa_111";
        String language = "AA";
        String country = "111";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(language, country));
    }

    @Test
    public void should_thrown_exception_with_illegal_numeric_when_two_segment() {
        //given
        String str = "aa_11";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_lower_country_when_two_segment() {
        //given
        String str = "aa_bb";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_illegal_language_and_country_when_two_segment() {
        //given
        String str = "AA_11";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_illegal_country_when_two_segment() {
        //given
        String str = "AA_aa";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_segments_length_is_three_and_has_ISO639_and_empty() {
        //given
        String str = "aa__111";
        String language = "aa";
        String country = "";
        String variant = "111";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(language, country, variant));
    }

    @Test
    public void should_segments_length_is_three_and_has_ISO639_and_ISO3366() {
        //given
        String str = "aa_BB_111";
        String language = "aa";
        String country = "BB";
        String variant = "111";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(language, country, variant));
    }

    @Test
    public void should_segments_length_is_three_and_has_ISO639_and_numeric() {
        //given
        String str = "aa_111_b";
        String language = "aa";
        String country = "111";
        String variant = "b";

        //when
        Locale result = localeUtils.toLocale(str);

        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(language, country, variant));
    }


    @Test
    public void should_thrown_exception_with_empty_language_when_two_segment() {
        //given
        String str = "__BB";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_no_arguments_when_three_segment() {
        //given
        String str = "__";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_no_arguments_when_two_segment() {
        //given
        String str = "aa_";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_variant_is_empty_when_three_segment() {
        //given
        String str = "aa_111_";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_illegal_language_when_three_segment() {
        //given
        String str = "Aa_111_1";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }

    @Test
    public void should_thrown_exception_with_illegal_country_when_three_segment() {
        //given
        String str = "aaa_1a_1";

        //when
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid locale format: " + str);
        localeUtils.toLocale(str);
    }
}
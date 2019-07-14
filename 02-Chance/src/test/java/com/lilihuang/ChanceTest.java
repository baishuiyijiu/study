package com.lilihuang;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ChanceTest {

    private static final double P1 = 0.44;
    private static final double P2 = 0.55;

    private Chance chance1;
    private Chance chance2;

    @Before
    public void setup() {
        chance1 = new Chance(P1);
        chance2 = new Chance(P2);
    }

    @Test
    public void should_get_not_chance() {
        //given
        //when
        //then
        assertThat(new Chance(P1)).isEqualToComparingFieldByField(chance1.not().not());
    }

    @Test
    public void should_get_and_chance() {
        //given
        //when
        double doubleAndChance = P1 * P2;
        //then
        assertThat(new Chance(doubleAndChance)).isEqualToComparingFieldByField(chance1.and(chance2));
    }


    @Test
    public void should_get_or_chance() {
        //given
        //when
        double doubleOrChance = P1 + P2 - P1 * P2;
        //then
        assertThat(new Chance(doubleOrChance)).isEqualToComparingFieldByField(chance1.or(chance2));
    }
}

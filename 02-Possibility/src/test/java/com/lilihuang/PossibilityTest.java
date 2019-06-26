package com.lilihuang;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PossibilityTest {

    private static final double P1 = 0.44;
    private static final double P2 = 0.55;
    private static final double P3 = 0.66;

    private Possibility possibility1;
    private Possibility possibility2;
    private Possibility possibility3;

    @Before
    public void setup() {
        possibility1 = new Possibility(P1);
        possibility2 = new Possibility(P2);
        possibility3 = new Possibility(P3);
    }

    @Test
    public void should_get_not_chance() {
        //given
        //when
        double expectedNotChance = 1 - P1;
        //then
        Assert.assertEquals(new Possibility(expectedNotChance), possibility1.not());
        Assert.assertEquals(new Possibility(P1), possibility1.not().not());
    }

    @Test
    public void should_get_and_chance() {
        //given
        //when
        double doubleAndChance = P1 * P2;
        double tripleAndChance = P1 * P2 * P3;
        //then
        Assert.assertEquals(new Possibility(doubleAndChance), possibility1.and(possibility2));
        Assert.assertEquals(new Possibility(tripleAndChance),
                possibility1.and(possibility2).and(possibility3));
    }


    @Test
    public void should_get_or_chance() {
        //given
        //when
        double doubleOrChance = P1 + P2 - P1 * P2;
        double tripleOrChance = doubleOrChance + P3 - doubleOrChance * P3;
        //then
        Assert.assertEquals(new Possibility(doubleOrChance), possibility1.or(possibility2));
        Assert.assertEquals(new Possibility(tripleOrChance), possibility1.or(possibility2).or(possibility3));
    }
}

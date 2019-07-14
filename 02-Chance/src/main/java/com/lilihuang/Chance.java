package com.lilihuang;

public class Chance {
    private static final double FULL = 1;

    private double chance;

    public Chance(double value) {
        assert(value >= 0 && value <=1);
        chance = value;
    }

    public Chance not() {
        return new Chance(FULL - chance);
    }

    // TODO 这里假设事件相互独立，否则应该使用条件概率P(AB) = P(A)*P(B|A) or P(AB) = P(B)*P(A|B)
    public Chance and(Chance other) {
        return new Chance(chance * other.chance);
    }

    public Chance or(Chance other) {
        return new Chance(chance + other.chance - this.and(other).chance);
    }
}

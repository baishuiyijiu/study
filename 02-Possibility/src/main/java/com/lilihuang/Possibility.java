package com.lilihuang;

/**
 *Possibility Class
 */

public class Possibility {

    private static final double PRECISION = 0.000000001;

    public double chance;

    public Possibility(double value) {
        assert(value >= 0 && value <=1);
        chance = value;
    }

    public Possibility not() {
        return new Possibility(1 - chance);
    }

    // TODO 这里假设事件相互独立，否则应该使用条件概率P(AB) = P(A)*P(B|A) or P(AB) = P(B)*P(A|B)
    public Possibility and(Possibility other) {
        return new Possibility(chance * other.chance);
    }

    public Possibility or(Possibility other) {
        return new Possibility(chance + other.chance - this.and(other).chance);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Possibility) {
            if (this == obj) return true;
            return Math.abs(this.chance - ((Possibility) obj).chance) < PRECISION;
        } else {
            return false;
        }
    }
}

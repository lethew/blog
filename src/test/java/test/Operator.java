package test;

import java.util.Optional;

public enum Operator {
    plus(1,'+'){
        @Override
        public Optional<Integer> compute(Integer left, Integer right) {
            return Optional.of(left + right);
        }
    },
    minus(1, '-'){
        @Override
        public Optional<Integer> compute(Integer left, Integer right) {
            return Optional.of(left - right);
        }
    },
    multiplied(2, '*'){
        @Override
        public Optional<Integer> compute(Integer left, Integer right) {
            return Optional.of(left * right);
        }
    },
    divided(2, '/') {
        @Override
        public Optional<Integer> compute(Integer left, Integer right) {
            if(null != right &&  0 != right && left % right == 0) {
                return Optional.of(left / right);
            } else {
                return Optional.empty();
            }

        }
    };
    private int level;
    private char symbol;

    Operator(int level, char symbol) {
        this.level = level;
        this.symbol = symbol;
    }

    public int getLevel() {
        return level;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Optional<Integer> compute(Integer left, Integer right);
}

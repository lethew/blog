package test;

import java.util.*;

/**
 *
 */
public class Demo {
    private static final int ARG_LEN = 4;
    private static final int MAX = 10;
    private static final int RESULT = 24;
    private static final int MAX_THREE = MAX * RESULT;

    public static void main(String[] args) {
        List<Integer> integers = parse(args);
        int minArg = integers.get(0);
        int maxArg = integers.get(ARG_LEN -1);
        int max = Math.min(maxArg * maxArg * maxArg, MAX_THREE);
        int min = minArg - maxArg*maxArg;

        // 构造两项式
        Map<Integer, List<Equation>> twoItems = computeTwoItemExpression(integers);
        // 构造三项式
        Map<Integer, List<Equation>> threeItems = computeThreeExpression(twoItems, integers);

        // 将结果分解为两项式
        List<Equation> resolvedTwoItems = resolveToTwoItems(min, max);
        // 分解为四项式
        List<Equation> resultEquations = resolve(integers, twoItems, threeItems, resolvedTwoItems);

        for (Equation resultEquation : resultEquations) {
            // 过滤重复使用数字的
            List<Integer> items = resultEquation.getItem();
            boolean isValid = true;
            for (Integer integer : integers) {
                isValid = items.remove(integer);
                if(!isValid) break;;
            }

            // 输出合法组合
            if(isValid) {
                System.out.println(resultEquation + "=" + resultEquation.getResult());
            }
        }

    }

    private static List<Equation> resolve(List<Integer> integers, Map<Integer, List<Equation>> twoItems, Map<Integer, List<Equation>> threeItems, List<Equation> resolvedTwoItems) {
        List<Equation> resultEquations = new ArrayList<>();
        for (Equation resolvedTwoItem : resolvedTwoItems) {
            // 2 + 2
            resolveToTwoPlusTwo(twoItems, resultEquations, resolvedTwoItem);
            // 3 + 1
            if(integers.indexOf(resolvedTwoItem.getRight()) > -1) {
                resolveToThreePlusOne(threeItems, resultEquations, resolvedTwoItem);
            }
            // 1 + 3
            if(integers.indexOf(resolvedTwoItem.getLeft()) > -1) {
                resolveToOnePlusThree(threeItems, resultEquations, resolvedTwoItem);
            }
        }
        return resultEquations;
    }

    private static void resolveToOnePlusThree(Map<Integer, List<Equation>> threeItems, List<Equation> resultEquations, Equation resolvedTwoItem) {
        List<Equation> right = threeItems.get(resolvedTwoItem.getRight());
        if (isNotEmpty(right)) {
            for (Equation ri : right) {
                Equation of = Equation.of(resolvedTwoItem.getLeft(), resolvedTwoItem.getOperator(), ri);
                if (of != null) {
                    resultEquations.add(of);
                }
            }
        }
    }

    private static void resolveToThreePlusOne(Map<Integer, List<Equation>> threeItems, List<Equation> resultEquations, Equation resolvedTwoItem) {
        List<Equation> left = threeItems.get(resolvedTwoItem.getLeft());
        if (isNotEmpty(left)) {
            for (Equation le : left) {
                Equation of = Equation.of(le, resolvedTwoItem.getOperator(), resolvedTwoItem.getRight());
                if (of != null) {
                    resultEquations.add(of);
                }
            }
        }
    }

    private static void resolveToTwoPlusTwo(Map<Integer, List<Equation>> twoItems, List<Equation> resultEquations, Equation resolvedTwoItem) {
        List<Equation> left = twoItems.get(resolvedTwoItem.getLeft());
        List<Equation> right = twoItems.get(resolvedTwoItem.getRight());
        if (isNotEmpty(left) && isNotEmpty(right)) {
            for (Equation le : left) {
                for (Equation ri : right) {
                    Equation of = Equation.of(le, resolvedTwoItem.getOperator(), ri);
                    if(of != null) {
                        resultEquations.add(of);
                    }
                }
            }
        }
    }

    private static boolean isNotEmpty(List<Equation> equations) {
        return null != equations && !equations.isEmpty();
    }

    private static List<Equation> resolveToTwoItems(int min, int max) {
        List<Equation> equations = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            for (int j = min; j <= max; j++) {
                for (Operator operator : Operator.values()) {
                    Equation equation = Equation.of(i, operator, j);
                    if (null != equation && equation.getResult().equals(RESULT)) {
                        equations.add(equation);
                    }
                }
            }
        }
        return equations;
    }


    private static List<Integer> parse(String[] args) {
        List<Integer> integers = new ArrayList<>(args.length);
        for (String arg : args) {
            integers.add(Integer.parseInt(arg));
        }
        Collections.sort(integers);
        return integers;
    }


    private static Map<Integer, List<Equation>> computeTwoItemExpression(List<Integer> ints) {
        assert ints != null && ints.size() == ARG_LEN;
        Map<Integer, List<Equation>> result = new HashMap<>();
        for (Integer left : ints) {
            for (Integer right : ints) {
                for (Operator operator : Operator.values()) {
                    Equation equation = Equation.of(left, operator, right);
                    if(null != equation) {
                        List<Equation> list = result.computeIfAbsent(equation.getResult(), k -> new ArrayList<>());
                        list.add(equation);
                    }
                }
            }
        }
        return result;
    }

    private static Map<Integer, List<Equation>> computeThreeExpression(Map<Integer, List<Equation>> twoItems, List<Integer> ints) {
        assert ints != null && ints.size() >= 3 && ints.size() == ARG_LEN;
        assert twoItems != null && !twoItems.isEmpty();
        Map<Integer, List<Equation>> result = new HashMap<>();
        for (List<Equation> twoItemList : twoItems.values()) {
            if (twoItemList != null) {
                for (Equation twoItem : twoItemList) {
                    for (Integer anInt : ints) {
                        for (Operator operator : Operator.values()) {
                            // 1 + 2
                            Equation threeItem = Equation.of(anInt, operator, twoItem);
                            if (null != threeItem) {
                                List<Equation> list = result.computeIfAbsent(threeItem.getResult(), k -> new ArrayList<>());
                                list.add(threeItem);
                            }
                            // 2 + 1
                            threeItem = Equation.of(twoItem, operator, anInt);
                            if (null != threeItem) {
                                List<Equation> list = result.computeIfAbsent(threeItem.getResult(), k -> new ArrayList<>());
                                list.add(threeItem);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}

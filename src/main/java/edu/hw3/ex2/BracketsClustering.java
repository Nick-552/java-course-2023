package edu.hw3.ex2;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class BracketsClustering {

    private BracketsClustering() {}

    public static String[] clusterBrackets(@NotNull String expr) {
        if (!hasOnlyRoundBrackets(expr)) {
            throw new IllegalArgumentException("String contains wrong symbols (should contain only round brackets)");
        }
        if (!isBalanced(expr)) {
            throw new IllegalArgumentException("Brackets not balanced");
        }
        return doClustering(expr);
    }

    private static String[] doClustering(String expr) {
        int brackets = 0;
        int beginIndex = 0;
        List<String> clusters = new ArrayList<>();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                brackets++;
            } else if (expr.charAt(i) == ')') {
                brackets--;
            }
            if (brackets == 0) {
                clusters.add(expr.substring(beginIndex, i + 1));
                beginIndex = i + 1;
            }
        }
        return clusters.toArray(new String[]{});
    }

    private static boolean isBalanced(String expr) {
        int openingBrackets = 0;
        int closingBrackets = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                openingBrackets++;
            } else {
                closingBrackets++;
            }
            if (closingBrackets > openingBrackets) {
                return false;
            }
        }
        return (openingBrackets == closingBrackets);
    }

    private static boolean hasOnlyRoundBrackets(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) != '(' && expr.charAt(i) != ')') {
                return false;
            }
        }
        return true;
    }
}

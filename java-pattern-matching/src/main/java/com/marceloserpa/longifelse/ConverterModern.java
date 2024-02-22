package com.marceloserpa.longifelse;

public class ConverterModern {

    public static String getValueText(Object value) {
        final String newExpression = switch (value) {
            case String string -> '"' + string + '"';
            case Character c -> '\'' + value.toString() + '\'';
            case Long l -> value.toString() + 'L';
            case Double v -> getNewExpression(v);
            case Float v -> getNewExpression(v);
            case null -> "null";
            default -> String.valueOf(value);
        };
        return newExpression;
    }

    private static String getNewExpression(Float value) {
        final String newExpression;
        final float v = value;
        if (Float.isNaN(v)) {
            newExpression = "java.lang.Float.NaN";
        }
        else if (Float.isInfinite(v)) {
            if (v > 0.0F) {
                newExpression = "java.lang.Float.POSITIVE_INFINITY";
            }
            else {
                newExpression = "java.lang.Float.NEGATIVE_INFINITY";
            }
        }
        else {
            newExpression = Float.toString(v) + 'f';
        }
        return newExpression;
    }

    private static String getNewExpression(Double value) {
        final String newExpression;
        final double v = value;
        if (Double.isNaN(v)) {
            newExpression = "java.lang.Double.NaN";
        }
        else if (Double.isInfinite(v)) {
            if (v > 0.0) {
                newExpression = "java.lang.Double.POSITIVE_INFINITY";
            }
            else {
                newExpression = "java.lang.Double.NEGATIVE_INFINITY";
            }
        }
        else {
            newExpression = Double.toString(v);
        }
        return newExpression;
    }
}

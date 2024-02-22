package com.marceloserpa.longifelse;

public class ConverterLegacy {

    public static String getValueText(Object value) {
        final String newExpression;
        if (value instanceof String) {
            final String string = (String)value;
            newExpression = '"' + string + '"';
        }
        else if (value instanceof Character) {
            newExpression = '\'' + value.toString() + '\'';
        }
        else if (value instanceof Long) {
            newExpression = value.toString() + 'L';
        }
        else if (value instanceof Double) {
            final double v = (Double)value;
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
        }
        else if (value instanceof Float) {
            final float v = (Float) value;
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
        }
        else if (value == null) {
            newExpression = "null";
        }
        else {
            newExpression = String.valueOf(value);
        }
        return newExpression;
    }
}

package me.ANONIMUS.jvm.utils;

import me.ANONIMUS.jvm.interfaces.JVMOpcodes;

import org.apache.commons.lang3.math.NumberUtils;

public class JVMUtils implements JVMOpcodes {
    public static final int FALSE = 0, TRUE = 1;

    public static Number toNumber(Object object) {
        String s = toString(object);

        if (NumberUtils.isCreatable(s)) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return Float.parseFloat(s);
            }
        }

        return (Number) object;
    }

    public static String toString(Object object) {
        if (object instanceof String) {
            return (String) object;
        }

        return object.toString();
    }

    public static Object doMath(Number second, Number first, int operator) {
        String numberType = first.getClass().getSimpleName();

        Error mathError = new Error("Invalid math operator (" + operator + ") to used number [" + numberType + "]");

        switch (operator) {
            case LT: {
                switch (numberType) {
                    case "Long": { return (first.longValue() < 0) ? TRUE : FALSE; }
                    case "Short": { return (first.shortValue() < 0) ? TRUE : FALSE; }
                    case "Float": { return (first.floatValue() < 0) ? TRUE : FALSE; }
                    case "Double": { return (first.doubleValue() < 0) ? TRUE : FALSE; }
                    case "Byte": { return (first.byteValue() < 0) ? TRUE : FALSE; }
                    case "Integer": { return (first.intValue() < 0) ? TRUE : FALSE; }
                    default: return FALSE;
                }
            }
            case EQ: {
                switch (numberType) {
                    case "Long": { return (first.longValue() == 0) ? TRUE : FALSE; }
                    case "Short": { return (first.shortValue() == 0) ? TRUE : FALSE; }
                    case "Float": { return (first.floatValue() == 0) ? TRUE : FALSE; }
                    case "Double": { return (first.doubleValue() == 0) ? TRUE : FALSE; }
                    case "Byte": { return (first.byteValue() == 0) ? TRUE : FALSE; }
                    case "Integer": { return (first.intValue() == 0) ? TRUE : FALSE; }
                    default: return FALSE;
                }
            }

            case ADD:
                switch (numberType) {
                    case "Long": { return first.longValue() + second.longValue(); }
                    case "Short": { return first.shortValue() + second.shortValue(); }
                    case "Float": { return first.floatValue() + second.floatValue(); }
                    case "Double": { return first.doubleValue() + second.doubleValue(); }
                    case "Byte": { return first.byteValue() + second.byteValue(); }
                    default: return first.intValue() + second.intValue();
                }
            case SUB:
                switch (numberType) {
                    case "Long": { return first.longValue() - second.longValue(); }
                    case "Short": { return first.shortValue() - second.shortValue(); }
                    case "Float": { return first.floatValue() - second.floatValue(); }
                    case "Double": { return first.doubleValue() - second.doubleValue(); }
                    case "Byte": { return first.byteValue() - second.byteValue(); }
                    default: return first.intValue() - second.intValue();
                }
            case MUL:
                switch (numberType) {
                    case "Long": { return first.longValue() * second.longValue(); }
                    case "Short": { return first.shortValue() * second.shortValue(); }
                    case "Float": { return first.floatValue() * second.floatValue(); }
                    case "Double": { return first.doubleValue() * second.doubleValue(); }
                    case "Byte": { return first.byteValue() * second.byteValue(); }
                    default: return first.intValue() * second.intValue();
                }
            case DIV:
                switch (numberType) {
                    case "Long": { return first.longValue() / second.longValue(); }
                    case "Short": { return first.shortValue() / second.shortValue(); }
                    case "Float": { return first.floatValue() / second.floatValue(); }
                    case "Double": { return first.doubleValue() / second.doubleValue(); }
                    case "Byte": { return first.byteValue() / second.byteValue(); }
                    default: return first.intValue() / second.intValue();
                }
            case REM:
                switch (numberType) {
                    case "Long": { return first.longValue() % second.longValue(); }
                    case "Short": { return first.shortValue() % second.shortValue(); }
                    case "Float": { return first.floatValue() % second.floatValue(); }
                    case "Double": { return first.doubleValue() % second.doubleValue(); }
                    case "Byte": { return first.byteValue() % second.byteValue(); }
                    default: return first.intValue() % second.intValue();
                }
            case XOR:
                switch (numberType) {
                    case "Long": { return first.longValue() ^ second.longValue(); }
                    case "Short": { return first.shortValue() ^ second.shortValue(); }
                    case "Float":
                    case "Double": { throw mathError; }
                    case "Byte": { return first.byteValue() ^ second.byteValue(); }
                    default: return first.intValue() ^ second.intValue();
                }
            case OR:
                switch (numberType) {
                    case "Long": { return first.longValue() | second.longValue(); }
                    case "Short": { return first.shortValue() | second.shortValue(); }
                    case "Float":
                    case "Double": { throw mathError; }
                    case "Byte": { return first.byteValue() | second.byteValue(); }
                    default: return first.intValue() | second.intValue();
                }
            case NEGATIVE:
                switch (numberType) {
                    case "Long": { return -first.longValue(); }
                    case "Short": { return -first.shortValue(); }
                    case "Float": { return -first.floatValue(); }
                    case "Double": { return -first.doubleValue(); }
                    case "Byte": { return -first.byteValue(); }
                    default: return -first.intValue();
                }
        }

        return 0;
    }
}
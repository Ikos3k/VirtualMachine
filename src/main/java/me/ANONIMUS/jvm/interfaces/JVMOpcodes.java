package me.ANONIMUS.jvm.interfaces;

public interface JVMOpcodes {
    byte IGNORE = -1;

    byte CLEAR_CACHE = 0;
    byte ADD = 1;
    byte SUB = 2;
    byte MUL = 3;
    byte DIV = 4;
    byte REM = 5;
    byte XOR = 6;
    byte OR = 7;
    byte LT = 8;
    byte EQ = 9;
    byte LOAD = 13;
    byte STORE = 14;
    byte RETURN = 15;
    byte NEGATIVE = 16;
    byte LDC = 18;

    //native methods
    byte PRINT = 19;
    byte LENGTH = 20;
    byte TO_LOWERCASE = 21;
    byte TO_UPPERCASE = 22;
    byte STR_REVERSE = 23;
    byte TO_STRING = 24;
    byte TO_NUMBER = 25;
    byte HASHCODE = 26;
}
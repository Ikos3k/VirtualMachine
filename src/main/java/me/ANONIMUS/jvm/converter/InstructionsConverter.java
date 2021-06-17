package me.ANONIMUS.jvm.converter;

import me.ANONIMUS.jvm.interfaces.JVMOpcodes;

import org.objectweb.asm.Opcodes;

import java.util.Arrays;

public class InstructionsConverter implements Opcodes {
    public enum instructions {
        ADD(JVMOpcodes.ADD, IADD, LADD, FADD, DADD),
        SUB(JVMOpcodes.SUB, ISUB, LSUB, FSUB, DSUB),
        DIV(JVMOpcodes.DIV, IDIV, LDIV, FDIV, DDIV),
        MUL(JVMOpcodes.MUL, IMUL, LMUL, FMUL, DMUL),
        REM(JVMOpcodes.REM, IREM, LREM, FREM, DREM),
        XOR(JVMOpcodes.XOR, IXOR, LXOR),
        OR(JVMOpcodes.OR, IOR, LXOR),
        NEGATIVE(JVMOpcodes.NEGATIVE, INEG, LNEG, FNEG, DNEG),

        RETURN(JVMOpcodes.RETURN, IRETURN, LRETURN, FRETURN, DRETURN, ARETURN),
        STORE(JVMOpcodes.STORE, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE),
        LOAD(JVMOpcodes.LOAD, ILOAD, LLOAD, FLOAD, DLOAD, ALOAD),
        LDC(JVMOpcodes.LDC, Opcodes.LDC),
        EQ(JVMOpcodes.EQ, IFEQ),
        LT(JVMOpcodes.LT, IFLT);

        public final int JVMOpcode;
        private final int[] opcodes;

        instructions(int JVMOpcode, int... opcodes) {
            this.JVMOpcode = JVMOpcode;
            this.opcodes = opcodes;
        }

        public boolean hasOpcode(int opcode) {
            for (int o : this.opcodes) {
                if (o == opcode) {
                    return true;
                }
            }
            return false;
        }
    }

    public static int convertFromASM(int opcode) {
        return Arrays.stream(instructions.values()).filter(a -> a.hasOpcode(opcode)).findFirst().get().JVMOpcode;
    }
}
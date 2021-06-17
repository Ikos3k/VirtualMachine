package me.ANONIMUS.jvm.java;

import lombok.Data;
import me.ANONIMUS.jvm.utils.JVMUtils;
import org.objectweb.asm.tree.AbstractInsnNode;

@Data
public class Method {
    public final Object[] locals = new Object[JVMUtils.maxLocalsSize];
    private Object[] stack = new Object[JVMUtils.maxStackSize];

    public final String name;
    public final String desc;
    public final AbstractInsnNode[] instructions;

    public int instructionIndex = 0;
    public int stackIndex = -1;

    public void clearStack() {
        this.stack = new Object[JVMUtils.maxStackSize];
    }

    public void push(Object o) {
        this.stack[++stackIndex] = o;
    }

    public Object pop() {
        return this.stack[stackIndex--];
    }

    public Object peek() {
        return this.stack[stackIndex - 1];
    }

    public void set(int index, Object o) {
        this.stack[index] = o;
    }

    public Object get(int index) {
        return this.stack[index];
    }

    public void store(int index, Object o) {
        this.locals[index] = o;
    }

    public int getStackIndex(Object o) {
        for (int i = 0; i < this.stack.length; i++) {
            if (this.stack[i] != null && this.stack[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int getLocalIndex(Object o) {
        for (int i = 0; i < this.locals.length; i++) {
            if (this.locals[i] != null && this.locals[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
}
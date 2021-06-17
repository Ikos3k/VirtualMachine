package me.ANONIMUS.jvm.objects;

import lombok.Data;

import org.objectweb.asm.tree.AbstractInsnNode;

import java.util.Stack;

@Data
public class Method {
    public Stack<Object> stack = new Stack<>();
    public Stack<Object> locals = new Stack<>();

    public final String name;
    public final String desc;
    public final AbstractInsnNode[] instructions;

    public void clearStack() {
        this.stack = new Stack<>();
    }

    public void storeLocal(int index, Object o) {
        this.locals.add(index, o);
    }
}
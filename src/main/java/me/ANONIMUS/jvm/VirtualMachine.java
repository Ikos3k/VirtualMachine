package me.ANONIMUS.jvm;

import me.ANONIMUS.jvm.interfaces.JVMOpcodes;
import me.ANONIMUS.jvm.java.Method;
import me.ANONIMUS.jvm.converter.InstructionsConverter;
import me.ANONIMUS.jvm.utils.JVMUtils;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.lang.reflect.Array;

public class VirtualMachine implements JVMOpcodes {
    private final boolean convert;
    private final Method method;

    public VirtualMachine(MethodNode methodNode, boolean convert) {
        this.method = new Method(methodNode.name, methodNode.desc, methodNode.instructions.toArray());

        this.convert = convert;
    }

    public Object execute() {
        while (method.instructionIndex != method.instructions.length) {
            AbstractInsnNode currentNode = method.instructions[method.instructionIndex];

            int opcode = currentNode.getOpcode();

            if (convert) {
                opcode = InstructionsConverter.convertFromASM(opcode);
            }

            switch (opcode) {
                case LDC:
                    method.push(((LdcInsnNode) currentNode).cst);
                    break;
                case STORE:
                    method.store(((VarInsnNode) currentNode).var, method.pop());
                    break;
                case LOAD:
                    method.push(method.getLocals()[((VarInsnNode) currentNode).var]);
                    break;
                case RETURN:
                    return method.pop();
                case STR_REVERSE:
                    method.push(new StringBuilder(JVMUtils.toString(method.pop())).reverse().toString());
                    break;
                case NEGATIVE:
                case LT:
                case EQ:
                    method.push(JVMUtils.doMath(null, (Number) method.pop(), opcode));
                    break;

                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case XOR:
                case OR:
                case REM:
                    method.push(JVMUtils.doMath((Number) method.pop(), (Number) method.pop(), opcode));
                    break;

                case PRINT:
                    try {
                        Object toPrint = method.pop();

                        System.out.println("[OBJECT_TYPE: " + toPrint.getClass().getSimpleName() + "] " + toPrint);
                    } catch (Exception e) {
                        System.out.println("No context to print! :D");
                        method.stackIndex++;
                    }
                    break;
                case LENGTH:
                    Object obj2 = method.pop();

                    if (obj2.getClass().getSimpleName().contains("[]")) {
                        method.push(Array.getLength(obj2));
                        break;
                    }

                    method.push(JVMUtils.toString(obj2).length());
                    break;
                case TO_UPPERCASE: {
                    method.push(JVMUtils.toString(method.pop()).toUpperCase());
                    break;
                }
                case TO_LOWERCASE: {
                    method.push(JVMUtils.toString(method.pop()).toLowerCase());
                    break;
                }
                case HASHCODE: {
                    method.push(method.pop().hashCode());
                    break;
                }
                case TO_STRING: {
                    method.push(JVMUtils.toString(method.pop()));
                    break;
                }
                case TO_NUMBER: {
                    method.push(JVMUtils.toNumber(method.pop()));
                    break;

                }
                case CLEAR_CACHE:
                    method.clearStack();
                    break;

                case IGNORE:
                    break;

                default:
                    throw new Error("Invalid Opcode: " + opcode + " convert: " + convert);
            }
            method.instructionIndex++;
        }
        return null;
    }
}
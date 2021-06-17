package me.ANONIMUS.jvm;

import me.ANONIMUS.jvm.converter.InstructionsConverter;
import me.ANONIMUS.jvm.interfaces.JVMOpcodes;
import me.ANONIMUS.jvm.objects.Method;
import me.ANONIMUS.jvm.utils.JVMUtils;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.lang.reflect.Array;

public class VirtualMachine implements JVMOpcodes {
    private final boolean convert;
    private final Method method;

    public int instructionIndex = 0;

    public VirtualMachine(MethodNode methodNode, boolean convert) {
        this.method = new Method(methodNode.name, methodNode.desc, methodNode.instructions.toArray());

        this.convert = convert;
    }

    public Object execute() {
        while (instructionIndex != method.instructions.length) {
            AbstractInsnNode currentNode = method.instructions[instructionIndex];

            int opcode = currentNode.getOpcode();

            if (convert) {
                opcode = InstructionsConverter.convertFromASM(opcode);
            }

            switch (opcode) {
                case LDC:
                    method.stack.push(((LdcInsnNode) currentNode).cst);
                    break;
                case STORE:
                    method.storeLocal(((VarInsnNode) currentNode).var, method.stack.pop());
                    break;
                case LOAD:
                    method.stack.push(method.locals.get(((VarInsnNode) currentNode).var));
                    break;
                case RETURN:
                    return method.stack.pop();
                case NEGATIVE:
                case LT:
                case EQ:
                    method.stack.push(JVMUtils.doMath(null, (Number) method.stack.pop(), opcode));
                    break;
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case XOR:
                case OR:
                case REM:
                    method.stack.push(JVMUtils.doMath((Number) method.stack.pop(), (Number) method.stack.pop(), opcode));
                    break;
                case PRINT:
                    try {
                        Object toPrint = method.stack.peek();

                        System.out.println("[OBJECT_TYPE: " + toPrint.getClass().getSimpleName() + "] " + toPrint);
                    } catch (Exception e) {
                        System.out.println("No context to print! :D");
                    }
                    break;
                case STR_REVERSE:
                    method.stack.push(new StringBuilder(JVMUtils.toString(method.stack.pop())).reverse().toString());
                    break;
                case LENGTH:
                    Object obj2 = method.stack.pop();

                    if (obj2.getClass().getSimpleName().contains("[]")) {
                        method.stack.push(Array.getLength(obj2));
                        break;
                    }

                    method.stack.push(JVMUtils.toString(obj2).length());
                    break;
                case TO_UPPERCASE: {
                    method.stack.push(JVMUtils.toString(method.stack.pop()).toUpperCase());
                    break;
                }
                case TO_LOWERCASE: {
                    method.stack.push(JVMUtils.toString(method.stack.pop()).toLowerCase());
                    break;
                }
                case HASHCODE: {
                    method.stack.push(method.stack.pop().hashCode());
                    break;
                }
                case TO_STRING: {
                    method.stack.push(JVMUtils.toString(method.stack.pop()));
                    break;
                }
                case TO_NUMBER: {
                    method.stack.push(JVMUtils.toNumber(method.stack.pop()));
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
            instructionIndex++;
        }
        return null;
    }
}
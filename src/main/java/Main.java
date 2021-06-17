import me.ANONIMUS.jvm.VirtualMachine;
import me.ANONIMUS.jvm.interfaces.JVMOpcodes;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

public class Main implements Opcodes {
    public static void main(String[] args) {
        VirtualMachine vm = new VirtualMachine(method2(), false);
        Object returnValue = vm.execute();

        if(returnValue != null) {
            System.out.println("[OBJECT_TYPE: " + returnValue.getClass().getSimpleName() + "] returnValue: " + returnValue);
        }
    }

    private static MethodNode method() {
        final MethodNode methodNode = new MethodNode(ACC_PROTECTED, "test", "()Ljava/lang/String;", null, null);

        methodNode.visitCode();
        methodNode.visitLdcInsn("github: https://github.com/Ikos3k");
        methodNode.visitVarInsn(ASTORE, 0);
        methodNode.visitLdcInsn("xd");
        methodNode.visitInsn(ARETURN);
        methodNode.visitEnd();

        return methodNode;
    }

    private static MethodNode method2() {
        final MethodNode methodNode = new MethodNode(ACC_PRIVATE, "test2", "()Ljava/lang/Integer;", null, null);

        methodNode.visitCode();
        methodNode.visitLdcInsn("test");
        methodNode.visitInsn(JVMOpcodes.LENGTH);
        methodNode.visitLdcInsn(5);
        methodNode.visitInsn(JVMOpcodes.ADD);
        methodNode.visitLdcInsn(1D);
        methodNode.visitInsn(JVMOpcodes.SUB);
        methodNode.visitVarInsn(JVMOpcodes.STORE, 0);
        methodNode.visitLdcInsn(5.0);
        methodNode.visitInsn(JVMOpcodes.PRINT);
        methodNode.visitVarInsn(JVMOpcodes.LOAD, 0);
        methodNode.visitInsn(JVMOpcodes.PRINT);
        methodNode.visitLdcInsn(5);
        methodNode.visitInsn(JVMOpcodes.RETURN);
        methodNode.visitEnd();

        return methodNode;
    }
}
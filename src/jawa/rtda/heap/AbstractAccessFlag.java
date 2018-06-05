package jawa.rtda.heap;

import static jawa.rtda.heap.ACCESS_FLAG.*;

public abstract class AbstractAccessFlag extends XObject implements AccessFlag {
    protected int accessFlags;

    public boolean isPublic() {
        return 0 != (accessFlags & ACC_PUBLIC.getValue());
    }

    public boolean isProtected() {
        return 0 != (accessFlags & ACC_PROTECTED.getValue());
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & ACC_ABSTRACT.getValue());
    }

    public boolean isFinal() {
        return 0 != (accessFlags & ACC_FINAL.getValue());
    }

    public boolean isSuper() {
        return 0 != (accessFlags & ACC_SUPER.getValue());
    }

    public boolean isInterface() {
        return 0 != (accessFlags & ACC_INTERFACE.getValue());
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & ACC_SYNCHRONIZED.getValue());
    }

    public boolean isAnnotation() {
        return 0 != (accessFlags & ACC_ANNOTATION.getValue());
    }

    public boolean isEnum() {
        return 0 != (accessFlags & ACC_ENUM.getValue());
    }

    public boolean isNative() {
        return 0 != (accessFlags & ACC_NATIVE.getValue());
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & ACC_PRIVATE.getValue());
    }

    public boolean isStatic() {
        return 0 != (accessFlags & ACC_STATIC.getValue());
    }

    public boolean isVolatile() {
        return 0 != (accessFlags & ACC_VOLATILE.getValue());
    }

    public boolean isTransient() {
        return 0 != (accessFlags & ACC_TRANSIENT.getValue());
    }

}

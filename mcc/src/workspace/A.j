.bytecode 50.0
.source factorial.int
.class public utg/mcc/bytecode/A
.super java/lang/Object


.method public <init>()V
	.limit locals 1
	.limit stack 1
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static f(I)I
			; start if
			; if condition
	iload 0		; load n
	ldc 0
	isub
	ifgt L2
	goto L1
			; true
L1:
			; start return
	ldc 1
	goto L0
			; end return
	goto L3
			; false
L2:
			; start return
	iload 0		; load n
	iload 0		; load n
	ldc 1
	isub
	invokestatic utg/mcc/bytecode/A/f(I)I
	imul
	goto L0
			; end return
			; end if
L3:
L0:
	ireturn
.limit locals 1
.limit stack 4
.end method

.method public static exec()V
			; start print
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc 6
	invokestatic utg/mcc/bytecode/A/f(I)I
	invokevirtual java/io/PrintStream/println(I)V
			; end print
	return
	.limit locals 0
	.limit stack 2
.end method

.bytecode 50.0
.source sub.fw
.class public sub
.super java/lang/Object

.method public <init>()V
.limit locals 1
.limit stack 1
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public static main([Ljava/lang/String;)V
	getstatic java/lang/System.out Ljava/io/PrintStream;
	aload 0
	ldc 0
	aaload
	invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I
	aload 0
	ldc 1
	aaload
	invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I
	invokestatic sub.exec(II)I
	invokevirtual java/io/PrintStream.println(I)V
	return
.limit locals 1
.limit stack 4
.end method

.method public static exec(II)I
	iload 0
	iload 1
	isub
	dup
	ifge L1
	pop
	ldc 0
L1:
	istore 0
	iload 0
	ireturn
.limit locals 2
.limit stack 2
.end method


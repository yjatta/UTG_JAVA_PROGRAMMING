.bytecode 50.0
.source sig.fw
.class public sig
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
	invokestatic sig.exec(I)I
	invokevirtual java/io/PrintStream.println(I)V
	return
.limit locals 1
.limit stack 3
.end method

.method public static exec(I)I
	iload 0
	ldc 1
	isub
	dup
	ifge L1
	pop
	ldc 0
L1:
	istore 1
	iload 0
	iload 1
	isub
	dup
	ifge L2
	pop
	ldc 0
L2:
	istore 0
	iload 0
	ireturn
.limit locals 2
.limit stack 2
.end method


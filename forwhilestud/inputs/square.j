.bytecode 50.0
.source square.fw
.class public square
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
	invokestatic square.exec(I)I
	invokevirtual java/io/PrintStream.println(I)V
	return
.limit locals 1
.limit stack 3
.end method

.method public static exec(I)I
	iload 0
	iload 0
	invokestatic mul.exec(II)I
	istore 0
	iload 0
	ireturn
.limit locals 1
.limit stack 2
.end method


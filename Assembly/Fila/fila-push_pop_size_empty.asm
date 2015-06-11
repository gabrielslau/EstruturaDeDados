# 
# Objetivo: implementar os conceitos  de fila (FIFO) (push, pop, size, isEmpty, tail, head, flush)
# 	    e utilizar em um programa para armazenar valores na pilha
# 
# a * b + c
# 
.text
main: 

jal Fila # inicdiializa o endereço de memória da pilha

addi $a0, $zero, 3
jal Push

addi $a0, $zero, 2
jal Push

# jal Pop # elimina o ultimo valor da pilha e exibe o valor

addi $a0, $zero, 5
jal Push

addi $a0, $zero, 10
jal Push

jal Head

jal Size
# jal Tail
#jal isEmpty

jal Pop
jal Pop
jal Size
jal Head
jal Pop
jal Pop

j Fim # interrompe o fluxo (fim do código)

# inicio dos blocos de "funcoes"

Fila: 
	addi $t9, $0, 0x10040000 # tail da fila
	addi $sp, $0, 0x10040000 # head da fila
	addi $s0, $0, 0 # inicia o contador de elementos
	jr $ra

Push: 	
	sw $a0, ($sp)
	addi $sp, $sp, 4 # avança a posição do ponteiro
	addi $s0, $s0, 1 # incrementa o contador
	jr $ra

Pop: 
	lw $v0, 0($t9)
	addi $t9, $t9, 4
	subi $s0, $s0, 1 # decrementa o contador
	sw $zero, -4($t9) # elimina da memoria heap
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra

Size: 
	sub $t0, $sp, $t9
	div $v0, $t0, 4
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra
	
isEmpty:
	bgt $s0, $zero, PrintEmpty
PrintEmpty :
	addi $v0, $zero, $zero
	li $v0, 1
	syscall
	jr $ra
PrintNotEmpty:
	addi $v0, $zero, 1
	li $v0, 1
	syscall
	jr $ra
	
Head: 
	lw $v0, 0($t9)
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra

Fim: 	nop

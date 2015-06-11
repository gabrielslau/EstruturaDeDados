# 
# Objetivo: implementar os conceitos  de pilha (push, pop e size)
# 	    e utilizar em um programa para armazenar valores na pilha
# 
# a * b + c
# 
.text
main: 

jal Pilha # inicdiializa o endereço de memória da pilha

addi $a0, $zero, 3
jal Push

addi $a0, $zero, 2
jal Push

# jal Pop # elimina o ultimo valor da pilha e exibe o valor

addi $a0, $zero, 5
jal Push

addi $a0, $zero, 10
jal Push

jal Size
jal Head
# jal Tail
jal isEmpty

jal Pop
jal Pop
jal Pop

j Fim # interrompe o fluxo (fim do código)

# inicio dos blocos de "funcoes"

Pilha: 
	addi $sp, $0, 0x10040000
	jr $ra

Push: 
	sw $a0, ($sp)
	addi $sp, $sp, 4
	jr $ra

Pop: 
	lw $v0, -4($sp)
	sub $sp, $sp, 4
	sw $zero, ($sp) # elimina da memoria heap
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra

Size: 
	sub $t0, $sp, 0x10040000
	div $v0, $t0, 4
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra
	
isEmpty: 
	sub $t0, $sp, 0x10040000
	div $v0, $t0, 4
	andi $t1, $v0, 0x10040000
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra
	
Head: 
	lw $v0, -4($sp)
	
	# exibe o valor no log
	move $a0, $v0
	li $v0, 1
	syscall
	jr $ra

Fim: 	nop

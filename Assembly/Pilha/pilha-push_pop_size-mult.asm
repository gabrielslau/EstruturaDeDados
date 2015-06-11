# 
# Objetivo: implementar os conceitos  de pilha (push, pop e size)
# 	    e utilizar em um programa para armazenar valores na pilha
# 
# a * b + c
# 
.text
main: 

jal Pilha # inicializa o endereço de memória da pilha

# a
addi $t0, $0, 2
# b
addi $t1, $0, 4
# c
addi $t2, $0, 6

# a * b
mul $a0, $t0, $t1
jal Push

add $a0, $a0, $t2
jal Push

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

Fim: 	nop

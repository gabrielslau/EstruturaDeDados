# 
# Objetivo: implementar os conceitos  de deck (pushTail, pushHead, popTail, popHead, size, isEmpty, tail, head, flush)
# 
.text
main: 

jal Deck # inicdiializa o endereço de memória da pilha

addi $a0, $zero, 3
jal PushHead

addi $a0, $zero, 2
jal PushHead

addi $a0, $zero, 9
jal PushTail

# jal Pop # elimina o ultimo valor da pilha e exibe o valor




j Fim # interrompe o fluxo (fim do código)

# inicio dos blocos de "funcoes"

Deck: 
	addi $t8, $0, 0x10040000 # inicioTail
	addi $t9, $0, 0x10040000 # inicioHead
	
	addi $k0, $0, 0x10040000 # fimTail
	addi $k1, $0, 0x10040000 # fimHead
	
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

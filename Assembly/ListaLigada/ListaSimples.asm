#
# Objetivo: implementar os conceitos  de deck (pushTail, pushHead, popTail, popHead, size, isEmpty, tail, head, flush)
#

.data
menu:   .asciiz "\n 1-PushTail \n 2-PushHead \n 3-PopTail \n 4-PopHead \n 5-GetTail \n 6-GetHead \n 7-GetSecond \n 8-GetSize \n"
insert: .asciiz "Insira um numero: \n"
error:  .asciiz "\nEntrada inválida: \n"
br:     .asciiz "\n"

.text
main:
    # Inicializa as regiões de memória
    addi $s7, $0, 0x10040000 # aponta pro inicio da lista
    addi $sp, $0, 0x10040000 # aponta pro fim da lista
    addi $t0, $0, 0 # inicia o contador de elementos

    # Inicializa as entradas do menu
    addi $t1, $0, 1 #PushTail
    addi $t2, $0, 2 #PushHead
    addi $t3, $0, 3 #PopTail
    addi $t4, $0, 4 #PopHead
    addi $t5, $0, 5 #GetTail
    addi $t6, $0, 6 #GetHead
    addi $t7, $0, 7 #GetSecond
    addi $t8, $0, 8 #GetSize

# $s0 armazena a opção escolhida do menu
escolha:
    li $v0, 4
    la $a0, menu
    syscall

    addi $v0, $0, 5
    syscall #option
    add $s0, $0, $v0

    beq $s0, $t1, PushTail
    beq $s0, $t2, PushHead
    beq $s0, $t3, PopTail
    beq $s0, $t4, PopHead
    beq $s0, $t5, GetTail
    beq $s0, $t6, GetHead
    beq $s0, $t7, GetSecond
    beq $s0, $t8, GetSize
    beq $s0, -1,  Fim

    # exibe mensagem de erro
    li $v0, 4
    la $a0, error
    syscall

    j escolha

# inicio dos blocos de "funcoes"

# Armazena no topo da lista (aponta para nada)
# Se já tiver algum elemento na lista,
# armazena a referência do valor a ser inserido
# no espaço de memória do elemento anterior
PushHead:
    # exibe menu
    li $v0, 4
    la $a0, insert
    syscall
    # insere o elemento no console
    addi  $v0, $0, 5
    syscall
    add   $a1, $0, $v0 # QUEM djabo é $A1???

    # lógica do método
    sw    $v0, 0($sp)
    la    $v0, 0($sp)  # pega o endereço de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posição anterior com o endereço do atual
    add   $sp, $sp, 8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador
    j escolha

# Armazena no início da lista (aponta para o que estava no início)
PushTail:
    # exibe menu
    li $v0, 4
    la $a0, insert
    syscall
    # insere o elemento no console
    addi  $v0, $0, 5
    syscall
    add   $a1, $0, $v0 # QUEM djabo é $A1???

    # lógica do método
    sw    $v0, -8($s7)
    la    $v0, 0($s7)  # pega o endereço de $sp e salva em $v0
    sw    $v0, -4($s7) # configura a posição anterior com o endereço do atual
    add   $s7, $s7, -8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador
    j escolha

PopTail:
    # TODO
    j escolha

PopHead:
    # TODO
    j escolha

# retorna o segundo elemento da lista
GetSecond:
    # $t9 é um registrador temporário e é usado apenas para pegar o endereço da memória
    lw    $t9, 4($s7)  # pega o endereço do próximo elemento após o primeiro da memória
    lw    $v0, 0($t9)  # pega o conteúdo do elemento capturado

    j Print

# retorna o elemendo no topo da lista (sem removê-lo)
GetHead:
    lw    $v0, -8($sp) # 0($sp) armazena o endereço do próximo elemento
    j Print

# retorna o elemendo no início da lista (sem removê-lo)
GetTail:
    lw    $v0, 0($s7)
    j Print

GetSize:
    # TODO
    j escolha

Print:
    # Quebra linha
    li $v0, 4
    la $a0, br
    syscall

    # exibe o valor no log
    move  $a0, $v0
    li    $v0, 1
    syscall
    j escolha

Fim:
    nop

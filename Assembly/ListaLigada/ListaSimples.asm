#
# Objetivo: implementar os conceitos  de deck (pushTail, pushHead, popTail, popHead, size, isEmpty, tail, head, flush)
#

.data
menu:   .asciiz "\n 1-PushTail \n 2-PushHead \n 3-PushOrdenado \n 4-GetTail \n 5-GetHead \n 6-GetSecond \n 7-ShowAll \n Escolha uma op��o: "
insert: .asciiz "\nInsira um numero: "
error:  .asciiz "\nEntrada inv�lida: \n"
br:     .asciiz "\n"

#
# Legenda dos registradores
#

# $t0: Contador de elementos

# $t1: Op��o do menu PushTail
# $t2: Op��o do menu PushHead
# $t3: Op��o do menu PushOrdenado
# $t4: Op��o do menu GetTail
# $t5: Op��o do menu GetHead
# $t6: Op��o do menu GetSecond
# $t7: Op��o do menu ShowAll

# $s0: Armazena a op��o escolhida do menu
# $s1: Armazena o valor digitado

# $s2: Registradores tempor�rios
# $s4: Registradores tempor�rios
# $s5: Registradores tempor�rios
# $s6: Registradores tempor�rios

# $s7: Aponta pro inicio da lista
# $sp: Aponta pro fim da lista

# $v0: Valor de entrada e sa�da (chamadas de fun��es)
# $a0: Chamadas do sistema

.text
main:
    # Inicializa as regi�es de mem�ria
    addi $s7, $0, 0x10040000
    addi $sp, $0, 0x10040000
    addi $t0, $0, 0 # inicia o contador de elementos

    # Inicializa as entradas do menu
    addi $t1, $0, 1 #PushTail
    addi $t2, $0, 2 #PushHead
    addi $t3, $0, 3 #PushOrdenado
    addi $t4, $0, 4 #GetTail
    addi $t5, $0, 5 #GetHead
    addi $t6, $0, 6 #GetSecond
    addi $t7, $0, 7 #ShowAll

escolha:
    li $v0, 4
    la $a0, menu
    syscall

    addi $v0, $0, 5
    syscall #option
    add $s0, $0, $v0

    beq $s0, $t1, PushTail
    beq $s0, $t2, PushHead
    beq $s0, $t3, PushOrdenado
    beq $s0, $t4, GetTail
    beq $s0, $t5, GetHead
    beq $s0, $t6, GetSecond
    beq $s0, $t7, ShowAll
    beq $s0, -1,  Fim

    # exibe mensagem de erro
    li $v0, 4
    la $a0, error
    syscall

    j escolha

# inicio dos blocos de "funcoes"

# Armazena no topo da lista (aponta para nada)
# Se j� tiver algum elemento na lista,
# armazena a refer�ncia do valor a ser inserido
# no espa�o de mem�ria do elemento anterior
PushHead:
    # exibe menu
    li $v0, 4
    la $a0, insert
    syscall
    # insere o elemento no console
    addi  $v0, $0, 5
    syscall

    # l�gica do m�todo
    sw    $v0,  0($sp)

    beqz  $t0, PushHead_Next # s� adiciona a refer�ncia do anterior se houver no m�nimo um registro

    la    $v0,  0($sp) # pega o endere�o de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posi��o anterior com o endere�o do atual

    PushHead_Next:
        add   $sp, $sp, 8  # desloca 2 casas
        addi  $t0, $t0, 1  # contador

    j escolha

# Armazena no in�cio da lista (aponta para o que estava no in�cio)
PushTail:
    # exibe menu
    li $v0, 4
    la $a0, insert
    syscall
    # insere o elemento no console
    addi  $v0, $0, 5
    syscall

    # l�gica do m�todo
    sw    $v0, -8($s7)

    beqz  $t0, PushTail_Next # s� adiciona a refer�ncia do anterior se houver no m�nimo um registro

    la    $v0,  0($s7) # pega o endere�o de $sp e salva em $v0
    sw    $v0, -4($s7) # configura a posi��o anterior com o endere�o do atual

    PushTail_Next:
        add   $s7, $s7, -8 # desloca 2 casas
        addi  $t0, $t0, 1  # contador

    j escolha

PushOrdenado:
    # exibe menu
    li $v0, 4
    la $a0, insert
    syscall
    # insere o elemento no console
    addi  $v0, $0, 5
    syscall

    # l�gica do m�todo
    # insere o novo valor no espa�o dispon�vel da mem�ria
    sw    $v0,  0($sp)
    move  $s1, $v0     # copia o valor de entrada para facilitar na compara��o

    add   $sp, $sp, 8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador

    la    $s6, 0($s7)  # clone do registrador
    PushOrdenado_Loop:
        beqz  $s6, escolha # volta para o menu se o endere�o de mem�ria for zero
        lw    $v0, 0($s6) # guarda o valor do registrador atual no loop

        # if $s1 < $v0, avan�a para o pr�ximo da lista
        blt $s1, $v0, PushOrdenado_Loop_Next

        # pega o pr�ximo elemento para verificar (indicado pelo endere�o de mem�ria)
        lw    $s5, 4($s6)
        beqz  $s5, escolha
        lw    $s2, 0($s5)

        # if $s2 < $s1, avan�a para o pr�ximo da lista
        blt $s2, $s1, PushOrdenado_Loop_Next

        # sen�o, aponta o endere�o do pr�ximo elemento do ultimo elemento inserido na mem�ria
        # para o endere�o apontado no elemento atual
        # e atualiza o pr�ximo elemento do elemento atual para o �ltimo elemento inserido na mem�ria
        # ( bugou ??? ) xD
        #sw $v0, -4($sp)
        lw $v0, 4($s6)
        sw $v0, -4($sp)

        la $s4, -8($sp)
        sw $s4, 4($s6)

        j escolha

        # avan�a para o pr�ximo item da lista (indicado pelo endere�o de mem�ria)
        PushOrdenado_Loop_Next:
            lw    $s6, 4($s6)
        j PushOrdenado_Loop

# retorna o segundo elemento da lista
GetSecond:
    # $s5 � um registrador tempor�rio e � usado apenas para pegar o endere�o da mem�ria
    lw    $s5, 4($s7)  # pega o endere�o do pr�ximo elemento ap�s o primeiro da mem�ria
    lw    $v0, 0($s5)  # pega o conte�do do elemento capturado

    j Print

# retorna o elemendo no topo da lista (sem remov�-lo)
GetHead:
    lw    $v0, -8($sp) # 0($sp) armazena o endere�o do pr�ximo elemento
    j Print

# retorna o elemendo no in�cio da lista (sem remov�-lo)
GetTail:
    lw    $v0, 0($s7)
    j Print

# Percorre toda a lista exibindo os elementos em sequ�ncia
ShowAll:
    la    $s6, 0($s7) # copia o endere�o da mem�ria para um registrador tempor�rio
    ShowAll_Loop:
        beqz   $s6, escolha # volta para o menu se o endere�o de mem�ria for zero
        lw    $v0, 0($s6)

        # avan�a para o pr�ximo elemento da lista (indicado pelo endere�o de mem�ria)
        lw    $s6, 4($s6)

        # exibe o valor no log
        move  $a0, $v0
        li    $v0, 1
        syscall

        j ShowAll_Loop

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

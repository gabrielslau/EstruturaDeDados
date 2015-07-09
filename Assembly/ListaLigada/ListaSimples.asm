#
# Objetivo: implementar os conceitos  de deck (pushTail, pushHead, popTail, popHead, size, isEmpty, tail, head, flush)
#

.data
menu:   .asciiz "\n 1-PushTail \n 2-PushHead \n 3-PushOrdenado \n 4-GetTail \n 5-GetHead \n 6-GetSecond \n 7-ShowAll \n Escolha uma opção: "
insert: .asciiz "\nInsira um numero: "
error:  .asciiz "\nEntrada inválida: \n"
br:     .asciiz "\n"

#
# Legenda dos registradores
#

# $t0: Contador de elementos

# $t1: Opção do menu PushTail
# $t2: Opção do menu PushHead
# $t3: Opção do menu PushOrdenado
# $t4: Opção do menu GetTail
# $t5: Opção do menu GetHead
# $t6: Opção do menu GetSecond
# $t7: Opção do menu ShowAll

# $s0: Armazena a opção escolhida do menu
# $s1: Armazena o valor digitado

# $s2: Registradores temporários
# $s4: Registradores temporários
# $s5: Registradores temporários
# $s6: Registradores temporários

# $s7: Aponta pro inicio da lista
# $sp: Aponta pro fim da lista

# $v0: Valor de entrada e saída (chamadas de funções)
# $a0: Chamadas do sistema

.text
main:
    # Inicializa as regiões de memória
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

    # lógica do método
    sw    $v0,  0($sp)

    beqz  $t0, PushHead_Next # só adiciona a referência do anterior se houver no mínimo um registro

    la    $v0,  0($sp) # pega o endereço de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posição anterior com o endereço do atual

    PushHead_Next:
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

    # lógica do método
    sw    $v0, -8($s7)

    beqz  $t0, PushTail_Next # só adiciona a referência do anterior se houver no mínimo um registro

    la    $v0,  0($s7) # pega o endereço de $sp e salva em $v0
    sw    $v0, -4($s7) # configura a posição anterior com o endereço do atual

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

    # lógica do método
    # insere o novo valor no espaço disponível da memória
    sw    $v0,  0($sp)
    move  $s1, $v0     # copia o valor de entrada para facilitar na comparação

    add   $sp, $sp, 8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador

    la    $s6, 0($s7)  # clone do registrador
    PushOrdenado_Loop:
        beqz  $s6, escolha # volta para o menu se o endereço de memória for zero
        lw    $v0, 0($s6) # guarda o valor do registrador atual no loop

        # if $s1 < $v0, avança para o próximo da lista
        blt $s1, $v0, PushOrdenado_Loop_Next

        # pega o próximo elemento para verificar (indicado pelo endereço de memória)
        lw    $s5, 4($s6)
        beqz  $s5, escolha
        lw    $s2, 0($s5)

        # if $s2 < $s1, avança para o próximo da lista
        blt $s2, $s1, PushOrdenado_Loop_Next

        # senão, aponta o endereço do próximo elemento do ultimo elemento inserido na memória
        # para o endereço apontado no elemento atual
        # e atualiza o próximo elemento do elemento atual para o último elemento inserido na memória
        # ( bugou ??? ) xD
        #sw $v0, -4($sp)
        lw $v0, 4($s6)
        sw $v0, -4($sp)

        la $s4, -8($sp)
        sw $s4, 4($s6)

        j escolha

        # avança para o próximo item da lista (indicado pelo endereço de memória)
        PushOrdenado_Loop_Next:
            lw    $s6, 4($s6)
        j PushOrdenado_Loop

# retorna o segundo elemento da lista
GetSecond:
    # $s5 é um registrador temporário e é usado apenas para pegar o endereço da memória
    lw    $s5, 4($s7)  # pega o endereço do próximo elemento após o primeiro da memória
    lw    $v0, 0($s5)  # pega o conteúdo do elemento capturado

    j Print

# retorna o elemendo no topo da lista (sem removê-lo)
GetHead:
    lw    $v0, -8($sp) # 0($sp) armazena o endereço do próximo elemento
    j Print

# retorna o elemendo no início da lista (sem removê-lo)
GetTail:
    lw    $v0, 0($s7)
    j Print

# Percorre toda a lista exibindo os elementos em sequência
ShowAll:
    la    $s6, 0($s7) # copia o endereço da memória para um registrador temporário
    ShowAll_Loop:
        beqz   $s6, escolha # volta para o menu se o endereço de memória for zero
        lw    $v0, 0($s6)

        # avança para o próximo elemento da lista (indicado pelo endereço de memória)
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

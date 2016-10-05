.data
menu:   .asciiz "\n 1-PushTail \n 2-PushHead \n 3-ShowAll \n 4-ShowAllReverse \n Escolha uma opção: "
insert: .asciiz "\nInsira um numero: "
error:  .asciiz "\nEntrada inválida: \n"

#
# Legenda dos registradores
#

# $t0: Contador de elementos

# $t1: Opção do menu PushTail
# $t2: Opção do menu PushHead
# $t3: Opção do menu ShowAll
# $t4: Opção do menu ShowAllReverse

# $s0: Armazena a opção escolhida do menu

# $s6: Registradores temporários

# $s7: Aponta pro inicio da lista
# $sp: Aponta pro fim da lista

# $v0: Valor de entrada e saída (chamadas de funções)
# $a0: Chamadas do sistema

#
# Ordem de agrupamento dos itens
#
# Valor, EndereçoItemAnterior, EndereçoItemProximo
#

.text
main:
    # Inicializa as regiões de memória
    addi $s7, $0, 0x10040000
    addi $sp, $0, 0x10040000
    addi $t0, $0, 0 # inicia o contador de elementos

    # Inicializa as entradas do menu
    addi $t1, $0, 1 #PushTail
    addi $t2, $0, 2 #PushHead
    addi $t3, $0, 3 #ShowAll
    addi $t4, $0, 4 #ShowAllReverse

escolha:
    li $v0, 4
    la $a0, menu
    syscall

    addi $v0, $0, 5
    syscall #option
    add $s0, $0, $v0

    beq $s0, $t1, PushTail
    beq $s0, $t2, PushHead
    beq $s0, $t3, ShowAll
    beq $s0, $t4, ShowAllReverse
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

    # configura o endereço do próximo do elemento anterior
    la    $v0,  0($sp) # pega o endereço de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posição anterior com o endereço do atual

    # configura o elemento do anterior do elemento atual
    la    $v0,  -12($sp) # pega o endereço de $sp e salva em $v0
    sw    $v0, 4($sp) # configura a posição anterior com o endereço do atual

    PushHead_Next:
        add   $sp, $sp, 12  # desloca 3 casas
        addi  $t0, $t0, 1   # contador

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
    sw    $v0, -12($s7)

    beqz  $t0, PushTail_Next # só adiciona a referência do anterior se houver no mínimo um registro

    # configura o endereço do anterior do próximo elemento
    la    $v0,  -12($s7)
    sw    $v0, 4($s7)

    # configura o endereço do próximo elemento no elemento atual
    la    $v0,  0($s7)
    sw    $v0, -4($s7)

    PushTail_Next:
        add   $s7, $s7, -12 # desloca 3 casas
        addi  $t0, $t0, 1  # contador

    j escolha

# Percorre toda a lista exibindo os elementos em sequência
ShowAll:
    la    $s6, 0($s7) # copia o endereço da memória para um registrador temporário
    ShowAll_Loop:
        beqz   $s6, escolha # volta para o menu se o endereço de memória for zero
        lw    $v0, 0($s6)

        # avança para o próximo elemento da lista (indicado pelo endereço de memória)
        lw    $s6, 8($s6)

        # exibe o valor no log
        move  $a0, $v0
        li    $v0, 1
        syscall

        j ShowAll_Loop

# Percorre toda a lista (de trás pra frente) exibindo os elementos em sequência
ShowAllReverse:
    la    $s6, -12($sp) # copia o endereço da memória para um registrador temporário
    ShowAllReverse_Loop:
        beqz   $s6, escolha # volta para o menu se o endereço de memória for zero
        lw    $v0, 0($s6)

        # avança para o próximo elemento da lista (indicado pelo endereço de memória)
        lw    $s6, 4($s6)

        # exibe o valor no log
        move  $a0, $v0
        li    $v0, 1
        syscall

        j ShowAllReverse_Loop

Fim:
    nop

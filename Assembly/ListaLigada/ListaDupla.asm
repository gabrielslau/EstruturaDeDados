.data
menu:   .asciiz "\n 1-PushTail \n 2-PushHead \n 3-ShowAll \n 4-ShowAllReverse \n Escolha uma op��o: "
insert: .asciiz "\nInsira um numero: "
error:  .asciiz "\nEntrada inv�lida: \n"

#
# Legenda dos registradores
#

# $t0: Contador de elementos

# $t1: Op��o do menu PushTail
# $t2: Op��o do menu PushHead
# $t3: Op��o do menu ShowAll
# $t4: Op��o do menu ShowAllReverse

# $s0: Armazena a op��o escolhida do menu

# $s6: Registradores tempor�rios

# $s7: Aponta pro inicio da lista
# $sp: Aponta pro fim da lista

# $v0: Valor de entrada e sa�da (chamadas de fun��es)
# $a0: Chamadas do sistema

#
# Ordem de agrupamento dos itens
#
# Valor, Endere�oItemAnterior, Endere�oItemProximo
#

.text
main:
    # Inicializa as regi�es de mem�ria
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

    # configura o endere�o do pr�ximo do elemento anterior
    la    $v0,  0($sp) # pega o endere�o de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posi��o anterior com o endere�o do atual

    # configura o elemento do anterior do elemento atual
    la    $v0,  -12($sp) # pega o endere�o de $sp e salva em $v0
    sw    $v0, 4($sp) # configura a posi��o anterior com o endere�o do atual

    PushHead_Next:
        add   $sp, $sp, 12  # desloca 3 casas
        addi  $t0, $t0, 1   # contador

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
    sw    $v0, -12($s7)

    beqz  $t0, PushTail_Next # s� adiciona a refer�ncia do anterior se houver no m�nimo um registro

    # configura o endere�o do anterior do pr�ximo elemento
    la    $v0,  -12($s7)
    sw    $v0, 4($s7)

    # configura o endere�o do pr�ximo elemento no elemento atual
    la    $v0,  0($s7)
    sw    $v0, -4($s7)

    PushTail_Next:
        add   $s7, $s7, -12 # desloca 3 casas
        addi  $t0, $t0, 1  # contador

    j escolha

# Percorre toda a lista exibindo os elementos em sequ�ncia
ShowAll:
    la    $s6, 0($s7) # copia o endere�o da mem�ria para um registrador tempor�rio
    ShowAll_Loop:
        beqz   $s6, escolha # volta para o menu se o endere�o de mem�ria for zero
        lw    $v0, 0($s6)

        # avan�a para o pr�ximo elemento da lista (indicado pelo endere�o de mem�ria)
        lw    $s6, 8($s6)

        # exibe o valor no log
        move  $a0, $v0
        li    $v0, 1
        syscall

        j ShowAll_Loop

# Percorre toda a lista (de tr�s pra frente) exibindo os elementos em sequ�ncia
ShowAllReverse:
    la    $s6, -12($sp) # copia o endere�o da mem�ria para um registrador tempor�rio
    ShowAllReverse_Loop:
        beqz   $s6, escolha # volta para o menu se o endere�o de mem�ria for zero
        lw    $v0, 0($s6)

        # avan�a para o pr�ximo elemento da lista (indicado pelo endere�o de mem�ria)
        lw    $s6, 4($s6)

        # exibe o valor no log
        move  $a0, $v0
        li    $v0, 1
        syscall

        j ShowAllReverse_Loop

Fim:
    nop

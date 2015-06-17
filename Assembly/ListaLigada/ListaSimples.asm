#
# Objetivo: implementar os conceitos  de deck (pushTail, pushHead, popTail, popHead, size, isEmpty, tail, head, flush)

.text
main:
    # Inicializa as regi�es de mem�ria
    addi $s7, $0, 0x10040000 # aponta pro inicio da lista
    addi $sp, $0, 0x10040000 # aponta pro fim da lista
    addi $t0, $0, 0 # inicia o contador de elementos


    # corpo com a��es
    addi $v0, $0, 3
    jal PushHead

    jal GetHead

    addi $v0, $0, 5
    jal PushHead

    addi $v0, $0, 20
    jal PushHead

    jal GetSecond

    addi $v0, $0, 30
    jal PushTail

    jal GetHead
    jal GetTail
    jal GetSecond

    j Fim # interrompe o fluxo (fim do c�digo)

# inicio dos blocos de "funcoes"

# Armazena no topo da lista (aponta para nada)
# Se j� tiver algum elemento na lista,
# armazena a refer�ncia do valor a ser inserido
# no espa�o de mem�ria do elemento anterior
PushHead:
    sw    $v0, 0($sp)
    la    $v0, 0($sp)  # pega o endere�o de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posi��o anterior com o endere�o do atual
    add   $sp, $sp, 8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador
    jr    $ra

# Armazena no in�cio da lista (aponta para o que estava no in�cio)
PushTail:
    sw    $v0, -8($s7)
    la    $v0, 0($s7)  # pega o endere�o de $sp e salva em $v0
    sw    $v0, -4($s7) # configura a posi��o anterior com o endere�o do atual
    add   $s7, $s7, -8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador
    jr    $ra

# retorna o segundo elemento da lista
GetSecond:
    # $t9 � um registrador tempor�rio
    lw    $t9, 4($s7)  # pega o endere�o do pr�ximo elemento ap�s o primeiro da mem�ria
    lw    $v0, 0($t9)  # pega o conte�do do elemento capturado

    # exibe o valor no log
    move  $a0, $v0
    li    $v0, 1
    syscall
    jr    $ra

# retorna o elemendo no topo da lista (sem remov�-lo)
GetHead:
    lw    $v0, -8($sp) # 0($sp) armazena o endere�o do pr�ximo elemento
    # exibe o valor no log
    move  $a0, $v0
    li    $v0, 1
    syscall
    jr    $ra

# retorna o elemendo no in�cio da lista (sem remov�-lo)
GetTail:
    lw    $v0, 0($s7)
    # exibe o valor no log
    move  $a0, $v0
    li    $v0, 1
    syscall
    jr    $ra

Fim:
    nop

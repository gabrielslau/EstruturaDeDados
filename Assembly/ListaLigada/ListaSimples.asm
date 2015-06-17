#
# Objetivo: implementar os conceitos  de deck (pushTail, pushHead, popTail, popHead, size, isEmpty, tail, head, flush)

.text
main:
    # Inicializa as regiões de memória
    addi $s7, $0, 0x10040000 # aponta pro inicio da lista
    addi $sp, $0, 0x10040000 # aponta pro fim da lista
    addi $t0, $0, 0 # inicia o contador de elementos


    # corpo com ações
    addi $v0, $0, 3
    jal PushHead

    addi $v0, $0, 5
    jal PushHead

    addi $v0, $0, 20
    jal PushHead

    jal GetSecond

    j Fim # interrompe o fluxo (fim do código)

# inicio dos blocos de "funcoes"

# Armazena no topo da lista (aponta para nada)
# Se já tiver algum elemento na lista,
# armazena a referência do valor a ser inserido
# no espaço de memória do elemento anterior
PushHead:
    sw    $v0, 0($sp)
    la    $v0, 0($sp)  # pega o endereço de $sp e salva em $v0
    sw    $v0, -4($sp) # configura a posição anterior com o endereço do atual
    add   $sp, $sp, 8  # desloca 2 casas
    addi  $t0, $t0, 1  # contador
    jr $ra

# retorna o segundo elemento da lista
GetSecond:
    # $t9 é um registrador temporário
    lw    $t9, 4($s7)  # pega o endereço do próximo elemento após o primeiro da memória
    lw    $v0, 0($t9)  # pega o conteúdo do elemento capturado

    # exibe o valor no log
    move $a0, $v0
    li $v0, 1
    syscall
    jr $ra
Fim:
    nop

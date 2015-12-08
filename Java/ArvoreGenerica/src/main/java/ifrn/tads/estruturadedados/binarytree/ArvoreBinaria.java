package ifrn.tads.estruturadedados.binarytree;

public class ArvoreBinaria implements IArvoreBinaria {
    INo raiz;

    public ArvoreBinaria(Integer o) {
        raiz = new No(null, o);
    }

    public INo pesquisar(No no, Integer o) {
        return null;
    }

    public INo pesquisar(Integer elemento) {
        // No que vai começar a verificação
        INo noAtual = raiz();

        // procura até achar o elemento
        while (!noAtual.element().equals(elemento)) {
            // Começa a pesquisa pela esquerda
            if (elemento < noAtual.element()) {
                // continua pesquisando pela esquerda
                noAtual = noAtual.leftChild();
            } else {
                // procura pelo lado direito
                noAtual = noAtual.rightChild();
            }

            // Se chegar a este ponto, não encontrou
            if (noAtual == null)
                return null;
        }

        return noAtual;
    }

    public No incluir(Integer o) {
        // No que vai começar a verificação
        INo noAtual = raiz();
        // No que será identificado como pai do nó
        // a ser incluído
        INo pai;

        while (true) {
            pai = noAtual;

            // Verifica se deve inserir à esquerda
            if (o < noAtual.element()) {
                noAtual = noAtual.leftChild();

                // Se não tiver filhos
                if (noAtual == null) {
                    // cria um novo nó à esquerda
                    // do último pai identificado
                    No novoNo = new No(pai, o);
                    pai.leftChild(novoNo);

                    return novoNo;
                }
            } else {
                noAtual = noAtual.rightChild();

                // Se não tiver filhos
                if (noAtual == null) {
                    // cria um novo nó à direita
                    // do último pai identificado
                    No novoNo = new No(pai, o);
                    pai.rightChild(novoNo);

                    return novoNo;
                }
            }
        }
    }

    public boolean remover(Integer elemento) {
        // No que vai começar a verificação
        INo noAtual = raiz();
        // No que será identificado como pai do nó
        // a ser removido
        INo pai = raiz();

        boolean estaNoFilhoEsquerdo = true;

        while (!noAtual.element().equals(elemento)) {
            pai = noAtual;

            // Verifica se deve procurar à esquerda
            if (elemento < noAtual.element()) {
                estaNoFilhoEsquerdo = true;

                // continua procurando à esquerda
                noAtual = noAtual.leftChild();
            } else {
                estaNoFilhoEsquerdo = false;

                // continua procurando à direita
                noAtual = noAtual.rightChild();
            }

            // Se chegou aqui, não encontrou
            if (noAtual == null)
                return false;
        }


        // Se não tiver filhos
        if (noAtual.isLeaf()) {
            if (noAtual == raiz()) {
                raiz = null;
            } else if (estaNoFilhoEsquerdo) {
                pai.leftChild(null);
            } else {
                pai.rightChild(null);
            }
        }
        // se não tiver filhos à direita
        else if (noAtual.rightChild() == null) {
            if (noAtual == raiz()) {
                raiz = noAtual.leftChild();
            } else if (estaNoFilhoEsquerdo) {
                pai.leftChild(noAtual.leftChild());
            } else {
                pai.rightChild(noAtual.leftChild());
            }
        }
        // se não tiver filhos à esquerda
        else if (noAtual.leftChild() == null) {
            if (noAtual == raiz()) {
                raiz = noAtual.rightChild();
            } else if (estaNoFilhoEsquerdo) {
                pai.leftChild(noAtual.rightChild());
            } else {
                pai.rightChild(noAtual.rightChild());
            }
        } else {
            // Só tem dois filhos-folha
            INo replacement = getReplacementNode(noAtual);

            // If the noAtual is root replace root
            // with the replacement
            if (noAtual == raiz()) {
                raiz = replacement;
            } else if (estaNoFilhoEsquerdo) {
                // If the deleted node was a left child
                // make the replacement the left child
                pai.leftChild(replacement);
            } else {
                // Vice versa if it was a right child
                pai.rightChild(replacement);
            }

            replacement.leftChild(noAtual.leftChild());
        }

        return true;
    }

    public INo raiz() {
        return raiz;
    }

    public void setRaiz(No o) {
        if (raiz.childrenNumber() > 0) {
            // faz backup da raiz antiga
            INo raizAntiga = raiz;
            // Adiciona o novo elemento como raiz da árvore
            // e atualiza os filhos
            raiz = new No(null, o.element());
            raiz.leftChild(raizAntiga.leftChild());
            raiz.rightChild(raizAntiga.rightChild());

            // insere o elemento da raiz antiga
            // como um novo nó
            incluir(raizAntiga.element());
        } else {
            raiz = new No(null, o.element());
        }
    }

    public void preOrderTraverseTree(INo focusNode) {
        if (focusNode != null) {

            System.out.println(focusNode);

            preOrderTraverseTree(focusNode.leftChild());
            preOrderTraverseTree(focusNode.rightChild());

        }
    }

    public void postOrderTraverseTree(INo focusNode) {
        if (focusNode != null) {

            postOrderTraverseTree(focusNode.leftChild());
            postOrderTraverseTree(focusNode.rightChild());

            System.out.println(focusNode);
        }
    }

    public INo getReplacementNode(INo replacedNode) {

        INo replacementParent = replacedNode;
        INo replacement = replacedNode;
        INo focusNode = replacedNode.rightChild();

        // While there are no more left children
        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild();
        }

        // If the replacement isn't the right child
        // move the replacement into the parents
        // leftChild slot and move the replaced nodes
        // right child into the replacements rightChild
        if (replacement != replacedNode.rightChild()) {
            replacementParent.leftChild(replacement.rightChild());
            replacement.rightChild(replacedNode.rightChild());
        }

        return replacement;
    }
}

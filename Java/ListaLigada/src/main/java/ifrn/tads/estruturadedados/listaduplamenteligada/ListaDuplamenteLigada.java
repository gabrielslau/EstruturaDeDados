package ifrn.tads.estruturadedados.listaduplamenteligada;

class ListaDuplamenteLigada {
    private Celula primeira;
    private Celula ultima;
    private int totalDeElementos;

    ListaDuplamenteLigada() {
        totalDeElementos = 0;
        primeira = null;
        ultima = null;
    }

    public int size() {
        return totalDeElementos;
    }

    public void increase() {
        this.totalDeElementos++;
    }

    public void decrease() {
        this.totalDeElementos--;
    }

    public void setTotalDeElementos(int totalDeElementos) {
        this.totalDeElementos = totalDeElementos;
    }

    public ListaDuplamenteLigada setPrimeira(Celula primeira) {
        this.primeira = primeira;

        return this;
    }

    public ListaDuplamenteLigada setUltima(Celula ultima) {
        this.ultima = ultima;

        return this;
    }

    public Celula getPrimeira() {
        return primeira;
    }

    public Celula getUltima() {
        return ultima;
    }

    /**
     * Insere um novo elemento no topo da lista
     *
     * @param elemento Objeto a ser inserido na lista
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada inserir(Object elemento) {
        Celula nova = new Celula(elemento);

        if (this.size() == 0) {
            this.setPrimeira(nova);
            this.setUltima(nova);
        } else {
            nova.setAnterior(ultima);
            this.ultima.setProxima(nova);
            this.setUltima(nova);
        }
        this.increase();

        return this;
    }

    /**
     * Insere um elemento em uma dada posição
     *
     * @param elemento Objeto a ser inserido na lista
     * @param posicao  posição para inserir o elemento
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada inserir(Object elemento, int posicao) {
        Celula atual = primeira;
        Celula novaCelula = new Celula(elemento);

        for (int i = 0; i <= this.size(); i++) {
            if (i + 1 == posicao) {
                novaCelula.setAnterior(atual);
                novaCelula.setProxima(atual.getProxima());

                atual.getProxima().setAnterior(novaCelula);
                atual.setProxima(novaCelula);
                this.increase();

                return this;
            }

            atual = atual.getProxima();
        }

        return this;
    }

    /**
     * Insere um elemento na lista em ordem crescente
     *
     * @param elemento elemento a inserir
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada inserirOrdenado(Object elemento) {
        Celula nova = new Celula(elemento);

        if (this.size() == 0) {
            this.setPrimeira(nova);
            this.setUltima(nova);
        } else {
            Celula atual = primeira;

            for (int i = 0; i <= this.size(); i++) {
                if (nova.lessThenOrEqualTo(atual)) {
                    nova.setAnterior(atual.getAnterior());
                    nova.setProxima(atual);

                    if (atual.hasPrevious() && nova.biggerThenOrEqualTo(atual.getAnterior())) {
                        atual.getAnterior().setProxima(nova);
                        atual.setAnterior(nova);

                        break;
                    } else {
                        this.setPrimeira(nova);
                        atual.setAnterior(nova);

                        break;
                    }

                } else if (nova.biggerThenOrEqualTo(atual)) {
                    nova.setAnterior(atual);
                    nova.setProxima(atual.getProxima());

                    if (atual.hasNext() && nova.lessThenOrEqualTo(atual.getProxima())) {
                        atual.getProxima().setAnterior(nova);
                        atual.setProxima(nova);

                        break;
                    } else {
                        this.setUltima(nova);
                        atual.setProxima(nova);

                        break;
                    }
                }

                atual = atual.getProxima();
            }
        }
        this.increase();

        return this;
    }

    /**
     * Remove um elemento da lista
     *
     * @param posicao posição do elemento à ser removido da lista
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada deletar(int posicao) {
        Celula atual = primeira;

        for (int i = 0; i <= this.size(); i++) {
            if (i + 1 == posicao) {
                atual.getProxima().getProxima().setAnterior(atual);
                atual.setProxima(atual.getProxima().getProxima());
                decrease();

                return this;
            }

            atual = atual.getProxima();
        }

        return this;
    }

    /**
     * Junta duas listas em uma só
     *
     * @param lista Lista a ser unida
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada juntar(ListaDuplamenteLigada lista) {
        this.ultima.setProxima(lista.primeira);
        lista.getPrimeira().setAnterior(this.ultima);

        this.ultima = lista.ultima;
        this.totalDeElementos += lista.size();

        lista.primeira = this.primeira;
        lista.totalDeElementos = this.totalDeElementos;

        return this;
    }

    /**
     * Divide uma lista em duas
     *
     * @param posicao posição da lista à separar
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada dividir(int posicao, ListaDuplamenteLigada novaLista) {
        Celula atual = primeira;

        // Percorrendo até o penúltimo elemento.
        for (int i = 0; i <= this.size(); i++) {
            if (i == posicao) {
                atual.getProxima().setAnterior(null);
                novaLista.setPrimeira(atual.getProxima());
                novaLista.setTotalDeElementos(this.size() - (i + 1));
                atual.setProxima(null);

                this.setUltima(atual);
                this.setTotalDeElementos(i + 1);

                return this;
            }

            atual = atual.getProxima();
        }

        return this;
    }

    /**
     * Ordena uma lista com base no conteúdo
     *
     * @return instância da própria lista
     */
    public ListaDuplamenteLigada ordenar() {
        primeira.ordenarCrescente();

        // Corrige apontamento
        if (primeira.hasPrevious()) {
            primeira = primeira.getAnterior();
        }
        if (ultima.hasNext()) {
            ultima = ultima.getProxima();
        }

        /*Celula atual = primeira;

        while (atual != null) {
            atual.ordenarCrescente();

            atual = atual.getProxima();
        }*/

        return this;
    }

    /**
     * @return elementos da lista
     */
    public String toString() {
        // Verificando se a Lista está vazia
        if (this.totalDeElementos == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("");
        try {
            // Percorrendo até o penúltimo elemento.
            Celula atual = primeira;

            while (atual != null) {
                builder.append(atual.getElemento());

                if (atual.hasNext()) {
                    builder.append(", ");
                    atual = atual.getProxima();
                } else {
                    atual = null;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}

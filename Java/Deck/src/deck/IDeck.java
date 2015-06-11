package deck;

public interface IDeck<T> {

    /**
     * Insere um novo elemento no inicio da lista
     *
     * @param item
     */
    public void pushInicio(T item);

    /**
     * Insere um novo elemento no fim da lista
     *
     * @param item
     */
    public void pushFim(T item);

    /**
     * Retorna (e remove) o primeiro objeto inserido no inicio da lista
     *
     * @return
     */
    public T popInicio();

    /**
     * Retorna (e remove) o primeiro objeto inserido no fim da lista
     *
     * @return
     */
    public T popFim();

    /**
     * retorna a quantidade de elementos na fila
     *
     * @return
     */
    public int size();

    /**
     * Verifica se a fila está vazia
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * Retorna o valor no topo da lista (sem removê-lo)
     *
     * @return
     */
    public T inicio();

    /**
     * Retorna o valor no fim da lista (sem removê-lo)
     *
     * @return
     */
    public T fim();

    /**
     * Elimina todos os valores da lista
     */
    public void flush();
}
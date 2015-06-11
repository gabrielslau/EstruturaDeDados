package fila;

public interface Fifo<T> {

    /**
     * Insere um novo elemento na fila
     *
     * @param item
     */
    public void push(T item);

    /**
     * Retorna (e remove) o primeiro objeto inserido na fila
     *
     * @return
     */
    public T pop();

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
     * Retorna o valor no topo da fila (sem removê-lo)
     *
     * @return
     */
    public T head();

    /**
     * Retorna o valor do elemento no fim da fila (sem removê-lo)
     *
     * @return
     */
    public T tail();

    /**
     * Elimina todos os valores da fila
     */
    public void flush();
}
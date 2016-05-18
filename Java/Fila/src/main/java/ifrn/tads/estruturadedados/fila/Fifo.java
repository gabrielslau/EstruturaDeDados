package ifrn.tads.estruturadedados.fila;

public interface Fifo<T> {

    /**
     * Insere um novo elemento na ifrn.tads.estruturadedados.fila
     *
     * @param item
     */
    public void push(T item);

    /**
     * Retorna (e remove) o primeiro objeto inserido na ifrn.tads.estruturadedados.fila
     *
     * @return
     */
    public T pop();

    /**
     * retorna a quantidade de elementos na ifrn.tads.estruturadedados.fila
     *
     * @return
     */
    public Integer size();

    /**
     * Verifica se a ifrn.tads.estruturadedados.fila está vazia
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * Retorna o valor no topo da ifrn.tads.estruturadedados.fila (sem removê-lo)
     *
     * @return
     */
    public T head();

    /**
     * Retorna o valor do elemento no fim da ifrn.tads.estruturadedados.fila (sem removê-lo)
     *
     * @return
     */
    public T tail();

    /**
     * Elimina todos os valores da ifrn.tads.estruturadedados.fila
     */
    public void flush();
}
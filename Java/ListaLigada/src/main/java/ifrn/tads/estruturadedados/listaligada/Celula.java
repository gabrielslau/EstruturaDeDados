package ifrn.tads.estruturadedados.listaligada;

public class Celula {
    private Celula proxima;
    private Object elemento;

    public Celula(Object elemento) {
        this.elemento = elemento;
    }

    public Object getElemento() {
        return elemento;
    }

    public Celula getProxima() {
        return proxima;
    }

    public void setProxima(Celula proxima) {
        this.proxima = proxima;
    }

    public void getProxima(Celula proxima) {
        this.proxima = proxima;
    }

    public String toString() {
        return elemento.toString();
    }
}

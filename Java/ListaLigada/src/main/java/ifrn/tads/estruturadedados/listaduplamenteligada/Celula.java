package ifrn.tads.estruturadedados.listaduplamenteligada;

public class Celula {
    private Celula anterior;
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

    public Celula getAnterior() {
        return anterior;
    }

    public void setAnterior(Celula anterior) {
        this.anterior = anterior;
    }

    public String toString() {
        return elemento.toString();
    }

    public boolean hasNext() {
        return proxima != null;
    }

    public boolean hasPrevious() {
        return anterior != null;
    }

    public boolean lessThen(Celula celula) {
        return (
            this.elemento != null &&
                celula != null &&
                this.elemento.toString().compareTo(celula.getElemento().toString()) < 0
        );
    }

    public boolean biggerThen(Celula celula) {
        return (
            this.elemento != null &&
                celula != null &&
                this.elemento.toString().compareTo(celula.getElemento().toString()) > 0
        );
    }

    public boolean lessThenOrEqualTo(Celula celula) {
        boolean result = false;

        if(this.elemento != null && celula != null){
            int comparator = this.elemento.toString().compareTo(celula.getElemento().toString());

            result = comparator < 0 || comparator == 0;
        }

        return result;
    }


    public boolean biggerThenOrEqualTo(Celula celula) {
        boolean result = false;

        if(this.elemento != null && celula != null){
            int comparator = this.elemento.toString().compareTo(celula.getElemento().toString());

            result = comparator > 0 || comparator == 0;
        }

        return result;
    }


    /**
     * Alterna a posição do elemento atual com o elemento anterior
     */
    private void switchPrevious() {
        if (this.hasNext()) {
            proxima.setAnterior(anterior);
        }

        anterior.setProxima(proxima);
        this.setProxima(anterior);
        this.setAnterior(anterior.getAnterior());
        proxima.setAnterior(this);
    }

    /**
     * Alterna a posição do elemento atual com o próximo elemento
     */
    private void switchNext() {
        if (this.hasPrevious()) {
            anterior.setProxima(proxima);
        }

        proxima.setAnterior(anterior);
        this.setAnterior(proxima);
        this.setProxima(proxima.getProxima());
        anterior.setProxima(this);

//        if(this.elemento.equals("Soldado Invernal")){
//            System.out.println(this + " > " + proxima + " < " + anterior);
//            System.out.println(anterior + " > " + anterior.getProxima() + " < " + anterior.getAnterior());
//        }
    }

    public void ordenarCrescente() {
        //System.out.println("Ordenando: " + this);

        // Verifica se precisa ordenar com o elemento anterior
        if (this.hasPrevious()) {
            System.out.println(this + " < " + anterior + " = " + this.lessThen(anterior));

            if (this.lessThen(anterior)) {
                switchPrevious();

                // Ativa uma reação em cadeia e
                // faz com que as células anteriores se ordenem sozinhas
                //if (this.hasPrevious()) {
                System.out.println("ordenando em cadeia previous");
                this.getAnterior().ordenarCrescente();
                //}
            }
        }

        // Verifica se precisa ordenar com o próximo elemento
        if (this.hasNext()) {
            System.out.println(this + " > " + proxima + " = " + this.biggerThen(proxima));
            if (this.biggerThen(proxima)) {
                switchNext();
            }
        }


        // Ativa uma reação em cadeia e
        // faz com que as próximas células se ordenem sozinhas
        if (this.hasNext()) {
            //System.out.println("ordenando em cadeia next");
            this.getProxima().ordenarCrescente();
        }
    }
}

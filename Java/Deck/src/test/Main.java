package test;

import deck.Deck;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("isEmpty? " + deck.isEmpty());

        deck.pushInicio(3);
        deck.pushFim(5);
        deck.pushInicio(8);
        deck.pushInicio(12);
        deck.pushFim(1);
        
        System.out.println("isEmpty? " + deck.isEmpty());

        System.out.println("Deck: " + deck );
        
        System.out.println( "Inicio: " + deck.inicio() );
        System.out.println( "Fim: " + deck.fim() );
        
        System.out.println( "PopFim: " + deck.popFim() );
        System.out.println( "PopFim: " + deck.popFim() );
        
        System.out.println( "PopInicio: " + deck.popInicio() );
        System.out.println( "PopInicio: " + deck.popInicio() );
        
        System.out.println("Deck: " + deck );
        
        deck.flush();
        
        System.out.println("isEmpty? " + deck.isEmpty());
    }
}

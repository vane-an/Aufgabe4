import java.util.LinkedList;

/**
 * Klasse Produkt ist eine Superklasse für die Klassen Stuhl und Sofa
 *
 * @author Antonia Albani
 * @version 21.11.2021
 */
public class Produkt
{
    /** Variable zustand gibt den Zustand des bestellten Produktes an
    * Mögliche Zustände: 
    * 1: Bestellt
    * 2: In Produktion 
    * 3: Bereit für Auslieferung 
    * 4: Ausgeliefert
    */
    private int zustand;
    private LinkedList<Roboter> produktionsAblauf;
    

    /**
     * Konstruktor
     */
    public Produkt()
    {
        zustand = 1;
        produktionsAblauf = new LinkedList<Roboter>();
        
    }
 
    /**
     * Hier wird für das Produkt festgelegt, in welcher Reihenfolge es von
     * den Robotern bearbeitet wird
     * 
     * @param produktionsAblauf LinkedList<Roboter> die List mit der Reihenfolge der zu druchlaufenden Roboter
     */
    
    public void setzteProduktionsAblauf(LinkedList<Roboter> produktionsAblauf){
        this.produktionsAblauf = produktionsAblauf;
    }
    
     /**
     * Wenn ein Roboter das Produkt bearbeitet hat, wird das Produkt der nächsten Produktionsstation (Roboter) 
     * zugeteilt
     */   
    
    
    public void naechsteProduktionsStation(){
        if(produktionsAblauf.peek() != null){
            Roboter roboter = produktionsAblauf.poll();
            System.out.println("Produkt: Nächste Produktionsstation " + roboter.gibName());
            zustandAendern(2);
            roboter.fuegeProduktHinzu(this);
        }
        else{
            System.out.println("Produkt: " + this +" bereit zur Auslieferung ");
            zustandAendern(3);
        }
    }
 
    

    /**
     * Zustand des Produkts wird geändert
     *
     * @param  zustand : neuer Zustand des Produkt
     */
    public void zustandAendern(int zustand)
    {
        this.zustand = zustand;
        
    }

    
     /**
     * Aktueller Zustand des Produkts wird ausgegeben
     *
     * @return zustand Zustand des Produkt wird ausgegeben
     */   
    
    public int aktuellerZustand()
    {
        return zustand;
        
    }
    
}

import java.util.LinkedList;

/**
 * Roboter verarbeitet die bestellten Produkte
 *
 * @author Antonia Albani
 * @version 09.12.2021
 */
public class Roboter extends Thread
{
    //In der Warteschlange werden alle zu produzierende Produkte gespeichert
    private LinkedList<Produkt> warteschlange = new LinkedList<Produkt>();
    //Name des Roboters
    private String name = null;
    //Zeit, welche der Roboter zum Produzieren braucht
    private int produktionsZeit = 2000;
    
    /**
     * Konstruktor
     * 
     * @param name des Roboters
     */
    public Roboter(String name){
        synchronisiertesPrintln("Roboter gestartet");        
        this.name = name;
    }
    
    
    /**
     * Die Hauptmethode des Threads
     * Hier werden die zu produzierende Prodzkte aus der Warteschlange geholt und
     * produziert, indem der Roboterthread für eine gewisse Zeit schläft.
     * Danach wird das Produkt der nächsten Produktionsstation (Roboter) übergeben
     */
    public void run(){
        while(true){
            
            Produkt produkt = warteschlange.poll();
            //synchronisiertesPrintln("Roboter: " + name + " wartet auf Produkte " + produkt);
            if(produkt != null){
                synchronisiertesPrintln("Roboter " + this.name +": nimmt " + produkt + " aus der Warteschlange");                
                produziereProdukt(produkt);
                produkt.naechsteProduktionsStation();
                warteschlange.remove(produkt);
            }
            sicheresSchlafen(1000);
        }
        
    }
   
    /**
     * Ein neues Produkt wird dem Roboter übergeben und in die Warteschlange
     * gespeichert
     * 
     */
    
    public void fuegeProduktHinzu(Produkt produkt){
        System.out.println("Roboter " + this.name + ": Produkt " + produkt + " zur Warteschlange hinzugefügt");
        warteschlange.add(produkt); 
    }
    
    
    private void synchronisiertesPrintln(String output){
        synchronized (System.out){
            System.out.println(output);
        }        
    }

    
    private void sicheresSchlafen(int zeit){
        try
        {
            Thread.sleep(zeit);            
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }    

    
    /**
     * Ermöglicht die Produktionszeit, und somit die Schlafenszeit des Threads, zu setzten
     * 
     * @param zeit die Zeit, welche für die Produktion benötigt wird
     */
    public void setzteProduktionsZeit(int zeit){
        produktionsZeit = zeit;
    }
    
    /**
     * Retourniert den Namen des Roboters
     * 
     * @return Namen des Roboters
     */
    
    public String gibName(){
        return name;
    }
    
    
    /**
     * Die Produktion eines Produkts wird simuliert indem man den Thread schlafen legt
     */
    public void produziereProdukt(Produkt produkt){
        synchronisiertesPrintln("Roboter " + this.name +": produziert " + produkt);
        sicheresSchlafen(produktionsZeit);
        synchronisiertesPrintln("Roboter " + this.name +": hat " + produkt + " fertig produziert");
    }
}

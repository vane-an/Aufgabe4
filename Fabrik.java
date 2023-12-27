import java.util.ArrayList;

/**
 * Die Klasse Fabrik ist die Hauptklasse des Programms.
 * Hier befindet sich auch die main Methode zum Starten des Programms.
 *
 * @author Antonia Albani
 * @version 1.0
 */


public class Fabrik
{
    // Die Liste bestellteProdukte enthält alle Produkte, welche  bestellt worden sind
    private ArrayList <Bestellung> bestellungen;
    private int bestellungsNr;
    private Lager lager;
    private Produktions_Manager produktionsManager;

    /**
     * Konstruktor der Klasse
     * Hier werden alle globale Variablen initialisiert
     * 
     * Die ArrayList soll alle in der Fabrik aufgegebenen Bestellungen abspeichern
     * Eine individuelle BestellNr. wird vergeben
     * Ein Lager wird angelegt
     */
    
    public Fabrik()
    {
        bestellungen = new ArrayList<Bestellung>();
        bestellungsNr = 0;
        lager = new Lager();
        produktionsManager = new Produktions_Manager(this, lager);
        produktionsManager.start();
              
    }
    
     /**
     * Mit dieser Methode kann eine Bestellung aufgegeben werden.
     * 
     * Die Beschaffungszeit und die Lieferzeit werden mit den Werten -1 initialisiert.
     * Es wird eine eindeutige Bestellnummer vergeben, dadurch dass eine Nummer einfach immer weiter hochgezählt wird.
     * Die Lieferzeit wird berechnet, indem die Anzahl zu produzierende Stühle x 22 Min. gerechnet wird. Dazu wird die Anzahl
     * zu produzierende Sofas x 60 Min. addiert. Das Gesamtergebnis wird durch 60 geteilt, um auf Stunden zu kommen und dies wiederum durch
     * 24 geteilt, um auf die Anzahl Tage zu kommen, die benötigt werden, um die Ware zu produzieren. Dazu wird noch ein Tag als Standardlieferzeit 
     * hinzugerechnet und die Beschaffungszeit addiert.
     * Die Beschaffungszeit, Lieferzeit und die Bestellbestätigung werden in der Bestellung abgespeichert. 
     * Die Bestellung wird in die Liste aller Bestellungen der Fabrik hinzugefügt.
     * 
     * @param stuehle Anzahl bestellter Stühle 
     * @param sofas   Anzahl bestellter Sofas
     */
    public void bestellungAufgeben(int stuehle, int sofas)
    {
        int beschaffungsZeit = -1;
        float lieferzeit = -1;
        bestellungsNr = bestellungsNr+1;
        Bestellung bestellung = new Bestellung(bestellungsNr, stuehle, sofas);
        beschaffungsZeit=lager.gibBeschaffungsZeit(bestellung);
        if(beschaffungsZeit>=0){
            bestellung.setzeBeschaffungsZeit(beschaffungsZeit);
            lieferzeit = (Stuhl.gibBenoetigteProduktionszeit()*stuehle+Sofa.gibBenoetigteProduktionszeit()*sofas)/(60*24) + 1 + beschaffungsZeit; 
            bestellung.setzteLieferZeit(lieferzeit); 
            bestellung.bestellungBestaetigen();
            bestellungen.add(bestellung);  
            produktionsManager.fuegeZuVerarbeitendeBestellungenHinzu(bestellung);
            System.out.println("Fabrik: Bestellung aufgegeben!");
        }else{
            System.out.println("Fabrik: Bestellung kann nicht bestätigt werden!");
        }       
    }     
 
        
     /**
     * Mit dieser Methode wird das Lager angeordnet Material wieder nachzubestellen
     * 
     */
    
    public void lagerAuffuellen(){
        lager.lagerAuffuellen();
    }
    
     /**
     * Mit dieser Methode wird die Liste mit allen Bestellungen der Fabrik retourniert
     * 
     */   
    
    public ArrayList<Bestellung> gibBestellungen(){
        return bestellungen;
    }

    
     /**
     * Mit dieser Methode werden die Informationen zu allen Bestellungen ausgegeben 
     * 
     */
    public void bestellungenAusgeben()
    {   
        for(Bestellung bestellung : bestellungen) {
            System.out.println("----------------------------------------------");
            System.out.println("Fabrik: ");
            System.out.println("Bestellnummer: " + bestellung.gibBestellungsNr());
            System.out.println("Anzahl Stühle: " + bestellung.gibAnzahlStuehle());
            System.out.println("Anzahl Sofas: " + bestellung.gibAnzahlSofas());
            System.out.println("Beschaffungszeit: " + bestellung.gibBeschaffungsZeit());
            System.out.println("Lieferzeit: " + bestellung.gibLieferZeit());            
            System.out.println("Bestellbestätigung: " + bestellung.gibBestellBestaetigung());
            System.out.println("----------------------------------------------");
        }      
    }  
    
    public int gibBestellungsNr(){
        return this.bestellungsNr;
    }

    
    /**
     * Mit der main Methode wird das Programm gestartet
     * 
     */    
    public static void main(String[] args) {
        System.out.println("Aeki Fabrik wird gestartet");
        Fabrik fabrik = new Fabrik();
    }
    
    
    //Simon & Vanessa --> Implementierung der Methode, welche alle Produkte der getätigten Bestellungen aufsummiert
    public int berechneGesamtAnzahlProdukte(){
        int gesamtanzahl = 0;
        ArrayList<Bestellung> bestellungen = gibBestellungen();
        for (Bestellung bestellung : bestellungen) {
            gesamtanzahl += bestellung.gibAnzahlStuehle() + bestellung.gibAnzahlSofas();
        }
        return gesamtanzahl;
    }
    //BIS HIER
    
    
    }

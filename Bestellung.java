import java.util.ArrayList;

/**
 * Die Klasse Bestellung beinhaltet die bestellten Produkte
 *
 * @author Antonia Albani
 * @version 21.11.2021
 */
public class Bestellung
{
    // Die Liste bestellteProdukte enthält alle Produkte, welche  bestellt worden sind
    private ArrayList <Produkt> bestellteProdukte;
    // Die bestellBestaetigung gibt an, ob eine Bestellung bestätigt wurde oder nicht (boolean)
    private boolean bestellBestaetigung;
    // Die lieferZeit gibt an, wie lange es dauert, bis die Ware rausgeschickt wird
    private float lieferZeit;
    // Die Beschaffungszeit gibt an, wie lange die Lieferzeit (in Tage) für die Produkte ist
    // -1 ist der Initialisierungswert
    private int beschaffungsZeit;
    // Jede Bestellung erhält eine Bestellnummer
    private int bestellungsNr;  
    // Anzahl der bestellten Stühle
    private int anzahlStuehle;  
    // Anzahl der bestellten Sofas
    private int anzahlSofas;  
    // Stuhl
    private Stuhl stuhl;
    // Sofa
    private Sofa sofa;
    // gibt an, ob alle Produkte produziert sind
    private boolean alleProdukteProduziert; 
    

    /**
     * Konstruktor für Klasse Bestellung.
     * Hier werden alle globalen Variablen initialisiert
     * 
     * @param bestellungsNr Zugeordnete Bestellnummer
     * @param stuehle Anzahl bestellter Stühle
     * @param sofas Anzahl bestellter Sofas 
     */
    public Bestellung(int bestellungsNr, int anzahlStuehle, int anzahlSofas)
    {
        int i;
        alleProdukteProduziert = false;
        this.anzahlStuehle = anzahlStuehle;
        this.anzahlSofas = anzahlSofas;
        bestellteProdukte = new ArrayList();
        bestellBestaetigung = false;
        lieferZeit = -1;
        beschaffungsZeit = -1;    
        this.bestellungsNr = bestellungsNr;
                       
        for(i=0;i<anzahlStuehle;i++)
        {
            stuhlHinzufuegen();
        }
 
        for(i=0;i<anzahlSofas;i++)
        {
            sofaHinzufuegen();
        }
        
    }
    
    /**
     * Mit dieser Methode wird ein Stuhl erstellt und zur Liste der bestellten Produkte hinzugefügt 
     * 
     */    
    private void stuhlHinzufuegen()
    {
         stuhl = new Stuhl();
         bestellteProdukte.add(stuhl);       
    }  

    /**
     * Mit dieser Methode wird ein Sofa erstellt und zur Liste der bestellten Produkte hinzugefügt 
     * 
     */      
    private void sofaHinzufuegen()
    {
        sofa = new Sofa();
        bestellteProdukte.add(sofa);         
    }  
    
    /**
     * Mit dieser Methode wird die Liste mit den bestellten Produkten zurückgeliefert 
     * 
     * @return Liste mit den bestellten Produkten
     */      
    public ArrayList<Produkt> liefereBestellteProdukte()
    {
        
        return bestellteProdukte;         
    }    
    
    /**
     * Mit dieser Methode wird eine Bestellung bestätigt 
     * 
     */
    public void bestellungBestaetigen()
    {
        bestellBestaetigung = true;
    }   
        
    /**
     * Mit dieser Methode wird der Zustand einer Bestellung abgefragt 
     * 
     * @return bestellBestaetigung Zustand der Bestellbestätigung
     */
    public boolean gibBestellBestaetigung()
    {
        return bestellBestaetigung;
    }       
    
    /**
     * Mit dieser Methode wird die Beschaffungszeit für die Bestellung gesetzt
     * 
     * @param beschaffungszeit wird übergeben
     */
    public void setzeBeschaffungsZeit(int beschaffungsZeit)
    {
        this.beschaffungsZeit = beschaffungsZeit;
    }   
    
    /**
     * Mit dieser Methode wird die Beschaffungszeit für die Bestellung ausgegeben 
     * 
     * @return beschaffungsZeit
     * 
     */
    public int gibBeschaffungsZeit()
    {
        return this.beschaffungsZeit;
    }   
    
    
    /**
     * Mit dieser Methode wird die Lieferzeit für die Bestellung gesetzt 
     * 
     * @param lieferZeit wird übergeben und gesetzt
     * 
     */
    public void setzteLieferZeit(float lieferZeit)
    {
         this.lieferZeit=lieferZeit;
    }   
    
    
    /**
     * Mit dieser Methode wird die Lieferzeit für die Bestellung ausgegeben 
     * 
     * 
     *@return lieferZeit wird zurückgegeben
     */
    public float gibLieferZeit()
    {
        return this.lieferZeit;
    }   

    /**
     * Mit dieser Methode wird die Bestellnummer für die Bestellung ausgegeben 
     * 
     *@param bestellungsNr wird retourniert
     */
    public int gibBestellungsNr()
    {
        return bestellungsNr;
    }   
    
    /**
     * Mit dieser Methode wird die Anzahl Stühle für die Bestellung ausgegeben 
     * 
     * @return anzahlStuehle wird retourniert
     */
    public int gibAnzahlStuehle()
    {
        return anzahlStuehle;
    }     
 
    /**
     * Mit dieser Methode wird die Anzahl Sofas für die Bestellung ausgegeben 
     * 
     * 
     * @return anzahlSofas wird retourniert
     * 
     */
    public int gibAnzahlSofas()
    {
        return anzahlSofas;
    }  
    
    public ArrayList<Produkt>gibBestellteProdukte(){
        return this.bestellteProdukte;
    }
    public float gibLieferzeit(){
        return this.lieferZeit;
    }
    
    public boolean gibAlleProduziert(){
        return this.alleProdukteProduziert;
    }
    /**
     * Mit dieser Methode wird festgehalten, dass alle Produkte produziert wurden
     * 
     * 
     */    
    
    
    public void setzeAlleProdukteProduziert(){
        System.out.println("Bestellung " + bestellungsNr + ": alle Produkte sind produziert und können versandt werden");
        alleProdukteProduziert = true;
    }
}

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Der Produktionsmanager ist verantwortlich für die Produktion und die Steuerung der Roboter
 *
 * @author Antonia Albani
 * @version 09.12.2021
 */
public class Produktions_Manager extends Thread
{
     private Holzbearbeitungs_Roboter holzRoboter;
     private Montage_Roboter montageRoboter;
     private Lackier_Roboter lackierRoboter;
     private Verpackungs_Roboter verpackungsRoboter;
     
     private Fabrik meineFabrik;
     private Lager meinLager;
     
     private LinkedList<Bestellung> zuVerarbeitendeBestellungen;
     private LinkedList<Bestellung> bestellungenInProduktion;
    

    /**
     * Der Konstruktor
     */
    public Produktions_Manager(Fabrik meineFabrik, Lager meinLager)
    {       
        zuVerarbeitendeBestellungen = new LinkedList<Bestellung>();
        bestellungenInProduktion = new LinkedList<Bestellung>(); 
        
        this.meineFabrik = meineFabrik;
        this.meinLager = meinLager;
        
        holzRoboter = new Holzbearbeitungs_Roboter("Holzroboter");
        montageRoboter = new Montage_Roboter("Montageroboter");
        lackierRoboter = new Lackier_Roboter("Lackierroboter");
        verpackungsRoboter = new Verpackungs_Roboter("Verpackungsroboter");
        
        holzRoboter.start();
        montageRoboter.start();
        lackierRoboter.start();
        verpackungsRoboter.start();
    }
    
    
    /**
     * Lässt den Thread um die Zeit zeit schlafen
     * 
     * @param zeit  welche der Thread schlafen soll 
     */

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
     * Eine neue Bestellung wird der Liste zuVerarbeitendeBestellungen hinzugefügt
     * 
     * @param bestellung  welche der Thread schlafen soll 
     */   
    
    public void fuegeZuVerarbeitendeBestellungenHinzu(Bestellung bestellung){
        zuVerarbeitendeBestellungen.add(bestellung);
    }
    
    /**
     * Die run Methode des Threads nimmt die Bestellungen aus der Liste der zu 
     * verarbeitenden Bestellungen. Wenn genügend Material im Lager ist, wird die 
     * Produktion der Bestellten Produkte gestartet und die Bestellung wird
     * der Liste betsellungenInProduktion hinzugefügt.
     * 
     * Bei den Bestellungen in Produktion wird dann geschaut, ob alle Produkte
     * bereits produziert wurden. Wenn ja, wird die Bestellung von der Liste, der 
     * zu produzierenden Bestellungen gelöscht und in der Klasse Bestellung wird
     * festgehalten, dass alle Produkte produziert und zur Auslieferung bereit sind. 
     */
    
    public void run(){
        synchronisiertesPrintln("Produktionsmanager gestartet");
        while(true){
            Bestellung naechsteBestellung = zuVerarbeitendeBestellungen.peek();
            if(naechsteBestellung != null && meinLager.liefereMaterial(naechsteBestellung)){
                naechsteBestellung = zuVerarbeitendeBestellungen.poll();
                bestellungenInProduktion.add(naechsteBestellung);
                starteProduktion(naechsteBestellung);
            }
            for(Bestellung bestellung : bestellungenInProduktion){
            boolean alleProdukteProduziert = true;
            for(Produkt produkt : bestellung.liefereBestellteProdukte()){
                 if(produkt.aktuellerZustand()!=3){
                     alleProdukteProduziert = false;
                     break;
                }
             }
             if(alleProdukteProduziert){
                 bestellungenInProduktion.remove(bestellung);
                 bestellung.setzeAlleProdukteProduziert();
             }             
         }            
         sicheresSchlafen(100);  
        }
    }
  
    /**
     * Die Methode starteProduktion weist jedem Produkt der Bestellung die entsprecheenden 
     * Roboter zu und startet die Produktion beim ersten Roboter
     */
    
    private void starteProduktion(Bestellung bestellung){
        synchronisiertesPrintln("Produktionsmanager: starte Produktion für Bestellung " + bestellung.gibBestellungsNr());
        for(Produkt produkt : bestellung.liefereBestellteProdukte()){
            alloziereRoboter(produkt);
            produkt.naechsteProduktionsStation();
        }         
    }
    
    private void synchronisiertesPrintln(String output){
        synchronized (System.out){
            System.out.println(output);
        }        
    }
    
    /**
     * In dieser Methode wird für jedes Produkt die richtige Roboterreihenfolge
     * festgelegt. Diese Information wird dann im Produkt selber gespeichert
     */
    
    private void alloziereRoboter(Produkt produkt){
        LinkedList<Roboter> bearbeitungsReihenfolge = new LinkedList<Roboter>();
        if(produkt instanceof Stuhl){
            bearbeitungsReihenfolge.add(holzRoboter);
            bearbeitungsReihenfolge.add(montageRoboter);
            bearbeitungsReihenfolge.add(lackierRoboter);
            bearbeitungsReihenfolge.add(verpackungsRoboter);
            produkt.setzteProduktionsAblauf(bearbeitungsReihenfolge);
        }
        else if(produkt instanceof Sofa){
            bearbeitungsReihenfolge.add(holzRoboter);
            bearbeitungsReihenfolge.add(lackierRoboter);
            bearbeitungsReihenfolge.add(montageRoboter);            
            bearbeitungsReihenfolge.add(verpackungsRoboter);
            produkt.setzteProduktionsAblauf(bearbeitungsReihenfolge);

        }        
    }

}


/**
 * Mit dieser Klasse wird die Lieferzeit des Lieferanten simuliert
 *
 * @author Antonia Albani
 * @version 23.11.2021
 */
public class Lieferant extends Thread
{
    private Lager lager;
 
    /**
     * Konstruktor
     */
    public Lieferant(Lager lager)
    {
        super();
        this.lager=lager;
    }
    
    public void run(){
        try{
            Thread.sleep(2000);
            System.out.println("Lieferant: Ware wird versandt"); 
            lager.wareLiefern();
        }catch(InterruptedException ie){
            System.out.println("Lieferant: Thread Exception!");
        }
    }

}

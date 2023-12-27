import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * Write a description of class GUI here.
 *
 * @author FP & JG
 * @version 0.0.1
 */
public class GUI 
{
    private JFrame fenster;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel produktPanel;
    private Fabrik fabrik;

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        this.fabrik = new Fabrik();
        fensterErzeugen();
    }
    
    
    private void displayBestellungenInTable(ArrayList<Bestellung> bestellungen) {

        
        int reihen = bestellungen.size();
        Object[][] data = new Object[reihen][7];
        
        for(int i = 0; i < reihen; i ++){
            
            Bestellung bestellung = bestellungen.get(i);            
            Object[] attributes = new Object[] {
            bestellung.gibBestellteProdukte().size(),
            bestellung.gibBestellBestaetigung(),
            bestellung.gibLieferZeit(),
            bestellung.gibBestellungsNr(),
            bestellung.gibAnzahlStuehle(),
            bestellung.gibAnzahlSofas(),
            bestellung.gibAlleProduziert()
            };
            
            data[i] = attributes;
        // Column names for the table
    
        }

        String[] columnNames = { 
            "Bestellte Produkte",
            "Bestellbestätigung",
            "Lieferzeit",
            "Bestellnummer",
            "Anzahl Stühle",
            "Anzahl Sofas",
            "Fertig" };
        // Create table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create the table
        JTable table = new JTable(model);

        // Set up the frame
        JFrame frame = new JFrame("Bestellungen Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Adjust the size of the frame and make it visible
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    
    //Simon & Vanessa
    
    private void gibGesamtAnzahlVerarbeiteterProdukte(){
        int total = fabrik.berechneGesamtAnzahlProdukte();
        JOptionPane.showMessageDialog(fenster, "Gesamtanzahl verarbeiteter Produkte: " + total, "Info", JOptionPane.INFORMATION_MESSAGE);
        
    }
    //BIS HIER
    
    private JPanel createHeaderPanel() {
        // Create the navbar panel with buttons
        JPanel navbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnOrderEnter = new JButton("Bestellung eingeben");
        btnOrderEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                bestellungEingabeMaskeEinzeigen();
            }
            
        });
        

        JButton btnOrderView = new JButton("Bestellung ansehen");
        
        btnOrderView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Bestellung> bestellungen = fabrik.gibBestellungen();
                System.out.println("Aktive Bestellugen " + bestellungen.size());
                displayBestellungenInTable(bestellungen);
            }
            
        });
        JButton btnInventory = new JButton("Lagerbestand");
        JButton btnProductionStatus = new JButton("Status Produktion");
        
        //HIER
        //Simon und Vanessa --> Anzahl verarbeitete Produkte Overall
        JButton btnTotalProducts = new JButton("Gesamtanzahl verarbeiteter Produkte");
        btnTotalProducts.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gibGesamtAnzahlVerarbeiteterProdukte(); //ist oben ab Line 92 definiert worden
                
            }
        });
        //BIS HIER
        
        // Add buttons to navbar panel
        navbarPanel.add(btnOrderEnter);        
        navbarPanel.add(btnOrderView);
        navbarPanel.add(btnInventory);
        navbarPanel.add(btnProductionStatus);
        navbarPanel.add(btnTotalProducts);

        return navbarPanel;
    }
    
    private void bestellungEingabeMaskeEinzeigen(){
        System.out.println("IIIIIIIIIII");
        JPanel panel = this.mainPanel;
        mainPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();

        JLabel labelSofas = new JLabel("Anzahl Sofas:");
        JLabel labelChairs = new JLabel("Anzahl Stühle:");
        JTextField textFieldSofas = new JTextField(10);
        JTextField textFieldChairs = new JTextField(10);
        JTextField textFieldOrderNumber = new JTextField(10);
        

        JButton validateButton = new JButton("Validierung der Bestellung");
        
        
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String sofas = textFieldSofas.getText();
                String chairs = textFieldChairs.getText();

                int sofasInt = 0;
                int stuehleInt = 0;
                try{
                    sofasInt = Integer.parseInt(sofas);    
                } catch(NumberFormatException ex){
                    ex.printStackTrace();
                }
                try{
                    stuehleInt = Integer.parseInt(chairs);    
                } catch(NumberFormatException ex){
                    ex.printStackTrace();
                }
                // hier sicherstellen, dass stuehleInt + sofasInt >= 0 sind
                fabrik.bestellungAufgeben(stuehleInt, sofasInt);
            }
        });
        
        // Configure the grid layout
        c.insets = new Insets(10, 10, 10, 10); // padding
        c.gridx = 0; c.gridy = 0;
        mainPanel.add(labelSofas, c);
        c.gridx = 1;
        mainPanel.add(textFieldSofas, c);
        
        c.gridx = 0; c.gridy = 1;
        mainPanel.add(labelChairs, c);
        c.gridx = 1;
        mainPanel.add(textFieldChairs, c);

        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 3;
        mainPanel.add(validateButton, c);
        
            mainPanel.revalidate();
    mainPanel.repaint();
        
    }

    private JPanel createMainPanel() {
        // Create the panel to hold all components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        return panel;
    }
    
    public void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Furniture Order Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        
        // Create and add the main panel
        mainPanel = createMainPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        // Create and add the header panel
        headerPanel = createHeaderPanel();
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Show the frame
        frame.setVisible(true);
    }

    private void fensterErzeugen(){

        this.createAndShowGUI();

    }
    

}

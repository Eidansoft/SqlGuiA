/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eidansoft.sqlguia;

import com.eidansoft.sqlguia.formularios.SQLGuiA;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author alorente
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{
        log.info("Lanzando interfaz...");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.  
        JFrame.setDefaultLookAndFeelDecorated(true);  
   
        //Create and set up the window.  
        JFrame frame = new SQLGuiA();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
   
        //Display the window.  
        frame.pack();  
        frame.setVisible(true); 
    }
}

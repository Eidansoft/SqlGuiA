/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eidansoft.sqlguia;

import com.eidansoft.sqlguia.formularios.SQLGuiA;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alorente
 */
public class FuncionesComunesDeArchivos {
    public static boolean guardaArchivo(String ruta, String datos){
        FileOutputStream fos = null;
        Writer writer = null;
        boolean res = false;
        try {
            fos = new FileOutputStream(ruta);
            writer = new OutputStreamWriter(fos, "UTF-8");
            writer.write(datos);
            writer.close();
            res = true;
        } catch(IOException e) {
            Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, "Error al guardar el archivo '" + ruta + "'");
            //e.printStackTrace(); // this obviously needs to be refined.
        } finally {
            if(fos!=null) {
                try{
                    fos.close();
                } catch (IOException e2) {
                    Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, "Error al cerrar el Stream al archivo '" + ruta + "'");
                    //e2.printStackTrace(); // this obviously needs to be refined.
                }
            }
        }
        return res;
    }
    
    public static byte[] cargaArchivo(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                sb.append(linea);
            }
        } catch(Exception e){
            Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, "Error al leer el archivo '" + ruta + "'");
            //e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
            }catch (Exception e2){ 
                Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, "Error al cerrar el Stream del archivo '" + ruta + "'");
               //e2.printStackTrace();
            }
        }
        return sb.toString().getBytes();
    }
}

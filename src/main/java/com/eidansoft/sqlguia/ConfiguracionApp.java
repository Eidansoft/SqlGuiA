/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eidansoft.sqlguia;

import java.io.Serializable;

/**
 *
 * @author alorente
 */
public class ConfiguracionApp implements Serializable{
    private static final long serialVersionUID = -7649025684644748236L;
    
    
    //Datos de configuracion
    private int maxRows = 1000;
    private String rutaArchivoSql = "./sentencias.sql";

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public String getRutaArchivoSql() {
        return rutaArchivoSql;
    }

    public void setRutaArchivoSql(String rutaArchivoSql) {
        this.rutaArchivoSql = rutaArchivoSql;
    }    
}

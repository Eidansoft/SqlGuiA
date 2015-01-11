/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eidansoft.sqlguia.consulta;

/**
 *
 * @author alorente
 */
public class Tabla {
    private String nombre;
    private String alias;

    public Tabla(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public String getNombreConAlias(){
        return nombre + (alias != null? " " + alias : "");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

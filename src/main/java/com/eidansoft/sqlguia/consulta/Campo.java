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
public class Campo {
    private String nombre;
    private Tabla tabla;
    private String alias;

    public Campo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return (tabla != null? tabla.toString() + "." : "") + nombre;
    }
    
    public String getNombreConAlias(){
        return nombre + (alias != null? " as " + alias : "");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }
    
    
}

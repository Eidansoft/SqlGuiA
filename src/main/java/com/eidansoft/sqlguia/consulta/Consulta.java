/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eidansoft.sqlguia.consulta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alorente
 */
public class Consulta {
    private List<Campo> campos = new ArrayList<Campo>();
    private Tabla tablaPrincipal;

    public Consulta(Tabla tablaPrincipal) {
        this.tablaPrincipal = tablaPrincipal;
    }
    
    @Override
    public String toString() {
        return "SELECT " + listaCampos() + " FROM " + tablaPrincipal;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public Tabla getTablaPrincipal() {
        return tablaPrincipal;
    }

    public void setTablaPrincipal(Tabla tablaPrincipal) {
        this.tablaPrincipal = tablaPrincipal;
    }

    private String listaCampos() {
        StringBuilder buffer = new StringBuilder();
        String res = "*";
        if (campos != null && !campos.isEmpty()){
            Iterator<Campo> it = campos.iterator();
            while (it.hasNext()) {
                Campo campo = it.next();
                buffer.append(campo.getNombreConAlias());
                buffer.append(", ");
            }
            res = buffer.substring(0, buffer.length() - 2);
        }
        return res;
    }
    
}

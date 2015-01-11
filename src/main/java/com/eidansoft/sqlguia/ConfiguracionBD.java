/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eidansoft.sqlguia;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author alorente
 */
public class ConfiguracionBD implements Serializable{
    
    /* Constantes */
    public static final int MYSQL = 0;
    public static final String MYSQL_NAME = "MySQL";
    public static final int ORACLE = 1;
    public static final String ORACLE_NAME = "ORACLE";
    
    private String ip;
    private String puerto;
    private String SID;
    private String usuario;
    private String pw;
    private String nombre;
    private int tipo;

    public static int String2Tipo(String nombre) {
        int res = -1;
        if (nombre.equalsIgnoreCase(MYSQL_NAME)){
            res = ConfiguracionBD.MYSQL;
        } else if (nombre.equalsIgnoreCase(ORACLE_NAME)){
            res = ConfiguracionBD.ORACLE;
        } else {
            throw new IllegalArgumentException("El tipo '" + nombre + "' no se reconoce");
        }
        return res;
    }
    
    public static String Tipo2String(int tipo) {
        String res = null;
        switch (tipo) {
            case ConfiguracionBD.MYSQL:
                res = MYSQL_NAME;
                break;
            case ConfiguracionBD.ORACLE:
                res = ORACLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("El tipo de BD '" + tipo + "' no es un tipo valido");
        }
        return res;
    }
    
    public static String[] getAllTipos(){
        return new String[] { MYSQL_NAME, ORACLE_NAME };
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.ip);
        hash = 73 * hash + Objects.hashCode(this.puerto);
        hash = 73 * hash + Objects.hashCode(this.SID);
        hash = 73 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConfiguracionBD other = (ConfiguracionBD) obj;
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        if (!Objects.equals(this.puerto, other.puerto)) {
            return false;
        }
        if (!Objects.equals(this.SID, other.SID)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + " [" + usuario + "@" +  ip + ":" + puerto + ":" + SID.toUpperCase() + "]";
    }
    
    
}

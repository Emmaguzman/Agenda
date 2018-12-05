/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Emmanuel
 */
public class Contacto {
    
    int id;
    private String nombre;
    private String apellido;
    private String alias;
    private String mail;
    private String telefono;
 
    @Override
    public String toString() {
        return "Contacto{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", alias=" + alias + ", mail=" + mail + ", telefono=" + telefono + '}';
    }
    public Contacto(){}
    

    public Contacto(String nombre, String apellido, String alias, String mail, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Contacto(int id, String nombre, String apellido,
            String alias, String mail, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.mail = mail;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}

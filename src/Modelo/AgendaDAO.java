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
public interface AgendaDAO {
    public void agregar(Contacto c);
    public void modificar(Contacto c,int id);
    public void eliminar(String id);
    public void mostrarTodos();
    public void buscar(String buscar);
}

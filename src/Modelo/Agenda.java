/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Emmanuel
 */
public class Agenda implements AgendaDAO {
    String[] filas;
    Connection conn= Conectar.Singleton_SQLServer.getConnection();
    Statement sc;
    PreparedStatement ac;
    List lista;
    DefaultTableModel model; 
    String busqueda;
    ResultSet rs ;
    int cantidad;
    
final String AGREGAR="INSERT INTO CONTACTOS(NOMBRE,APELLIDO,ALIAS,EMAIL,TELEFONO) VALUES(?,?,?,?,?)";
final String MOSTRARTODOS="SELECT * FROM CONTACTOS";
final String ACTUALIZAR ="UPDATE CONTACTOS SET NOMBRE=? , APELLIDO=? , ALIAS= ? , EMAIL= ? ,TELEFONO= ? WHERE id=?";
final String ELIMINAR="DELETE FROM CONTACTOS WHERE id= ?";
final String CONTARTODOS="SELECT COUNT(*) 'COUNT' FROM CONTACTOS";
String BUSCAR ="SELECT * FROM Contactos WHERE nombre LIKE  '%"+busqueda+"%'";


 public Agenda(){}
 public Agenda(Contacto c) {
    }
    @Override
    public void agregar(Contacto c) {
          try{
            
            ac = conn.prepareStatement(AGREGAR);
            ac.setString(1, c.getNombre());
            ac.setString(2, c.getApellido());
            ac.setString(3, c.getAlias());
            ac.setString(4, c.getMail());
            ac.setString(5, c.getTelefono());
            
            int n = ac.executeUpdate();
            
            if (n > 0) {
                System.out.println("EL CONTACTO SE AGREGO CORRECTAMENTE");
               ac.close();
            }} catch (SQLException e) {
              System.out.println("algo salio mal");
            e.printStackTrace();
        }
            cantidad=contarTodos();
    }


    @Override
    public void modificar(Contacto c,int mod) {
        
         try{
            ac = conn.prepareStatement(ACTUALIZAR);
          
            ac.setString(1, c.getNombre());
            ac.setString(2, c.getApellido());
            ac.setString(3, c.getAlias());
            ac.setString(4, c.getMail());
            ac.setString(5, c.getTelefono());
            ac.setInt(6, mod);
            System.out.println(mod);
           ac.executeUpdate();
            
            int n = ac.executeUpdate();
            
            System.out.println("MODIFICAMO?");
            if (n > 0) {
                JOptionPane.showMessageDialog(null,"LOS DATOS DEL CONTACTO N° "+mod+" \n FUERON ACTUALIZADOS CORRECTAMENTE","ACTUALIZACION COMPLETADA",1);
               // JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS CORRECTAMENTE");
                System.out.println("LOS DATOS SE MODIFICARON...");
           
                  ac.close();
            }

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "algo salio mal");
            e.printStackTrace();}
    }

    



    @Override
    public void mostrarTodos() {
     try {
           
            String[] titulos = {"ID", "Nombre", "Apellido", "Alias", "Email", "Telefono"};
            model = new DefaultTableModel(null, titulos);
            sc=conn.createStatement();
            rs= sc.executeQuery(MOSTRARTODOS);
 
            filas= new String[6];
            
            while (rs.next()) {
                
                filas[0] = rs.getString("ID");
                filas[1] = rs.getString("NOMBRE");
                filas[2] = rs.getString("APELLIDO");
                filas[3] = rs.getString("ALIAS");
                filas[4] = rs.getString("EMAIL");
                filas[5] = rs.getString("TELEFONO");
                
              
                System.out.println( filas[0] +"  "+filas[1] +"  "+filas[2] +"  "
                                    +filas[3] +"  "+filas[4] +"  "+filas[5]);
                System.out.println("------------------------------------------------------");
                model.addRow(filas); 
               
          
               
                
            }
                int contador= contarTodos();
                System.out.println("****TENEMOS ["+contador+"] Contactos****");
       
       
     }catch(SQLException e){
         e.printStackTrace();
     
     }
     
     
    }
     @Override
    public void eliminar(String id) {
         
        try{
          ac = conn.prepareStatement(ELIMINAR);
          ac.setString(1,id);
          ac.executeUpdate();
            int n = ac.executeUpdate();
            if (n > 0) {
             JOptionPane.showMessageDialog(null, "EL SE BORRO CORRECTAMENTE");
             
             ac.close();
            } 
 
    }catch(SQLException e){}
    
}

    @Override
    public void buscar(String buscar) {
        try{
        System.out.println("ESTOY BUSCANDO A..."+buscar);
        String[] titulos = {"ID", "Nombre", "Apellido", "Alias", "Email", "Telefono"};
        model = new DefaultTableModel(null, titulos);  
        ac=conn.prepareStatement("SELECT * FROM Contactos WHERE nombre LIKE  '%"+buscar+"%'");
        rs=ac.executeQuery();
        filas = new String[6];
       
         while (rs.next()) {

                filas[0] = rs.getString("ID");
                filas[1] = rs.getString("NOMBRE");
                filas[2] = rs.getString("APELLIDO");
                filas[3] = rs.getString("ALIAS");
                filas[4] = rs.getString("EMAIL");
                filas[5] = rs.getString("TELEFONO");
                
                String nombre=filas[1];
                String apellido=filas[2];
                String alias=filas[3];
                System.out.println(nombre +"  "+alias+"   "+apellido);
                
                model.addRow(filas);
                
                System.out.println("");
         }
     
       
           }catch(NullPointerException f){
                    
                    JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN CONTACTO CON ESE NOMBRE");
                    f.printStackTrace();
                
                
            
        }catch(SQLException e){}
        
      
    }

 
 
    
 public int contarTodos(){
    cantidad=0;
     try{
       sc=conn.createStatement();
       rs= sc.executeQuery(CONTARTODOS);
        while(rs.next()){
       cantidad=rs.getInt("Count");
      }
     }catch (SQLException e){
     
     }
    return  cantidad;
 
 }
 
 
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel
 */
public class Hilo extends Thread {

    boolean salir;
    String escritorio= System.getProperty("user.home")+"\\desktop";
    String origenConArchivo = "D:\\PROGRAMACION\\Java\\Leyendocsv\\Contactos2.csv";
    String carpetaDestino = escritorio+"\\CSVs";
    String directorio = "D:\\PROGRAMACION\\Java\\Agenda2_1\\src\\";
    FileReader archivo;
    File origenArchivo, archivosCarpeta;

    @Override
    public void run() {
        try {          
            File destino = new File(carpetaDestino);
            if (destino.exists()) {
                System.out.println("EL DIRECTORIO EXISTE");
            } else {
                System.out.println("CREANDO DIRECTORIO");
                destino.mkdir();
            }
            archivo = new FileReader(new File(origenConArchivo));
            Hilo.sleep(200);
            BufferedReader br = new BufferedReader(archivo);
            salir = false;

            while (!salir) {
                try {
                    String linea = br.readLine();
                    if (linea != null) {

                        String[] parte = linea.split("[, ;]");

                        String nombre = parte[0];
                        String apellido = parte[1];
                        String alias = parte[2];
                        String mail = parte[3];
                        String numero = parte[4];

                        System.out.println("Nombre: " + nombre);
                        System.out.println("Apellido: " + apellido);
                        System.out.println("Alias: " + alias);
                        System.out.println("Mail: " + mail);
                        System.out.println("Numero :" + numero);

                        System.out.println("----------------------------");

                        Contacto contactoCsv = new Contacto(nombre, apellido, alias, mail, numero);

                        Agenda cd = new Agenda(contactoCsv);
                        cd.agregar(contactoCsv);
                        System.out.println("El contacto " + contactoCsv.getNombre() + " ha sido agregado a la base de datos");
                        System.out.println("----------------------");

                        Hilo.sleep(200);
                    }
                } catch (ArrayIndexOutOfBoundsException x) {
                    System.out.println("EL HILO NO ENCONTRO MAS CONTACTOS");
                   
                }
                 
            }
            
            moverArchivo(origenConArchivo, carpetaDestino + "/nuevoillo.csv");

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "ERROR EN EL HILO!! EL ARCHIVO NO SE ENCUENTRA");
            salir = true;
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void detenerHilo() {
        try {
            salir = true;
            archivo.close();

        } catch (Exception e) {

        }
    }

    public void dameArchivos(File carpeta) {

        File[] ficheros = carpeta.listFiles();
        for (int x = 0; x < ficheros.length; x++) {
            System.out.println(ficheros[x].getName());
        }

    }

    public void moverArchivo(String origena, String destinoa) {
        Path origenPath = FileSystems.getDefault().getPath(origena);
        Path destinoPath = FileSystems.getDefault().getPath(destinoa);

        JOptionPane.showMessageDialog(null, "EL ARCHIVO CSV YA FUE LEÍDO, SERA PASADO A " + destinoPath.toString());
        System.out.println("EL ARHIVO FUE MOVIDO CON EXITO");
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}

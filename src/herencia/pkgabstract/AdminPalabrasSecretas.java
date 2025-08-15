/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;



import java.util.ArrayList;
import java.util.Random;

public class AdminPalabrasSecretas {

    private ArrayList<String> listaPalabras;
    private Random aleatorio;

    public AdminPalabrasSecretas() {
        listaPalabras = new ArrayList<>();
        aleatorio = new Random();
    }

    
    public void cargarPalabrasIniciales() {
        listaPalabras.clear();
        listaPalabras.add("PROGRAMACION");
        listaPalabras.add("JAVA");
        listaPalabras.add("COMPUTADORA");
        listaPalabras.add("TELEFONO");
        listaPalabras.add("TECLADO");
        listaPalabras.add("CARLOS");
        listaPalabras.add("PANTALLA");
        listaPalabras.add("CODIGO");
        listaPalabras.add("CLASE");
        listaPalabras.add("OBJETO");
    }

    public boolean agregarPalabra(String palabra) {
        palabra = palabra.toUpperCase();
        if (!listaPalabras.contains(palabra)) {
            listaPalabras.add(palabra);
            return true;
        }
        return false;
    }

    public boolean editarPalabra(int indice, String nuevaPalabra) {
        nuevaPalabra = nuevaPalabra.toUpperCase();
        if (!listaPalabras.contains(nuevaPalabra)) {
            listaPalabras.set(indice, nuevaPalabra);
            return true;
        }
        return false;
    }

    public String obtenerPalabraAzar() {
        if (listaPalabras.isEmpty()) return "JAVA";
        int posicion = aleatorio.nextInt(listaPalabras.size());
        return listaPalabras.get(posicion);
    }

    public ArrayList<String> getListaPalabras() {
        return listaPalabras;
    }
}

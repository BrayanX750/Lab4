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

    public boolean agregarPalabra(String palabra) {
        if (palabra == null) return false;
        String p = palabra.trim().toUpperCase();
        if (p.isEmpty()) return false;
        if (existePalabra(p)) return false;
        listaPalabras.add(p);
        return true;
    }

    public String obtenerPalabraAzar() {
        if (listaPalabras.isEmpty()) return "JAVA";
        int posicion = aleatorio.nextInt(listaPalabras.size());
        return listaPalabras.get(posicion);
    }

    public boolean existePalabra(String palabra) {
        if (palabra == null) return false;
        String p = palabra.trim().toUpperCase();
        for (int i = 0; i < listaPalabras.size(); i++) {
            if (listaPalabras.get(i).equals(p)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getListaPalabras() {
        return listaPalabras;
    }
}
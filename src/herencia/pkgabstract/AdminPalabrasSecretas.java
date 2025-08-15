/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;





import java.util.ArrayList;

public class AdminPalabrasSecretas {
    private ArrayList<String> listaPalabras;

    public AdminPalabrasSecretas() {
        listaPalabras = new ArrayList<>();
    }

    public void cargarPalabrasIniciales() {
        listaPalabras.clear();
        listaPalabras.add("JAVA");
        listaPalabras.add("PROGRAMACION");
        listaPalabras.add("CODIGO");
        listaPalabras.add("ALGORITMO");
        listaPalabras.add("VARIABLE");
        listaPalabras.add("OBJETO");
        listaPalabras.add("CLASE");
        listaPalabras.add("HERENCIA");
        listaPalabras.add("POLIMORFISMO");
        listaPalabras.add("INTERFAZ");
    }

    public boolean cambiarPalabra(int index, String nueva) {
        if (index >= 0 && index < listaPalabras.size()
                && nueva != null && !nueva.trim().isEmpty()
                && !listaPalabras.contains(nueva.toUpperCase())) {

            listaPalabras.set(index, nueva.toUpperCase());
            return true;
        }
        return false;
    }

    public ArrayList<String> getListaPalabras() {
        return listaPalabras;
    }
}
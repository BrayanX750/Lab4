/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;

import java.util.ArrayList;
import java.util.Random;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {

    private ArrayList<String> palabras;
    private Random random;

    public JuegoAhorcadoAzar() {
        palabras = new ArrayList<>();
        palabras.add("JAVA");
        palabras.add("AHORCADO");
        palabras.add("SISTEMAS");
        palabras.add("MUNDO");
        palabras.add("BALONCESTO");
        palabras.add("TACOS");
        palabras.add("CARLOS");
        palabras.add("TELEFONO");
        palabras.add("BOLSON");
        palabras.add("LENTES");

        random = new Random();
    }

    public JuegoAhorcadoAzar(ArrayList<String> lista) {
        if (lista == null || lista.isEmpty()) {
            palabras = new ArrayList<>();
            palabras.add("JAVA");
            palabras.add("AHORCADO");
            palabras.add("SISTEMAS");
            palabras.add("MUNDO");
            palabras.add("BALONCESTO");
            palabras.add("TACOS");
            palabras.add("CARLOS");
            palabras.add("TELEFONO");
            palabras.add("BOLSON");
            palabras.add("LENTES");

        } else {
            palabras = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                String w = lista.get(i);
                if (w != null) {
                    palabras.add(w.toUpperCase());
                }
            }
        }
        random = new Random();
    }

    public void agregarPalabra(String palabra) {
        if (palabra == null) {
            return;
        }
        if (palabra.trim().isEmpty()) {
            return;
        }
        palabras.add(palabra.trim().toUpperCase());
    }

    @Override
    public void inicializarPalabraSecreta() {
        if (palabras.isEmpty()) {
            setPalabraSecreta("JAVA");
            return;
        }
        int idx = random.nextInt(palabras.size());
        String elegida = palabras.get(idx);
        setPalabraSecreta(elegida);
    }

    @Override
    public void jugar() {
        inicializarPalabraSecreta();
    }

    @Override
    protected void actualizarPalabraActual(char letra) {
        String nueva = "";
        for (int i = 0; i < palabraSecreta.length(); i++) {
            char c = palabraSecreta.charAt(i);
            if (c == letra) {
                nueva += letra;
            } else {
                nueva += palabraActual.charAt(i);
            }
        }
        setPalabraActual(nueva);
    }

    @Override
    protected boolean verificarLetra(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }
}

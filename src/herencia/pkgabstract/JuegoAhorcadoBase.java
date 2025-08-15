/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public abstract class JuegoAhorcadoBase implements JuegoAhorcado {

    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;

    private final Set<Character> letrasUsadasSet;

    protected JuegoAhorcadoBase() {
        letrasUsadas = new ArrayList<>();
        letrasUsadasSet = new HashSet<>();
        figuraAhorcado = new ArrayList<>();
        cargarFiguraDefault();
        resetearIntentos();
        palabraSecreta = "";
        palabraActual  = "";
    }

    protected abstract void actualizarPalabraActual(char letra);
    protected abstract boolean verificarLetra(char letra);
    protected abstract boolean hasGanado();

    @Override
    public abstract void inicializarPalabraSecreta();

    @Override
    public abstract void jugar();

    protected void setPalabraSecreta(String palabra) {
        if (palabra == null) palabra = "";
        palabraSecreta = palabra.trim().toUpperCase();
        palabraActual  = construirOcultaDesde(palabraSecreta);
        resetearIntentos();
        limpiarLetrasUsadas();
    }

    protected int procesarIntento(char entrada) {
        char letra = normalizarLetra(entrada);
        if (!Character.isLetter(letra)) return -1;
        if (letraYaUsada(letra)) return -1;

        registrarLetra(letra);

        boolean acierta = verificarLetra(letra);
        if (acierta) {
            actualizarPalabraActual(letra);
            return 1;
        } else {
            descontarIntento();
            return 0;
        }
    }

    protected String construirOcultaDesde(String palabra) {
        StringBuilder sb = new StringBuilder(palabra.length());
        for (char c : palabra.toCharArray()) {
            if (Character.isLetter(c)) sb.append('_');
            else sb.append(c);
        }
        return sb.toString();
    }

    public String getFiguraActual() {
        int fallos = limiteIntentos - intentos;
        if (fallos < 0) fallos = 0;
        if (fallos >= figuraAhorcado.size()) fallos = figuraAhorcado.size() - 1;
        return figuraAhorcado.get(fallos);
    }

    protected void resetearIntentos() {
        this.intentos = this.limiteIntentos;
    }

    protected void descontarIntento() {
        if (intentos > 0) intentos--;
    }

    protected void limpiarLetrasUsadas() {
        letrasUsadas.clear();
        letrasUsadasSet.clear();
    }

    protected void registrarLetra(char letra) {
        if (!letrasUsadasSet.contains(letra)) {
            letrasUsadas.add(letra);
            letrasUsadasSet.add(letra);
        }
    }

    protected boolean letraYaUsada(char letra) {
        return letrasUsadasSet.contains(letra);
    }

    protected char normalizarLetra(char c) {
        return Character.toUpperCase(c);
    }

    public boolean sinIntentos() {
        return intentos <= 0 && !hasGanado();
    }

    private void cargarFiguraDefault() {
        figuraAhorcado.clear();
        figuraAhorcado.add(
            """
            +---+
            |   |
                |
                |
                |
                |
            ======="""
        );
        figuraAhorcado.add(
            """
            +---+
            |   |
            O   |
                |
                |
                |
            ======="""
        );
        figuraAhorcado.add(
            """
            +---+
            |   |
            O   |
            |   |
                |
                |
            ======="""
        );
        figuraAhorcado.add(
            """
            +---+
            |   |
            O   |
           /|   |
                |
                |
            ======="""
        );
        figuraAhorcado.add(
            """
            +---+
            |   |
            O   |
           /|\\  |
                |
                |
            ======="""
        );
        figuraAhorcado.add(
            """
            +---+
            |   |
            O   |
           /|\\  |
           /    |
                |
            ======="""
        );
        figuraAhorcado.add(
            """
            +---+
            |   |
            O   |
           /|\\  |
           / \\  |
                |
            ======="""
        );
    }

    public String getPalabraSecreta() { return palabraSecreta; }
    public String getPalabraActual()  { return palabraActual; }
    public int getIntentos()          { return intentos; }
    public int getLimiteIntentos()    { return limiteIntentos; }
    public ArrayList<Character> getLetrasUsadas() { return letrasUsadas; }
    public ArrayList<String> getFiguraAhorcado()  { return figuraAhorcado; }

    public void setLimiteIntentos(int limite) {
        if (limite < 1) limite = 1;
        this.limiteIntentos = limite;
        resetearIntentos();
    }

    public void setFiguraAhorcado(ArrayList<String> figura) {
        if (figura != null && figura.size() >= (limiteIntentos + 1)) {
            this.figuraAhorcado = figura;
        }
    }

    protected void setPalabraActual(String nueva) {
        this.palabraActual = nueva;
    }
}

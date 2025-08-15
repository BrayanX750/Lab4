/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;







import java.util.ArrayList;

public abstract class JuegoAhorcadoBase implements JuegoAhorcado {

    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;

    protected JuegoAhorcadoBase() {
        letrasUsadas = new ArrayList<>();
        figuraAhorcado = new ArrayList<>();
        cargarFiguraDefault();
        resetearIntentos();
        palabraSecreta = "";
        palabraActual = "";
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
        palabraActual = construirOcultaDesde(palabraSecreta);
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
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (Character.isLetter(c)) {
                resultado += "_";
            } else {
                resultado += c;
            }
        }
        return resultado;
    }

    public String getFiguraActual() {
        int fallos = limiteIntentos - intentos;
        if (fallos < 0) fallos = 0;
        if (fallos >= figuraAhorcado.size()) fallos = figuraAhorcado.size() - 1;
        return figuraAhorcado.get(fallos);
    }

    protected void resetearIntentos() {
        intentos = limiteIntentos;
    }

    protected void descontarIntento() {
        if (intentos > 0) intentos--;
    }

    protected void limpiarLetrasUsadas() {
        letrasUsadas.clear();
    }

    protected void registrarLetra(char letra) {
        letrasUsadas.add(letra);
    }

    protected boolean letraYaUsada(char letra) {
        for (int i = 0; i < letrasUsadas.size(); i++) {
            if (letrasUsadas.get(i) == letra) return true;
        }
        return false;
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
            "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "======="
        );
        figuraAhorcado.add(
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "======="
        );
        figuraAhorcado.add(
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "======="
        );
        figuraAhorcado.add(
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "/|  |\n" +
            "    |\n" +
            "    |\n" +
            "======="
        );
        figuraAhorcado.add(
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "/|\\ |\n" +
            "    |\n" +
            "    |\n" +
            "======="
        );
        figuraAhorcado.add(
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "/|\\ |\n" +
            "/   |\n" +
            "    |\n" +
            "======="
        );
        figuraAhorcado.add(
            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "/|\\ |\n" +
            "/ \\ |\n" +
            "    |\n" +
            "======="
        );
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public String getPalabraActual() {
        return palabraActual;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getLimiteIntentos() {
        return limiteIntentos;
    }

    public ArrayList<Character> getLetrasUsadas() {
        return letrasUsadas;
    }

    public ArrayList<String> getFiguraAhorcado() {
        return figuraAhorcado;
    }

    public void setLimiteIntentos(int limite) {
        if (limite < 1) limite = 1;
        limiteIntentos = limite;
        resetearIntentos();
    }

    public void setFiguraAhorcado(ArrayList<String> figura) {
        if (figura != null && figura.size() >= (limiteIntentos + 1)) {
            figuraAhorcado = figura;
        }
    }

    protected void setPalabraActual(String nueva) {
        palabraActual = nueva;
    }
}

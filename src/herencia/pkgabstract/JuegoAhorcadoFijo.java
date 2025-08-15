/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private String palabraFija;

    public JuegoAhorcadoFijo(String palabra) {
        this.palabraFija = palabra;
    }

    @Override
    public void inicializarPalabraSecreta() {
        setPalabraSecreta(palabraFija);
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
            if (palabraSecreta.charAt(i) == letra) return true;
        }
        return false;
    }

    @Override
    protected boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }
}
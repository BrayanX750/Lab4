/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPalabrasSecretas admin = new AdminPalabrasSecretas();
            admin.cargarPalabrasIniciales(); 

            JuegoAhorcadoFijo fijo = new JuegoAhorcadoFijo("TELEFONO");
            JuegoAhorcadoAzar azar = new JuegoAhorcadoAzar(admin.getListaPalabras());

            VentanaAhorcado ventana = new VentanaAhorcado(fijo, azar, admin);
            ventana.setVisible(true);
        });
    }
}

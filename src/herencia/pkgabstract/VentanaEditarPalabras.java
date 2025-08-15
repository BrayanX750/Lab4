/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;

import javax.swing.*;
import java.awt.*;

public class VentanaEditarPalabras extends JDialog {
    private AdminPalabrasSecretas admin;
    private JList<String> lista;
    private DefaultListModel<String> modelo;

    public VentanaEditarPalabras(JFrame parent, AdminPalabrasSecretas admin) {
        super(parent, "Editar Palabras", true);
        this.admin = admin;
        setSize(400, 300);
        setLocationRelativeTo(parent);

        modelo = new DefaultListModel<>();
        for (String palabra : admin.getListaPalabras()) {
            modelo.addElement(palabra);
        }
        lista = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(lista);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarSeleccion());

        JPanel abajo = new JPanel();
        abajo.add(btnEditar);

        add(scroll, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);
    }

    private void editarSeleccion() {
        int index = lista.getSelectedIndex();
        if (index >= 0) {
            String nueva = JOptionPane.showInputDialog(this, "Nueva palabra:", modelo.get(index));
            if (nueva != null && !nueva.trim().isEmpty()) {
                admin.cambiarPalabra(index, nueva);
                modelo.set(index, nueva.toUpperCase());
            }
        }
    }
}

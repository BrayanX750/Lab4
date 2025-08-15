/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaEditarPalabras extends JDialog {

    private AdminPalabrasSecretas admin;
    private JComboBox<String> comboPalabras;
    private JTextField txtNueva;
    private JButton btnCambiar;
    private JLabel lblMsg;

    public VentanaEditarPalabras(JFrame padre, AdminPalabrasSecretas admin) {
        super(padre, "Editar Palabras", true);
        this.admin = admin;

        setSize(400, 200);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout(10, 10));

        comboPalabras = new JComboBox<>(admin.getListaPalabras().toArray(new String[0]));
        txtNueva = new JTextField(15);
        btnCambiar = new JButton("Cambiar");
        lblMsg = new JLabel("");

        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("Palabra actual:"));
        panelTop.add(comboPalabras);

        JPanel panelMid = new JPanel();
        panelMid.add(new JLabel("Nueva palabra:"));
        panelMid.add(txtNueva);

        JPanel panelBottom = new JPanel();
        panelBottom.add(btnCambiar);
        panelBottom.add(lblMsg);

        add(panelTop, BorderLayout.NORTH);
        add(panelMid, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        btnCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = comboPalabras.getSelectedIndex();
                String nueva = txtNueva.getText();
                if (admin.editarPalabra(idx, nueva)) {
                    lblMsg.setText("Palabra cambiada!");
                    comboPalabras.removeAllItems();
                    for (String palabra : admin.getListaPalabras()) {
                        comboPalabras.addItem(palabra);
                    }
                    txtNueva.setText("");
                } else {
                    lblMsg.setText("Error: inválida o duplicada");
                }
            }
        });
    }
}

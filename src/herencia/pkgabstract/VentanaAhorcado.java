/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia.pkgabstract;





import javax.swing.*;
import java.awt.*;

public class VentanaAhorcado extends JFrame {

    private JuegoAhorcadoFijo juegoFijo;
    private JuegoAhorcadoAzar juegoAzar;
    private AdminPalabrasSecretas admin;

    private JuegoAhorcadoBase juegoActual;

    private JButton btnFijo;
    private JButton btnAzar;
    private JButton btnNuevo;
    private JLabel lblModo;
    private JLabel lblPalabra;
    private JLabel lblIntentos;
    private JLabel lblMsg;
    private JLabel lblAcertadas;
    private JLabel lblErradas;
    private JTextField txtLetra;
    private JButton btnProbar;
    private JTextArea areaFigura;

    public VentanaAhorcado(JuegoAhorcadoFijo fijo, JuegoAhorcadoAzar azar, AdminPalabrasSecretas admin) {
        this.juegoFijo = fijo;
        this.juegoAzar = azar;
        this.admin = admin;

        setTitle("Ahorcado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnFijo = new JButton("Modo Fijo");
        btnAzar = new JButton("Modo Azar");
        btnNuevo = new JButton("Nuevo juego");
        JButton btnEditar = new JButton("Editar palabras");
        lblModo = new JLabel("Modo: ");

        top.add(btnFijo);
        top.add(btnAzar);
        top.add(btnNuevo);
        top.add(btnEditar);
        top.add(lblModo);

        add(top, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel izquierda = new JPanel();
        izquierda.setLayout(new BoxLayout(izquierda, BoxLayout.Y_AXIS));

        lblPalabra = new JLabel("_");
        lblPalabra.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblPalabra.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel filaIntentos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel t1 = new JLabel("Intentos restantes: ");
        lblIntentos = new JLabel("6");
        filaIntentos.add(t1);
        filaIntentos.add(lblIntentos);
        filaIntentos.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel filaIngreso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel t2 = new JLabel("Ingrese una letra:");
        txtLetra = new JTextField(5);
        btnProbar = new JButton("Probar");
        filaIngreso.add(t2);
        filaIngreso.add(txtLetra);
        filaIngreso.add(btnProbar);
        filaIngreso.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblMsg = new JLabel("Listo");
        lblMsg.setForeground(new Color(30, 30, 30));
        lblMsg.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblAcertadas = new JLabel("Acertadas: ");
        lblErradas = new JLabel("Erradas: ");
        lblAcertadas.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblErradas.setAlignmentX(Component.LEFT_ALIGNMENT);

        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(lblPalabra);
        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(filaIntentos);
        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(filaIngreso);
        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(lblMsg);
        izquierda.add(Box.createVerticalStrut(10));
        izquierda.add(lblAcertadas);
        izquierda.add(Box.createVerticalStrut(5));
        izquierda.add(lblErradas);

        JPanel derecha = new JPanel(new BorderLayout());
        areaFigura = new JTextArea();
        areaFigura.setEditable(false);
        areaFigura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane sp = new JScrollPane(areaFigura);
        derecha.add(sp, BorderLayout.CENTER);

        centro.add(izquierda);
        centro.add(derecha);
        add(centro, BorderLayout.CENTER);

        btnFijo.addActionListener(e -> {
            juegoActual = juegoFijo;
            lblModo.setText("Modo: Fijo");
            iniciarJuegoActual();
        });

        btnAzar.addActionListener(e -> {
            juegoActual = juegoAzar;
            lblModo.setText("Modo: Azar");
            iniciarJuegoActual();
        });

        btnNuevo.addActionListener(e -> iniciarJuegoActual());

        btnEditar.addActionListener(e -> {
            VentanaEditarPalabras v = new VentanaEditarPalabras(this, admin);
            v.setVisible(true);
        });

        btnProbar.addActionListener(e -> probarEntrada());
        txtLetra.addActionListener(e -> probarEntrada());

        juegoActual = juegoFijo;
        lblModo.setText("Modo: Fijo");
        iniciarJuegoActual();
    }

    private void iniciarJuegoActual() {
        if (juegoActual == null) return;
        juegoActual.jugar();
        actualizarVistaInicio();
    }

    private void actualizarVistaInicio() {
        lblPalabra.setText(espaciar(juegoActual.getPalabraActual()));
        lblIntentos.setText(String.valueOf(juegoActual.getIntentos()));
        areaFigura.setText(juegoActual.getFiguraActual());
        lblMsg.setText("Ingrese una letra");
        actualizarListas();
        habilitarEntrada(true);
        txtLetra.setText("");
        txtLetra.requestFocusInWindow();
    }

    private void probarEntrada() {
        if (juegoActual == null) return;
        String s = txtLetra.getText();
        if (s == null || s.trim().isEmpty()) {
            lblMsg.setText("Ingrese una letra");
            return;
        }
        char c = s.trim().toUpperCase().charAt(0);
        int r = juegoActual.procesarIntento(c);
        if (r == -1) {
            lblMsg.setText("Letra inválida o repetida");
        } else if (r == 1) {
            lblMsg.setText("¡Bien! Acierto");
        } else {
            lblMsg.setText("Incorrecta. Intentos restantes");
        }
        refrescar();
        txtLetra.setText("");
        if (juegoActual.hasGanado()) {
            lblMsg.setText("¡Ganaste! La palabra era: " + juegoActual.getPalabraSecreta());
            habilitarEntrada(false);
        } else if (juegoActual.sinIntentos()) {
            lblMsg.setText("Perdiste. La palabra era: " + juegoActual.getPalabraSecreta());
            habilitarEntrada(false);
        }
    }

    private void refrescar() {
        lblPalabra.setText(espaciar(juegoActual.getPalabraActual()));
        lblIntentos.setText(String.valueOf(juegoActual.getIntentos()));
        areaFigura.setText(juegoActual.getFiguraActual());
        actualizarListas();
    }

    private void actualizarListas() {
        String ac = "Acertadas: ";
        String er = "Erradas: ";
        for (int i = 0; i < juegoActual.getLetrasUsadas().size(); i++) {
            char ch = juegoActual.getLetrasUsadas().get(i);
            if (contiene(juegoActual.getPalabraSecreta(), ch)) ac += ch + " ";
            else er += ch + " ";
        }
        lblAcertadas.setText(ac);
        lblErradas.setText(er);
    }

    private boolean contiene(String s, char c) {
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == c) return true;
        return false;
    }

    private String espaciar(String s) {
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            r += s.charAt(i);
            if (i < s.length() - 1) r += " ";
        }
        return r;
    }

    private void habilitarEntrada(boolean on) {
        txtLetra.setEnabled(on);
        btnProbar.setEnabled(on);
    }
}

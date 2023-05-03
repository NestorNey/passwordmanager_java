package lunarsoftware.gdc.gui.auth;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Interfaz extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JTextField textFieldPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfaz frame = new Interfaz();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Interfaz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsuario.setBounds(63, 78, 61, 17);
        contentPane.add(lblUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(63, 128, 81, 17);
        contentPane.add(lblPassword);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(166, 77, 186, 20);
        contentPane.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(166, 127, 186, 20);
        contentPane.add(textFieldPassword);

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMensaje.setBounds(166, 168, 186, 17);
        contentPane.add(lblMensaje);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String usuario = textFieldUsuario.getText();
                String password = textFieldPassword.getText();

                if (usuario.equals("admin") && password.equals("1234")) {
                    lblMensaje.setForeground(Color.GREEN);
                    lblMensaje.setText("Acceso concedido");
                } else {
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("Acceso denegado");
                }
            }
        });
        btnAceptar.setBounds(166, 196, 89, 23);
        contentPane.add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnCancelar.setBounds(263, 196, 89, 23);
        contentPane.add(btnCancelar);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registro registro = new Registro();
                registro.setVisible(true);
            }
        });
        btnRegistrar.setBounds(63, 196, 89, 23);
        contentPane.add(btnRegistrar);
    }
}
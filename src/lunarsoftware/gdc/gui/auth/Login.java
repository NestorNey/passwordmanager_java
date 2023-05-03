package lunarsoftware.gdc.gui.auth;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import lunarsoftware.gdc.gui.Major;
import lunarsoftware.gdc.gui.MoverFrame;
import lunarsoftware.lib.jdbmanager.JdbManager;

import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Insets;

import java.awt.Component;
import java.awt.Label;

public class Login extends JFrame {
	private JdbManager manager;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String userPath;

	/**
	 * Iniziador del frame(Ventana)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creacion del frame(Ventana)
	 */
	public Login() {
		userPath = System.getProperty("user.dir");
		manager = new JdbManager(JdbManager.AUTH, null);
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 356);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setSize(new Dimension(0, 500000));
		contentPane.setBackground(new Color(45, 3, 59));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el label
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setBackground(new Color(255, 70, 70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setBackground(new Color(45, 3, 60));
			}
			/**
			 * Cierre de ventana y finalizacion del programa
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				dispatchEvent(new WindowEvent(new Registro(), WindowEvent.WINDOW_CLOSING));
				System.exit(0);
			}
		});
		lblNewLabel.setBackground(new Color(45, 3, 60));
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setSize(new Dimension(100, 1000));
		lblNewLabel.setIcon(new ImageIcon(userPath + "\\access\\icons\\frameslash\\close.png"));
		lblNewLabel.setBounds(340, 0, 37, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el label
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setBackground(new Color(250, 75, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setBackground(new Color(45, 3, 60));
			}
			/**
			 * Minimizacion de la ventana
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(userPath + "\\access\\icons\\frameslash\\minimize.png"));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setSize(new Dimension(100, 1000));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(new Color(45, 3, 60));
		lblNewLabel_1.setBounds(303, 0, 37, 24);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setAlignmentY(Component.TOP_ALIGNMENT);
		textField.setAlignmentX(Component.LEFT_ALIGNMENT);
		textField.setMargin(new Insets(1, 1, 1, 1));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField.setForeground(new Color(239, 239, 239));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Correo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(239, 239, 239)));
		textField.setBackground(new Color(45, 3, 59));
		textField.setBounds(40, 99, 295, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Iniciar sesion");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(239, 239, 239));
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 35));
		lblNewLabel_2.setBounds(40, 40, 295, 46);
		contentPane.add(lblNewLabel_2);
		JLabel lblNewLabel3 = new JLabel("Las credenciales son invalidas");
		lblNewLabel3.setVisible(false);
		lblNewLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setForeground(new Color(255, 128, 128));
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel3.setBounds(37, 235, 309, 14);
		contentPane.add(lblNewLabel3);
		
		textField_1 = new JTextField();
		textField_1.setMargin(new Insets(1, 1, 1, 1));
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setForeground(new Color(239, 239, 239));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_1.setColumns(10);
		textField_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(239, 239, 239)));
		textField_1.setBackground(new Color(45, 3, 59));
		textField_1.setBounds(40, 165, 295, 42);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Siguiente");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el label
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3.setBackground(new Color(85, 5, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.setBackground(new Color(68, 4, 89));
			}
			/**
			 * Comprobacion de credenciales
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] objsToSearch = {"mail", "pass"};
				String[] objsReturn;
				objsReturn = manager.getObjectsInFile(objsToSearch, 2);
				
				if(objsReturn[1].equals(textField.getText()) && objsReturn[3].equals(textField_1.getText())) {
					dispatchEvent(new WindowEvent(new Registro(), WindowEvent.WINDOW_CLOSING));
					Major major = new Major();
					major.main(null);
				}else {
					lblNewLabel3.setVisible(true);
				}
			}
		});
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(68, 4, 89));
		lblNewLabel_3.setForeground(new Color(212, 212, 212));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(127, 269, 126, 42);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Manual de usuario");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			/**
			 * Funcion para abrir el manual
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().open(new File(userPath + "\\Manual usuario.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(185, 185, 185));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(280, 331, 87, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Ayuda");
		lblNewLabel_4_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(new Color(185, 185, 185));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4_1.setBounds(233, 331, 37, 14);
		contentPane.add(lblNewLabel_4_1);
		
		Label label = new Label("");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//Agrega la funcionalidad de mover el frame en un label
		MoverFrame mov = new MoverFrame(this);
		label.addMouseListener(mov);
		label.addMouseMotionListener(mov);
		label.setBackground(new Color(63, 3, 82));
		label.setBounds(0, 0, 303, 24);
		contentPane.add(label);
		
		setLocationRelativeTo(null);
	}
}

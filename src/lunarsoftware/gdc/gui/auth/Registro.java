package lunarsoftware.gdc.gui.auth;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lunarsoftware.gdc.Tools;
import lunarsoftware.gdc.gui.MoverFrame;

import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class Registro extends JFrame {
	boolean mailComp;
	boolean passComp;
	boolean compPassComp;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String userPath;

	/**
	 * Iniziador del frame(Ventana)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		userPath = System.getProperty("user.dir");
		mailComp = false;
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 484);
		contentPane = new JPanel();
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
		
		JLabel lblNewLabel4 = new JLabel("El correo es incorrecto");
		lblNewLabel4.setVisible(false);
		lblNewLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel4.setForeground(new Color(255, 128, 128));
		lblNewLabel4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel4.setBounds(36, 293, 309, 14);
		contentPane.add(lblNewLabel4);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			/**
			 * Aqui se comprueba que el correo contenga un dominio valido
			 */
			@Override public void changedUpdate(DocumentEvent e) {}
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!Tools.error.getHasArrobaError(textField)&&
						!Tools.error.getArrobaError(textField) &&
							!Tools.error.getDomainError(textField)) {
					lblNewLabel4.setVisible(false);
					setMailComp(true);
				}else {setMailComp(false);lblNewLabel4.setVisible(true);}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!Tools.error.getArrobaError(textField) &&
						!Tools.error.getDomainError(textField)&&
								!Tools.error.getHasArrobaError(textField)) {
					lblNewLabel4.setVisible(false);
					setMailComp(true);
				}else {setMailComp(false);lblNewLabel4.setVisible(true);}
			}
		});
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
		
		JLabel lblNewLabel_2 = new JLabel("Registro");
		lblNewLabel_2.setForeground(new Color(239, 239, 239));
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(114, 40, 149, 46);
		contentPane.add(lblNewLabel_2);
		JLabel lblNewLabel3 = new JLabel("La contraseña debe ser mayor de 8 y contener numeros");
		lblNewLabel3.setVisible(false);
		lblNewLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setForeground(new Color(255, 128, 128));
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel3.setBounds(37, 322, 309, 14);
		contentPane.add(lblNewLabel3);
		
		textField_1 = new JTextField();
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void changedUpdate(DocumentEvent e) {}
			/**
			 * Aqui el software comprueba que la contraseña se haya escrito
			 * de manera correcta (minimo 8 caracteres y que contenga numeros)
			 */
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!Tools.error.getMinLngPassError(textField_1)&&
						!Tools.error.getPassNumError(textField_1)) {
					lblNewLabel3.setVisible(false);
					setPassComp(true);
				}else {lblNewLabel3.setVisible(true);setPassComp(false);}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!Tools.error.getMinLngPassError(textField_1)&&
						!Tools.error.getPassNumError(textField_1)) {
					lblNewLabel3.setVisible(false);
					setPassComp(true);
				}else {lblNewLabel3.setVisible(true);setPassComp(false);}
			}
		});
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
		
		JLabel lblNewLabel6 = new JLabel("Las contraseñas no son iguales");
		lblNewLabel6.setVisible(false);
		lblNewLabel6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel6.setForeground(new Color(255, 128, 128));
		lblNewLabel6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel6.setBounds(37, 348, 309, 14);
		contentPane.add(lblNewLabel6);
		
		textField_2 = new JTextField();
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void changedUpdate(DocumentEvent e) {}
			/**
			 * Aqui el software comprueba que las contraseñas coincidan
			 */
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(textField_2.getText().equals(textField_1.getText())) {
					lblNewLabel6.setVisible(false);
					setCompPassComp(true);
				}else {lblNewLabel6.setVisible(true);setCompPassComp(false);}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(textField_2.getText().equals(textField_1.getText())) {
					lblNewLabel6.setVisible(false);
					setCompPassComp(true);
				}else {lblNewLabel6.setVisible(true);setCompPassComp(false);}
			}
		});
		textField_2.setMargin(new Insets(1, 1, 1, 1));
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setForeground(new Color(239, 239, 239));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_2.setColumns(10);
		textField_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Confirmar contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(239, 239, 239)));
		textField_2.setBackground(new Color(45, 3, 59));
		textField_2.setBounds(40, 232, 295, 42);
		contentPane.add(textField_2);
		
		
		JLabel lblNewLabel5 = new JLabel("Completa el registro de manera correcta");
		lblNewLabel5.setVisible(false);
		lblNewLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel5.setForeground(new Color(255, 128, 128));
		lblNewLabel5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel5.setBounds(37, 431, 309, 14);
		contentPane.add(lblNewLabel5);
		
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
			 * Aqui el software comprueba que el campo de correo y contraseña
			 * hayan sido validados de manera correcta.
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(getMailComp() && getPassComp() && getCompPassComp()) {
					lblNewLabel5.setVisible(false);
					dispatchEvent(new WindowEvent(new Registro(), WindowEvent.WINDOW_CLOSING));
					GetName frame = new GetName(textField.getText(), textField_1.getText());
					frame.main(null, textField.getText(), textField_1.getText());
				}else {
					lblNewLabel5.setVisible(true);
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
		lblNewLabel_3.setBounds(127, 377, 126, 42);
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
		lblNewLabel_4.setBounds(280, 459, 87, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Ayuda");
		lblNewLabel_4_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(new Color(185, 185, 185));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4_1.setBounds(233, 459, 37, 14);
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
	
	public void setMailComp(boolean mailComp) {
		this.mailComp = mailComp;
	}
	public boolean getMailComp() {
		return mailComp;
	}
	
	public void setPassComp(boolean passComp) {
		this.passComp = passComp;
	} 
	public boolean getPassComp() {
		return passComp;
	}
	
	public void setCompPassComp(boolean compPassComp) {
		this.compPassComp = compPassComp;
	}
	public boolean getCompPassComp() {
		return compPassComp;
	}
}

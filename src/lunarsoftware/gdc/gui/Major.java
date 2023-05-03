package lunarsoftware.gdc.gui;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import lunarsoftware.gdc.Tools;
import lunarsoftware.gdc.gui.auth.Registro;
import lunarsoftware.lib.jdbmanager.JdbManager;
import lunarsoftware.lib.jdbmanager.misc.Misc;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Major extends JFrame {
	private int cdp;
	private int pac;
	
	private JdbManager manager;
	private JPanel contentPane;
	private JTextField textField;
	private String userPath;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel showPanel;
	private JPanel barraPanel;
	private JPanel addPanel;
	private JPanel pagesPanel;

	/**
	 * Iniziador del frame(Ventana)
	 */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Alerta: El software esta en modo desarrollo, los datos aqui guardados\n"
                + ""
                + ""
                + "no son de ninguna forma encriptados, porfavor no ingrese ningun tipo\n"
                + "de dato personal, el programa solo muestra la funcionalidad principal."
                + "", null, 2, null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Major frame = new Major();
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
	public Major() {
		userPath = System.getProperty("user.dir");
		Misc misc = new Misc();
		pac = 1;
		int cdI = misc.getCdi();
		cdp = (int) Math.ceil(((float)cdI) / 15);
		
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 244, 244));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		Label label = new Label("");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//Agrega la funcionalidad de mover el frame en un label
		MoverFrame mov = new MoverFrame(this);
		label.addMouseListener(mov);
		label.addMouseMotionListener(mov);
		label.setBackground(new Color(63, 3, 82));
		label.setBounds(0, 0, 1226, 24);
		contentPane.add(label);
		
		JLabel ButtonExit = new JLabel("");
		ButtonExit.setOpaque(true);
		ButtonExit.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el label
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				ButtonExit.setBackground(new Color(255, 70, 70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ButtonExit.setBackground(new Color(45, 3, 60));
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
		ButtonExit.setBackground(new Color(45, 3, 60));
		ButtonExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ButtonExit.setToolTipText("");
		ButtonExit.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonExit.setHorizontalTextPosition(SwingConstants.CENTER);
		ButtonExit.setSize(new Dimension(100, 1000));
		ButtonExit.setIcon(new ImageIcon(userPath + "\\access\\icons\\frameslash\\close.png"));
		ButtonExit.setBounds(1263, 0, 37, 24);
		contentPane.add(ButtonExit);
		
		JLabel ButtonMinimize = new JLabel("");
		ButtonMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ButtonMinimize.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el label
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				ButtonMinimize.setBackground(new Color(250, 75, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ButtonMinimize.setBackground(new Color(45, 3, 60));
			}
			/**
			 * Minimizacion de la ventana
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		ButtonMinimize.setIcon(new ImageIcon(userPath + "\\access\\icons\\frameslash\\minimize.png"));
		ButtonMinimize.setToolTipText("");
		ButtonMinimize.setSize(new Dimension(100, 1000));
		ButtonMinimize.setOpaque(true);
		ButtonMinimize.setHorizontalTextPosition(SwingConstants.CENTER);
		ButtonMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonMinimize.setBackground(new Color(45, 3, 60));
		ButtonMinimize.setBounds(1226, 0, 37, 24);
		contentPane.add(ButtonMinimize);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 3, 59));
		panel.setBounds(0, 24, 261, 676);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1.setBackground(new Color(168, 16, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_1.setBackground(new Color(129, 12, 168));
			}
		});
		panel_1.setBackground(new Color(129, 12, 168));
		panel_1.setBounds(10, 11, 241, 39);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(userPath + "\\access\\icons\\panel\\metrics.png"));
		lblNewLabel_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(0, 0, 47, 39);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblEstadisticas = new JLabel("Estadisticas <C-N-D>");
		lblEstadisticas.setForeground(new Color(233, 233, 233));
		lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 15));
		lblEstadisticas.setBounds(43, 0, 160, 39);
		panel_1.add(lblEstadisticas);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1_1.setBackground(new Color(168, 16, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_1_1.setBackground(new Color(129, 12, 168));
			}
		});
		panel_1_1.setBackground(new Color(129, 12, 168));
		panel_1_1.setBounds(10, 84, 241, 39);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 47, 39);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(userPath + "\\access\\icons\\panel\\database.png"));
		panel_1_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Cuentas <C-D.S-C>");
		lblNewLabel.setBounds(43, 0, 168, 39);
		lblNewLabel.setForeground(new Color(233, 233, 233));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		panel_1_1.add(lblNewLabel);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.addMouseListener(new MouseAdapter() {
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1_2.setBackground(new Color(168, 16, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_1_2.setBackground(new Color(129, 12, 168));
			}
		});
		panel_1_2.setBackground(new Color(129, 12, 168));
		panel_1_2.setBounds(10, 157, 241, 39);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(userPath + "\\access\\icons\\panel\\category.png"));
		lblNewLabel_1_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(0, 0, 47, 39);
		panel_1_2.add(lblNewLabel_1_1_1);
		
		JLabel lblCategorias = new JLabel("Categorias <C-N-D>");
		lblCategorias.setForeground(new Color(233, 233, 233));
		lblCategorias.setFont(new Font("Arial", Font.BOLD, 15));
		lblCategorias.setBounds(43, 0, 169, 39);
		panel_1_2.add(lblCategorias);
		
		JPanel panel_1_4 = new JPanel();
		panel_1_4.setBounds(250, 11, 6, 39);
		panel.add(panel_1_4);
		panel_1_4.setBackground(new Color(70, 3, 92));
		
		JPanel panel_1_4_1 = new JPanel();
		panel_1_4_1.setBackground(new Color(70, 3, 92));
		panel_1_4_1.setBounds(250, 84, 6, 39);
		panel.add(panel_1_4_1);
		
		JPanel panel_1_4_1_1 = new JPanel();
		panel_1_4_1_1.setBackground(new Color(70, 3, 92));
		panel_1_4_1_1.setBounds(250, 157, 6, 39);
		panel.add(panel_1_4_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(166, 166, 166));
		panel_2.setBounds(259, 24, 6, 676);
		contentPane.add(panel_2);
		
		showPanel = new JPanel();
		showItems();
		contentPane.add(showPanel);
		
		barraPanel = new JPanel();
		barraPanel.setBackground(new Color(192, 192, 192));
		barraPanel.setBounds(265, 25, 1032, 45);
		contentPane.add(barraPanel);
		barraPanel.setLayout(null);
		
		
		/*
		 * 	Funcion de buscador <E-D>
		 * 
			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setIcon(new ImageIcon(userPath + "\\access\\icons\\panel\\search.png"));
			lblNewLabel_2.setBounds(300, 10, 29, 24);
			barraPanel.add(lblNewLabel_2);
			
			textField = new JTextField();
			textField.getDocument().addDocumentListener(new DocumentListener() {
				@Override public void changedUpdate(DocumentEvent e) {}
				@Override
				public void insertUpdate(DocumentEvent e) {
					
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					
				}
			});
			textField.setMargin(new Insets(2, 18, 2, 2));
			textField.setBackground(new Color(233, 233, 233));
			textField.setText("Buscar");
			textField.setForeground(new Color(73, 73, 73));
			textField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
			textField.setColumns(10);
			textField.setBorder(null);
			textField.setBounds(21, 10, 279, 24);
			barraPanel.add(textField);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBackground(new Color(233, 233, 233));
			panel_5.setBounds(8, 9, 320, 26);
			barraPanel.add(panel_5);
		*/
		addPanel = new JPanel();
		addPanel.addMouseListener(new MouseAdapter() {
			/**
			 * Aqui el software llama a las funciones necesarias para mostrar el
			 * panel para añadir un nuevo item(Cuenta o contraseñas)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				showPanel.removeAll();
				showPanel.revalidate();
				showPanel.repaint();
				barraPanel.remove(addPanel);
				barraPanel.revalidate();
				barraPanel.repaint();
				showAddPanel();
			}
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				addPanel.setBackground(new Color(17, 196, 62));
			}
			public void mouseExited(MouseEvent e) {
				addPanel.setBackground(new Color(16, 182, 57));
			}
		});
		addPanel.setBackground(new Color(16, 182, 57));
		addPanel.setBounds(930, 10, 92, 28);
		barraPanel.add(addPanel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		addPanel.add(lblNewLabel_5);
		lblNewLabel_5.setBackground(new Color(0, 198, 50));
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_5.setIcon(new ImageIcon(userPath + "\\access\\icons\\panel\\add.png"));
		
		JLabel lblNewLabel_5_1 = new JLabel("Añadir");
		lblNewLabel_5_1.setForeground(new Color(233, 233, 233));
		addPanel.add(lblNewLabel_5_1);
		lblNewLabel_5_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_5_1.setBackground(new Color(0, 198, 50));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(166, 166, 166));
		panel_2_1.setBounds(261, 696, 1039, 5);
		contentPane.add(panel_2_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(new Color(166, 166, 166));
		panel_2_2.setBounds(1297, 24, 6, 676);
		contentPane.add(panel_2_2);
		
		//Llamada al metodo para generar las paginas necesarias
		pagesPanel = new JPanel();
		generatePages();
		
		contentPane.add(pagesPanel);
		
		
		setLocationRelativeTo(null);
	}
	
	public void generatePages() {
		/**
		 * Aqui el software itera sobre los items y los divide en multiplos de 20
		 * para generar las paginas(< 1 2 3 4 5 6 7 8 X>)
		 */
		pagesPanel.removeAll();
		pagesPanel.revalidate();
		pagesPanel.repaint();
		FlowLayout flowLayout = (FlowLayout) pagesPanel.getLayout();
		flowLayout.setVgap(10);
		pagesPanel.setBackground(new Color(244, 244, 244));
		pagesPanel.setBounds(275, 70, 1012, 35);
		contentPane.add(pagesPanel);
		
		JLabel substracLabel = new JLabel("<");
		substracLabel.addMouseListener(new MouseAdapter() {
			/**
			 * Restador de pagina actual
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pac != 1) {
					pac --;
					generatePages();
					showItems();
					contentPane.add(pagesPanel);
				}
				showItems();
			}
		});
		
		int inc;int fnl = pac;for(fnl = pac;!(fnl%20 == 0);fnl +=1);inc = fnl - 19;
		
		/**
		 * Generador de paginas
		 */
		pagesPanel.add(substracLabel);
		for(int i=inc;i<=fnl + 1;i++) {
			if(i > cdp) {
				break;
			}else {
				JLabel numberOfPage;
				if(i <= fnl) {
					numberOfPage = new JLabel(""+i);
					final int param = i;
					numberOfPage.addMouseListener(new MouseAdapter() {
						/**
						 * Aqui se le añade la pagina correcta a la que tiene que apuntar
						 * al ser precionado
						 */
						@Override
						public void mouseClicked(MouseEvent e) {
							pac = param;
							generatePages();
							showItems();
							contentPane.add(pagesPanel);
						}
					});
					pagesPanel.add(numberOfPage);
				}else {
					/**
					 * Cuando el numero de paginas exede las 20 paginas se le
					 * agrega un label " a - b" donde a indica donde incia la siguiente
					 * sccion y b donde termina
					 */
					if((i + 19) > cdp) {
						numberOfPage = new JLabel(""+ i +"-"+cdp);
						pagesPanel.add(numberOfPage);
					}else {
						numberOfPage = new JLabel(""+ i + " - " + (i + 19));
						pagesPanel.add(numberOfPage);
					}
				}
				
				/**
				 * Si el label a agregar es igual a la pagina en la que se encuentra
				 * se le agregara un efecto de seleccion
				 */
				if(i == pac) {
					numberOfPage.setBorder(new LineBorder(new Color(212, 212, 212), 1, true));
					numberOfPage.setOpaque(true);
					numberOfPage.setBackground(new Color(231, 231, 231));
					pagesPanel.add(numberOfPage);
				}
			}
		}
		
		/**
		 * Sumador de pagina actual
		 */
		JLabel plussLabel = new JLabel(">");
		plussLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pac < cdp) {
					pac ++;
					generatePages();
					contentPane.add(pagesPanel);
				}
				showItems();
			}
		});
		pagesPanel.add(plussLabel);
		
		pagesPanel.revalidate();
		pagesPanel.repaint();
	}
	
	public void showAddPanel() {
		/**
		 * Genera un formulario para la obtencion de los datos del
		 * item(Cuenta o contraseña)
		 */
		showPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		showPanel.setBackground(new Color(228, 228, 228));
		showPanel.setBounds(276, 105, 703, 299);
		contentPane.add(showPanel);
		showPanel.setLayout(null);
		
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(40, 55, 98, 98);
		showPanel.add(imgPanel);
		imgPanel.setLayout(null);
		
		JLabel icoLabel = new JLabel("");
		icoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		icoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		icoLabel.setIcon(new ImageIcon(userPath + "\\access\\icons\\acounticons\\default.png"));
		icoLabel.setBounds(0, 0, 98, 98);
		imgPanel.add(icoLabel);
		
		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		nombreLabel.setBounds(181, 40, 90, 25);
		showPanel.add(nombreLabel);
		
		JTextField nombreField = new JTextField();
		nombreField.setBounds(254, 40, 205, 25);
		showPanel.add(nombreField);
		nombreField.setColumns(10);
		
		JLabel correoLabel = new JLabel("Correo o nombre de usuario:");
		correoLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		correoLabel.setBounds(181, 76, 205, 25);
		showPanel.add(correoLabel);
		
		JTextField correoField = new JTextField();
		correoField.setColumns(10);
		correoField.setBounds(387, 77, 205, 25);
		showPanel.add(correoField);
		
		JLabel passLabel = new JLabel("Contraseña:");
		passLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		passLabel.setBounds(181, 113, 90, 25);
		showPanel.add(passLabel);
		
		JLabel typeLabel = new JLabel("Tipo:");
		typeLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		typeLabel.setBounds(181, 151, 90, 25);
		showPanel.add(typeLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			/**
			 * Generacion de imagen segun seleccion
			 */
			public void itemStateChanged(ItemEvent e) {
				icoLabel.removeAll();
				icoLabel.setIcon(new ImageIcon(userPath + "\\access\\icons\\acounticons\\" + (String) comboBox.getSelectedItem() + ".png"));
				icoLabel.revalidate();
				icoLabel.repaint();
			}
		});
		comboBox.addItem("Otro");
		comboBox.addItem("Netflix");
		comboBox.addItem("Facebook");
		comboBox.addItem("Twitter");
		comboBox.addItem("Instagram");
		comboBox.addItem("GitHub");
		comboBox.addItem("Office");
		comboBox.addItem("Gmail");
		comboBox.setBounds(229, 150, 205, 25);
		showPanel.add(comboBox);
		
		JTextField passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(277, 113, 205, 25);
		showPanel.add(passField);
		
		JLabel errorLabel = new JLabel("El nombre de la cuenta ya existe, intenta con otro nombre...");
		errorLabel.setVisible(false);
		errorLabel.setForeground(new Color(234, 0, 0));
		errorLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		errorLabel.setBounds(180, 206, 402, 14);
		showPanel.add(errorLabel);
		
		JPanel panelAdd = new JPanel();
		panelAdd.addMouseListener(new MouseAdapter() {
			/**
			 * Aqui el software añade el item(Cuenta o contraseña)
			 * a la base de datos el programa
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				Misc misc = new Misc();
				
				if(misc.writeItemMetada(nombreField.getText())) {
					JdbManager itemManager = new JdbManager(JdbManager.ITEMS, JdbManager.MODIFIABLE, nombreField.getText());
					
					itemManager.writeObj("Name", nombreField.getText());
					itemManager.writeObj("Mail", correoField.getText());
					itemManager.writeObj("Pass", passField.getText());
					itemManager.writeObj("Type", (String) comboBox.getSelectedItem());
					showPanel.removeAll();
					showPanel.revalidate();
					showPanel.repaint();			
					barraPanel.remove(addPanel);
					barraPanel.revalidate();
					barraPanel.repaint();
					showAddButton();
					showItems();
					int cdI = misc.getCdi();
					cdp = (int) Math.ceil(((float)cdI) / 15);
					generatePages();
					errorLabel.setVisible(false);
				}else {
					errorLabel.setVisible(true);
				}
			}
			
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAdd.setBackground(new Color(17, 196, 62));
			}
			public void mouseExited(MouseEvent e) {
				panelAdd.setBackground(new Color(16, 182, 57));
			}
		});
		panelAdd.setBackground(new Color(16, 182, 57));
		panelAdd.setBounds(581, 248, 92, 28);
		showPanel.add(panelAdd);
		
		JLabel ButtonAdd = new JLabel("Añadir");	
		ButtonAdd.setBackground(new Color(16, 182, 57));
		ButtonAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		ButtonAdd.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonAdd.setForeground(new Color(233, 233, 233));
		ButtonAdd.setFont(new Font("Arial", Font.BOLD, 13));
		ButtonAdd.setBackground(new Color(0, 198, 50));
		panelAdd.add(ButtonAdd);
		
		JPanel panelCancel = new JPanel();
		panelCancel.setOpaque(true);
		panelCancel.addMouseListener(new MouseAdapter() {
			/**
			 * Vuelve a generar los items y cancela la operacion
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				showItems();
				showAddButton();
			}
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panelCancel.setBackground(new Color(237, 50, 50));
			}
			public void mouseExited(MouseEvent e) {
				panelCancel.setBackground(new Color(232, 21, 21));
			}
		});
		panelCancel.setBackground(new Color(232, 21, 21));
		panelCancel.setBounds(460, 248, 92, 28);
		showPanel.add(panelCancel);
		
		JLabel ButtonCancel = new JLabel("Cancelar");
		ButtonCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		ButtonCancel.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonCancel.setForeground(new Color(233, 233, 233));
		ButtonCancel.setFont(new Font("Arial", Font.BOLD, 13));
		ButtonCancel.setBackground(new Color(232, 21, 21));
		panelCancel.add(ButtonCancel);
	}
	
	public void showAddButton() {
		/**
		 * Vuelve a generar el boton de Añadir item(Cuenta o contraseña)
		 */
		addPanel = new JPanel();
		addPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				showPanel.removeAll();
				showPanel.revalidate();
				showPanel.repaint();
				barraPanel.remove(addPanel);
				barraPanel.revalidate();
				barraPanel.repaint();
				showAddPanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				addPanel.setBackground(new Color(17, 196, 62));
			}
			public void mouseExited(MouseEvent e) {
				addPanel.setBackground(new Color(16, 182, 57));
			}
		});
		addPanel.setBackground(new Color(16, 182, 57));
		addPanel.setBounds(930, 10, 92, 28);
		barraPanel.add(addPanel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		addPanel.add(lblNewLabel_5);
		lblNewLabel_5.setBackground(new Color(0, 198, 50));
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_5.setIcon(new ImageIcon(userPath + "\\access\\icons\\panel\\add.png"));
		
		JLabel lblNewLabel_5_1 = new JLabel("Añadir");
		lblNewLabel_5_1.setForeground(new Color(233, 233, 233));
		addPanel.add(lblNewLabel_5_1);
		lblNewLabel_5_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_5_1.setBackground(new Color(0, 198, 50));
	}
	
	public void showItems() {
		/**
		 * Genera el panel principal y genera los items(Cuentas o contraseñas)
		 * que se hallan en la base de datos
		 */
		showPanel.removeAll();
		showPanel.revalidate();
		showPanel.repaint();
		String userPath = System.getProperty("user.dir");
		JdbManager manager = new JdbManager(JdbManager.METADATA, "items");	
		
		showPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		showPanel.setBackground(new Color(233, 233, 233));
		showPanel.setBounds(276, 105, 1011, 559);
		showPanel.setLayout(new GridLayout(0, 5, 15, 40));
		int inc = (pac * 15) - 14;
		int fnl = pac * 15;
		
		for(int i=inc;i<=fnl;i++) {
			String[] objToSearch = {"item" + i};
			String[] objReturn;
			objReturn = manager.getObjectsInFile(objToSearch, 1);
			JdbManager item = new JdbManager(JdbManager.ITEMS, objReturn[1]);
			
			if(objReturn[1] != null) {
				/**
				 * Si el objeto existe se agrega el panel de mandera normal
				 * y toma las propiedades del item(Cuenta o contraseña) 
				 * para generarlo
				 */
				String[] panelObjs = {"Name", "Type"};
				String[] panelObjsRtn;
				panelObjsRtn = item.getObjectsInFile(panelObjs, 2);
				
				JPanel subPanel = new JPanel();
				subPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						subPanel.setBackground(new Color(245, 245, 245));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						subPanel.setBackground(new Color(255, 255, 255));
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						showItemPanel(((JLabel) subPanel.getComponent(1)).getText());
					}
				});
				subPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				subPanel.setBackground(new Color(255, 255, 255));
				SpringLayout sl_subPanel = new SpringLayout();
				subPanel.setLayout(sl_subPanel);
				
				JLabel icon = new JLabel("");
				sl_subPanel.putConstraint(SpringLayout.NORTH, icon, 22, SpringLayout.NORTH, subPanel);
				sl_subPanel.putConstraint(SpringLayout.WEST, icon, 45, SpringLayout.WEST, subPanel);
				sl_subPanel.putConstraint(SpringLayout.EAST, icon, -47, SpringLayout.EAST, subPanel);
				subPanel.add(icon);
				icon.setHorizontalAlignment(SwingConstants.CENTER);
				icon.setIcon(new ImageIcon(userPath + "\\access\\icons\\acounticons\\" + panelObjsRtn[3] + ".png"));
				
				JLabel name = new JLabel(panelObjsRtn[1]);
				
				sl_subPanel.putConstraint(SpringLayout.SOUTH, icon, -6, SpringLayout.NORTH, name);
				sl_subPanel.putConstraint(SpringLayout.NORTH, name, 126, SpringLayout.NORTH, subPanel);
				sl_subPanel.putConstraint(SpringLayout.WEST, name, 7, SpringLayout.WEST, subPanel);
				sl_subPanel.putConstraint(SpringLayout.SOUTH, name, -10, SpringLayout.SOUTH, subPanel);
				sl_subPanel.putConstraint(SpringLayout.EAST, name, -7, SpringLayout.EAST, subPanel);
				name.setVerticalTextPosition(SwingConstants.TOP);
				name.setHorizontalAlignment(SwingConstants.CENTER);
				subPanel.add(name);
				
				showPanel.add(subPanel);
			}else{
				/**
				 * Si el item no existe agrega un panel de relleno(Panel en blanco)
				 */
				JPanel nullPanel = new JPanel();
				nullPanel.setLayout(null);
				nullPanel.setBackground(new Color(233, 233, 233));
				showPanel.add(nullPanel);
			}
		}
		contentPane.add(showPanel);
		showPanel.revalidate();
		showPanel.repaint();
	}
	
	public void showItemPanel(String name) {
		/**
		 * Creacion del panel para visualizar las propiedades
		 * del item(Cuenta o contraseña)
		 */
		
		showPanel.removeAll();
		showPanel.revalidate();
		showPanel.repaint();
		
		JdbManager localManager = new JdbManager(JdbManager.ITEMS, JdbManager.MODIFIABLE, name);
		String[] rtn;
		String[] ico = {"Type","Name", "Mail", "Pass"};rtn = localManager.getObjectsInFile(ico, 4);
		showPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		showPanel.setBackground(new Color(228, 228, 228));
		showPanel.setBounds(276, 105, 703, 299);
		contentPane.add(showPanel);
		showPanel.setLayout(null);
		
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(40, 55, 98, 98);
		showPanel.add(imgPanel);
		imgPanel.setLayout(null);
		
		JLabel icoLabel = new JLabel("");
		icoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		icoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		icoLabel.setIcon(new ImageIcon(userPath + "\\access\\icons\\acounticons\\"+ rtn[1] + ".png"));
		icoLabel.setBounds(0, 0, 98, 98);
		imgPanel.add(icoLabel);
		
		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		nombreLabel.setBounds(181, 40, 90, 25);
		showPanel.add(nombreLabel);
		
		JLabel nombreLabelres = new JLabel(rtn[3]);
		nombreLabelres.setForeground(new Color(60, 60, 60));
		nombreLabelres.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		nombreLabelres.setBounds(254, 40, 205, 25);
		showPanel.add(nombreLabelres);
		
		JLabel correoLabel = new JLabel("Correo:");
		correoLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		correoLabel.setBounds(181, 76, 205, 25);
		showPanel.add(correoLabel);
		
		JLabel correoField = new JLabel(rtn[5]);
		correoField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		correoField.setBounds(250, 77, 300, 25);
		showPanel.add(correoField);
		
		JLabel passLabel = new JLabel("Contraseña:");
		passLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		passLabel.setBounds(181, 113, 90, 25);
		showPanel.add(passLabel);
		
		JLabel passField = new JLabel(rtn[7]);
		passField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		passField.setBounds(277, 113, 205, 25);
		showPanel.add(passField);
		
		JPanel panelBorrar = new JPanel();
		panelBorrar.setOpaque(true);
		panelBorrar.addMouseListener(new MouseAdapter() {
			/**
			 * Eliminacion del item(Cuenta o contraseña)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				JdbManager met = new JdbManager(JdbManager.METADATA, JdbManager.MODIFIABLE, "items");
				int ot = 0;
				String[] AllObjs = met.getAllObjectsInFile();
				met.deleteDataBase();
				met.evaluarDb();
				/**
				 * Regeneracion del archivo sin el item eliminado
				 */
				for(int i=1;i<=(AllObjs.length - 1);i+=2) {
					ot ++;
					if(AllObjs != null) {
					if(AllObjs[i].equals(name)) {
						for(int it = i + 2; it<=(AllObjs.length - 1);it+=2) {
							met.writeObj("item" + (it - 2 - ot), AllObjs[it]);
							ot ++;
						}
						break;
					}else {
						met.writeObj(AllObjs[i-1], AllObjs[i]);
					}}
				}
				met.writeObj("Cdo", ""+(Integer.parseInt(AllObjs[1]) - 1));
				JdbManager file = new JdbManager(JdbManager.ITEMS, JdbManager.MODIFIABLE, name);
				file.deleteDataBase();
				showItems();
			}
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panelBorrar.setBackground(new Color(237, 50, 50));
			}
			public void mouseExited(MouseEvent e) {
				panelBorrar.setBackground(new Color(232, 21, 21));
			}
		});
		panelBorrar.setBackground(new Color(232, 21, 21));
		panelBorrar.setBounds(460, 248, 92, 28);
		showPanel.add(panelBorrar);
		
		JLabel ButtonDelete = new JLabel("Borrar");
		ButtonDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		ButtonDelete.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonDelete.setForeground(new Color(233, 233, 233));
		ButtonDelete.setFont(new Font("Arial", Font.BOLD, 13));
		ButtonDelete.setBackground(new Color(232, 21, 21));
		panelBorrar.add(ButtonDelete);
		
		JPanel panelRegresar = new JPanel();
		panelRegresar.setOpaque(true);
		panelRegresar.addMouseListener(new MouseAdapter() {
			/**
			 * Regresa el panel a su estado principal llamando
			 * a la funcion para generar los items
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				showItems();
			}
			/**
			 * Efecto hover sobre el panel
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				panelRegresar.setBackground(new Color(220, 220, 220));
			}
			public void mouseExited(MouseEvent e) {
				panelRegresar.setBackground(new Color(233, 233, 233));
			}
		});
		panelRegresar.setBackground(new Color(233, 233, 233));
		panelRegresar.setBounds(581, 248, 92, 28);
		showPanel.add(panelRegresar);
		
		JLabel ButtonRegresar = new JLabel("Regresar");
		ButtonRegresar.setHorizontalTextPosition(SwingConstants.CENTER);
		ButtonRegresar.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonRegresar.setForeground(new Color(40, 40, 40));
		ButtonRegresar.setFont(new Font("Arial", Font.BOLD, 13));
		ButtonRegresar.setBackground(new Color(232, 21, 21));
		panelRegresar.add(ButtonRegresar);
		
		showPanel.revalidate();
		showPanel.repaint();
	}
}

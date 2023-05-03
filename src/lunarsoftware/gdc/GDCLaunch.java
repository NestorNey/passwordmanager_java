package lunarsoftware.gdc;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import lunarsoftware.gdc.gui.auth.Login;
import lunarsoftware.gdc.gui.auth.Registro;
import lunarsoftware.lib.jdbmanager.JdbManager;


/**
 *	Nombre del software: Gestor de contraseñas
 *	@author: Nestor Emmanuel
 *
 *	Fecha de inicio: 1/12/2022   
 *	Ultima modificacion: 4/12/2022
*/

public class GDCLaunch{
	static boolean DEV = true;
	
	public static void main(String[] args) {
		JdbManager write = new JdbManager(JdbManager.AUTH, JdbManager.UNMODIFIABLE, null);
		
		/**
		 *	Aqui checo que el software no se encuentre en desarrollo y 
		 *	sea seguro ingresar datos personales, de otra forma le informo
		 *	al usuario que el software esta en modo desarrollo y no es seguro.
		 */
		
		JOptionPane.showMessageDialog(null, "Alerta: El software esta en modo desarrollo, los datos aqui guardados\n"
                + ""
                + ""
                + "no son de ninguna forma encriptados, porfavor no ingrese ningun tipo\n"
                + "de dato personal, el programa solo muestra la funcionalidad principal."
                + "", null, 2, null);
		//Posdata: No alcanze a terminar todas las funcionalidades que queria agregar  :C
		
		
			/*try {
				Desktop.getDesktop().open(new File("D:\\Compu\\Projects\\JavaWorkspaces\\GestorDeContraseñas\\src\\lunarsoftware\\gdc\\oas.docx"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		/**
		 * Aqui lanza el frame(Ventana) correnpodiente a si el usuario esta o no registrado
		 */
		
		if(write.getUserCheker()) {
			Login loging = new Login();
			Login.main(null);
		}else {
			Registro registro = new Registro();
			registro.main(null);
		}		
	}
}


package lunarsoftware.gdc;

import javax.swing.JTextField;
	

/**
 * Clase usada como caja de herrmanietas
 */
public class Tools{
	/**
	 * Variable estatica par acceder a la clase Errors 
	 * de obtencion de errores
	 */
	public static Errors error = new Errors();
	
	/**
	 * Clase creada para la obtencion de errores
	 */
	public static class Errors{
		
		public boolean getHasArrobaError(JTextField textField) {
			/**
			 * Funcion que devuelve un booleano si el
			 * correo contiene un "@"
			 */
			boolean rtn = false;
			if(!textField.getText().contains("@")) {
				rtn = true;
			}
			return rtn;
		}
		
		public boolean getArrobaError(JTextField textField) {
			/**
			 * Fuencion que devuelve un booleano si el correo
			 * contiene mas de dos "@"
			 */
			boolean rtn = false;
			for(int i=0;i<textField.getText().length();i++) {
				if(textField.getText().charAt(i) == '@') {
					for(int o=i+1;o<textField.getText().length();o++) {
						if(textField.getText().charAt(o) == '@') {
							rtn = true;
							break;
						}
					}
					break;
				}else {rtn = false;}
			}
			return rtn;
		}
		
		public boolean getDomainError(JTextField textField) {
			/**
			 * Funcion que devuelve un booleano si el dominio
			 * contiene una direccion ip o dns valida(like .com .net etc)
			 */
			boolean rtn = false;
			for(int i=0;i<textField.getText().length();i++) {
				if(textField.getText().charAt(i) == '@') {
					for(int o=i+1;o<textField.getText().length();o++) {
						if(textField.getText().charAt(o) == '.' && textField.getText().length() != o + 1) {
							rtn = false;
							break;
						}else {
							rtn = true;
						}
					}
					break;
				}else {rtn = true;}
			}
			return rtn;
		}
		
		public boolean getMinLngPassError(JTextField textField) {
			/**
			 * Funcion que retorna un booleano si la contraseña no es mayor
			 * de 8 caracteres
			 */
			boolean rtn = false;
			if(textField.getText().length() < 8) {
				rtn = true;
			}
			return rtn;
		}
		
		public boolean getPassNumError(JTextField textField) {
			/**
			 * Funcion que retorna un booleano si la contraseña
			 * contiene uno o mas numeros
			 */
			boolean rtn = false;
			for(int ot=0;ot<=9;ot++) {
				if(textField.getText().contains(""+ot)) {
					rtn = false;
					break;
				}else rtn = true;
			}
			return rtn;
		}
	}
}
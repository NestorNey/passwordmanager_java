package lunarsoftware.lib.jdbmanager.misc;

import lunarsoftware.lib.jdbmanager.JdbManager;

public class Misc{
	public Misc() {
	}
	
	public int getCdi() {
		/**
		 * Funcion que retorna la cantidad de items(Cuentas contraseñas) 
		 * agregados por el usuario
		 */
		int rtn = 0;
		JdbManager write = new JdbManager(JdbManager.METADATA, JdbManager.MODIFIABLE, "items");
		String[] cdoToSearch = {"Cdo"};
		String[] cdoReturn = write.getObjectsInFile(cdoToSearch, 1);
		
		if(cdoReturn[1] != null) {
			rtn = Integer.parseInt(cdoReturn[1]);
		}else {
			rtn = 0;
		}
		
		return rtn;
	}
	
	public boolean writeItemMetada(String nombre) {
		/**
		 * Funcion que escribe la metadata del item especificado
		 * en "nombre"
		 */
		boolean band = false;
		
		JdbManager write = new JdbManager(JdbManager.METADATA, JdbManager.MODIFIABLE, "items");
		
		String[] cdoToSearch = {"Cdo"};
		String[] cdoReturn = write.getObjectsInFile(cdoToSearch, 1);
		
		System.out.println(write.valExist(nombre));
		
		if(!write.valExist(nombre)) {
			/**
			 * Si el item no existe lo agrega
			 */
			if(cdoReturn[1] != null) {
				write.writeObj("Cdo", "" + (Integer.parseInt(cdoReturn[1]) + 1));
				write.writeObj("item" + (Integer.parseInt(cdoReturn[1]) + 1), nombre);
			}else {
				write.writeObj("Cdo", "1");
				write.writeObj("item" + 1, nombre);
			}
			band = true;
		}else {
			/**
			 * Si el item existe le retorna un boleano para especificar
			 * que el item ya esta creado y que no puede ser agregado dos
			 * veces o mas
			 */
			band = false;
		}
		
		return band;
	}
}
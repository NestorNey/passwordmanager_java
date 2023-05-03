package lunarsoftware.lib.jdbmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;

import lunarsoftware.lib.jdbmanager.misc.Misc;

public class JdbManager{
	/**
	 * Variables estaticas para identificar al tipo
	 * de base de datos a la que se va a acceder
	 */
	public static int AUTH = 0;
	public static int ITEMS = 1;
	public static int METADATA = 2;
	
	
	/**
	 * Variables estaticas para identificar el 
	 * permiso de escritura
	 */
	public static int MODIFIABLE = 0;
	public static int UNMODIFIABLE = 1;
	
	private boolean userCheker;
	private Reader readPerm;
	protected int token;
	protected int type;
	protected String nmb;
	protected String userPath;
	protected File dataBase;
	Misc misc;
	
	
	// Constructor para escribir
	public JdbManager(int Tipo, int Permiso, String Nombre) {
		userPath = System.getProperty("user.dir");
		userCheker = false;
		if(Tipo == JdbManager.AUTH) {
			dataBase = (File) new File(userPath + "\\database\\auth", 
					File.separator + "user.jdb");
		}else if(Tipo == JdbManager.ITEMS) {
			dataBase = (File) new File(userPath + "\\database\\items", 
					File.separator + Nombre +".item");
		}else if(Tipo == JdbManager.METADATA) {
			dataBase = (File) new File(userPath + "\\database\\.metdata", 
					File.separator + Nombre +".data");
		}
		this.type = Tipo;
		this.nmb = Nombre;
		token = Permiso;
		
		evaluarDb();
	}
	
	//Constructor para leer
	public JdbManager(int Tipo, String Nombre) {
		userPath = System.getProperty("user.dir");
		userCheker = false;
		if(Tipo == JdbManager.AUTH) {
			dataBase = (File) new File(userPath + "\\database\\auth", 
					File.separator + "user.jdb");
		}else if(Tipo == JdbManager.ITEMS) {
			dataBase = (File) new File(userPath + "\\database\\items", 
					File.separator + Nombre +".item");
		}else if(Tipo == JdbManager.METADATA) {
			dataBase = (File) new File(userPath + "\\database\\.metdata", 
					File.separator + Nombre +".data");
		}
		this.type = Tipo;
		this.nmb = Nombre;
		
		evaluarDb();
	}
	
	public void deleteDataBase() {
		if(!dataBase.delete()) {
			System.out.println("Consola: Error al eliminar el archivo");
		}
	}
	
	public void evaluarDb() {
		/**
		 * Evaluacion de la existencia del achivo(base de datos)
		 * y creacion de tal.
		 */
		if (!dataBase.exists()) {
			try {
				dataBase.getParentFile().mkdirs();
				dataBase.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			setUserCheker(false);
		}else{
			setUserCheker(true);
		}
	}	
	
	public void writeObj(String obj, String val) {
		/**
		 * Funcion de escritura de objeto-valor
		 */
			String aux[], objs[];
			FileWriter writePerm = null;
			PrintWriter writer = null;
			
			if(!objExist(obj)) {
				/**
				 * Si el objeto no existe lo crea
				 */
				
				try {
					writePerm = new FileWriter(dataBase,true);
					writer = new PrintWriter(writePerm);
				}catch(Exception e) {
					e.printStackTrace();
				}
				writer.println(obj + ": " + val);
				try {
					writePerm.close();
					writer.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else if(token == 0) {
				/**
				 * Si el objeto no existe y si el permiso para escribir
				 * es MODIFICABLE entonces cambia el valor del objeto
				 */
				objs = getAllObjectsInFile();
				dataBase.delete();
				evaluarDb();
				try {
					writePerm = new FileWriter(dataBase,true);
					writer = new PrintWriter(writePerm);
				}catch(Exception e) {
					e.printStackTrace();
				}
				for(int i = 0; i< objs.length; i+=2) {
					if(objs[i] == null) {
						break;
					}
					if(objs[i].equals(obj)) {
						writer.println(obj + ": " + val);
					}else {
						writer.println(objs[i] + ": " + objs[i+1]);
					}
				}
				try {
					writePerm.close();
					writer.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	public String[] getObjectsInFile(String[] objs, int lng) {
		/**
		 * Obtiene ciertos objetos espesificados en "objs" de
		 * la base de datos
		 */
			String[] rtn = new String[lng * 2];
			int cont = 0; int i = 0;
			String aux = "";
			Reader readPerm = null;
			BufferedReader read = null;
			
			do {
				try {
					readPerm = new FileReader(dataBase);
					read = new BufferedReader(readPerm);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				do {
					try {
						aux = read.readLine();
					}catch (Exception e) {
						e.printStackTrace();
					}
					if(aux != null && aux.contains(objs[i] + ":")) {
						for(int it = 0; it<= aux.length(); it++) {
							if(aux.charAt(it) == objs[i].charAt(0)) {
								aux = aux.substring(it + objs[i].length() + 2);
								break;
							}
						}
						rtn[cont] = objs[i];
						rtn[cont + 1] = aux;
						aux = null;
					}else {
						rtn[cont] = objs[i];
						rtn[cont + 1] = null;
					}
				}while(aux != null);
				
				try {
					readPerm.close();
					read.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				i ++;
				cont += 2;
			}while(i != lng);
			return rtn;
		}
		
	public String[] getAllObjectsInFile() {
		/**
		 * Obtencion de todos los objetos dentro de la base de datos
		 * 
		 * Nota: por el momento se usa misc.getCdi() ya que la funcion
		 * solo se usa para obtener todos los items dentro de la base 
		 * de datos de la metadata
		 */
			Misc misc = new Misc();
			String[] rtn = new String[misc.getCdi() * 2 + 2];
			int cont = 0; int ot;
			String aux = "";String aux2 = "";
			String rdr = "";
			readPerm = null;
			BufferedReader read = null;
			
			try {
				readPerm = new FileReader(dataBase);
				read = new BufferedReader(readPerm);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			do {
				try {
					aux = read.readLine();
				}catch (Exception e) {
					e.printStackTrace();
				}
				if(aux != null && aux.contains(":")) {
					for(int it = 0; it<= aux.length(); it++) {
						if(aux.charAt(it) != ' ') {
							for(ot = 0; ot <= aux.length(); ot ++) {
								if(aux.charAt(ot) == ':') {
									break;
								}
							}
							
							rdr = aux.substring(it, ot);
							aux2 = aux.substring(ot + 2,aux.length());
							break;
						}
					}
					rtn[cont] = rdr;
					rtn[cont + 1] = aux2;
					cont += 2;
				}
			}while(aux != null);
				
			try {
				readPerm.close();
				read.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return rtn;
		}
		
	public String[] getAllObjectsInFile(int lng) {
		/**
		 * Obtencion de todos los objetos dentro de la base de datos
		 * especificando la cantidad de objetos a buscar
		 */
			String[] rtn = new String[lng * 2];
			int cont = 0; int ot;
			String aux = "";String aux2 = "";
			String rdr = "";
			readPerm = null;
			BufferedReader read = null;
			
			try {
				readPerm = new FileReader(dataBase);
				read = new BufferedReader(readPerm);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			do {
				try {
					aux = read.readLine();
				}catch (Exception e) {
					e.printStackTrace();
				}
				if(aux != null && aux.contains(":")) {
					for(int it = 0; it<= aux.length(); it++) {
						if(aux.charAt(it) != ' ') {
							for(ot = 0; ot <= aux.length(); ot ++) {
								if(aux.charAt(ot) == ':') {
									break;
								}
							}
							
							rdr = aux.substring(it, ot);
							aux2 = aux.substring(ot + 2,aux.length());
							break;
						}
					}
					rtn[cont] = rdr;
					rtn[cont + 1] = aux2;
					cont += 2;
				}
			}while(aux != null);
				
			try {
				readPerm.close();
				read.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return rtn;
		}
		
	public boolean objExist(String obj) {
		/**
		 * Retorno de un booleano si el objeto especificado
		 * en "obj" existe o no en la base de datos
		 */
			boolean band = false;
			String aux = "";
			Reader readPerm = null;
			String str = "";
			BufferedReader read = null;
			
			try {
				readPerm = new FileReader(dataBase);
				read = new BufferedReader(readPerm);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			do {
				try {
					aux = read.readLine();
				}catch (Exception e) {
					e.printStackTrace();
				}
				if(aux != null && aux.length() != 0) {
					for(int it = 0; it < aux.length(); it++) {
						if(aux.charAt(it) != ' ') {
							for(int ot = 0; ot <= aux.length(); ot ++) {
								if(aux.charAt(ot) == ':') {
									str = aux.substring(it, ot);
									break;
								}else {
									str = null;
								}
							}
							break;
						}else {
							str = null;
						}
					}
				}
				if(str != null && str.equals(obj)) {
					band = true;
					break;
				}
			}while(aux != null);
			
			try {
				readPerm.close();
				read.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return band;
		}
	
	public boolean valExist(String val) {
		/**
		 * Retorno de un booleano si el valor especificado
		 * en "val" existe o no en la base de datos
		 */
			boolean band = false;
			String aux = "";
			Reader readPerm = null;
			String str = "";
			BufferedReader read = null;
			
			try {
				readPerm = new FileReader(dataBase);
				read = new BufferedReader(readPerm);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			do {
				try {
					aux = read.readLine();
				}catch (Exception e) {
					e.printStackTrace();
				}
				if(aux != null && aux.length() != 0) {
					for(int it = 0; it < aux.length(); it++) {
						if(aux.charAt(it) != ' ') {
							for(int ot = 0; ot <= aux.length(); ot ++) {
								if(aux.charAt(ot) == ':') {
									str = aux.substring(ot + 2, aux.length());
									break;
								}else {
									str = null;
								}
							}
							break;
						}else {
							str = null;
						}
					}
				}
				
				if(str != null && str.equals(val)) {
					band = true;
					break;
				}
			}while(aux != null);
			
			try {
				readPerm.close();
				read.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return band;
		}
		
	public boolean getUserCheker() {
			return userCheker;
	}
		
	public void setUserCheker(boolean userCheker) {
			this.userCheker = userCheker;
	}
}

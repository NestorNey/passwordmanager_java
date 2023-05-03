package lunarsoftware.lib.jdbmanager;

import java.sql.*;

public class JdbManager {
    private Connection conn;
    
    public JdbManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            
            // Verificar si la tabla existe, si no, crearla
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS user " +
                         "(mail TEXT PRIMARY KEY NOT NULL, " +
                         " username TEXT NOT NULL, " +
                         " password TEXT NOT NULL, " +
                         " keepSession BOOLEAN NOT NULL, " +
                         " logged BOOLEAN NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public void addNewUser(String mail, String username, String password, boolean keepSession) throws Exception {
        // Verificar si el correo ya existe en la tabla
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE mail = ?");
        stmt.setString(1, mail);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Si el correo ya existe, lanzar excepción
            throw new Exception("El correo ya está registrado en la base de datos");
        } else {
            // Insertar nuevo usuario en la tabla
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user (mail, username, password, keepSession, logged) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, mail);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setBoolean(4, keepSession);
            pstmt.setBoolean(5, false);
            pstmt.executeUpdate();
            pstmt.close();
        }
        
        rs.close();
        stmt.close();
    }
    
    public boolean login(String mail, String password, boolean keepSession) throws Exception {
        // Verificar si el correo y la contraseña corresponden a un usuario en la tabla
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE mail = ? AND password = ?");
        stmt.setString(1, mail);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Actualizar los atributos del usuario
            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET keepSession = ?, logged = ? WHERE mail = ?");
            pstmt.setBoolean(1, keepSession);
            pstmt.setBoolean(2, true);
            pstmt.setString(3, mail);
            pstmt.executeUpdate();
            pstmt.close();
            rs.close();
        	stmt.close();
            return true;
        } else {
        	rs.close();
        	stmt.close();
            throw new Exception("Correo o contraseña incorrectos");
        }
    }
    
    public void logout(String mail) throws Exception {
        // Cambiar el atributo logged del usuario a falso
        PreparedStatement stmt = conn.prepareStatement("UPDATE user SET logged = ? WHERE mail = ?");
        stmt.setBoolean(1, false);
        stmt.setString(2, mail);
        stmt.executeUpdate();
        stmt.close();
    }
    
    public void deleteUser(String mail) throws Exception {
        // Eliminar usuario de la tabla
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE mail = ?");
        stmt.setString(1, mail);
        stmt.executeUpdate();
        stmt.close();
    }
    public void changePassword(String mail, String newPassword) throws Exception {
        // Verificar si el correo existe en la tabla
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE mail = ?");
        stmt.setString(1, mail);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Actualizar la contraseña del usuario
            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET password = ? WHERE mail = ?");
            pstmt.setString(1, newPassword);
            pstmt.setString(2, mail);
            pstmt.executeUpdate();
            pstmt.close();
        } else {
            throw new Exception("El correo no está registrado en la base de datos");
        }
        
        rs.close();
        stmt.close();
    }
    public void changeUsername(String mail, String newUsername) throws Exception {
        // Verificar si el correo corresponde a un usuario en la tabla
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE mail = ?");
        stmt.setString(1, mail);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Actualizar el nombre de usuario
            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET username = ? WHERE mail = ?");
            pstmt.setString(1, newUsername);
            pstmt.setString(2, mail);
            pstmt.executeUpdate();
            pstmt.close();
        } else {
            throw new Exception("No se encontró ningún usuario con el correo especificado");
        }
        
        rs.close();
        stmt.close();
    }
    public void changeEmail(String currentMail, String newMail) throws Exception {
        // Verificar si el nuevo correo ya existe en la tabla
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE mail = ?");
        stmt.setString(1, newMail);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Si el nuevo correo ya existe, lanzar excepción
            throw new Exception("El correo ya está registrado en la base de datos");
        } else {
            // Actualizar correo electrónico del usuario
            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET mail = ? WHERE mail = ?");
            pstmt.setString(1, newMail);
            pstmt.setString(2, currentMail);
            pstmt.executeUpdate();
            pstmt.close();
        }
        
        rs.close();
        stmt.close();
    }
}
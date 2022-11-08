package Control;


import java.sql.*;


public class Connect {
    public static Connection getConexion() throws ClassNotFoundException{
        
        String connectionUrl = "jdbc:sqlserver://DESKTOP-UQUV652\\SQLEXPRESS:1433;"
                +"database=DB_Veterinary;"
                +"user=superusuario;"
                +"password=contrasuperu1;"
                +"encrypt=true;"
                +"trustServerCertificate=true;"
                +"loginTimeout=30;";
        
        
        try  {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");     
            System.out.println("Driver funciona correctamente.");
            
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        }catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
        
    }
}
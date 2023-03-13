package Control;


import java.sql.*;


public class Connect {
    public static Connection getConexion() throws ClassNotFoundException{       // Realiza la conexión con la base de datos
        
        String connectionUrl = "jdbc:sqlserver://DESKTOP-UQUV652\\SQLEXPRESS:1433;"        // Genera el URL de la conexión
                +"database=DB_Veterinary;"
                +"user=superusuario;"
                +"password=contrasuperu1;"
                +"encrypt=true;"
                +"trustServerCertificate=true;"
                +"loginTimeout=30;";
        
        
        try  {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");        // Utiliza el driver para conectarse a SQLServer
            System.out.println("Driver funciona correctamente.");                // Imprime que el driver funciona correctamente
            
            Connection con = DriverManager.getConnection(connectionUrl);        // Realiza la conexión utilizando el URL
            return con;                                                        // Regresa la conexión a la ventana que la llamó
        }catch(SQLException ex){
            System.out.println(ex.toString());                             // Imprime el error en caso de fallar la conexión
            return null;
        }
        
    }
}
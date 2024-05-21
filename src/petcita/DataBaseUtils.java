package petcita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


public class DataBaseUtils {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";	

    public static Connection getConnection(String nomeBanco) 
                    throws ClassNotFoundException, SQLException 
    {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL+nomeBanco, "adm_petcita", "P3tc!t@20524");
    }

    public static boolean insert(Connection conn, String Sql) throws SQLException
    {
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(Sql);
        
        return true;
    }
    
    public static int insertRetornaId(Connection conn, String Sql) throws SQLException
    {
        PreparedStatement cmd = conn.prepareStatement(Sql, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.executeUpdate();
        ResultSet resposta = cmd.getGeneratedKeys();
        //ResultSet resposta = cmd.executeQuery(Sql+" RETURNING "+retorno);
        resposta.next();
        
        return resposta.getInt(1);
    }
    
    public static boolean delete(Connection conn, String Sql) throws SQLException
    {
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(Sql);

        return true;
    }

}

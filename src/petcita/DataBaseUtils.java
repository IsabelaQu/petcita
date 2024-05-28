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
        
        int registrosGerados = cmd.executeUpdate();
        
        // Valida se realemente gerou elementos
        if (registrosGerados > 0) {
            // Busca os ids gerados
            ResultSet resposta = cmd.getGeneratedKeys();

            // Move para o pr�ximo, nesse caso primeiro item do ResultSet
            if (resposta.next()) {
                // Retorna o Id gerado pelo Insert
                return resposta.getInt(1);
            } else {
                throw new SQLException("Nenhum ID de usu�rio encontrado.");
            }
        } else {
            throw new SQLException("Nenhum usu�rio foi gerado.");
        }
    }
    
    public static boolean delete(Connection conn, String Sql) throws SQLException
    {
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(Sql);
        return true;
    }
    
    public static ResultSet select(Connection conn, String Sql) throws SQLException
    {
        Statement cmd = conn.createStatement();
         
        return cmd.executeQuery(Sql);
    }

    public static boolean update(Connection conn, String Sql) throws SQLException {
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(Sql);
        return true;
    }

}

package petcita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBaseUtils {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/";	
	
	public static Connection getConnection(String nomeBanco, String userbd, String senhabd) 
			throws ClassNotFoundException, SQLException 
	{
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL+nomeBanco, userbd, senhabd);
	}

	public void MySQLConnection() {
		Scanner in = new Scanner (System.in);
		
		System.out.println ("--- Conectando ao Banco de Dados PETCITA. Insira suas credenciais: ---");
		
		while (true) {
			System.out.println ("Digite o usuário do Banco de Dados: ");
			String userbd = in.nextLine();
			
			System.out.println ("Digite a senha do Banco de Dados: ");
			String senhabd = in.nextLine();
			
			try  {
				Connection conn = DataBaseUtils.getConnection("PETCITA", userbd, senhabd);
				
				System.out.println("Conexão bem-sucedida! Você acessou o Banco de Dados PETCITA.");
				break;
			}	catch (ClassNotFoundException | SQLException e) {		
				System.out.println("Falha ao conectar ao Banco de Dados. Verifique suas credenciais e tente novamente.");
				System.out.println("");

			}
		}
	}
}
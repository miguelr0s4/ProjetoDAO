package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//padrão de persistência Factory = é instanciada uma vez, mas usada igualmente em várias instâncias. Por isso é estático.
//Padrão de persistência é tudo que está relacionado à arquitetura do projeto, objetivando a persistência do sistema
public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException {
		
		//associa a camada de aplicação do driver e jdbc para chegar no banco.
		//Controla o caminho inicial; contrói a comunicação om o banco. Faz a ponte
		String url = "jdbc:mysql://localhost/test";
		String user = "root";
		String password = "";
		
		return DriverManager.getConnection(url, user, password);
	}
		
}

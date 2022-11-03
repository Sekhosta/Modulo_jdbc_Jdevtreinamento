package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
private static  String url="jdbc:postgresql://localhost:5432/posjava";
private static String user="postgres";
private static String password="077ces";
private static Connection connection=null;

static {
	conectar();
	//de qualquer lugar invoca o metodo conectar
}

public SingleConnection() {
	conectar();
	//construtor chamando o método conectar
}


private static void conectar() {
	//criação do método conectar 
	//1° a ser criado
	try {
		if(connection==null) {
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			System.out.println("Conectou com sucesso!");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public static Connection getConnection(){
	return connection;
}//
}

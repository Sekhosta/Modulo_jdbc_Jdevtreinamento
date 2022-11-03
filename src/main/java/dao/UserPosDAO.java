package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;
import java.util.*;
public class UserPosDAO {

/** The connection. */
//dml 
	private Connection connection;
	//criando o construtor
	public UserPosDAO() {
		connection=SingleConnection.getConnection();
	} 
	
	public void salvar(Userposjava userposjava) {
		try {
			String sql="insert into userposjava (nome, email) values (?,?);";
			//preparando para inserir os dados
			PreparedStatement insert=connection.prepareStatement(sql);
			//inserindo de forma estática
			//insert.setLong(1, 6);
			//insert.setString(2, "Cesar SalvarDAO");
			//insert.setString(3, "cesar.egidio@gmail.com");
	
			insert.setString(1,userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit(); //salva no banco
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		
			e.printStackTrace();
		} 
	}
	public void salvarTelefone(Telefone telefone) {
		try { String sql="INSERT INTO telefoneuser(\n"
				+ "	 numero, tipo, usuariopessoa)\n"
				+ "	VALUES ( ?, ?, ?); ";
		PreparedStatement statement=connection.prepareStatement(sql);
		statement.setString(1, telefone.getNumero());
		statement.setString(2, telefone.getTipo());
		statement.setLong(3, telefone.getUsuario());
		statement.execute();
		connection.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	}
	
	
	public List<Userposjava> listar() throws Exception{
		List<Userposjava> list= new ArrayList<Userposjava>();
		
		String sql="select*from userposjava";
		
		PreparedStatement statement=connection.prepareStatement(sql);
		ResultSet resultado=statement.executeQuery();
		
		while(resultado.next()) {
			Userposjava userposjava= new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			list.add(userposjava);
		}
		
		return list;
	}
	//pra consultar só um objeto 
	//ctrl + alt + R serve para renomear
	
	public Userposjava buscar(Long id) throws Exception{
		Userposjava retorno = new Userposjava();
		
		String sql="select*from userposjava where id="+ id;
		
		PreparedStatement statement=connection.prepareStatement(sql);
		ResultSet resultado=statement.executeQuery();
		
		while(resultado.next()) {//retorna apenas 1 ou nenhum
			Userposjava userposjava= new Userposjava();
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
			
		}
		
		return retorno;
	}
	
	public void atualizar(Userposjava userposjava) {
		try { 
			String sql="update userposjava set nome= ? where id =" + userposjava.getId();
   	 PreparedStatement statement = connection.prepareStatement(sql);
   	 statement.setString(1, userposjava.getNome());
   	 statement.execute();
   	 connection.commit();
	} catch (SQLException e) {
		try {
			connection.rollback();
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
		
}
	public void deletar(Long id) {
		try {
			String sql=" delete from userposjava where id =" + id+ ";";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.execute();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public List<BeanUserFone> listaUserFone(Long idUser){
		List<BeanUserFone>beanUserFones= new ArrayList<BeanUserFone>();
		String sql=" select  nome,numero,email from telefoneuser as fone  ";
		sql+=" inner join userposjava as userp ";
		sql+=" on fone.usuariopessoa=userp.id ";
		sql+=" where userp.id= "+ idUser;
		
		try {
			
			PreparedStatement statement= connection.prepareStatement(sql);
		ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()){
				BeanUserFone userFone =new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				
				beanUserFones.add(userFone);
			}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return beanUserFones;
		
	}
public void deleteFonesPorUser(Long idUser){
	
	try { 
		String sqlFone=" delete from telefoneuser where usuariopessoa= "+idUser;
	String sqlUser= " delete from userposjava where id= "+idUser;
	PreparedStatement preparedStatement=connection.prepareStatement(sqlFone);
	preparedStatement.executeUpdate();
	connection.commit();
	preparedStatement=connection.prepareStatement(sqlUser);
	preparedStatement.executeUpdate();
	connection.commit();
	
}catch (Exception e) {
	e.printStackTrace();
	try {
		connection.rollback();
	} catch (SQLException e1) {
		
		e1.printStackTrace();
	}
	}
}
}


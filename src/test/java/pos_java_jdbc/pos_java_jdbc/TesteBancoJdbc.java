package pos_java_jdbc.pos_java_jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {
	@Test
	public void initBanco() {
		// (não preciso mais)SingleConnection.getConnection();
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		userposjava.setNome("Paulo");
		userposjava.setEmail("Paulo@gmail.com");
		userPosDAO.salvar(userposjava);
	}

	@Test
         public void initListar() {
        	 UserPosDAO dao=new UserPosDAO();
        	 try {
        	  List<Userposjava> list=dao.listar();
				for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("------------------------------");
				}
			} catch (Exception e) {
				
			}
			}	
         
        	 @Test
        	 public void initBuscar(){
        	 
        		 UserPosDAO dao =new UserPosDAO();
        		 
        	 try {
				Userposjava userposjava=dao.buscar(5L);
				System.out.println(userposjava);
			} catch (Exception e) {
				e.printStackTrace();
			}
         }
        	@Test
        	 public void initAtualizar(){
        		UserPosDAO dao=new UserPosDAO();
        		try {
            		Userposjava objetoBanco=dao.buscar(5L);
            		objetoBanco.setNome("Nome mudado com método atualizar");
            		dao.atualizar(objetoBanco);
				} catch (Exception e) {
					// TODO: handle exception
				}
        		
        	} 
        	@Test
        	public void initDeletar() {
        		try {
					UserPosDAO dao=new UserPosDAO();
					dao.deletar(12L);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	} 
     @Test
        	public void  testeInsertTelefone() {
    	  Telefone telefone=new Telefone();
    	  telefone.setNumero("(87)4445-4545");
    	  telefone.setTipo("Casa");
    	  telefone.setUsuario(15L);
    	  
    	  UserPosDAO dao=new UserPosDAO();
    	  dao.salvarTelefone(telefone);
      }
 @Test
     public void testeCarregaFoneUser() {
    	 
    	 UserPosDAO dao =new UserPosDAO();
    	 
    	 List<BeanUserFone>beanUserFones=dao.listaUserFone(15L);
    	 for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("----------------------------------");
		}
     }
 @Test
public void testeDeleteUserFone() {
	
	 UserPosDAO dao=new UserPosDAO();
	dao.deleteFonesPorUser(14L);
	
}
 
}







	



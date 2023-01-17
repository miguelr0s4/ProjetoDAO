package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Contato;
import sgbd.ConnectionFactory;

public class ContatoDAO {

	private Connection connection;
	
	public ContatoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Contato contato) throws SQLException {
		
		String sql_insert = "insert into contatos (nome, email, endereco) values (?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(sql_insert);
		
		
		ps.setString(1, contato.getNome());
		ps.setString(2, contato.getEmail());
		ps.setString(3, contato.getEndereco());
		
		ps.execute();
		
		ps.close();
		connection.close();  
	}
	
	public List <Contato> listaDeContatos() throws SQLException {
		
		String sql_list = "select * from contatos ";
		
		PreparedStatement ps = connection.prepareStatement(sql_list);
		ResultSet rs = ps.executeQuery();
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		while(rs.next()) {
			Contato contato = new Contato();
			
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
			contatos.add(contato);
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return contatos;
	}


	public List <Contato> pesquisaLetra(String letraBusca)  throws SQLException {
		
		String sql_searchLetter = "select * from contatos where nome like '"
				+ letraBusca + "%'";
				
		PreparedStatement ps = connection.prepareStatement(sql_searchLetter);
		ResultSet rs = ps.executeQuery();
		
		List<Contato> contatosComLetra = new ArrayList<Contato>();
		
		while(rs.next()) {
			Contato contato = new Contato();
			
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
			contatosComLetra.add(contato);
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return contatosComLetra;
	}
	
	public Contato pesquisa(Long id) throws SQLException {
		
		String sql_search = "select * from contatos where id='"+ id +"'";

		PreparedStatement ps = connection.prepareStatement(sql_search);
		ResultSet rs = ps.executeQuery();
		
		Contato contato = new Contato();
		
		if(rs.next()) {
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
		}
		
		rs.close();

		return contato; 
	}


	public void altera(Long id, String nome, String email, String endereco) throws SQLException {
		
		String sql_update = "update contatos set nome='" + nome + "' ,  email='" + email +"' , endereco='" + endereco + "' where id='" + id + "'";
		PreparedStatement ps = connection.prepareStatement(sql_update);
		ps.execute();
		connection.close();
	}
	
	public void exclui(Long id) throws SQLException {
		
		String sql_delete = "delete from contatos where id='" + id + "'";

		PreparedStatement ps = connection.prepareStatement(sql_delete);
		ps.execute();
		ps.close();

		connection.close();
		
	}
		
}

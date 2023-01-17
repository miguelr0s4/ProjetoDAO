package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ContatoDAO;
import model.Contato;

public class Menu {

	public static void main(String[] args) throws SQLException {
		
		int opContinua; 
		opContinua = 1;
		
		do {
			System.out.println("\n\n=======   AGENDA   =======");
			System.out.println("1 – Cadastrar contato");
			System.out.println("2 – Listar contatos com Id");
			System.out.println("3 – Pesquisar contatos por inicial");
			System.out.println("4 – Pesquisar contato por Id");
			System.out.println("5 - Alterar contato");
			System.out.println("6 - Excluir contato");
			System.out.println("7 - Encerrar programa");
			System.out.println("\nInsira a opção desejada: ");
			
			Scanner sc = new Scanner(System.in);
			
			int op = Integer.parseInt(sc.nextLine());
			
			switch (op) {
		     case 1:
				ContatoDAO cDao1 = new ContatoDAO();
				Contato c1 = new Contato();
		    	 
		    	System.out.println("\nInsira o nome: ");
		    	c1.setNome(sc.nextLine());
								
		    	System.out.println("\nInsira o email: ");
		    	c1.setEmail(sc.nextLine());
		    	
		    	System.out.println("\nInsira o endereço: ");
		    	c1.setEndereco(sc.nextLine());
			
				try {
					cDao1.adiciona(c1);
					System.out.println("\nContato adicionado com sucesso.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
								
		       break;

		     case 2:		    	 		    	 
		    	ContatoDAO cDao2 = new ContatoDAO();
				List<Contato> lista = new ArrayList<Contato>();
				lista = cDao2.listaDeContatos();
				

				for (Contato contato : lista) {
					System.out.println("\nId: " + contato.getId());
					System.out.println("Nome: " +contato.getNome());
					System.out.println("Email: " +contato.getEmail());
					System.out.println("Endereço: " +contato.getEndereco());
				}
				
				break;
     
		     
		     case 3:			    	 
		    	System.out.println("\nInsira a inicial que deseja buscar: ");
				String inicial = sc.nextLine();

				try {

					ContatoDAO cDao3 = new ContatoDAO();
					List<Contato> lista3 = new ArrayList<Contato>();
					lista3 = cDao3.pesquisaLetra(inicial);
					
					
					for (Contato contato3 : lista3) {
						System.out.println("Id: " + contato3.getId());
						System.out.println("Nome: " +contato3.getNome());
						System.out.println("Email: " +contato3.getEmail());
						System.out.println("Endereço: " +contato3.getEndereco());
					}
					
							
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
		       break;		
		       
		     case 4:			    	 
		    	System.out.println("\nInsira o id que deseja buscar: ");
				Long idBusca = sc.nextLong();
				
				try {
					ContatoDAO cDao4 = new ContatoDAO();
					Contato c4 = new Contato();
	
					c4 = cDao4.pesquisa(idBusca);
					
					System.out.println("\n"+c4.getNome());
					System.out.println(c4.getEmail());
					System.out.println(c4.getEndereco());
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

		       break;		       
		       
		     case 5:		
		    	System.out.println("\nInsira o id que deseja alterar: ");
				Long idAltera = Long.parseLong(sc.nextLine());
				
				try {
					ContatoDAO cDao5 = new ContatoDAO();					
					Contato c5 = new Contato();
					
					c5 = cDao5.pesquisa(idAltera);
					
					String novoNome="";
					String novoEmail=""; 
					String novoEndereco="";

					String respostaNome="";
					String respostaEmail="";
					String respostaEndereco="";
					
					String nomeAnterior = c5.getNome();
					String emailAnterior = c5.getEmail();
					String enderecoAnterior = c5.getEndereco();

					System.out.println("\n\nAltere os dados necessários. Caso não queira alterar algum, tecle enter:");
					System.out.println("Nome: "+ nomeAnterior);
					System.out.println("Novo nome:");
					respostaNome = sc.nextLine();
					
					if (respostaNome != null || respostaNome != "") {
						c5.setNome(respostaNome);
						novoNome = respostaNome;
					}
				
					System.out.println("\nEmail: "+ emailAnterior);
					System.out.println("Novo email:");
					respostaEmail = sc.nextLine();
					
					if (respostaEmail != null || respostaEmail != "") {
						c5.setNome(respostaEmail);
						novoEmail = respostaEmail;
					}
					
					System.out.println("\nEndereço: "+ enderecoAnterior);
					System.out.println("Novo endereço:");
					respostaEndereco = sc.nextLine();				

					if (respostaEndereco != null || respostaEndereco != "") {
						c5.setNome(respostaEndereco);
						novoEndereco = respostaEndereco;
					}
					
					cDao5.altera(idAltera, novoNome, novoEmail, novoEndereco);
					
					System.out.println("\nContato alterado! Veja como ficou: ");					
					System.out.println("\nNome: "+ novoNome);
					System.out.println("Email: "+ novoEmail);
					System.out.println("Endereco : "+ novoEndereco +"\n");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		       break;			       
		       
		     case 6:			    	 
		    	System.out.println("\nInsira o id que deseja excluir:  ");
				Long idExcluir = sc.nextLong();
				
				try {
					ContatoDAO cDao6 = new ContatoDAO();
					Contato c6 = new Contato();
					c6 = cDao6.pesquisa(idExcluir);
					String nomeExcluido = c6.getNome();
					
					cDao6.exclui(idExcluir);
					
					System.out.println("O contato de " + nomeExcluido + " foi excluído com sucesso.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

		       break;	
		       
		     case 7:
		    	System.out.println("\nCerteza que deseja fechar o programa? Digite qualquer letra para sim e 0 para retornar ao menu.");

		    	String return7 = sc.next();
				if (return7.equals("0")) {
					break;	
				}else {
					opContinua = 0;
				}

		       break;

		     default:
		    	 System.out.println("\nOpção inválida.");
		       break;
			}

		}while(opContinua == 1);
		
   	 System.out.println("Obrigado por usar!");
   	 System.exit(0);
		
	}

}

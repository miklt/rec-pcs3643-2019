package rec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rec.model.Produto;
import rec.model.Venda;

public class ProdutoDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/db7?useSSL=false";
	private String jdbcUsername = "u7";
	private String jdbcPassword = "4xH33X";

	private static final String INSERIR_PRODUTO = "INSERT INTO produtos set nome=?, quantidade=?;";

	private static final String SELECIONAR_PRODUTO_PELO_ID = "select id,nome,quantidade from produtos where id = ?;";
	private static final String SELECIONAR_TODOS_OS_PRODUTOS = "select id, nome, quantidade from produtos";	
	private static final String ATUALIZAR_QUANTIDADE_DE_PRODUTOS_PELO_ID = "update produtos set quantidade=? where id = ?";
	public ProdutoDAO() {
		
	}
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public List<Produto> selecionarTodos() { 
		List<Produto> produtos = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECIONAR_TODOS_OS_PRODUTOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int quantidade = rs.getInt("quantidade");				
				produtos.add(new Produto(id, nome, quantidade));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println(produtos.size());
		return produtos;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public Produto selecionarPeloId(Produto produto) { 
		Produto prod = null;
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECIONAR_PRODUTO_PELO_ID);) {
			preparedStatement.setInt(1, produto.getId());
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			if (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int quantidade = rs.getInt("quantidade");				
				prod = new Produto(id, nome, quantidade);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println("produto dao => selecionar pelo id");
		System.out.println(prod.getId());
		System.out.println(prod.getNome());
		System.out.println(prod.getQuantidade());
		System.out.println("produto dao => selecionar pelo id");
		return prod;
	}
	public void atualizarQuantidadeDeProdutos(Produto produto) {
		// Identifica o produto, a quantidade de elementos que foram vendidos e diminui o estoque do produto por essa quantidade
		try ( Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(ATUALIZAR_QUANTIDADE_DE_PRODUTOS_PELO_ID);) {
				System.out.println("produto dao => atualizarQuantidade");
				preparedStatement.setInt(1, produto.getQuantidade());
				preparedStatement.setInt(2, produto.getId());
								
				System.out.println(preparedStatement);
				int colunas= preparedStatement.executeUpdate();

				// Step 4: Process the ResultSet object.
				if (colunas < 1) {
					// apresentar um erro.
					System.out.println("Erro ao atualizar a tabela de produtos");
				}
				System.out.println("produto dao => atualizarQuantidade");
			} catch (SQLException e) {
				printSQLException(e);
			}
	}
}

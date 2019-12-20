package rec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rec.model.Produto;
import rec.model.Venda;

public class VendaDAO {
	private String jdbcURL = "jdbc:mariadb://db-labsoft.ml:3306/db7?useSSL=false";
	private String jdbcUsername = "u7";
	private String jdbcPassword = "4xH33X";
	private static final String REGISTRAR_VENDA = "INSERT INTO vendas" + " set id_produto=?, quantidade=?;";
	
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

	public void registrarVenda(Venda venda) {		
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(REGISTRAR_VENDA);) {
			preparedStatement.setInt(1, venda.getProduto().getId());
			preparedStatement.setInt(2, venda.getQuantidade());
			

			int colunas= preparedStatement.executeUpdate();

			// Step 4: Process the ResultSet object.
			if (colunas < 1) {
				// apresentar o erro.
				System.out.println("Quantidade de Produtos insuficiente");
			}else {
				// atualizar a tabela de produtos
				ProdutoDAO produtoDAO = new ProdutoDAO();
				Produto produto = produtoDAO.selecionarPeloId(venda.getProduto());				
				int novaQuantidade = produto.getQuantidade() - venda.getQuantidade();
				produto.setQuantidade(novaQuantidade);
				System.out.println(produto.getQuantidade());
				System.out.println(produto.getId());
				produtoDAO.atualizarQuantidadeDeProdutos(produto);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}		
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

	
}

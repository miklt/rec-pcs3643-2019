package rec.web;
import rec.dao.*;
import rec.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns= {"/produto", "/produtos"})
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDAO;
	
	public void init() {
		produtoDAO = new ProdutoDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {		
						
			case "/produto":
				//mostrarProduto(request, response);
				break;
			case "/produtos":
				listarProdutos(request, response);
				break;
			default:
				listarProdutos(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	// Listar 
	private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Produto> listaProdutos = produtoDAO.selecionarTodos();
		request.setAttribute("listaProdutos", listaProdutos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista_produtos.jsp");
		dispatcher.forward(request, response);
	}
}

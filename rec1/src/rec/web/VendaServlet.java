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



@WebServlet(urlPatterns= {"/vender", "/venda"})
public class VendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDAO;
	private VendaDAO vendaDAO;
	
	public void init() {
		produtoDAO = new ProdutoDAO();
		vendaDAO = new VendaDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/vender":
				mostrarFormularioVenda(request, response);
				break;
			default:				
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {							
			
			case "/vender":
				realizarVenda(request, response);
				break;
			default:				
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void mostrarFormularioVenda(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Produto p = new Produto();
		p.setId(id);
		Produto produtoExistente = produtoDAO.selecionarPeloId(p);
		RequestDispatcher dispatcher = request.getRequestDispatcher("vende_produto.jsp");
		request.setAttribute("produto", produtoExistente);
		dispatcher.forward(request, response);
	}
	private void realizarVenda(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id_produto = Integer.parseInt(request.getParameter("id_produto"));
		int quantidade_da_venda = Integer.parseInt(request.getParameter("quantidade_da_venda"));
		int quantidade_no_estoque = Integer.parseInt(request.getParameter("quantidade_no_estoque"));
		System.out.println("Quantidades...");
		System.out.println(quantidade_da_venda);
		System.out.println(quantidade_no_estoque);
		if (quantidade_da_venda > quantidade_no_estoque) {
			//retornar erro.
			System.out.println("A quantidade da venda superior à quantidade no estoque");
		}
		else {
			Produto produto = new Produto();
			produto.setId(id_produto);
			Venda venda = new Venda();
			venda.setProduto(produto);
			venda.setQuantidade(quantidade_da_venda);
			vendaDAO.registrarVenda(venda);					
		}
		
		List<Produto> listaProdutos = produtoDAO.selecionarTodos();
		request.setAttribute("listaProdutos", listaProdutos);
		/*TODO */
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista_produtos.jsp");
		dispatcher.forward(request, response);
	}
}

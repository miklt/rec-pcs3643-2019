package rec.model;

import java.sql.Timestamp;

public class Venda {
	private Produto produto;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getData_venda() {
		return data_venda;
	}
	public void setData_venda(Timestamp data_venda) {
		this.data_venda = data_venda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	private int id;
	private Timestamp data_venda;
	private int quantidade;
}

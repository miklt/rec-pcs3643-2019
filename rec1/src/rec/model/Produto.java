package rec.model;

import java.io.Serializable;

public class Produto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;	
	private int quantidade;
	
	public Produto() {
	}
	public Produto(int id, String nome, int quantidade) {
		this.setId(id);
		this.setNome(nome);
		this.setQuantidade(quantidade);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}

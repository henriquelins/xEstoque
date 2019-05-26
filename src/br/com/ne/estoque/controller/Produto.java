package br.com.ne.estoque.controller;

import java.util.Arrays;

public class Produto {
	
	private int id_produto;
	private String categoria;
	private String codigo;
	private String nomeDescricao;
	private double valorUnitario;
	private double valorVenda;
	private int estoqueAtual;
	private String unidadeMedida;
	private int estoqueMinimo;
	private String observacoes;
	private byte[] foto;
		
	public Produto() {}
	
	

	public Produto(int id_produto, String categoria, String codigo, String nomeDescricao, double valorUnitario,
			double valorVenda, int estoqueAtual, String unidadeMedida, int estoqueMinimo, String observacoes,
			byte[] foto) {
		
		this.id_produto = id_produto;
		this.categoria = categoria;
		this.codigo = codigo;
		this.nomeDescricao = nomeDescricao;
		this.valorUnitario = valorUnitario;
		this.valorVenda = valorVenda;
		this.estoqueAtual = estoqueAtual;
		this.unidadeMedida = unidadeMedida;
		this.estoqueMinimo = estoqueMinimo;
		this.observacoes = observacoes;
		this.foto = foto;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeDescricao() {
		return nomeDescricao;
	}

	public void setNomeDescricao(String nomeDescricao) {
		this.nomeDescricao = nomeDescricao;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public int getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(int estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + estoqueAtual;
		result = prime * result + estoqueMinimo;
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result + id_produto;
		result = prime * result + ((nomeDescricao == null) ? 0 : nomeDescricao.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((unidadeMedida == null) ? 0 : unidadeMedida.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorUnitario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorVenda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (estoqueAtual != other.estoqueAtual)
			return false;
		if (estoqueMinimo != other.estoqueMinimo)
			return false;
		if (!Arrays.equals(foto, other.foto))
			return false;
		if (id_produto != other.id_produto)
			return false;
		if (nomeDescricao == null) {
			if (other.nomeDescricao != null)
				return false;
		} else if (!nomeDescricao.equals(other.nomeDescricao))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (unidadeMedida == null) {
			if (other.unidadeMedida != null)
				return false;
		} else if (!unidadeMedida.equals(other.unidadeMedida))
			return false;
		if (Double.doubleToLongBits(valorUnitario) != Double.doubleToLongBits(other.valorUnitario))
			return false;
		if (Double.doubleToLongBits(valorVenda) != Double.doubleToLongBits(other.valorVenda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [id_produto=").append(id_produto).append(", categoria=").append(categoria)
				.append(", codigo=").append(codigo).append(", nomeDescricao=").append(nomeDescricao)
				.append(", valorUnitario=").append(valorUnitario).append(", valorVenda=").append(valorVenda)
				.append(", estoqueAtual=").append(estoqueAtual).append(", unidadeMedida=").append(unidadeMedida)
				.append(", estoqueMinimo=").append(estoqueMinimo).append(", observacoes=").append(observacoes)
				.append(", foto=").append(Arrays.toString(foto)).append("]");
		return builder.toString();
	}

	
}

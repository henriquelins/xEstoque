package br.com.ne.estoque.controller;

import java.sql.Date;
import java.sql.Time;

public class Transacao {

	private int id_transacao;
	private int tipo;
	private Date dataTransacao;
	private Time horaTransacao;
	private double valorUnitario;
	private double valorVenda;
	private int estoqueAnterior;
	private int ajuste;
	private int estoqueAtual;
	private Produto produto;
	private Operador operador;

	public Transacao() {
	}
	
	public Transacao(int tipo, Date dataTransacao, Time horaTransacao, double valorUnitario,
			double valorVenda, int estoqueAnterior, int ajuste, int estoqueAtual, Produto produto, Operador operador) {
	
		this.tipo = tipo;
		this.dataTransacao = dataTransacao;
		this.horaTransacao = horaTransacao;
		this.valorUnitario = valorUnitario;
		this.valorVenda = valorVenda;
		this.estoqueAnterior = estoqueAnterior;
		this.ajuste = ajuste;
		this.estoqueAtual = estoqueAtual;
		this.produto = produto;
		this.operador = operador;
		
	}

	public Transacao(int id_transacao, int tipo, Date dataTransacao, Time horaTransacao, double valorUnitario,
			double valorVenda, int estoqueAnterior, int ajuste, int estoqueAtual, Produto produto, Operador operador) {
		
		this.id_transacao = id_transacao;
		this.tipo = tipo;
		this.dataTransacao = dataTransacao;
		this.horaTransacao = horaTransacao;
		this.valorUnitario = valorUnitario;
		this.valorVenda = valorVenda;
		this.estoqueAnterior = estoqueAnterior;
		this.ajuste = ajuste;
		this.estoqueAtual = estoqueAtual;
		this.produto = produto;
		this.operador = operador;
		
	}

	public int getId_transacao() {
		return id_transacao;
	}

	public void setId_transacao(int id_transacao) {
		this.id_transacao = id_transacao;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Time getHoraTransacao() {
		return horaTransacao;
	}

	public void setHoraTransacao(Time horaTransacao) {
		this.horaTransacao = horaTransacao;
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

	public int getEstoqueAnterior() {
		return estoqueAnterior;
	}

	public void setEstoqueAnterior(int estoqueAnterior) {
		this.estoqueAnterior = estoqueAnterior;
	}

	public int getAjuste() {
		return ajuste;
	}

	public void setAjuste(int ajuste) {
		this.ajuste = ajuste;
	}

	public int getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(int estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ajuste;
		result = prime * result + ((dataTransacao == null) ? 0 : dataTransacao.hashCode());
		result = prime * result + estoqueAnterior;
		result = prime * result + estoqueAtual;
		result = prime * result + ((horaTransacao == null) ? 0 : horaTransacao.hashCode());
		result = prime * result + id_transacao;
		result = prime * result + ((operador == null) ? 0 : operador.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + tipo;
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
		Transacao other = (Transacao) obj;
		if (ajuste != other.ajuste)
			return false;
		if (dataTransacao == null) {
			if (other.dataTransacao != null)
				return false;
		} else if (!dataTransacao.equals(other.dataTransacao))
			return false;
		if (estoqueAnterior != other.estoqueAnterior)
			return false;
		if (estoqueAtual != other.estoqueAtual)
			return false;
		if (horaTransacao == null) {
			if (other.horaTransacao != null)
				return false;
		} else if (!horaTransacao.equals(other.horaTransacao))
			return false;
		if (id_transacao != other.id_transacao)
			return false;
		if (operador == null) {
			if (other.operador != null)
				return false;
		} else if (!operador.equals(other.operador))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (tipo != other.tipo)
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
		builder.append("Transacao [id_transacao=").append(id_transacao).append(", tipo=").append(tipo)
				.append(", dataTransacao=").append(dataTransacao).append(", horaTransacao=").append(horaTransacao)
				.append(", valorUnitario=").append(valorUnitario).append(", valorVenda=").append(valorVenda)
				.append(", estoqueAnterior=").append(estoqueAnterior).append(", ajuste=").append(ajuste)
				.append(", estoqueAtual=").append(estoqueAtual).append(", produto=").append(produto)
				.append(", operador=").append(operador).append("]");
		return builder.toString();
	}

	
	
	
}

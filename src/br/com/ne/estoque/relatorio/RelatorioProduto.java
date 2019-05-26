package br.com.ne.estoque.relatorio;

import java.io.InputStream;
import java.util.List;

import javax.swing.JDialog;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioProduto {

	private String tipo;
	private String codigo;
	private String nome;
	private String categoria;
	private String estoqueAtual;
	private String situacao;
		
	public RelatorioProduto() {}
	
	public RelatorioProduto(String tipo, String codigo, String nome, String categoria, String estoqueAtual,
			String situacao) {
		
		this.tipo = tipo;
		this.codigo = codigo;
		this.nome = nome;
		this.categoria = categoria;
		this.estoqueAtual = estoqueAtual;
		this.situacao = situacao;
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(String estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((estoqueAtual == null) ? 0 : estoqueAtual.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		RelatorioProduto other = (RelatorioProduto) obj;
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
		if (estoqueAtual == null) {
			if (other.estoqueAtual != null)
				return false;
		} else if (!estoqueAtual.equals(other.estoqueAtual))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelatorioProduto [tipo=").append(tipo).append(", codigo=").append(codigo).append(", nome=")
				.append(nome).append(", categoria=").append(categoria).append(", estoqueAtual=").append(estoqueAtual)
				.append(", situacao=").append(situacao).append("]");
		return builder.toString();
	}
	

	public void gerarRelatorioProduto(List<RelatorioProduto> listaRelatorioProduto ) throws JRException {
				
		JDialog viewer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
		viewer.setSize(1200, 800);
		viewer.setLocationRelativeTo(null);
		
		InputStream fonte = RelatorioProduto.class.getResourceAsStream("/report/relatorioProdutos.jrxml");
		
		JRDataSource jrds = new JRBeanCollectionDataSource(listaRelatorioProduto);

		JasperReport report = JasperCompileManager.compileReport(fonte);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, null , jrds);
		JasperViewer jrViewer = new JasperViewer(jasperPrint, false);	

		viewer.getContentPane().add(jrViewer.getContentPane());
		viewer.setVisible(true);
		
	}

	

}

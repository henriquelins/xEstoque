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

public class RelatorioTransacao {

	private String modalidade;
	private String codigo;
	private String categoria;
	private String nome;
	private String estoqueAnterior;
	private String ajuste;
	private String estoqueAtual;
	private String colaborador;
	private String tipo;
			
	public RelatorioTransacao() {}

	public RelatorioTransacao(String modalidade, String codigo, String categoria, String nome, String estoqueAnterior,
			String ajuste, String estoqueAtual, String colaborador, String tipo) {
		
		this.modalidade = modalidade;
		this.codigo = codigo;
		this.categoria = categoria;
		this.nome = nome;
		this.estoqueAnterior = estoqueAnterior;
		this.ajuste = ajuste;
		this.estoqueAtual = estoqueAtual;
		this.colaborador = colaborador;
		this.tipo = tipo;
		
	}
	
	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstoqueAnterior() {
		return estoqueAnterior;
	}

	public void setEstoqueAnterior(String estoqueAnterior) {
		this.estoqueAnterior = estoqueAnterior;
	}

	public String getAjuste() {
		return ajuste;
	}

	public void setAjuste(String ajuste) {
		this.ajuste = ajuste;
	}

	public String getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(String estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ajuste == null) ? 0 : ajuste.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((colaborador == null) ? 0 : colaborador.hashCode());
		result = prime * result + ((estoqueAnterior == null) ? 0 : estoqueAnterior.hashCode());
		result = prime * result + ((estoqueAtual == null) ? 0 : estoqueAtual.hashCode());
		result = prime * result + ((modalidade == null) ? 0 : modalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		RelatorioTransacao other = (RelatorioTransacao) obj;
		if (ajuste == null) {
			if (other.ajuste != null)
				return false;
		} else if (!ajuste.equals(other.ajuste))
			return false;
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
		if (colaborador == null) {
			if (other.colaborador != null)
				return false;
		} else if (!colaborador.equals(other.colaborador))
			return false;
		if (estoqueAnterior == null) {
			if (other.estoqueAnterior != null)
				return false;
		} else if (!estoqueAnterior.equals(other.estoqueAnterior))
			return false;
		if (estoqueAtual == null) {
			if (other.estoqueAtual != null)
				return false;
		} else if (!estoqueAtual.equals(other.estoqueAtual))
			return false;
		if (modalidade == null) {
			if (other.modalidade != null)
				return false;
		} else if (!modalidade.equals(other.modalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
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
		builder.append("RelatorioTransacao [modalidade=").append(modalidade).append(", codigo=").append(codigo)
				.append(", categoria=").append(categoria).append(", nome=").append(nome).append(", estoqueAnterior=")
				.append(estoqueAnterior).append(", ajuste=").append(ajuste).append(", estoqueAtual=")
				.append(estoqueAtual).append(", colaborador=").append(colaborador).append(", tipo=").append(tipo)
				.append("]");
		return builder.toString();
	}

	public void gerarRelatorioTransacao(List<RelatorioTransacao> listaRelatorioTransacao ) throws JRException {
				
		JDialog viewer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
		viewer.setSize(1200, 800);
		viewer.setLocationRelativeTo(null);
		
		InputStream fonte = RelatorioTransacao.class.getResourceAsStream("/report/relatorioTransacoes.jrxml");
		
		JRDataSource jrds = new JRBeanCollectionDataSource(listaRelatorioTransacao);

		JasperReport report = JasperCompileManager.compileReport(fonte);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, null , jrds);
		JasperViewer jrViewer = new JasperViewer(jasperPrint, false);	

		viewer.getContentPane().add(jrViewer.getContentPane());
		viewer.setVisible(true);
		
	}

	

}

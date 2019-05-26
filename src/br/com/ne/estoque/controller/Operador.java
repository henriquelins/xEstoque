package br.com.ne.estoque.controller;

public class Operador {

	private int id_operador;
	private String nome;
	private String login;
	private String senha;

	public Operador() {

	}

	public Operador(String nome, String login, String senha) {

		this.nome = nome;
		this.login = login;
		this.senha = senha;

	}

	public Operador(int id_operador, String nome, String login, String senha) {

		this.id_operador = id_operador;
		this.nome = nome;
		this.login = login;
		this.senha = senha;

	}

	public int getId_operador() {
		return id_operador;
	}

	public void setId_operador(int id_operador) {
		this.id_operador = id_operador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_operador;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Operador other = (Operador) obj;
		if (id_operador != other.id_operador)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Operador [id_operador=").append(id_operador).append(", nome=").append(nome).append(", login=")
				.append(login).append(", senha=").append(senha).append("]");
		return builder.toString();
	}

	
	

}

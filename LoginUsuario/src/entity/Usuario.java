package entity;

public class Usuario {

	private String nome;
	private String senha;
	private String confirmacaoSenha;
	
	public Usuario(String nome, String senha, String confirmacaoSenha) {
		this.nome = nome;
		this.senha = senha;
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	
}

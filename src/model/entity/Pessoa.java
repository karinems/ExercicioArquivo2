package model.entity;

public class Pessoa {
	private int Cod;
	private String nome;
	private String email;

	public Pessoa() {
		this.Cod = 0;
		this.nome = "";
		this.email = "";
	}

	public int getCod() {
		return Cod;
	}

	public void setCod(int cod) {
		Cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

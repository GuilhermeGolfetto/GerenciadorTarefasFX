package model;

public class Tarefa {

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	private String nome;
	private String descricao;
	private String prazo;
	private String prioridade;
	private String concluida;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getConcluida() {
		return concluida;
	}

	public void setConcluida(String concluida) {
		this.concluida = concluida;
	}


}

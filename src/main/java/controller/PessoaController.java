package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<>();

	@PostConstruct
	public void initialize() {
		for (int i = 0; i < 21; i++) {
			pessoa.setNome("Teste: " + i);
			pessoa.setIdade(18 + i);
			pessoa.setSexo(i % 2 == 0 ? "M" : "F");
			pessoas.add(pessoa);
			pessoa = new Pessoa();
		}
	}

	public void editar(ActionEvent event) {
		setPessoa((Pessoa) event.getComponent().getAttributes().get("pessoaSelecionada"));
	}

	public void excluir(ActionEvent event) {
		pessoas.remove((Pessoa) event.getComponent().getAttributes().get("pessoaExcluida"));
	}

	public void salvar() {
		pessoas.add(pessoa);
		addMessage("Salvo com sucesso", "Pessoa " + pessoa.getNome() + " salvo com sucesso!");

		pessoa = new Pessoa();
	}

	public void addMessage(String mensagem, String detalhe) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, detalhe);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}

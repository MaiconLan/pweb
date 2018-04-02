package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa  = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<>();

	public void salvar() {
		pessoas.add(pessoa);
		pessoa = new Pessoa();
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

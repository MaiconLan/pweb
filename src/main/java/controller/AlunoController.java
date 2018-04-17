package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import business.AlunoBusiness;
import model.Aluno;

@ViewScoped
@ManagedBean(name="alunoController")
public class AlunoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno;

	private List<Aluno> alunos = new ArrayList<>();

	private AlunoBusiness alunoBusiness;

	@PostConstruct
	public void initialize() {
		novoAluno();
		for (int i = 0; i < 11; i++) {
			aluno.setNome("Teste: " + i);
			aluno.setNota1(10D);
			aluno.setNota2(9D);
			aluno.setNota3(4D);
			alunos.add(aluno);
			novoAluno();
		}
		
		alunoBusiness = new AlunoBusiness();
	}

	public String navegar() {
		return "cliente";
	}
	
	
	public void editar(ActionEvent event) {
		setAluno((Aluno) event.getComponent().getAttributes().get("alunoSelecionado"));
	}

	public void excluir(ActionEvent event) {
		alunos.remove((Aluno) event.getComponent().getAttributes().get("alunoExcluido"));
	}

	public void salvar() {
		aluno.setMedia(alunoBusiness.gerarMedia(aluno));
		aluno.setSituacao(alunoBusiness.gerarSituacao(aluno));
		alunos.add(aluno);
		novoAluno();
	}

	public void novoAluno() {
		aluno = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}

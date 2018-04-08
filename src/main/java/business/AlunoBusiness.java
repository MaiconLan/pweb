package business;

import model.Aluno;

public class AlunoBusiness {

	public Double gerarMedia(Aluno aluno) {
		Double media = 0D;
		media += (aluno.getNota1() + aluno.getNota2() + aluno.getNota3()) / 3;
		return media;
	}

	public String gerarSituacao(Aluno aluno) {
		Double media = aluno.getMedia();
		String situacao = "";

		if(media >= 7) {
			situacao = "Aprovado";
		} else if(media < 4) {
			situacao = "Reprovado";
		} else {
			situacao = "Recuperação";
		}
		
		return situacao;
	}
}

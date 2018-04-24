package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import model.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaController implements Serializable {

	/**
	 * 
	 */
	private String caminhoArquivoEnviado = "";

	private UploadedFile arquivoEnviado = null;

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();

	private List<Pessoa> pessoas = new ArrayList<>();

	@PostConstruct
	public void initialize() {
		// for (int i = 0; i < 21; i++) {
		// pessoa.setNome("Teste: " + i);
		// pessoa.setIdade(18 + i);
		// pessoa.setSexo(i % 2 == 0 ? "M" : "F");
		// pessoas.add(pessoa);
		// pessoa = new Pessoa();
		// }
	}

	public void upload(FileUploadEvent event) {
		try {
			limparCache();
			// UploadedFile uploadedFile = event.getFile();
			// File file = new File("C:\\uploads", uploadedFile.getFileName());
			// OutputStream out = new FileOutputStream(file);
			// out.write(uploadedFile.getContents());
			// out.close();

			UploadedFile x = event.getFile();
			Path t = Files.createTempFile(null, null);
			Files.copy(x.getInputstream(), t, StandardCopyOption.REPLACE_EXISTING);

			caminhoArquivoEnviado = t.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent event) {
		setPessoa((Pessoa) event.getComponent().getAttributes().get("pessoaSelecionada"));
	}

	public void excluir(ActionEvent event) {
		pessoas.remove((Pessoa) event.getComponent().getAttributes().get("pessoaExcluida"));
	}

	public void limparCache() {
		arquivoEnviado = null;
		caminhoArquivoEnviado = "";
	}

	public void salvar() {
		try {
			pessoas.add(pessoa);
			Path origem = Paths.get(caminhoArquivoEnviado);
			Path destino = Paths.get("C:\\uploads\\" + pessoa.getIdPessoa() + "_" + pessoa.getNome() + ".png");

			System.out.println(caminhoArquivoEnviado);
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			addMessage("Salvo com sucesso", "Pessoa " + pessoa.getNome() + " salvo com sucesso!");
			pessoa = new Pessoa();

		} catch (IOException e) {
			e.printStackTrace();
		}
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

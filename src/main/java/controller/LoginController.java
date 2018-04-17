package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Usuario;

@ViewScoped
@ManagedBean(name = "loginController")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	@PostConstruct
	public void initialize() {
		usuario = new Usuario();
	}

	public String logar() {
		if (usuario.getLogin().equals("admin") && usuario.getSenha().equals("admin")) {
			return "cliente";
		}
		return "template/error";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

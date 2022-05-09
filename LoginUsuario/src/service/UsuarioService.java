package service;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import entity.Usuario;

public class UsuarioService {
	
	private List<Usuario> usuarios = new ArrayList<>();

	public UsuarioService() {}
	
	public UsuarioService(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void criarUsuario(Usuario usuario) {
		 usuarios.add(usuario);
	}
	
	public void AtualizarLista(List<Usuario> users) {
		 Iterator itr = usuarios.iterator();
		 while (itr.hasNext()) {
			 Usuario u = (Usuario) itr.next();
			itr.remove();
		}
		 
		for(Usuario user : users) {
			usuarios.add(user);
		}
	}
	
	public void removerUsuario(String nomeUsuario, String senhaUsuario) {
		try {
			for(Usuario user : usuarios) {
				if(user.getNome().equals(nomeUsuario) && user.getSenha().equals(senhaUsuario)) {
					usuarios.remove(user);
				}
			}
		} catch (ConcurrentModificationException e) {
			System.out.println("Sem usuários cadastrado.");
		}
	}
	
	public void alterarUsuario(Usuario usuario, String nomeUsuario, String senhaUsuario) {
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getNome().equals(nomeUsuario) && usuarios.get(i).getSenha().equals(senhaUsuario)) {
				usuarios.set(i, usuario);
			}
		}
	}
	
	public List<Usuario> findAll() {
		return usuarios;
	}
}
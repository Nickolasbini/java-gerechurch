package controllers;

import java.util.ArrayList;

import classes.Pessoa;
import helpers.FunctionsClass;

public class PessoaController {
	
	public static Pessoa person;
	public PessoaController() {
		person = new Pessoa();
	}
	
	public static Pessoa autenticate(String email, String password) {
		if(person == null)
			person = new Pessoa();
		ArrayList<String[]> resultado = person.buscar("email", email, true);
		if(resultado.size() < 1)
			return null;
		String[] dado = resultado.get(0);
		person = person.criarObjetoComResultado(dado, false);
		if(person.getPassword().equals(password)) {
			return person;
		}
		return null;
	}
	
	public static Boolean salvar(Pessoa person) {
		return person.salvar(person);
	}
	
	public static Pessoa buscarPessoaPorId(Integer id) {
		String[] result = person.buscarPorId(Integer.toString(id));
		if(result.length == 0)
			return null;
		person = person.criarObjetoComResultado(result, true);
		return person;
	}
	
	public static Boolean remover(Pessoa personObj) {
		if(personObj.getId() == null)
			return false;
		return person.remover(personObj.getId());
	}
	
	public static ArrayList<String[]> listarFuncionarios(Integer institutionId) {
		return person.buscar("institutionId", Integer.toString(institutionId), true);
	}
	
	public static Boolean removerPorId(String id) {
		return person.remover(id);
	}
}

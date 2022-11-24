package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Estabelecimento;
import classes.Pessoa;
import helpers.FunctionsClass;

public class FuncionarioController {
	
	private static Pessoa funcionario;
	public static Estabelecimento institution;
	public FuncionarioController() {
		funcionario = new Pessoa();
	}
	
	public static Object[][] buscarMeusFuncionarios() {
		if(funcionario == null)
			funcionario = new Pessoa();
		ArrayList<String[]> resultado = funcionario.buscar("institutionId", "1", true);
		resultado = funcionario.filtrarDados(resultado, 10, funcionario.FUNCIONARIO, true);
		
		String[][] dados = new String[resultado.size()][resultado.size()];
		
		String[] colunas = buscarColunasTabela();
		Integer posicao = 0;
		Integer[] posicoes = funcionario.posicoesCabecalho(funcionario.FUNCIONARIO);
		for(String[] dado : resultado) {
			String[] valores = new String[posicoes.length];
			Integer count = 0;
			dado[6] = (dado[6].equals("1") ? "Sim" : "Não");
			for(Integer i : posicoes) {
				if(FunctionsClass.returnPosicionOfArray("Salário", colunas) == i) {
					valores[count] = FunctionsClass.parseMonetary(dado[i]);
				}else {
					valores[count] = FunctionsClass.maskValue(dado[i]);
				}
				count++;
			}
			dados[posicao] = valores;
			posicao++;
		}
		
		Object[][] dadosTabela = {dados};
		return dados;
	}
	
	public static String[] buscarColunasTabela() {
		if(funcionario == null)
			funcionario = new Pessoa();
		return funcionario.gerarCabecalhoTabela(funcionario.FUNCIONARIO);
	}
	
	public static String[] comboFuncionarios(String institutionId) {
		funcionario = new Pessoa();
		ArrayList<String[]> resultado = funcionario.buscar("status", funcionario.FUNCIONARIO, true);
		resultado = funcionario.filtrarDados(resultado, 5, institutionId, true);
		resultado = funcionario.filtrarDados(resultado, 6, "1", true);
		String[] valoresParaRetornar = new String[resultado.size()];
		Integer position = 0;
		for(String[] dados : resultado) {
			valoresParaRetornar[position] = dados[1];
			position++;
		}
		return valoresParaRetornar;
	}
	
	public static ArrayList<Pessoa> comboFuncionariosComoObjetos(String institutionId) {
		funcionario = new Pessoa();
		ArrayList<String[]> resultado = funcionario.buscar("status", funcionario.FUNCIONARIO, true);
		resultado = funcionario.filtrarDados(resultado, 5, institutionId, true);
		resultado = funcionario.filtrarDados(resultado, 6, "1", true);
		ArrayList<Pessoa> retorno = new ArrayList<Pessoa>(resultado.size());
		for(String[] array : resultado) {
			Pessoa objeto = new Pessoa();
			retorno.add(objeto.criarObjetoComResultado(array, false));
		}
		return retorno;
	}
}

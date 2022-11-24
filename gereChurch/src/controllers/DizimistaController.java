package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Estabelecimento;
import classes.Pessoa;
import helpers.FunctionsClass;

public class DizimistaController {
	
	private static Pessoa dizimista;
	
	public static Estabelecimento institution;
	public DizimistaController() {
		dizimista = new Pessoa();
	}
	
	public static Object[][] buscarMeusDizimistas(String estabelecimentoId) {
		dizimista = new Pessoa();
		ArrayList<String[]> resultado = dizimista.buscar("institutionId", estabelecimentoId, true);
		resultado = dizimista.filtrarDados(resultado, 10, dizimista.DIZIMISTA, true);
		
		String[][] dados = new String[resultado.size()][resultado.size()];
		
		String[] colunas = buscarColunasTabela();
		Integer posicao = 0;
		Integer[] posicoes = dizimista.posicoesCabecalho(dizimista.DIZIMISTA);
		for(String[] dado : resultado) {
			String[] valores = new String[posicoes.length];
			Integer count = 0;
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
		return dados;
	}
	
	public static String[] buscarColunasTabela() {
		dizimista = new Pessoa();
		return dizimista.gerarCabecalhoTabela(dizimista.DIZIMISTA);
	}
	
	public static String[] comboMeusDizimistas(String estabelecimentoId) {
		dizimista = new Pessoa();
		ArrayList<String[]> resultado = dizimista.buscar("institutionId", estabelecimentoId, true);
		resultado = dizimista.filtrarDados(resultado, 10, dizimista.DIZIMISTA, true);
		String[] valoresParaRetornar = new String[resultado.size()];
		Integer position = 0;
		for(String[] dados : resultado) {
			valoresParaRetornar[position] = dados[1];
			position++;
		}
		return valoresParaRetornar;
	}
	
	public static ArrayList<Pessoa> comboMeusDizimistasComoObjetos(String estabelecimentoId) {
		dizimista = new Pessoa();
		ArrayList<String[]> resultado = dizimista.buscar("institutionId", estabelecimentoId, true);
		resultado = dizimista.filtrarDados(resultado, 10, dizimista.DIZIMISTA, true);
		ArrayList<Pessoa> retorno = new ArrayList<Pessoa>(resultado.size());
		for(String[] array : resultado) {
			Pessoa objeto = new Pessoa();
			retorno.add(objeto.criarObjetoComResultado(array, false));
		}
		return retorno;
	}
}

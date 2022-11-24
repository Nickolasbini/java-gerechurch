package controllers;

import java.util.ArrayList;

import classes.Estabelecimento;
import classes.Pessoa;
import classes.TipoPagamento;

public class TipoPagamentoController {
	
	public static TipoPagamento tipoPagamento;
	
	public TipoPagamentoController() {
		tipoPagamento = new TipoPagamento();
	}
	
	public static String[] comboTipoPagamento() {
		tipoPagamento = new TipoPagamento();
		ArrayList<String[]> resultado = tipoPagamento.buscarTodosComoArrayList();
		String[] valoresParaRetornar = new String[resultado.size()];
		Integer position = 0;
		for(String[] dados : resultado) {
			valoresParaRetornar[position] = dados[1];
			position++;
		}
		return valoresParaRetornar;
	}
	
	public static ArrayList<TipoPagamento> comboMeusTiposPagamentoComoObjetos(String estabelecimentoId) {
		tipoPagamento = new TipoPagamento();
		ArrayList<String[]> resultado = tipoPagamento.buscarTodosComoArrayList();
		ArrayList<TipoPagamento> retorno = new ArrayList<TipoPagamento>(resultado.size());
		for(String[] array : resultado) {
			TipoPagamento objeto = new TipoPagamento();
			retorno.add(objeto.criarObjetoComResultado(array, false));
		}
		return retorno;
	}
}

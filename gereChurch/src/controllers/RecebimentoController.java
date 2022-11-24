package controllers;

import java.util.ArrayList;
import java.util.Collections;

import classes.Pessoa;
import classes.Recebimento;
import helpers.FunctionsClass;

public class RecebimentoController {
	public static Recebimento recebimento;
	public static String institutionId;
	public RecebimentoController(String idOfInstitution) {
		recebimento = new Recebimento();
		institutionId = idOfInstitution;
	}
	
	public static ArrayList<String[]> listarTodosRecebimentos() {
		ArrayList<String[]> resultado = recebimento.buscar("institutionId", institutionId.toString(), true);
		return resultado;
	}
	
	public static Float listarTotalPorData(String data) {
		ArrayList<String[]> result = listarTodosRecebimentos();
		String[] dataArray = data.split("-");
		String dataAnoMes = "" + dataArray[0] + "-" + dataArray[1];
		ArrayList<String[]> dados = recebimento.filtrarDados(result, 3, dataAnoMes, false);
		Float valorTotal = (float)0;
		for(String[] valor : dados) {
			String valorRecebido = valor[2];
			if(valorRecebido == null)
				continue;
			Float valorRecebidoEmFloat = Float.parseFloat(valorRecebido);
			valorTotal = valorTotal + valorRecebidoEmFloat;
		}
		return valorTotal;
	}
	
	public static ArrayList<String[]> buscarTopTres(String data) {
		ArrayList<String[]> result = listarTodosRecebimentos();
		String[] dataArray = data.split("-");
		String dataAnoMes = "" + dataArray[0] + "-" + dataArray[1];
		ArrayList<String[]> dados = recebimento.filtrarDados(result, 3, dataAnoMes, false);
		ArrayList<String[]> topTresArray = new ArrayList<String[]>();
		// top 1
		String idPrimeiroLugar = "0";
		Float maiorValor = (float)0;
		for(String[] valor : dados) {
			String valorRecebido = valor[2];
			if(valorRecebido == null)
				continue;
			Float valorRecebidoEmFloat = Float.parseFloat(valorRecebido);
			if(valorRecebidoEmFloat > maiorValor) {
				maiorValor = valorRecebidoEmFloat;
				topTresArray.add(0, valor);
				idPrimeiroLugar = valor[0];
			}
		}
		// top 2
		String idSegundoLugar = "0";
		maiorValor = (float)0;
		for(String[] valor : dados) {
			String valorRecebido = valor[2];
			if(valorRecebido == null || valor[0].equals(idPrimeiroLugar))
				continue;
			Float valorRecebidoEmFloat = Float.parseFloat(valorRecebido);
			if(valorRecebidoEmFloat > maiorValor) {
				maiorValor = valorRecebidoEmFloat;
				topTresArray.add(1, valor);
				idSegundoLugar = valor[0];
			}
		}
		Integer sizeOfTopTresArrayList = topTresArray.size();
		// top 3
		String idTerceiroLugar = "0";
		maiorValor = (float)0;
		for(String[] valor : dados) {
			String valorRecebido = valor[2];
			if(valorRecebido == null || valor[0].equals(idPrimeiroLugar) || valor[0].equals(idSegundoLugar))
				continue;
			Float valorRecebidoEmFloat = Float.parseFloat(valorRecebido);
			if(valorRecebidoEmFloat > maiorValor) {
				maiorValor = valorRecebidoEmFloat;
				topTresArray.add(2, valor);
			}
		}
		ArrayList<String[]> topTresArrayAtualizada = new ArrayList<String[]>();
		Integer posicoes = 3;
		String[] vazia = {};
		for(Integer i = 0; i < posicoes; i++) {
			try {
				String[] valorEncontrado = topTresArray.get(i);
				Recebimento objeto = new Recebimento();
				objeto = objeto.criarObjetoComResultado(valorEncontrado, true);
				String[] dadosParaUsar = {objeto.getTither().getName(), objeto.getValue()};
				topTresArrayAtualizada.add(i, dadosParaUsar);
			} catch (Exception e) {
				// TODO: handle exception
				topTresArrayAtualizada.add(i, vazia);
			}
		}
		return topTresArrayAtualizada;
	}
	
	public Object[][] retornarMesesComRecebimentos() {
		ArrayList<String[]> resultado = recebimento.buscar("institutionId", institutionId, true);
		resultado = recebimento.filtrarDados(resultado, 5, institutionId, true);
		ArrayList<String> mesesComRecebimentos = new ArrayList<>();
		ArrayList<String> valoresEncontrados   = new ArrayList<>();
		Boolean temResultados = false;
		for(String[] data : resultado) {
			String date  = data[3];
			String valor = data[2];
			if(date == null)
				continue;
			date = FunctionsClass.formatarDataUTC(date);
			if(mesesComRecebimentos.contains(date) == false) {
				mesesComRecebimentos.add(date);
			}
		}
		ArrayList<String[]> dadosEncontrados = new ArrayList<>();
		String[][] dados = new String[mesesComRecebimentos.size()][mesesComRecebimentos.size()];
		Integer position = 0;
		for(String mesRelacionado : mesesComRecebimentos) {
			if(mesRelacionado == null)
				continue;
			dadosEncontrados = recebimento.filtrarDados(resultado, 3, mesRelacionado, false);
			String valorTotalizado = FunctionsClass.parseMonetary(recebimento.totalizarValorRecebido(dadosEncontrados));
			String[] dadoArray = {FunctionsClass.parseDateToBR(mesRelacionado), valorTotalizado};
			dados[position] = dadoArray;
			position++;
			temResultados = true;
		}
		if(temResultados == false)
			dados = new String[0][];
		return dados;
	}
	
	public String[] buscarColunasTabela(Boolean minimizado) {
		if(recebimento == null)
			recebimento = new Recebimento();
		return recebimento.gerarCabecalhoTabela(minimizado);
	}
	
	public Object[][] buscarRecebimentosPorMes (String data, String valorParaBusca) {
		ArrayList<String[]> resultado = recebimento.buscar("institutionId", institutionId, true);
		String[] dataArray = data.split("-");
		String dataFormatada = dataArray[1] + "-" + dataArray[0];
		resultado = recebimento.filtrarDados(resultado, 3, dataFormatada, false);
		
		String[][] dados = new String[resultado.size()][resultado.size()];
		
		String[] colunas = buscarColunasTabela(false);
		Integer posicao = 0;
		Integer[] posicoes = recebimento.posicoesCabecalho(false);
		for(String[] dado : resultado) {
			Recebimento objetoAtual = new Recebimento();
			objetoAtual = objetoAtual.criarObjetoComResultado(dado, true);
			String[] valorUsado = {
				objetoAtual.getId(),
				objetoAtual.getPaymentType().getName(), 
				FunctionsClass.parseMonetary(objetoAtual.getValue()),
				FunctionsClass.formatPTBR(objetoAtual.getPaymentDate().toString()),
				objetoAtual.getTither().getName(),
				objetoAtual.getWorker().getName()
			};
			dados[posicao] = valorUsado;
			posicao++;
		}
		
		return dados;
	}
	
	public Object[][] buscarRecebimentosDizimista (Pessoa dizimista) {
		if(!dizimista.getStatus().equals(dizimista.DIZIMISTA))
			return null;
		System.out.println(dizimista.getId());
		ArrayList<String[]> resultado = recebimento.buscar("titherId", dizimista.getId(), true);
		Collections.reverse(resultado);
		String[][] dados = new String[resultado.size()][resultado.size()];
		String[] colunas = buscarColunasTabela(false);
		Integer posicao = 0;
		Integer[] posicoes = {3,2};
		for(String[] dado : resultado) {
			Recebimento objetoAtual = new Recebimento();
			objetoAtual = objetoAtual.criarObjetoComResultado(dado, true);
			System.out.println(objetoAtual.getPaymentDate());
			String[] valorUsado = {
				FunctionsClass.formatPTBR(objetoAtual.getPaymentDate().toString()),
				FunctionsClass.parseMonetary(objetoAtual.getValue()),
			};
			dados[posicao] = valorUsado;
			posicao++;
		}
		return dados;
	}
	
	public static Boolean salvar(Recebimento recebimento) {
		return recebimento.salvar(recebimento);
	}
	
	public static Boolean remover(String id) {
		if(id == null)
			return false;
		Recebimento recebimento = new Recebimento();
		recebimento = recebimento.buscarRecebimentoPorId(FunctionsClass.parseToInteger(id), false);
		if(recebimento.getId() == null)
			return false;
		return recebimento.remover(recebimento.getId());
	}
}

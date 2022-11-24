package controllers;

import java.util.ArrayList;

import classes.Estabelecimento;
import classes.Pessoa;
import classes.Pessoa_Instituicao;
import classes.Recebimento;
import helpers.FunctionsClass;

public class EstabelecimentoController {
	
	public static void main(String[] args) {
		institution = new Estabelecimento();
	}
	
	public static Estabelecimento institution;
	public EstabelecimentoController(String estabelecimentoId) {
		institution = new Estabelecimento();
		if(estabelecimentoId != null) 
			institution = buscarEstabelecimentoPorId(Integer.parseInt(estabelecimentoId));
	}
	
	public static Estabelecimento buscarEstabelecimentoPorId(Integer id) {
		String[] result = institution.buscarPorId(Integer.toString(id));
		if(result.length == 0)
			return null;
		institution = institution.criarObjetoComResultado(result, true);
		return institution;
	}
	
	public static Object[][] buscarEstabelecimentos(Pessoa usuarioLogado) {
		institution = new Estabelecimento();
		ArrayList<String[]> resultado = null;
		if(usuarioLogado.administradorMestre(usuarioLogado)) {
			resultado = institution.buscarTodosComoArrayList();
		}else {
			resultado = institution.buscar("id", usuarioLogado.getInstitutionId(), true);
		}
		String[][] dados = new String[resultado.size()][resultado.size()];
		
		String[] colunas = buscarColunasTabela();
		Integer posicao = 0;
		Integer[] posicoes = institution.posicoesCabecalho();
		for(String[] dado : resultado) {
			Estabelecimento objeto = new Estabelecimento();
			objeto = objeto.criarObjetoComResultado(dado, true);
			String[] valores = {
				objeto.getId(),
				objeto.getName(),
				objeto.getFullAddress(objeto),
				objeto.getRepresentative().getName()
			};
			dados[posicao] = valores;
			posicao++;
		}
		return dados;
	}
	
	public static String[] buscarColunasTabela() {
		institution = new Estabelecimento();
		return institution.gerarCabecalhoTabela();
	}
	
	public static Boolean salvar(Estabelecimento institution) {
		Pessoa_Instituicao personInstitutionObj = new Pessoa_Instituicao();
		personInstitutionObj.sincronizarPessoaInstituicao(institution.getId(), institution.getRepresentativeId());
		return institution.salvar(institution);
	}
	
	public static Boolean remover(String idDoEstabelecimento) {
		if(institution.getId() == null && idDoEstabelecimento.equals(""))
			return false;
		String institutionId         = idDoEstabelecimento;
		Integer institutionIdInteger = FunctionsClass.parseToInteger(institutionId); 
		Boolean resultado = institution.remover(institutionId);
		if(resultado == true) {
			// Remove Pessoas da instituição
			PessoaController objetoPessoaController = new PessoaController();
			ArrayList<String[]> todasAsPessoas = objetoPessoaController.listarFuncionarios(institutionIdInteger);
			for(String[] dadosPessoa : todasAsPessoas) {
				Pessoa objetoPessoa = new Pessoa();
				objetoPessoa.remover(dadosPessoa[0]);
			}
			// Remove Recebimentos
			RecebimentoController objetoRecebimentoController = new RecebimentoController(institutionId);
			ArrayList<String[]> todasOsRecebimentos = objetoRecebimentoController.listarTodosRecebimentos();
			for(String[] dadosRecebimento : todasOsRecebimentos) {
				Recebimento objetoRecebimento = new Recebimento();
				objetoRecebimento.remover(dadosRecebimento[0]);
			}
		}
		return resultado;
	}
}

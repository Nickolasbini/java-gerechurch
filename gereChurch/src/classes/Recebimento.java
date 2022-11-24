package classes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import helpers.FunctionsClass;

public class Recebimento extends ModeloBase {
	private String[] classMap = {
		"id",
		"paymentTypeId",
		"value",
		"paymentDate",
		"titherId",
		"institutionId",
		"workerId"
	};
	
	private static String fileName = "receivement";
	
	private static Recebimento receivement;
	
	private String id;
	private String paymentTypeId;
	private TipoPagamento paymentType;
	private String value;
	private String paymentDate;
	private String titherId;
	private Pessoa tither;
	private String institutionId;
	private Estabelecimento institution;
	private String workerId;
	private Pessoa worker;
	
	public Recebimento() {
		this.setNomeArquivoObj(fileName);
		this.setAtributos(classMap);
		this.receivement = this;
		this.setObjetoAtual(receivement);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	
	public TipoPagamento getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(TipoPagamento paymentType) {
		this.paymentType = paymentType;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public String getTitherId() {
		return titherId;
	}
	public void setTitherId(String titherId) {
		this.titherId = titherId;
	}
	
	public Pessoa getTither() {
		return tither;
	}
	public void setTither(Pessoa tither) {
		this.tither = tither;
	}
	
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	
	public Estabelecimento getInstitution() {
		return institution;
	}
	public void setInstitution(Estabelecimento institution) {
		this.institution = institution;
	}
	
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	
	public Pessoa getWorker() {
		return worker;
	}
	public void setWorker(Pessoa worker) {
		this.worker = worker;
	}
	
	public static String[] pegarDadosDoObjeto(Recebimento receivement) {
		String[] objectData = {
			receivement.getId(), 
			receivement.getPaymentTypeId(),
			receivement.getValue(),
			receivement.getPaymentDate(),
			receivement.getTitherId(),
			receivement.getInstitutionId(),
			receivement.getWorkerId()
		};
		return objectData;
	}
	
	public static Recebimento criarObjetoComResultado(String[] data, Boolean fetchRelations) {
		receivement.setId(data[0]);
		receivement.setPaymentTypeId(data[1]);
		if(receivement.getPaymentTypeId() != "null" && fetchRelations == true) {
			TipoPagamento paymentTypeObj = new TipoPagamento();
			TipoPagamento result = paymentTypeObj.buscarTipoPagamentoPorId(Integer.parseInt(receivement.getPaymentTypeId()), false);
			receivement.setPaymentType(result);
		}
		receivement.setValue(data[2]);
		receivement.setPaymentDate(data[3]);
		receivement.setTitherId(data[4]);
		if(receivement.getTitherId() != "null" && fetchRelations == true) {
			Pessoa titherObj = new Pessoa();
			Pessoa result = titherObj.buscarPessoaPorId(Integer.parseInt(receivement.getTitherId()), false);
			receivement.setTither(result);
		}
		receivement.setInstitutionId(data[5]);
		if(receivement.getInstitutionId() != "null" && fetchRelations == true) {
			Estabelecimento institutionObj = new Estabelecimento();
			Estabelecimento result = institutionObj.buscarEstabelecimentoPorId(Integer.parseInt(receivement.getInstitutionId()), false);
			receivement.setInstitution(result);
		}
		receivement.setWorkerId(data[6]);
		if(receivement.getWorkerId() != "null" && fetchRelations == true) {
			Pessoa workerObj = new Pessoa();
			Pessoa result = workerObj.buscarPessoaPorId(Integer.parseInt(receivement.getWorkerId()), false);
			receivement.setWorker(result);
		}
		return receivement;
	}
	
	public static Recebimento buscarRecebimentoPorId(Integer id, Boolean fetchRelations) {
		String[] objectData;
		String result = buscarDadosArquivoPorId("receivement", Integer.toString(id));
		if(result == null)
			return null;
		objectData = result.split(",");
		return criarObjetoComResultado(objectData, fetchRelations);
	}
	
	public static String[] gerarCabecalhoTabela(Boolean minimizada) {
		if(minimizada == true) {
			String[] cabecalho = {"Mês do Pagamento", "Valor Total do Mês"};
			return cabecalho;
		}else {
			String[] cabecalho = {"Identificador", "Tipo de Pagamento", "Valor", "Data do Pagamento", "Dizimista", "Funcionário"};
			return cabecalho;
		}
	}
	
	public static Integer[] posicoesCabecalho(Boolean minimizado) {
		if(minimizado == true) {
			Integer[] posicoes = {0,1};
			return posicoes;
		}else {
			Integer[] posicoes = {0,1,2,3,4,6};
			return posicoes;
		}
	}
	
	public static String totalizarValorRecebido(ArrayList<String[]> arrayList) {
		String valorTotal = "";
		Float total = (float)0;
		for(String[] dado : arrayList) {
			Float valor = Float.parseFloat(dado[2]);
			total = total + valor;
		}
		valorTotal = total.toString();
		return valorTotal;
	}
	
	public static Boolean salvar(Recebimento recebimento) {
		String[] objectData = pegarDadosDoObjeto(recebimento);
		if(objectData[0] == null)
			objectData[0] = "id";
		for(Integer i = 0; i < objectData.length; i++) {
			if((objectData[i] == null || objectData[i].equals("")) && i > 0)
				objectData[i] = "null";
		}
		String values = String.join(",", objectData);
		return salvarObj(values, fileName);
	}
}

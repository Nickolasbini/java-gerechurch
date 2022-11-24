package classes;

import helpers.FunctionsClass;

public class TipoPagamento extends ModeloBase {
	private String[] classMap = {
		"id",
		"name"
	};
	
	private static String fileName = "paymentType";
	
	private static TipoPagamento paymentType;
	
	private String id;
	private String name;
	
	public TipoPagamento() {
		this.setNomeArquivoObj(fileName);
		this.setAtributos(classMap);
		this.paymentType = this;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static String[] pegarDadosDoObjeto(TipoPagamento paymentType) {
		String[] objectData = {
			paymentType.getId(), 
			paymentType.getName()
		};
		return objectData;
	}
	
	public static TipoPagamento criarObjetoComResultado(String[] data, Boolean fetchRelations) {
		paymentType.setId(data[0]);
		paymentType.setName(data[1]);
		return paymentType;
	}
	
	public static TipoPagamento buscarTipoPagamentoPorId(Integer id, Boolean fetchRelations) {
		String[] objectData;
		String result = buscarDadosArquivoPorId("paymentType", Integer.toString(id));
		if(result == null)
			return null;
		objectData = result.split(",");
		return criarObjetoComResultado(objectData, fetchRelations);
	}
}

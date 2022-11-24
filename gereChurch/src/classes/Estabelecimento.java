package classes;

import controllers.PessoaController;
import helpers.FunctionsClass;

public class Estabelecimento extends ModeloBase {
	private String[] classMap = {
		"id",
		"name",
		"street",
		"number",
		"neighborhood",
		"city",
		"country",
		"representativeId"
	};
	
	private static String fileName = "institution";
	
	private static Estabelecimento institution;
	
	private String id;
	private String name;
	private String street;
	private String number;
	private String neighborhood;
	private String city;
	private String country;
	private String representativeId;
	private Pessoa representative;
	
	public Estabelecimento() {
		this.setNomeArquivoObj(fileName);
		this.setAtributos(classMap);
		this.institution = this;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getRepresentativeId() {
		return representativeId;
	}
	public void setRepresentativeId(String representativeId) {
		this.representativeId = representativeId;
	}
	
	public Pessoa getRepresentative() {
		return representative;
	}
	public void setRepresentative(Pessoa representative) {
		this.representative = representative;
	}
	
	// Methods
	public static Boolean salvar(Estabelecimento institution) {
		String[] objectData = pegarDadosDoObjeto(institution);
		if(objectData[0] == null)
			objectData[0] = "id";
		for(Integer i = 1; i < objectData.length; i++) {
			if((objectData[i] == null || objectData[i].equals("")) && i > 0)
				objectData[i] = "null";
		}
		String values = String.join(",", objectData);
		return salvarObj(values, fileName);
	}
	
	public static String[] pegarDadosDoObjeto(Estabelecimento institution) {
		String[] objectData = {
			institution.getId(), 
			institution.getName(),
			institution.getStreet(),
			institution.getNumber(),
			institution.getNeighborhood(),
			institution.getCity(),
			institution.getCountry(),
			institution.getRepresentativeId()
		};
		return objectData;
	}
	
	public static Estabelecimento criarObjetoComResultado(String[] data, Boolean fetchRelations) {
		institution.setId(data[0]);
		institution.setName(data[1]);
		institution.setStreet(data[2]);
		institution.setNumber(data[3]);
		institution.setNeighborhood(data[4]);
		institution.setCity(data[5]);
		institution.setCountry(data[6]);
		institution.setRepresentativeId(data[7]);
		if(institution.getRepresentativeId() != "null" && fetchRelations == true) {
			Pessoa representativeObj = new Pessoa();
			Pessoa result = representativeObj.buscarPessoaPorId(Integer.parseInt(institution.getRepresentativeId()), false);
			institution.setRepresentative(result);
		}
		return institution;
	}
	
	public static Estabelecimento buscarEstabelecimentoPorId(Integer id, Boolean fetchRelations) {
		String[] objectData;
		String result = buscarDadosArquivoPorId("institution", Integer.toString(id));
		if(result == null)
			return null;
		objectData = result.split(",");
		return criarObjetoComResultado(objectData, fetchRelations);
	}
	
	public static String[] gerarCabecalhoTabela() {
		String[] cabecalho = {"Identificador", "Nome", "Endereço","Representante"};
		return cabecalho;
	}
	
	public static Integer[] posicoesCabecalho() {
		Integer[] posicoes = {0,1,2,3,7};
		return posicoes;
	}
	
	public static String getFullAddress(Estabelecimento obj) {
		return obj.getStreet() + " - N°:" + obj.getNumber() + ", " + obj.getNeighborhood() + " ," + obj.getCity() + " ," + obj.getCountry();
	}
}

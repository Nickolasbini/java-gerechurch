package classes;

import java.util.ArrayList;

import helpers.FunctionsClass;

public class Pessoa_Instituicao extends ModeloBase{
	private String[] classMap = {
		"id",
		"personId",
		"institutionId"
	};
	
	private static String fileName = "person_institution";
	
	private static Pessoa_Instituicao person_institution;
	
	private String  id;
	private String  personId;
	private Pessoa  person;
	private String  institutionId;
	private Estabelecimento institution;
	
	public Pessoa_Instituicao() {
		this.setNomeArquivoObj(fileName);
		this.setAtributos(classMap);
		this.person_institution = this;
		this.setObjetoAtual(person_institution);
	}
	
	// Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Pessoa getPerson() {
		return person;
	}
	public void setPerson(Pessoa person) {
		this.person = person;
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
	
	// Methods
	public static Boolean salvar(Pessoa_Instituicao personInstitution) {
		String[] objectData = pegarDadosDoObjeto(personInstitution);
		if(objectData[0] == null)
			objectData[0] = "id";
		for(Integer i = 0; i < objectData.length; i++) {
			if((objectData[i] == null || objectData[i].equals("")) && i > 0)
				objectData[i] = "null";
		}
		String values = String.join(",", objectData);
		return salvarObj(values, fileName);
	}
	
	public static String[] pegarDadosDoObjeto(Pessoa_Instituicao personInstitution) {
		String[] objectData = {
			personInstitution.getId(), 
			personInstitution.getPersonId(),
			personInstitution.getInstitutionId(),
		};
		return objectData;
	}
	
	public static Pessoa_Instituicao criarObjetoComResultado(String[] data, Boolean fetchRelations) {
		person_institution.setId(data[0]);
		person_institution.setPersonId(data[1]);
		if(person_institution.getPersonId() != "null" && fetchRelations == true) {
			Pessoa personObj = new Pessoa();
			Pessoa result = personObj.buscarPessoaPorId(Integer.parseInt(person_institution.getPersonId()), false);
			person_institution.setPerson(result);
		}
		person_institution.setInstitutionId(data[2]);
		if(person_institution.getInstitutionId() != "null" && fetchRelations == true) {
			Estabelecimento institutionObj = new Estabelecimento();
			Estabelecimento result = institutionObj.buscarEstabelecimentoPorId(Integer.parseInt(person_institution.getInstitutionId()), false);
			person_institution.setInstitution(result);
		}
		return person_institution;
	}
	
	public static Boolean sincronizarPessoaInstituicao(String pessoaId, String instituicaoId) {
		Pessoa_Instituicao pessoaInstituicaoObj = new Pessoa_Instituicao();
		ArrayList<String[]> dados = pessoaInstituicaoObj.buscar("institutionId", instituicaoId, true);
		dados = pessoaInstituicaoObj.filtrarDados(dados, 2, pessoaId, true);
		if(dados.size() == 0) {
			pessoaInstituicaoObj.setPersonId(pessoaId);
			pessoaInstituicaoObj.setInstitutionId(instituicaoId);
			pessoaInstituicaoObj.salvar(pessoaInstituicaoObj);
			return true;
		}
		return true;
	}
}

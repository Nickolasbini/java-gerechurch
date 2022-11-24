package classes;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import controllers.EstabelecimentoController;
import controllers.PessoaController;
import helpers.FunctionsClass;

public class Pessoa extends ModeloBase{
	private String[] classMap = {
		"id",
		"name",
		"phone",
		"email",
		"password",
		"institutionId",
		"hasPrivileges",
		"masterAdmin",
		"profession",
		"salary",
		"status",
		"lgpd"
	};
	
	private static String fileName = "person";
	
	public static final String DIZIMISTA   = "1";
	public static final String FUNCIONARIO = "2";
	public static final String MASTER_ADMIN = "3";
	
	private static Pessoa person;
	
	private String  id;
	private String  name;
	private String  phone;
	private String  email;
	private String  password;
	private String  institutionId;
	private Estabelecimento institution;
	private Boolean hasPrivilegies;
	private Boolean masterAdmin;
	private String  profession;
	private String  salary;
	private String  status;
	private Boolean  lgpd;
	
	public Pessoa() {
		this.setNomeArquivoObj(fileName);
		this.setAtributos(classMap);
		this.setHasPrivilegies(false);
		this.setMasterAdmin(false);
		this.person = this;
		this.setObjetoAtual(person);
	}

	// Getters and Setters
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

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getHasPrivilegies() {
		return hasPrivilegies;
	}
	public void setHasPrivilegies(Boolean hasPrivilegies) {
		this.hasPrivilegies = hasPrivilegies;
	}

	public Boolean getMasterAdmin() {
		return masterAdmin;
	}
	public void setMasterAdmin(Boolean masterAdmin) {
		this.masterAdmin = masterAdmin;
	}

	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Boolean getLgpd() {
		return lgpd;
	}
	public void setLgpd(Boolean lgpd) {
		this.lgpd = lgpd;
	}

	//	Methods
	public static Boolean salvar(Pessoa pessoa) {
		String[] objectData = pegarDadosDoObjeto(pessoa);
		if(objectData[0] == null)
			objectData[0] = "id";
		for(Integer i = 0; i < objectData.length; i++) {
			if((objectData[i] == null || objectData[i].equals("")) && i > 0)
				objectData[i] = "null";
		}
		String values = String.join(",", objectData);
		return salvarObj(values, fileName);
	}
	
	public static String[] pegarDadosDoObjeto(Pessoa pessoa) {
		String[] objectData = {
			pessoa.getId(), 
			pessoa.getName(),
			pessoa.getPhone(),
			pessoa.getEmail(),
			pessoa.getPassword(),
			pessoa.getInstitutionId(),
			FunctionsClass.parseBooleanToString(pessoa.getHasPrivilegies()),
			FunctionsClass.parseBooleanToString(pessoa.getMasterAdmin()),
			pessoa.getProfession(),
			pessoa.getSalary(),
			pessoa.getStatus(),
			FunctionsClass.parseBooleanToString(pessoa.getLgpd())
		};
		return objectData;
	}
	
	public static Pessoa criarObjetoComResultado(String[] data, Boolean fetchRelations) {
		person.setId(data[0]);
		person.setName(data[1]);
		person.setPhone(data[2]);
		person.setEmail(data[3]);
		person.setPassword(data[4]);
		person.setInstitutionId(data[5]);
		if(person.getInstitutionId() != "null" && fetchRelations == true) {
			Estabelecimento institutionObj = new Estabelecimento();
			Estabelecimento result = institutionObj.buscarEstabelecimentoPorId(Integer.parseInt(person.getInstitutionId()), false);
			person.setInstitution(result);
		}
		person.setHasPrivilegies(FunctionsClass.parseStringToBoolean(data[6]));
		person.setMasterAdmin(FunctionsClass.parseStringToBoolean(data[7]));
		person.setProfession(data[8]);
		person.setSalary(data[9]);
		person.setStatus(data[10]);
		person.setLgpd(FunctionsClass.parseStringToBoolean(data[11]));
		return person;
	}
	
	public static Pessoa buscarPessoaPorId(Integer id, Boolean fetchRelations) {
		String[] objectData;
		String result = buscarDadosArquivoPorId("person", Integer.toString(id));
		if(result == null)
			return null;
		objectData = result.split(",");
		return criarObjetoComResultado(objectData, fetchRelations);
	}
	
	public static Boolean funcionario(Pessoa pessoa) {
		if(pessoa.getStatus().equals(FUNCIONARIO)) {
			return true;
		}
		return false;
	}
	
	public static Boolean dizimista(Pessoa pessoa) {
		if(pessoa.getStatus().equals(DIZIMISTA)) {
			return true;
		}
		return false;
	}
	
	public static Boolean administradorMestre(Pessoa pessoa) {
		if(pessoa.getMasterAdmin() == true) {
			return true;
		}
		return false;
	}
	
	public static Boolean concederPrivilegioAdministrativo(Pessoa usuarioLogado, Pessoa usuarioAlvo) {
		if(usuarioLogado.administradorMestre(usuarioLogado) == false)
			return false;
		usuarioAlvo.setHasPrivilegies(true);
		return true;
	}
	
	public static String[] gerarCabecalhoTabela(String statusIdentificador) {
		if(statusIdentificador.equals(DIZIMISTA)) {
			String[] cabecalho = {"Identificador", "Nome", "Telefone", "Email", "Profissão","Salário"};
			return cabecalho;
		}else {
			String[] cabecalho = {"Identificador", "Nome", "Telefone", "Email", "Privilégios"};
			return cabecalho;
		}
	}
	
	public static Integer[] posicoesCabecalho(String statusIdentificador) {
		if(statusIdentificador.equals(DIZIMISTA)) {
			Integer[] posicoes = {0,1,2,3,8,9};
			return posicoes;
		}else {
			Integer[] posicoes = {0,1,2,3,6};
			return posicoes;
		}
	}
}
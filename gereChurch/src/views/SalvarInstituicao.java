package views;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Estabelecimento;
import classes.Pessoa;
import controllers.EstabelecimentoController;
import controllers.FuncionarioController;
import controllers.PessoaController;
import helpers.FunctionsClass;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class SalvarInstituicao extends JFrame {

	private static JPanel contentPane;
	
	static Pessoa loggedUser;
	static Estabelecimento objetoAlteravel;
	static JFrame currentFrame;
	static JFrame parentFrame;
	static EstabelecimentoController estabelecimentoController;
	static FuncionarioController funcionarioController;

	private static JTextField inputNome;
	private static JTextField inputRua;
	private static JTextField inputNumero;
	private static JTextField inptBairro;
	private static JTextField inputCidade;
	private static JTextField inputPais;
	private static JComboBox comboRepresentante;
	private static JLabel lblTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalvarInstituicao frame = new SalvarInstituicao(loggedUser);
					frame.setVisible(true);
					currentFrame = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalvarInstituicao(Pessoa obj) {
		if(obj == null) {
			Login loginFrame = new Login();
			loginFrame.setVisible(true);
			return;
		}
		loggedUser = obj;
		estabelecimentoController = new EstabelecimentoController(null);
		funcionarioController = new FuncionarioController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.getParent().getParent().getParent().setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 11, 89, 23);
		contentPane.add(btnVoltar);
		
		lblTitulo = new JLabel("");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(10, 45, 544, 23);
		contentPane.add(lblTitulo);
		
		Button separator_1 = new Button("");
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setEnabled(false);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(-110, 70, 711, 1);
		contentPane.add(separator_1);
		
		Button separator_1_1 = new Button("");
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setEnabled(false);
		separator_1_1.setBackground(Color.WHITE);
		separator_1_1.setBounds(277, 77, 1, 300);
		contentPane.add(separator_1_1);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 79, 89, 14);
		contentPane.add(lblNome);
		
		inputNome = new JTextField();
		inputNome.setBounds(10, 104, 261, 20);
		contentPane.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua do endereço");
		lblRua.setBounds(10, 148, 143, 14);
		contentPane.add(lblRua);
		
		inputRua = new JTextField();
		inputRua.setColumns(10);
		inputRua.setBounds(10, 173, 261, 20);
		contentPane.add(inputRua);
		
		JLabel lblNumero = new JLabel("Número do endereço");
		lblNumero.setBounds(10, 214, 143, 14);
		contentPane.add(lblNumero);
		
		inputNumero = new JTextField();
		inputNumero.setColumns(10);
		inputNumero.setBounds(10, 239, 261, 20);
		contentPane.add(inputNumero);
		
		JLabel lblNome_1_2 = new JLabel("Bairro do endereço");
		lblNome_1_2.setBounds(10, 287, 143, 14);
		contentPane.add(lblNome_1_2);
		
		inptBairro = new JTextField();
		inptBairro.setColumns(10);
		inptBairro.setBounds(10, 312, 261, 20);
		contentPane.add(inptBairro);
		
		JLabel lblNome_1_3 = new JLabel("Cidade do endereço");
		lblNome_1_3.setBounds(293, 79, 161, 14);
		contentPane.add(lblNome_1_3);
		
		inputCidade = new JTextField();
		inputCidade.setColumns(10);
		inputCidade.setBounds(293, 104, 261, 20);
		contentPane.add(inputCidade);
		
		JLabel lblNome_1_4 = new JLabel("Pais do endereço");
		lblNome_1_4.setBounds(293, 148, 161, 14);
		contentPane.add(lblNome_1_4);
		
		inputPais = new JTextField();
		inputPais.setColumns(10);
		inputPais.setBounds(293, 173, 261, 20);
		contentPane.add(inputPais);
		
		JLabel lblRepresentate = new JLabel("Representante (dono instituição)");
		lblRepresentate.setBounds(293, 214, 187, 14);
		contentPane.add(lblRepresentate);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(openModal("Você tem certeza que deseja salvar as alterações?", "Aviso Importante").equals(true)) {
					atualizarObjetoComDados();
					EstabelecimentoController estabelecimentoController = new EstabelecimentoController(null);
					Boolean resultado = estabelecimentoController.salvar(objetoAlteravel);
					if(resultado.equals(true)) {
						displayMessage("Salvo com sucesso");
						contentPane.getParent().getParent().getParent().setVisible(false);
						parentFrame.dispose();
						Instituicoes instituicoesView = new Instituicoes(loggedUser);
						instituicoesView.listarInstituicoes();
						instituicoesView.setVisible(true);
					}else {
						displayMessage("Um erro ocorreu, tente novamente");
					}
				}
			}
		});
		btnSalvar.setBounds(199, 398, 153, 31);
		contentPane.add(btnSalvar);
		
		comboFuncionarios();
	}

	static String idDoFuncionarioSelecionado = null;
	static ArrayList<Pessoa> funcionariosComboObjetos;
	static Pessoa funcionarioSelecionado = null;
	public static void comboFuncionarios() {
		funcionariosComboObjetos = funcionarioController.comboFuncionariosComoObjetos(loggedUser.getInstitutionId());
		String[] resultado 	     = funcionarioController.comboFuncionarios(loggedUser.getInstitutionId());
		comboRepresentante = new JComboBox(resultado);
		comboRepresentante.setBounds(293, 239, 261, 20);
		comboRepresentante.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Integer posicaoEmResultados = comboRepresentante.getSelectedIndex();
		    	if(funcionariosComboObjetos.size() == 0)
		    		return;
		    	if(posicaoEmResultados > -1)
		    		funcionarioSelecionado = funcionariosComboObjetos.get(posicaoEmResultados);
		    }
		});
		contentPane.add(comboRepresentante);
		Integer posicaoEmResultados = comboRepresentante.getSelectedIndex();
		if(funcionariosComboObjetos.size() == 0)
    		return;
		if(posicaoEmResultados > -1)
			funcionarioSelecionado = funcionariosComboObjetos.get(posicaoEmResultados);
	}
	
	public static void setObjetoParaAlteracao(String id) {
		if(id == null) {
			objetoAlteravel = new Estabelecimento();
		}else {
			objetoAlteravel = Estabelecimento.buscarEstabelecimentoPorId(FunctionsClass.parseToInteger(id), false);
		}
		alimentarTela();
	}
	
	public static void alimentarTela() {
		if(objetoAlteravel.getId() == null) {
			lblTitulo.setText("Criação de Estabelecimento");
			return;
		}else {
			lblTitulo.setText("Edição do Estabelecimento "+objetoAlteravel.getName());
		}
		inputNome.setText(objetoAlteravel.getName());
		inputRua.setText(objetoAlteravel.getStreet());
		inputNumero.setText(objetoAlteravel.getNumber());
		inptBairro.setText(objetoAlteravel.getNeighborhood());
		inputCidade.setText(objetoAlteravel.getCity());
		inputPais.setText(objetoAlteravel.getCountry());
		Integer position = 0;
		for(Pessoa funcionario : funcionariosComboObjetos) {
			if(funcionario.getId().equals(objetoAlteravel.getRepresentativeId()))
				break;
			position++;
		}
		comboRepresentante.setSelectedIndex(position);
		Integer posicaoEmResultados = comboRepresentante.getSelectedIndex();
    	funcionarioSelecionado = funcionariosComboObjetos.get(posicaoEmResultados);
	}
	
	public static void atualizarObjetoComDados() {
		objetoAlteravel.setName(inputNome.getText());
		objetoAlteravel.setStreet(inputRua.getText());
		objetoAlteravel.setNumber(inputNumero.getText());
		objetoAlteravel.setNeighborhood(inptBairro.getText());
		objetoAlteravel.setCity(inputCidade.getText());
		objetoAlteravel.setCountry(inputPais.getText());
		objetoAlteravel.setRepresentativeId(funcionarioSelecionado.getId());
	}
	
	public Boolean openModal(String message, String title) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(contentPane, message, title, dialogButton);
		if(dialogResult == 0)
			return true;
		return false;
	}
	
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(contentPane, message);
	}
}

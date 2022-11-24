package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Pessoa;
import controllers.PessoaController;
import helpers.FunctionsClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalvarPessoaModal extends JFrame {

	private JPanel contentPane;
	private static JLabel lblTitulo;
	private static JTextField inputName;
	private static JTextField inputPhone;
	private static JTextField inputEmail;
	private static JTextField inputSalary;
	private static JTextField inputProfession;
	private static JTextField inputSenha;
	private static JRadioButton inputPrivilegies;
	private static JLabel lblNome;
	private static JLabel lblTelefone;
	private static JLabel lblEmail;
	private static JLabel lblProfissao;
	private static JLabel lblSalario;
	private static JLabel lblSenha;
	
	static Pessoa loggedUser;
	static Pessoa objetoAlteravel;
	static JFrame currentFrame;
	static JFrame parentFrame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalvarPessoaModal frame = new SalvarPessoaModal(loggedUser);
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
	public SalvarPessoaModal(Pessoa obj) {
		if(obj == null) {
			Login loginFrame = new Login();
			loginFrame.setVisible(true);
			return;
		}
		loggedUser = obj;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Edição de ...");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 29, 544, 42);
		contentPane.add(lblTitulo);
		
		Button separator_1 = new Button("");
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setEnabled(false);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(-110, 70, 711, 1);
		contentPane.add(separator_1);
		
		JLabel lblId = new JLabel("");
		lblId.setBounds(0, 426, 46, 14);
		contentPane.add(lblId);
		
		Button separator_1_1 = new Button("");
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setEnabled(false);
		separator_1_1.setBackground(Color.WHITE);
		separator_1_1.setBounds(277, 77, 1, 300);
		contentPane.add(separator_1_1);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 95, 261, 14);
		contentPane.add(lblNome);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 190, 261, 14);
		contentPane.add(lblTelefone);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 295, 261, 14);
		contentPane.add(lblEmail);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(openModal("Você tem certeza que deseja salvar as alterações?", "Aviso Importante").equals(true)) {
					atualizarObjetoComDados();
					PessoaController pessoaController = new PessoaController();
					Boolean resultado = pessoaController.salvar(objetoAlteravel);
					if(resultado.equals(true)) {
						displayMessage("Salvo com sucesso");
						if(parentFrame != null)
							parentFrame.dispose();
						if(objetoAlteravel.getStatus().equals(objetoAlteravel.FUNCIONARIO)) {
							contentPane.getParent().getParent().getParent().setVisible(false);
							Funcionarios funcionariosView = new Funcionarios(loggedUser);
							funcionariosView.listarFuncionarios();
							funcionariosView.setVisible(true);
						}else {
							contentPane.getParent().getParent().getParent().setVisible(false);
							Dizimistas dizimistasView = new Dizimistas(loggedUser);
							dizimistasView.listarDizimistas();
							dizimistasView.setVisible(true);
						}
					}else {
						displayMessage("Um erro ocorreu, tente novamente");
					}
				}
			}
		});
		btnSalvar.setBounds(199, 398, 153, 31);
		contentPane.add(btnSalvar);
		
		inputName = new JTextField();
		inputName.setBounds(10, 120, 261, 20);
		contentPane.add(inputName);
		inputName.setColumns(10);
		
		inputPhone = new JTextField();
		inputPhone.setColumns(10);
		inputPhone.setBounds(10, 215, 261, 20);
		contentPane.add(inputPhone);
		
		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(10, 322, 261, 20);
		contentPane.add(inputEmail);
		
		lblSalario = new JLabel("Salário");
		lblSalario.setBounds(293, 95, 46, 14);
		contentPane.add(lblSalario);
		
		inputSalary = new JTextField();
		inputSalary.setColumns(10);
		inputSalary.setBounds(293, 120, 261, 20);
		contentPane.add(inputSalary);
		
		inputPrivilegies = new JRadioButton("Tem privilégios");
		inputPrivilegies.setHorizontalAlignment(SwingConstants.CENTER);
		inputPrivilegies.setBounds(294, 119, 260, 22);
		contentPane.add(inputPrivilegies);
		
		lblProfissao = new JLabel("Profissão");
		lblProfissao.setBounds(293, 190, 261, 14);
		contentPane.add(lblProfissao);
		
		inputProfession = new JTextField();
		inputProfession.setColumns(10);
		inputProfession.setBounds(293, 215, 261, 20);
		contentPane.add(inputProfession);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(objetoAlteravel.getStatus().equals(objetoAlteravel.FUNCIONARIO)) {
					contentPane.getParent().getParent().getParent().setVisible(false);
//					((Funcionarios) parentFrame).listarFuncionarios();
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
//					((Dizimistas) parentFrame).listarDizimistas();
				}
			}
		});
		btnVoltar.setBounds(10, 6, 89, 23);
		contentPane.add(btnVoltar);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(293, 297, 261, 14);
		contentPane.add(lblSenha);
		
		inputSenha = new JTextField();
		inputSenha.setColumns(10);
		inputSenha.setBounds(293, 322, 261, 20);
		contentPane.add(inputSenha);
	}
	
	public static void setObjetoParaAlteracao(String id, Pessoa objetoParaUsar) {
		if(id == null) {
			objetoAlteravel = objetoParaUsar;
			inputSenha.setText("default123");
			inputSenha.setVisible(true);
			lblSenha.setVisible(true);
		}else {
			Pessoa objetoParaAlteracao = new Pessoa();
			objetoAlteravel = objetoParaAlteracao.buscarPessoaPorId(FunctionsClass.parseToInteger(id), false);
			if(objetoAlteravel.getPassword().equals("null")) {
				inputSenha.setText("default123");
			}else {
				inputSenha.setText(objetoAlteravel.getPassword());
			}
			inputSenha.setVisible(true);
			lblSenha.setVisible(true);
		}
		alimentarTela();
	}
	
	public static void alimentarTela() {
		if(objetoAlteravel.getStatus().equals(objetoAlteravel.FUNCIONARIO)) {
			if(objetoAlteravel.getName() == null) {
				lblTitulo.setText("Criação de Funcionário(a) ");
			}else {
				lblTitulo.setText("Edição do(a) Funcionário(a) " + objetoAlteravel.getName());
			}
			inputSalary.setText("");
			inputSalary.setVisible(false);
			lblSalario.setVisible(false);
			inputProfession.setText("");
			inputProfession.setVisible(false);
			lblProfissao.setVisible(false);
			if(objetoAlteravel.getHasPrivilegies().equals(true)) {
				inputPrivilegies.doClick();
			}
			inputPrivilegies.setVisible(true);
		}else {
			if(objetoAlteravel.getName() == null) {
				lblTitulo.setText("Criação de Dizimista ");
			}else {
				lblTitulo.setText("Edição do(a) Dizimista " + objetoAlteravel.getName());
			}
			if(objetoAlteravel.getSalary() == null || objetoAlteravel.getSalary().equals("null")) {
				inputSalary.setText("R$00,00");
			}else {
				inputSalary.setText(FunctionsClass.parseMonetary(objetoAlteravel.getSalary()));
			}
			inputSalary.setVisible(true);
			lblSalario.setVisible(true);
			if(objetoAlteravel.getProfession() == null || objetoAlteravel.getProfession().equals("null")) {
				inputProfession.setText("");
			}else {
				inputProfession.setText(objetoAlteravel.getProfession());
			}
			inputProfession.setVisible(true);
			lblProfissao.setVisible(true);
			inputPrivilegies.setVisible(false);
		}
		inputName.setText(objetoAlteravel.getName());
		inputPhone.setText(objetoAlteravel.getPhone());
		inputEmail.setText(objetoAlteravel.getEmail());
	}
	
	public static void atualizarObjetoComDados() {
		objetoAlteravel.setName(inputName.getText());
		objetoAlteravel.setPhone(inputPhone.getText());
		objetoAlteravel.setEmail(inputEmail.getText());
		objetoAlteravel.setSalary(FunctionsClass.stripMonetary(inputSalary.getText()));
		if(objetoAlteravel.getSalary().equals("00.00")) {
			objetoAlteravel.setSalary(null);
		}
		objetoAlteravel.setProfession(inputProfession.getText());
		objetoAlteravel.setHasPrivilegies(inputPrivilegies.isSelected());
		objetoAlteravel.setPassword(inputSenha.getText());
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

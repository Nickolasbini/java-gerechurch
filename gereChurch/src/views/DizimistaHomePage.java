package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Estabelecimento;
import classes.Pessoa;
import controllers.DizimistaController;
import controllers.PessoaController;
import controllers.RecebimentoController;
import helpers.FunctionsClass;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class DizimistaHomePage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	static JFrame currentFrame;
	static Pessoa loggedUser;
	private JButton btnHome;
	private JButton btnHistorico;
	private HomePage frame;
	
	static DizimistaController dizimistaController;
	static RecebimentoController recebimentoObj;
    String [] colunas;
    Object [][] dados;
    private JButton logoIcon;
    private JLabel lblMinhaInstituio;
    private JTextField inputNome;
    private JTextField inputEmail;
    private JTextField inputProfissao;
    private JTextField inputSalario;
    private JPasswordField inputPassword;
    private JRadioButton radioLgpd;
    private JLabel enderecoInstituicao;
    private JLabel nomeInstituicao;
    private JLabel representanteInstituicao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DizimistaHomePage frame = new DizimistaHomePage(loggedUser);
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
	public DizimistaHomePage(Pessoa obj) {
		if(obj == null) {
//			Login loginFrame = new Login();
//			loginFrame.setVisible(true);
//			return;
			PessoaController controller = new PessoaController();
			obj = controller.autenticate("marlene.da.luz@hotmail.com", "default123");
		}
		loggedUser = obj;
		currentFrame = this;
		closeUnusedFrames();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 899, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// menu START		
		JPanel menu = new JPanel();
		menu.setBackground(Color.GREEN);
		menu.setBounds(0, 0, 156, 478);
		contentPane.add(menu);
		menu.setLayout(null);
		
		JLabel labelMyName = new JLabel("Meu Nome");
		labelMyName.setHorizontalAlignment(SwingConstants.CENTER);
		labelMyName.setBounds(10, 11, 136, 14);
		menu.add(labelMyName);
		labelMyName.setText(loggedUser.getName());
		
		Button separator = new Button("");
		separator.setForeground(Color.WHITE);
		separator.setEnabled(false);
		separator.setBackground(Color.WHITE);
		separator.setBounds(0, 37, 170, 1);
		menu.add(separator);
		
		btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				DizimistaHomePage frame = new DizimistaHomePage(loggedUser);
				frame.setVisible(true);
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnHome.setBounds(37, 44, 80, 80);
		btnHome.setIcon(FunctionsClass.createIconFromImg("home.png", 80, 80));
		btnHome.setDisabledIcon(FunctionsClass.createIconFromImg("home.png", 80, 80));
		menu.add(btnHome);
		
		btnHistorico = new JButton("Histórico");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null)
					currentFrame.setVisible(false);
				HistoricoDizimista frame = new HistoricoDizimista(loggedUser);
				frame.setVisible(true);
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnHistorico.setBounds(10, 135, 136, 29);
		menu.add(btnHistorico);
		
		Button separator_1 = new Button("");
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setEnabled(false);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(162, 38, 711, 1);
		contentPane.add(separator_1);
		
		JButton btnLogout = new JButton("Sair");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loggedUser = null;
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				Login loginFrame = new Login();
				loginFrame.setVisible(true);
				return;
			}
		});
		btnLogout.setBounds(784, 11, 89, 23);
		contentPane.add(btnLogout);
		
		logoIcon = new JButton("");
		logoIcon.setEnabled(false);
		logoIcon.setBounds(20, 352, 115, 115);
		logoIcon.setIcon(FunctionsClass.createIconFromImg("logo.png", 115, 115));
		logoIcon.setDisabledIcon(FunctionsClass.createIconFromImg("logo.png", 115, 115));
		menu.add(logoIcon);
		// menu END
		
		Button separator_1_1 = new Button("");
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setEnabled(false);
		separator_1_1.setBackground(Color.LIGHT_GRAY);
		separator_1_1.setBounds(162, 207, 711, 1);
		contentPane.add(separator_1_1);
		
		JLabel lblMeusDados = new JLabel("Meus dados");
		lblMeusDados.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMeusDados.setBounds(166, 228, 170, 19);
		contentPane.add(lblMeusDados);
		
		lblMinhaInstituio = new JLabel("Minha Instituição");
		lblMinhaInstituio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMinhaInstituio.setBounds(166, 45, 170, 19);
		contentPane.add(lblMinhaInstituio);

		btnHome.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(166, 258, 46, 14);
		contentPane.add(lblNewLabel);
		
		inputNome = new JTextField();
		inputNome.setBounds(166, 283, 264, 23);
		contentPane.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(166, 317, 46, 14);
		contentPane.add(lblEmail);
		
		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(166, 342, 264, 23);
		contentPane.add(inputEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(166, 376, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblProfisso = new JLabel("Profissão");
		lblProfisso.setBounds(594, 258, 109, 14);
		contentPane.add(lblProfisso);
		
		inputProfissao = new JTextField();
		inputProfissao.setColumns(10);
		inputProfissao.setBounds(594, 283, 264, 23);
		contentPane.add(inputProfissao);
		
		JLabel lblSalrio = new JLabel("Salário");
		lblSalrio.setBounds(594, 317, 46, 14);
		contentPane.add(lblSalrio);
		
		inputSalario = new JTextField();
		inputSalario.setColumns(10);
		inputSalario.setBounds(594, 342, 264, 23);
		contentPane.add(inputSalario);
		
		JLabel lblNewLabel_4_1 = new JLabel("LGPD");
		lblNewLabel_4_1.setBounds(594, 376, 46, 14);
		contentPane.add(lblNewLabel_4_1);
		
		radioLgpd = new JRadioButton("Aceito");
		radioLgpd.setBounds(594, 401, 109, 23);
		contentPane.add(radioLgpd);
		
		inputPassword = new JPasswordField();
		inputPassword.setBounds(166, 401, 264, 23);
		contentPane.add(inputPassword);
		
		JButton btnAtualizarDados = new JButton("Atualizar Dados");
		btnAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(openModal("Você tem certeza que deseja salvar as alterações?", "Aviso Importante").equals(true)) {
					loggedUser.setName(inputNome.getText());
					loggedUser.setEmail(inputEmail.getText());
					loggedUser.setPassword(inputPassword.getText());
					loggedUser.setProfession(inputProfissao.getText());
					loggedUser.setSalary(inputSalario.getText());
					if(radioLgpd.isSelected()) {
						loggedUser.setLgpd(true);
					}else {
						loggedUser.setLgpd(false);
					}
					PessoaController pessoaController = new PessoaController();
					Boolean resultado = pessoaController.salvar(loggedUser);
					if(resultado.equals(true)) {
						displayMessage("Salvo com sucesso");
					}else {
						displayMessage("Um erro ocorreu, tente novamente");
					}
				}
			}
		});
		btnAtualizarDados.setBounds(448, 435, 130, 32);
		contentPane.add(btnAtualizarDados);
		
		JLabel lblNomeDaInstituio = new JLabel("Instituição");
		lblNomeDaInstituio.setBounds(166, 75, 130, 14);
		contentPane.add(lblNomeDaInstituio);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(166, 145, 89, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblRepresentatnte = new JLabel("Representatante");
		lblRepresentatnte.setBounds(594, 75, 109, 14);
		contentPane.add(lblRepresentatnte);
		
		enderecoInstituicao = new JLabel("");
		enderecoInstituicao.setForeground(new Color(46, 139, 87));
		enderecoInstituicao.setBounds(166, 170, 692, 19);
		contentPane.add(enderecoInstituicao);
		
		nomeInstituicao = new JLabel((String) null);
		nomeInstituicao.setForeground(new Color(46, 139, 87));
		nomeInstituicao.setBounds(166, 100, 342, 19);
		contentPane.add(nomeInstituicao);
		
		representanteInstituicao = new JLabel((String) null);
		representanteInstituicao.setForeground(new Color(46, 139, 87));
		representanteInstituicao.setBounds(594, 100, 266, 19);
		contentPane.add(representanteInstituicao);
		preencherMeusDados();
		preencherInstituicao();
	}
	
	public void preencherInstituicao() {
		Estabelecimento instituicaoObj = new Estabelecimento();
		instituicaoObj = instituicaoObj.buscarEstabelecimentoPorId(FunctionsClass.parseToInteger(loggedUser.getInstitutionId()), true);
		nomeInstituicao.setText(instituicaoObj.getName());
		enderecoInstituicao.setText(instituicaoObj.getFullAddress(instituicaoObj));
		representanteInstituicao.setText(instituicaoObj.getRepresentative().getName());
	}
	
	public void preencherMeusDados() {
		this.inputNome.setText(loggedUser.getName());
		this.inputEmail.setText(loggedUser.getEmail());
		this.inputPassword.setText(loggedUser.getPassword());
		this.inputProfissao.setText(loggedUser.getProfession());
		this.inputSalario.setText(loggedUser.getSalary());
		this.radioLgpd.setSelected(false);
		if(loggedUser.getLgpd()) {
			this.radioLgpd.setSelected(true);
		}
	}
	
	public Boolean openModal(String message, String title) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(currentFrame, message, title, dialogButton);
		if(dialogResult == 0)
			return true;
		return false;
	}
	
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(currentFrame, message);
	}
	
	public void closeUnusedFrames() {
		Integer lastPosition = Frame.getFrames().length - 1;
		for(Integer a = 0; a < Frame.getFrames().length; a++) {
			if(a != lastPosition) {
				Frame.getFrames()[a].setVisible(false);
				Frame.getFrames()[a].dispose();
			}
		}
	}
}

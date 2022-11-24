package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	static JFrame currentFrame;
	static Pessoa loggedUser;
	private JButton btnHome;
	private JButton btnRecebimentos;
	private JButton btnHistorico;
	private JButton btnDizimistas;
	private JButton btnFuncionarios;
	private JButton btnEditarInstituicao;
	private HomePage frame;
	private JTextField txtDate;
	private JLabel lblTotalRecebimentosMes;
	
	private JButton btnPrimeiroLugar;
	private JButton btnSegundoLugar;
	private JButton btnTerceiroLugar;
	private JLabel lblNomePrimeiroLugar;
	private JLabel lblNomeSegundoLugar;
	private JLabel lblNomeTerceiroLugar;
	private JLabel lblValorPrimeiroLugar;
	private JLabel lblValorSegundoLugar;
	private JLabel lblValorTerceiroLugar;
	
	static DizimistaController dizimistaController;
	static RecebimentoController recebimentoObj;
    String [] colunas;
    Object [][] dados;
    private JButton logoIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(loggedUser);
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
	public HomePage(Pessoa obj) {
		if(obj == null) {
//			Login loginFrame = new Login();
//			loginFrame.setVisible(true);
//			return;
			PessoaController controller = new PessoaController();
			obj = controller.autenticate("vic@hotmail.com", "12345");
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
		
		btnHome = new JButton("Home Page");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				HomePage frame = new HomePage(loggedUser);
				frame.setVisible(true);
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnHome.setBounds(10, 44, 136, 29);
		menu.add(btnHome);
		
		btnRecebimentos = new JButton("Recebimentos");
		btnRecebimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				Recebimentos frame = new Recebimentos(loggedUser);
				frame.setVisible(true);
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnRecebimentos.setBounds(10, 80, 136, 29);
		menu.add(btnRecebimentos);
		
		btnHistorico = new JButton("Histórico de Recebimentos");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(currentFrame != null)
//					currentFrame.setVisible(false);
//				Funcionarios frame = new Funcionarios();
//				frame.setVisible(true);
//				frame.listarFuncionarios();
//				frame.currentFrame = frame;
//				currentFrame = frame;
			}
		});
		btnHistorico.setBounds(10, 80, 136, 29);
		menu.add(btnHistorico);
		
		btnDizimistas = new JButton("Dizimistas");
		btnDizimistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				Dizimistas frame = new Dizimistas(loggedUser);
				frame.setVisible(true);
				frame.listarDizimistas();
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnDizimistas.setBounds(10, 120, 136, 29);
		menu.add(btnDizimistas);
		
		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				Funcionarios frame = new Funcionarios(loggedUser);
				frame.setVisible(true);
				frame.listarFuncionarios();
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnFuncionarios.setBounds(10, 160, 136, 29);
		menu.add(btnFuncionarios);
		
		btnEditarInstituicao = new JButton("Instituições");
		btnEditarInstituicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				Instituicoes frame = new Instituicoes(loggedUser);
				frame.setVisible(true);
				frame.listarInstituicoes();
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnEditarInstituicao.setBounds(10, 200, 136, 29);
		menu.add(btnEditarInstituicao);
		
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
		
		txtDate = new JTextField();
		txtDate.setBounds(696, 51, 144, 23);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnBuscarDadosPorData = new JButton("");
		btnBuscarDadosPorData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarTotalRecebimentos();
				listarTopTresDizimistas();
			}
		});
		btnBuscarDadosPorData.setBounds(850, 51, 23, 23);
		contentPane.add(btnBuscarDadosPorData);
				
		JLabel lblNewLabel = new JLabel("Total de Recebimentos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(168, 52, 170, 19);
		contentPane.add(lblNewLabel);
		
		lblTotalRecebimentosMes = new JLabel("R$00,00");
		lblTotalRecebimentosMes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblTotalRecebimentosMes.setForeground(Color.GREEN);
		lblTotalRecebimentosMes.setBounds(166, 135, 156, 23);
		contentPane.add(lblTotalRecebimentosMes);
		
		JLabel lblTopDizimistas = new JLabel("Top 3 Dizimistas");
		lblTopDizimistas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTopDizimistas.setBounds(168, 228, 170, 19);
		contentPane.add(lblTopDizimistas);
		
	    
		btnPrimeiroLugar = new JButton("");
		btnPrimeiroLugar.setIcon(FunctionsClass.createIconFromImg("vencedora.png", 80, 80));
		btnPrimeiroLugar.setDisabledIcon(FunctionsClass.createIconFromImg("vencedora.png", 80, 80));
		btnPrimeiroLugar.setEnabled(false);
		btnPrimeiroLugar.setBounds(477, 243, 80, 80);
		contentPane.add(btnPrimeiroLugar);
		
		btnSegundoLugar = new JButton("");
		btnSegundoLugar.setIcon(FunctionsClass.createIconFromImg("segundo-premio.png", 80, 80));
		btnSegundoLugar.setDisabledIcon(FunctionsClass.createIconFromImg("segundo-premio.png", 80, 80));
		btnSegundoLugar.setEnabled(false);
		btnSegundoLugar.setBounds(294, 340, 80, 80);
		contentPane.add(btnSegundoLugar);
		
		btnTerceiroLugar = new JButton("");
		btnTerceiroLugar.setIcon(FunctionsClass.createIconFromImg("terceiro-premio.png", 80, 80));
		btnTerceiroLugar.setDisabledIcon(FunctionsClass.createIconFromImg("terceiro-premio.png", 80, 80));
		btnTerceiroLugar.setEnabled(false);
		btnTerceiroLugar.setBounds(700, 340, 80, 80);
		contentPane.add(btnTerceiroLugar);
		
		lblNomePrimeiroLugar = new JLabel("Primeiro lugar");
		lblNomePrimeiroLugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomePrimeiroLugar.setBounds(466, 334, 98, 14);
		contentPane.add(lblNomePrimeiroLugar);
		
		lblNomeSegundoLugar = new JLabel("Segundo Lugar");
		lblNomeSegundoLugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeSegundoLugar.setBounds(283, 431, 98, 14);
		contentPane.add(lblNomeSegundoLugar);
		
		lblNomeTerceiroLugar = new JLabel("Terceiro Lugar");
		lblNomeTerceiroLugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeTerceiroLugar.setBounds(693, 431, 98, 14);
		contentPane.add(lblNomeTerceiroLugar);
		
		lblValorPrimeiroLugar = new JLabel("R$00,00");
		lblValorPrimeiroLugar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorPrimeiroLugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorPrimeiroLugar.setBounds(466, 354, 98, 14);
		contentPane.add(lblValorPrimeiroLugar);
		
		lblValorSegundoLugar = new JLabel("R$00,00");
		lblValorSegundoLugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorSegundoLugar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorSegundoLugar.setBounds(283, 456, 98, 14);
		contentPane.add(lblValorSegundoLugar);
		
		lblValorTerceiroLugar = new JLabel("R$00,00");
		lblValorTerceiroLugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTerceiroLugar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorTerceiroLugar.setBounds(693, 456, 98, 14);
		contentPane.add(lblValorTerceiroLugar);

		// Initializing methods
		btnHome.setEnabled(false);
		filtrarOpcoesMenu();
		listarTotalRecebimentos();
		listarTopTresDizimistas();
	}
	
	public void filtrarOpcoesMenu() {
		if(loggedUser.dizimista(loggedUser) == true) {
			btnRecebimentos.setVisible(false);
			btnDizimistas.setVisible(false);
			btnFuncionarios.setVisible(false);
			btnEditarInstituicao.setVisible(false);
			
			btnHistorico.setVisible(true);
		}else {
			btnRecebimentos.setVisible(true);
			btnDizimistas.setVisible(true);
			
			if(loggedUser.getHasPrivilegies() == true) {
				// only Workers with privilegies can change other workers (or theirselves) records and the institution ones
				btnFuncionarios.setVisible(true);
				btnEditarInstituicao.setVisible(true);
			}else {
				btnFuncionarios.setVisible(false);
				btnEditarInstituicao.setVisible(false);
			}
			btnHistorico.setVisible(false);
			
			if(loggedUser.getMasterAdmin()) {
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					System.out.println(contentPane);
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
			}
		}
	}
	
	public void listarTotalRecebimentos() {
		recebimentoObj = new RecebimentoController(loggedUser.getInstitutionId());
		String dataAtual = txtDate.getText();
		if(dataAtual.equals(null) || dataAtual.equals("")) {
			dataAtual = FunctionsClass.dataAtual("yyyy-MM-dd");
		}else {
			dataAtual = FunctionsClass.formatarDataString(dataAtual);
			dataAtual = dataAtual + "-01";
		}
		Float total = recebimentoObj.listarTotalPorData(dataAtual);
		String valorTotalDesteMes = FunctionsClass.parseMonetary(total.toString());
		String dataCorreta = FunctionsClass.formatarDataString(dataAtual);
		txtDate.setText(FunctionsClass.apenasMesEAno(dataCorreta));
		lblTotalRecebimentosMes.setText(valorTotalDesteMes);
	}
	
	public void listarTopTresDizimistas() {
		recebimentoObj = new RecebimentoController(loggedUser.getInstitutionId());
		String dataAtual = txtDate.getText();
		if(dataAtual.equals(null) || dataAtual.equals("")) {
			dataAtual = FunctionsClass.dataAtual("yyyy-MM-dd");
		}else {
			dataAtual = FunctionsClass.formatarDataString(dataAtual);
			dataAtual = dataAtual + "-01";
		}
		ArrayList<String[]> resultado = recebimentoObj.buscarTopTres(dataAtual);
		Integer posicao = 0;
		for(String[] dados : resultado) {
			if(dados.length == 0) {
				if(posicao == 0) {
					lblNomePrimeiroLugar.setText("...");
					lblValorPrimeiroLugar.setText("");
					lblNomePrimeiroLugar.setVisible(true);
					lblValorPrimeiroLugar.setVisible(false);
					btnPrimeiroLugar.setVisible(false);
				}else if(posicao == 1) {
					lblNomeSegundoLugar.setText("");
					lblValorSegundoLugar.setText("");
					lblNomeSegundoLugar.setVisible(false);
					lblValorSegundoLugar.setVisible(false);
					btnSegundoLugar.setVisible(false);
				}else if(posicao == 2) {
					lblNomeTerceiroLugar.setText("");
					lblValorTerceiroLugar.setText("");
					lblNomeTerceiroLugar.setVisible(false);
					lblValorTerceiroLugar.setVisible(false);
					btnTerceiroLugar.setVisible(false);
				}else {
					break;
				}
				posicao++;
				continue;
			}
			String nome  = dados[0];
			String valor = FunctionsClass.parseMonetary(dados[1]);
			if(posicao == 0) {
				lblNomePrimeiroLugar.setText(nome);
				lblValorPrimeiroLugar.setText(valor);
				lblNomePrimeiroLugar.setVisible(true);
				lblValorPrimeiroLugar.setVisible(true);
				btnPrimeiroLugar.setVisible(true);
			}else if(posicao == 1) {
				lblNomeSegundoLugar.setText(nome);
				lblValorSegundoLugar.setText(valor);
				lblNomeSegundoLugar.setVisible(true);
				lblValorSegundoLugar.setVisible(true);
				btnSegundoLugar.setVisible(true);
			}else if(posicao == 2) {
				lblNomeTerceiroLugar.setText(nome);
				lblValorTerceiroLugar.setText(valor);
				lblNomeTerceiroLugar.setVisible(true);
				lblValorTerceiroLugar.setVisible(true);
				btnTerceiroLugar.setVisible(true);
			}else {
				break;
			}
			posicao++;
		}
	}
	
	public void esconderTopTresDizimistas(Boolean esconder) {
		lblNomePrimeiroLugar.setVisible(esconder);
		lblValorPrimeiroLugar.setVisible(esconder);
		lblNomeSegundoLugar.setVisible(esconder);
		lblValorSegundoLugar.setVisible(esconder);
		lblNomeTerceiroLugar.setVisible(esconder);
		lblValorTerceiroLugar.setVisible(esconder);
		btnPrimeiroLugar.setVisible(esconder);
		btnSegundoLugar.setVisible(esconder);
		btnTerceiroLugar.setVisible(esconder);
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

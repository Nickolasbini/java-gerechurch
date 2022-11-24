package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import classes.Pessoa;
import classes.Recebimento;
import classes.TipoPagamento;
import controllers.DizimistaController;
import controllers.RecebimentoController;
import controllers.TipoPagamentoController;
import helpers.FunctionsClass;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;

public class SalvarRecebimento extends JFrame {

	private static JPanel contentPane;
	static JFrame currentFrame;
	static Pessoa loggedUser;
	private JButton btnHome;
	private JButton btnRecebimentos;
	private JButton btnHistorico;
	private JButton btnDizimistas;
	private JButton btnFuncionarios;
	private JButton btnEditarInstituicao;
	private static JButton btnCalcular;
	private SalvarRecebimento frame;
	private static DizimistaController dizimistaController;
	private static TipoPagamentoController tipoPagamentoController;
	
	static JComboBox comboDizimista;
	static JComboBox comboTipoPagamento;
	static JTextField inputValor;
	static JTextField inputDataPagamento;
	static Recebimento objetoParaAtualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalvarRecebimento frame = new SalvarRecebimento(loggedUser);
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
	public SalvarRecebimento(Pessoa obj) {
		if(obj == null) {
			Login loginFrame = new Login();
			loginFrame.setVisible(true);
			return;
		}
		loggedUser 				= obj;
		dizimistaController		= new DizimistaController();
		tipoPagamentoController = new TipoPagamentoController();
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
				if(currentFrame != null)
					currentFrame.setVisible(false);
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
				if(currentFrame != null)
					currentFrame.setVisible(false);
				Recebimentos frame = new Recebimentos(loggedUser);
				frame.setVisible(true);
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnRecebimentos.setBounds(10, 80, 136, 29);
		menu.add(btnRecebimentos);
		
		btnDizimistas = new JButton("Dizimistas");
		btnDizimistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null)
					currentFrame.setVisible(false);
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
				if(currentFrame != null)
					currentFrame.setVisible(false);
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
		
		JButton logoIcon = new JButton("");
		logoIcon.setEnabled(false);
		logoIcon.setBounds(20, 352, 115, 115);
		logoIcon.setIcon(FunctionsClass.createIconFromImg("logo.png", 115, 115));
		logoIcon.setDisabledIcon(FunctionsClass.createIconFromImg("logo.png", 115, 115));
		menu.add(logoIcon);
		// menu END
		
		btnEditarInstituicao = new JButton("Instituições");
		btnEditarInstituicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame != null)
					currentFrame.setVisible(false);
				Funcionarios frame = new Funcionarios(loggedUser);
				frame.setVisible(true);
				frame.listarFuncionarios();
				frame.currentFrame = frame;
				currentFrame = frame;
			}
		});
		btnEditarInstituicao.setBounds(10, 200, 136, 29);
		menu.add(btnEditarInstituicao);
		
		
		JLabel lblTitulo = new JLabel("Cadastrar Recebimento");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(166, 45, 200, 17);
		contentPane.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Dizimista");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(207, 73, 58, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValor.setBounds(207, 111, 58, 17);
		contentPane.add(lblValor);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tipo do Pagamento");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(207, 153, 127, 17);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Data do Pagamento");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(207, 198, 127, 17);
		contentPane.add(lblNewLabel_1_1_1);
		
		inputValor = new JTextField();
		inputValor.setColumns(10);
		inputValor.setBounds(319, 110, 465, 20);
		contentPane.add(inputValor);
		
		inputDataPagamento = new JTextField();
		inputDataPagamento.setColumns(10);
		inputDataPagamento.setBounds(543, 197, 241, 20);
		contentPane.add(inputDataPagamento);
		
		JButton btnNovoDizimista = new JButton("+");
		btnNovoDizimista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa objetoNovo = new Pessoa();
				objetoNovo.setStatus(objetoNovo.DIZIMISTA);
				objetoNovo.setInstitutionId(loggedUser.getInstitutionId());
				objetoNovo.setLgpd(false);
				SalvarPessoaModal saveModal = new SalvarPessoaModal(loggedUser);
	        	saveModal.setVisible(true);
	        	saveModal.setObjetoParaAlteracao(null, objetoNovo);
	        	saveModal.parentFrame = frame;
			}
		});
		btnNovoDizimista.setBounds(794, 71, 79, 23);
		contentPane.add(btnNovoDizimista);
		
		btnCalcular = new JButton("calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCalcular.setBounds(794, 109, 79, 23);
		contentPane.add(btnCalcular);
		
		JButton btnSalvarRecebimento = new JButton("SALVAR");
		btnSalvarRecebimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inputValor.getText().equals("") || inputDataPagamento.getText().equals("") || dizimistaSelecionado == null || tipoPagamentoSelecionado == null) {
					displayMessage("Por favor informe todos os dados necessários para prosseguir");
				}else {
					RecebimentoController recebimentoController = new RecebimentoController(loggedUser.getInstitutionId());
					Recebimento objetoParaSalvar = new Recebimento();
					if(objetoParaAtualizar != null) 
						objetoParaSalvar = objetoParaAtualizar;
					objetoParaSalvar.setInstitutionId(loggedUser.getInstitutionId());
					objetoParaSalvar.setPaymentDate(FunctionsClass.parseENdateToBR(inputDataPagamento.getText()));
					objetoParaSalvar.setTitherId(dizimistaSelecionado.getId());
					objetoParaSalvar.setWorkerId(loggedUser.getId());
					objetoParaSalvar.setPaymentTypeId(tipoPagamentoSelecionado.getId());
					objetoParaSalvar.setValue(inputValor.getText());
					if(objetoParaSalvar.salvar(objetoParaSalvar) == true) {
						if(objetoParaAtualizar != null) {
							displayMessage("Recebimento atualizado");
						}else {
							displayMessage("Novo recebimento cadastrado");
						}
						if(currentFrame != null) {
							currentFrame.setVisible(false);
						}else {
							contentPane.getParent().getParent().getParent().setVisible(false);
						}
						Recebimentos frame = new Recebimentos(loggedUser);
						frame.setVisible(true);
						frame.buscarMesesComRecebimento();
						frame.currentFrame = frame;
						currentFrame = frame;
					}else {
						displayMessage("Um problema ocorreu, por favor tente novamente");
					}
				}
			}
		});
		btnSalvarRecebimento.setBounds(425, 444, 225, 23);
		contentPane.add(btnSalvarRecebimento);
		// menu END
		
		filtrarOpcoesMenu();
		comboDizimistas();
		comboTiposPagamento();
		setarDataAtual();
		objetoParaAtualizar = null;
	}
	
	static String idDoDizimistaSelecionado = null;
	static ArrayList<Pessoa> dizimistasComboObjetos;
	static Pessoa dizimistaSelecionado = null;
	public static void comboDizimistas() {
		dizimistasComboObjetos = dizimistaController.comboMeusDizimistasComoObjetos(loggedUser.getInstitutionId());
		String[] resultado = dizimistaController.comboMeusDizimistas(loggedUser.getInstitutionId());
		comboDizimista = new JComboBox(resultado);
		comboDizimista.setBounds(319, 72, 465, 20);
		comboDizimista.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Integer posicaoEmResultados = comboDizimista.getSelectedIndex();
				dizimistaSelecionado = dizimistasComboObjetos.get(posicaoEmResultados);
				handleCalculadora();
		    }
		});
		contentPane.add(comboDizimista);
		Integer posicaoEmResultados = comboDizimista.getSelectedIndex();
		dizimistaSelecionado = dizimistasComboObjetos.get(posicaoEmResultados);
		handleCalculadora();
	}
	
	static String idDoTipoPagamentoSelecionado = null;
	static ArrayList<TipoPagamento> tipoPagamentoComboObjetos;
	static TipoPagamento tipoPagamentoSelecionado = null;
	public static void comboTiposPagamento() {
		tipoPagamentoComboObjetos = tipoPagamentoController.comboMeusTiposPagamentoComoObjetos(loggedUser.getInstitutionId());
		String[] resultado 	      = tipoPagamentoController.comboTipoPagamento();
		comboTipoPagamento 		  = new JComboBox(resultado);
		comboTipoPagamento.setBounds(543, 152, 241, 20);
		comboTipoPagamento.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Integer posicaoEmResultados = comboTipoPagamento.getSelectedIndex();
		    	tipoPagamentoSelecionado = tipoPagamentoComboObjetos.get(posicaoEmResultados);
		    }
		});
		contentPane.add(comboTipoPagamento);
		Integer posicaoEmResultados = comboTipoPagamento.getSelectedIndex();
		tipoPagamentoSelecionado = tipoPagamentoComboObjetos.get(posicaoEmResultados);
	}
	
	public static void setarDataAtual() {
		inputDataPagamento.setText(FunctionsClass.dataAtual("dd-MM-yyyy"));
	}
	
	public static void handleCalculadora() {
		if(dizimistaSelecionado.getSalary().equals("null") && dizimistaSelecionado.getProfession().equals("null")) {
			btnCalcular.setVisible(false);
		}else {
			btnCalcular.setVisible(true);
		}
	}
	
	public void filtrarOpcoesMenu() {
		if(loggedUser.dizimista(loggedUser) == true) {
			btnRecebimentos.setVisible(false);
			btnDizimistas.setVisible(false);
			btnFuncionarios.setVisible(false);
			btnEditarInstituicao.setVisible(false);
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
		}
	}
	
	public void setarAtributosEdicao(Recebimento recebimentoObj) {
		Integer posicaoDizimista = 0;
		for(Pessoa dizimista : dizimistasComboObjetos){
			if(dizimista.getId().equals(recebimentoObj.getTitherId())) {
				idDoDizimistaSelecionado = dizimista.getId();
				comboDizimista.setSelectedIndex(posicaoDizimista);
				break;
			}
			posicaoDizimista++;
		}
		Integer posicaoTipoPagamento = 0;
		for(TipoPagamento pagamento : tipoPagamentoComboObjetos){
			if(pagamento.getId().equals(recebimentoObj.getPaymentTypeId())) {
				idDoTipoPagamentoSelecionado = pagamento.getId();
				comboTipoPagamento.setSelectedIndex(posicaoTipoPagamento);
				break;
			}
			posicaoTipoPagamento++;
		}
		inputValor.setText(recebimentoObj.getValue());
		inputDataPagamento.setText(FunctionsClass.formatarDataString(recebimentoObj.getPaymentDate()));
		objetoParaAtualizar = recebimentoObj;
		handleCalculadora();
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

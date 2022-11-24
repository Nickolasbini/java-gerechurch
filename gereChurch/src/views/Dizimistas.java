package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Pessoa;
import controllers.DizimistaController;
import controllers.PessoaController;
import helpers.FunctionsClass;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Point;
import java.awt.Button;

public class Dizimistas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	static JFrame currentFrame;
	static Pessoa loggedUser;
	private JButton btnHome;
	private JButton btnRecebimentos;
	private JButton btnHistorico;
	private JButton btnRelatorios;
	private JButton btnDizimistas;
	private JButton btnFuncionarios;
	private JButton btnEditarInstituicao;
	private Dizimistas frame;
	private JButton btnAlter;
	private JButton btnRemove;
	private JButton btnNew;
	
	static DizimistaController dizimistaController;
    String [] colunas;
    Object [][] dados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dizimistas frame = new Dizimistas(loggedUser);
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
	public Dizimistas(Pessoa obj) {
		if(obj == null) {
			Login loginFrame = new Login();
			loginFrame.setVisible(true);
			return;
		}
		loggedUser = obj;
		currentFrame = frame;
		
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
		
		btnDizimistas.setEnabled(false);
		
		btnAlter = new JButton("Alterar");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = table.getSelectedRow();
				if(id.equals(-1)) {
					displayMessage("Selecione um dizimista para prosseguir");
				}else {
					String idObjetoAlteravel = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
					SalvarPessoaModal saveModal = new SalvarPessoaModal(loggedUser);
		        	saveModal.setVisible(true);
		        	saveModal.setObjetoParaAlteracao(idObjetoAlteravel, null);
		        	saveModal.parentFrame = currentFrame;
				}
			}
		});
		btnAlter.setBounds(685, 61, 89, 23);
		contentPane.add(btnAlter);
		
		btnRemove = new JButton("X");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = table.getSelectedRow();
				if(id.equals(-1)) {
					displayMessage("Selecione um dizimista para prosseguir");
				}else {
					String idToRemove = (String) table.getModel().getValueAt(table.getSelectedRow(),0);
					String workerName = (String) table.getModel().getValueAt(table.getSelectedRow(),1);
					if(openModal("Tem certeza que deseja remover o(a) dizimista "+workerName, "Confirmação de Exclusão") == true) {
						Boolean resultado = PessoaController.removerPorId(idToRemove);
						if(resultado.equals(true)) {
							displayMessage("Dizimista removido");
							contentPane.getParent().getParent().getParent().setVisible(false);
							Dizimistas dizimistasView = new Dizimistas(loggedUser);
							dizimistasView.listarDizimistas();
							dizimistasView.setVisible(true);
						}
					}
				}
			}
		});
		btnRemove.setBounds(784, 61, 89, 23);
		contentPane.add(btnRemove);
		
		JButton btnRefresh = new JButton("Atualizar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarDizimistas();
			}
		});
		btnRefresh.setBounds(188, 61, 89, 23);
		contentPane.add(btnRefresh);
		
		btnNew = new JButton("+");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoa objetoNovo = new Pessoa();
				objetoNovo.setStatus(objetoNovo.DIZIMISTA);
				objetoNovo.setInstitutionId(loggedUser.getInstitutionId());
				objetoNovo.setLgpd(false);
				SalvarPessoaModal saveModal = new SalvarPessoaModal(loggedUser);
	        	saveModal.setVisible(true);
	        	saveModal.setObjetoParaAlteracao(null, objetoNovo);
	        	saveModal.parentFrame = currentFrame;
			}
		});
		btnNew.setBounds(586, 61, 89, 23);
		contentPane.add(btnNew);
		
		filtrarOpcoesMenu();
		btnDizimistas.setEnabled(false);
	}
	
	public void listarDizimistas() {
		dados   = dizimistaController.buscarMeusDizimistas(loggedUser.getInstitutionId());
		colunas = dizimistaController.buscarColunasTabela();
		table = new JTable(dados, colunas);
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            // your valueChanged overridden method
		        	String idObjetoAlteravel = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
		        	SalvarPessoaModal saveModal = new SalvarPessoaModal(loggedUser);
		        	saveModal.setVisible(true);
		        	saveModal.setObjetoParaAlteracao(idObjetoAlteravel, null);
		        	saveModal.parentFrame = currentFrame;
		        }
		    }
		});
		table.setBounds(188, 93, 685, 374);
		table.removeColumn(table.getColumnModel().getColumn(0));
		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(188, 93, 685, 374);
		contentPane.add(scrollPane);
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

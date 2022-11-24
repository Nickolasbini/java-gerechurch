package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import classes.Pessoa;
import controllers.PessoaController;
import controllers.RecebimentoController;
import helpers.FunctionsClass;
import javax.swing.JTextField;

public class HistoricoDizimista extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static Pessoa loggedUser;
	static JFrame currentFrame;
	String [] colunas;
    Object [][] dados;
    private JButton logoIcon;
	
	private JButton btnHome;
	private JButton btnHistorico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoricoDizimista frame = new HistoricoDizimista(loggedUser);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HistoricoDizimista(Pessoa obj) {
		if(obj == null) {
			Login loginFrame = new Login();
			loginFrame.setVisible(true);
			return;
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
		
		btnHistorico.setEnabled(false);
		buscarMeusRecebimentos();
	}
	
//	textField.setBounds(166, 50, 707, 417);
	
	public void buscarMeusRecebimentos() {
		RecebimentoController recebimentoController = new RecebimentoController(loggedUser.getInstitutionId());
		dados   = recebimentoController.buscarRecebimentosDizimista(loggedUser);
		String[] cabecalho = {"Data do Pagamento", "Valor Pago"};
		colunas = cabecalho;
		table = new JTable(dados, colunas);
		table.setBounds(188, 93, 685, 374);
		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(166, 50, 707, 417);
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

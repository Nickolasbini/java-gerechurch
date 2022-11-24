package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Pessoa;
import controllers.PessoaController;
import helpers.FunctionsClass;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField inputEmail;
	private JPasswordField inputPassword;
	static JFrame currentFrame; 
	private Login frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		currentFrame = frame;
		
		closeUnusedFrames();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(155, 128, 267, 23);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		inputPassword = new JPasswordField();
		inputPassword.setBounds(155, 201, 267, 23);
		contentPane.add(inputPassword);
		
		JLabel lblNewLabel = new JLabel("Senha");
		lblNewLabel.setBounds(155, 181, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Email");
		lblSenha.setBounds(155, 104, 46, 14);
		contentPane.add(lblSenha);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email    = inputEmail.getText();
				String password = inputPassword.getText();
				Pessoa object = PessoaController.autenticate(email, password);
				if(object == null) {
					displayMessage("Email ou senha incorretos");
					return;
				}
				if(currentFrame != null) {
					currentFrame.setVisible(false);
				}else {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}
				if(object.getMasterAdmin()) {
					Funcionarios funcionariosFrame = new Funcionarios(object);
					funcionariosFrame.setVisible(true);
					funcionariosFrame.listarFuncionarios();
				}else if(object.dizimista(object)){
					HomePage homepage = new HomePage(object);
					homepage.setVisible(true);
				}else {
					HomePage homepage = new HomePage(object);
					homepage.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(155, 268, 273, 23);
		contentPane.add(btnLogin);
		
		JButton btnNovaConta = new JButton("Esqueci a senha");
		btnNovaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayMessage("Para recuperar sua senha, entre em contato com o suporte. \nEmail: suporte@gerechurch.com.br");
			}
		});
		btnNovaConta.setBounds(155, 303, 273, 23);
		contentPane.add(btnNovaConta);
		
		JButton logoIcon = new JButton("");
		logoIcon.setBounds(250, 11, 80, 80);
		logoIcon.setIcon(FunctionsClass.createIconFromImg("logo.png", 80, 80));
		logoIcon.setDisabledIcon(FunctionsClass.createIconFromImg("logo.png", 80, 80));
		contentPane.add(logoIcon);
		logoIcon.setEnabled(false);
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

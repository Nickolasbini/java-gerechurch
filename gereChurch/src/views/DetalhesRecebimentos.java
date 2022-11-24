package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import classes.Pessoa;
import classes.Recebimento;
import controllers.PessoaController;
import controllers.RecebimentoController;
import helpers.FunctionsClass;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DetalhesRecebimentos extends JFrame {

	private JPanel contentPane;
	static JFrame currentFrame;
	static Pessoa loggedUser;
	private JTable table;
	private JTextField inputPesquisar;
	private JLabel lblTitulo;
	
	public static RecebimentoController recebimentoController;
	public static String dateToUse;
	public static JFrame parentFrame;
	
	String [] colunas;
    Object [][] dados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalhesRecebimentos frame = new DetalhesRecebimentos(loggedUser, dateToUse);
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
	public DetalhesRecebimentos(Pessoa obj, String currentDate) {
		if(obj == null) {
			Login loginFrame = new Login();
			loginFrame.setVisible(true);
			return;
		}
		loggedUser = obj;
		setDateToUse(currentDate);
		// Setting controller
		recebimentoController = new RecebimentoController(loggedUser.getInstitutionId());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 637, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFrame == null) {
					contentPane.getParent().getParent().getParent().setVisible(false);
				}else {
					currentFrame.setVisible(false);
				}
			}
		});
		btnVoltar.setBounds(10, 11, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnRemover = new JButton("Excluir");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = table.getSelectedRow();
				if(id.equals(-1)) {
					displayMessage("Selecione um recebimento para prosseguir com a exclusão");
				}else {
					String idToRemove = (String) table.getModel().getValueAt(table.getSelectedRow(),0);
					if(openModal("Tem certeza que deseja remover o recebimento selecionado?", "Confirmação de Exclusão") == true) {
						Boolean resultado = RecebimentoController.remover(idToRemove);
						if(resultado.equals(true)) {
							displayMessage("Recebimento removido");
							listarRecebimentos(null);
						}else{
							displayMessage("Um problema ocorreu, tente novamente");
						}
					}
				}
			}
		});
		btnRemover.setBounds(520, 11, 89, 23);
		contentPane.add(btnRemover);
		
		lblTitulo = new JLabel("RECEBIMENTOS DO MÊS ...");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 38, 601, 23);
		contentPane.add(lblTitulo);
		
		setTitulo();
		listarRecebimentos(null);
	}
	
	public void listarRecebimentos(String valorParaBusca) {
		colunas = recebimentoController.buscarColunasTabela(false);
	    dados   = recebimentoController.buscarRecebimentosPorMes(dateToUse, valorParaBusca);
	    table = new JTable(dados, colunas);
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	String id = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
		        	Recebimento recebimentoObj = new Recebimento();
		        	String[] resultado = recebimentoObj.buscarPorId(id);
		        	recebimentoObj = recebimentoObj.criarObjetoComResultado(resultado, true);
					SalvarRecebimento salvarRecebimentoFrame = new SalvarRecebimento(loggedUser);
					salvarRecebimentoFrame.setVisible(true);
					salvarRecebimentoFrame.setarAtributosEdicao(recebimentoObj);
		        }
		    }
		});
		table.setBounds(10, 106, 601, 316);
		table.removeColumn(table.getColumnModel().getColumn(0));
		contentPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 106, 601, 316);
		contentPane.add(scrollPane);
	}
	
	public void setTitulo() {
		String titulo = "RECEBIMENTOS DO MÊS " + dateToUse;
		lblTitulo.setText(titulo);
	}
	
	public void setDateToUse(String date) {
		dateToUse = date;
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
	
	public Frame getParentFrame() {
		Integer parentFramePosition = Frame.getFrames().length - 2;
		for(Integer a = 0; a < Frame.getFrames().length; a++) {
			if(a == parentFramePosition) {
				return Frame.getFrames()[a];
			}
		}
		return null;
	}
}

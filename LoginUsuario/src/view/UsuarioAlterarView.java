package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Usuario;
import service.UsuarioService;

public class UsuarioAlterarView extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private DefaultTableModel model;
	private String nomeAntigo, senhaAntiga, confirmaoSenhaAntiga;
	private UsuarioService usuarioService = new UsuarioService();
	private JScrollPane scrollPane;
	private JTextField textNome;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTable tableAlterar;
	private JButton btnAlterar;
	private JButton btnRetornarMenuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioAlterarView frame = new UsuarioAlterarView();
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
	public UsuarioAlterarView() {
		setTitle("Alterar usuarios autenticados\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 488, 216);
		contentPane.add(scrollPane);
		
		tableAlterar = new JTable();
		scrollPane.setViewportView(tableAlterar);
		tableAlterar.addMouseListener(this);
		tableAlterar.setFont(new Font("SansSerif", Font.BOLD, 10));
		
		JLabel lblNome = new JLabel("Usu\u00E1rio:");
		lblNome.setBounds(10, 10, 45, 13);
		contentPane.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 43, 45, 13);
		contentPane.add(lblSenha);
		
		JLabel lblConfirmeASenha = new JLabel("Confirme a Senha:");
		lblConfirmeASenha.setBounds(10, 78, 101, 13);
		contentPane.add(lblConfirmeASenha);
		
		textNome = new JTextField();
		textNome.setBounds(54, 7, 340, 19);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(48, 40, 345, 19);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(102, 75, 290, 19);
		contentPane.add(confirmPasswordField);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setBounds(133, 104, 85, 21);
		contentPane.add(btnAlterar);

		model = new DefaultTableModel();
		tableAlterar.setModel(model);
		
		btnRetornarMenuPrincipal = new JButton("Voltar");
		btnRetornarMenuPrincipal.addActionListener(this);
		btnRetornarMenuPrincipal.setBounds(244, 104, 85, 21);
		contentPane.add(btnRetornarMenuPrincipal);
		
		model.addColumn("Nome do usuario");
		model.addColumn("Senha do usuario");
		model.addColumn("Confirmação senha");
	}
	
	public void carregarDados(List<Usuario> usuario) {
		for(Usuario usuarioDto: usuario) {
			Object[] fila = new Object[3];
			fila[0] = usuarioDto.getNome();
			fila[1] = usuarioDto.getSenha();
			fila[2] = usuarioDto.getConfirmacaoSenha();
			
			model.addRow(fila);
		}
	}

	public void preencherListaUsuarios(List<Usuario> usuario) {
		for(Usuario usuarioDto: usuario) {
			usuarioService.criarUsuario(usuarioDto);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tableAlterar) {
			handle_tableAlterar_mouseClicked(e);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRetornarMenuPrincipal) {
			handle_btnRetornarMenuPrincipal_actionPerformed(e);
		}
		if (e.getSource() == btnAlterar) {
			handle_btnAlterar_actionPerformed(e);
		}
	}
	
	protected void handle_tableAlterar_mouseClicked(MouseEvent e) {
		 nomeAntigo = tableAlterar.getValueAt(tableAlterar.getSelectedRow(), 0).toString();
		 senhaAntiga = tableAlterar.getValueAt(tableAlterar.getSelectedRow(), 1).toString();
		 confirmaoSenhaAntiga = tableAlterar.getValueAt(tableAlterar.getSelectedRow(), 2).toString();
		
		textNome.setText(nomeAntigo);
		passwordField.setText(senhaAntiga);
		confirmPasswordField.setText(confirmaoSenhaAntiga);
	}
	
	protected void handle_btnAlterar_actionPerformed(ActionEvent e) {
		Usuario usuario = new Usuario(textNome.getText(), new String(passwordField.getPassword()), new String(confirmPasswordField.getPassword()));
		
		usuarioService.alterarUsuario(usuario, nomeAntigo, senhaAntiga);
		JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
		
		List<Usuario> listaUsuarios = usuarioService.findAll();
		((DefaultTableModel) tableAlterar.getModel()).setRowCount(0);
		carregarDados(listaUsuarios);
		
	}
	
	protected void handle_btnRetornarMenuPrincipal_actionPerformed(ActionEvent e) {
		dispose();
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}

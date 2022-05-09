package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entity.Usuario;
import service.UsuarioService;

public class UsuarioLoginView extends JFrame implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton btnInserir;
	private static UsuarioService usuarioService = new UsuarioService();
	private JButton btnConsultar;
	private UsuarioConsultaView jframeConsultar;
	private UsuarioAlterarView jframeAlterar;
	private UsuarioExcluirView jframeExcluir;
	private JButton btnAlterar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioLoginView frame = new UsuarioLoginView();
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
	public UsuarioLoginView() {
		setTitle("Cadastrar Usuario\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 512, 294);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNomeUsuario = new JLabel("Usuario:");
		lblNomeUsuario.setBounds(10, 21, 52, 13);
		panel.add(lblNomeUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 58, 52, 13);
		panel.add(lblSenha);
		
		JLabel lblConfirmeASenha = new JLabel("Confirme a senha:");
		lblConfirmeASenha.setBounds(10, 98, 103, 13);
		panel.add(lblConfirmeASenha);
		
		textUsuario = new JTextField();
		textUsuario.addFocusListener(this);
		textUsuario.setBounds(59, 18, 443, 19);
		panel.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(59, 55, 443, 19);
		panel.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(117, 95, 385, 19);
		panel.add(confirmPasswordField);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(this);
		btnInserir.setBounds(383, 190, 98, 67);
		panel.add(btnInserir);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(275, 190, 98, 67);
		panel.add(btnConsultar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setBounds(59, 190, 98, 67);
		panel.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setBounds(167, 190, 98, 67);
		panel.add(btnExcluir);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExcluir) {
			handle_btnExcluir_actionPerformed(e);
		}
		if (e.getSource() == btnAlterar) {
			handle_btnAlterar_actionPerformed(e);
		}
		if (e.getSource() == btnConsultar) {
			handle_btnConsultar_actionPerformed(e);
		}
		if (e.getSource() == btnInserir) {
			handle_btnInserir_actionPerformed(e);
		}
	}
	protected void handle_btnInserir_actionPerformed(ActionEvent e) {
		if(validarUsuario()){
			Usuario user = new Usuario(textUsuario.getText(), new String(passwordField.getPassword()), new String(confirmPasswordField.getPassword()));
			usuarioService.criarUsuario(user);
			
			int confirm = JOptionPane.showConfirmDialog(null, "Deseja cadastrar usuario?");
			if(confirm == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
				limpaTela();
			}
		}
	}
	
	protected void handle_btnConsultar_actionPerformed(ActionEvent e) {
		jframeConsultar = new UsuarioConsultaView();
		jframeConsultar.setVisible(true);
		
		List<Usuario> listaUsuarios = usuarioService.findAll();
		
		jframeConsultar.carregarDados(listaUsuarios);
	}
	
	protected void handle_btnAlterar_actionPerformed(ActionEvent e) {
		jframeAlterar = new UsuarioAlterarView();
		jframeAlterar.setVisible(true);
		
		List<Usuario> listaUsuarios = usuarioService.findAll();
		
		jframeAlterar.carregarDados(listaUsuarios);
		jframeAlterar.preencherListaUsuarios(listaUsuarios);
		
	}
	
	protected void handle_btnExcluir_actionPerformed(ActionEvent e) {
		jframeExcluir = new UsuarioExcluirView();
		jframeExcluir.setVisible(true);
		
		List<Usuario> listaUsuarios = usuarioService.findAll();
		
		jframeExcluir.carregarDados(listaUsuarios);
		jframeExcluir.preencherListaUsuarios(listaUsuarios);
	}
	
	private boolean validarUsuario() {
		 
		String nome = textUsuario.getText();
		String senha = new String(passwordField.getPassword());
		String confirmaSenha = new String(confirmPasswordField.getPassword());
		
		if(nome.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
		}else if(!senha.equals(confirmaSenha)){
			JOptionPane.showMessageDialog(null, "As senhas são diferentes!");
		}else {
			return true;
		}
		return false;
	}
	
	private void limpaTela() {
		 textUsuario.setText("");
		 passwordField.setText("");
		 confirmPasswordField.setText("");
	}
	
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent e) {
		if (e.getSource() == textUsuario) {
			handle_textUsuario_focusLost(e);
		}
	}
	
	protected void handle_textUsuario_focusLost(FocusEvent e) {
		String nomeUsuario = textUsuario.getText();
		
		if(nomeUsuario.length() < 3 || Character.isUpperCase(nomeUsuario.charAt(0)) != true) {
			JOptionPane.showMessageDialog(null, "O usuário deve ter mais que 3 caracteres e começar em caixa alta.");
		}
	}
	
	public void atualizarLista(List<Usuario> usuario) {
		usuarioService.AtualizarLista(usuario);
	}
}

package view;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Usuario;
import service.UsuarioService;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuarioExcluirView extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tableExcluir;
	private DefaultTableModel model;
	private UsuarioService usuarioService = new UsuarioService();
	private JButton btnRetornarMenuPrincipal;
	private UsuarioLoginView jframeUsuarioLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioExcluirView frame = new UsuarioExcluirView();
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
	public UsuarioExcluirView() {
		setTitle("Excluir usuarios\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 443, 243);
		contentPane.add(scrollPane);
		
		tableExcluir = new JTable();
		tableExcluir.addMouseListener(this);
		
		model = new DefaultTableModel();
		tableExcluir.setModel(model);
		
		model.addColumn("Nome do usuario");
		model.addColumn("Senha do usuario");
		model.addColumn("Confirmação senha");
		
		scrollPane.setViewportView(tableExcluir);
		
		btnRetornarMenuPrincipal = new JButton("Voltar");
		btnRetornarMenuPrincipal.addActionListener(this);
		btnRetornarMenuPrincipal.setBounds(173, 258, 85, 21);
		contentPane.add(btnRetornarMenuPrincipal);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tableExcluir) {
			handle_tableExcluir_mouseClicked(e);
		}
	}
	
	protected void handle_tableExcluir_mouseClicked(MouseEvent e) {
		String nomeUsuario = tableExcluir.getValueAt(tableExcluir.getSelectedRow(), 0).toString();
		String senhaUsuario = tableExcluir.getValueAt(tableExcluir.getSelectedRow(), 1).toString();
		
		usuarioService.removerUsuario(nomeUsuario, senhaUsuario);
		
		JOptionPane.showMessageDialog(null, "Usuario excluído com sucesso!");
		List<Usuario> listaUsuarios = usuarioService.findAll();
		
		((DefaultTableModel) tableExcluir.getModel()).setRowCount(0);
		
		if(!listaUsuarios.isEmpty()) {
			carregarDados(listaUsuarios);
		}
	}
	
	public void preencherListaUsuarios(List<Usuario> usuario) {
		for(Usuario usuarioDto: usuario) {
			usuarioService.criarUsuario(usuarioDto);
		}
	}
	
	public void carregarDados(List<Usuario> usuario) {
		for(Usuario usuarioDto: usuario) {
			Object[] fila = new Object[9];
			fila[0] = usuarioDto.getNome();
			fila[1] = usuarioDto.getSenha();
			fila[2] = usuarioDto.getConfirmacaoSenha();
			
			model.addRow(fila);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRetornarMenuPrincipal) {
			handle_btnRetornarMenuPrincipal_actionPerformed(e);
		}
	}
	
	protected void handle_btnRetornarMenuPrincipal_actionPerformed(ActionEvent e) {
		jframeUsuarioLogin = new UsuarioLoginView();
		
		List<Usuario> listaUsuarios = usuarioService.findAll();
	
		jframeUsuarioLogin.atualizarLista(listaUsuarios);
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

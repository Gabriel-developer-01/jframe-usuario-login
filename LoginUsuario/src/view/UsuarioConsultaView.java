package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Usuario;

public class UsuarioConsultaView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable tableConsultar;
	private JButton btnRetornarMenuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioConsultaView frame = new UsuarioConsultaView();
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
	public UsuarioConsultaView() {
		setTitle("Consultar Usuarios autenticados\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 488, 265);
		contentPane.add(scrollPane);
		
		tableConsultar = new JTable();
		scrollPane.setViewportView(tableConsultar);
		
		btnRetornarMenuPrincipal = new JButton("Voltar");
		btnRetornarMenuPrincipal.addActionListener(this);
		btnRetornarMenuPrincipal.setBounds(196, 278, 85, 21);
		contentPane.add(btnRetornarMenuPrincipal);
		
		model = new DefaultTableModel();
		tableConsultar.setModel(model);
		
		model.addColumn("Nome do usuario");
		model.addColumn("Senha do usuario");
		model.addColumn("Confirmação senha");
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
		dispose();
	}
}

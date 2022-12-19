package br.com.empresa.projsenac.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.service.IServicoBeanLocal;
import br.com.empresa.projsenac.service.ServicoBeanLocal;
import br.com.empresa.projsenac.view.util.RowData;
import br.com.empresa.projsenac.view.util.TableModel;
import br.com.empresa.projsenac.vo.BibliaVO;
import br.com.empresa.projsenac.vo.LivrosVO;

@SuppressWarnings("serial")
public class BibliaView extends JFrame {

	private JPanel contentPane;
	private JTextField tfVersiculoDe;
	private JTextField tfVersiculoAte;
	private JTextField tfTextoPesqusia;
	private JTable table;
	private TableModel tableModel;
	private JComboBox<LivrosVO> cbLivro;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCapitulo;
	private List<LivrosVO> livros;
	private List<BibliaVO> biblia;
	private IServicoBeanLocal serviceBeanLocal;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliaView frame = new BibliaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public BibliaView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BibliaView.class.getResource("/br/com/empresa/projsenac/view/img/biblia.png")));
		serviceBeanLocal = new ServicoBeanLocal();
		
		setTitle("Sistema de leitura biblia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(10, 9, 699, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cbLivro = new JComboBox<LivrosVO>();
		cbLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alimentaJcbCapitulo();
			}
		});
		cbLivro.setBounds(10, 21, 125, 22);
		panel.add(cbLivro);
		
		cbCapitulo = new JComboBox();
		cbCapitulo.setBounds(146, 21, 125, 22);
		panel.add(cbCapitulo);
		
		tfVersiculoDe = new JTextField();
		tfVersiculoDe.setBounds(370, 22, 86, 20);
		panel.add(tfVersiculoDe);
		tfVersiculoDe.setColumns(10);
		
		JLabel lblVersiculoDe = new JLabel("Versiculo de:");
		lblVersiculoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersiculoDe.setBounds(281, 25, 89, 14);
		panel.add(lblVersiculoDe);
		
		JLabel lblVersiculoAte = new JLabel("até:");
		lblVersiculoAte.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersiculoAte.setBounds(455, 25, 43, 14);
		panel.add(lblVersiculoAte);
		
		tfVersiculoAte = new JTextField();
		tfVersiculoAte.setBounds(495, 22, 86, 20);
		panel.add(tfVersiculoAte);
		tfVersiculoAte.setColumns(10);
		
		JButton btnPesquisarF = new JButton("Pesquisar");
		btnPesquisarF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisarF.setBounds(591, 21, 100, 23);
		panel.add(btnPesquisarF);
		
		JLabel lblNewLabel = new JLabel("Buscador por texto:");
		lblNewLabel.setBounds(10, 50, 115, 14);
		panel.add(lblNewLabel);
		
		tfTextoPesqusia = new JTextField();
		tfTextoPesqusia.setBounds(146, 47, 435, 20);
		panel.add(tfTextoPesqusia);
		tfTextoPesqusia.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(591, 46, 100, 23);
		panel.add(btnLimpar);
		
		JLabel lblLivro = new JLabel("Livro:");
		lblLivro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLivro.setBounds(10, 4, 89, 14);
		panel.add(lblLivro);
		
		JLabel lblCapitulo = new JLabel("Capitulo:");
		lblCapitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCapitulo.setBounds(146, 4, 89, 14);
		panel.add(lblCapitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 98, 699, 179);
		contentPane.add(scrollPane);
		
		//TABLE MODEL
		
		tableModel = new TableModel();
		tableModel.addColumn("Livro");
		tableModel.addColumn("Capitulo");
		tableModel.addColumn("Versiculo");
		tableModel.addColumn("Texto");
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JButton btnLer = new JButton("Ler");
		btnLer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ler();
			}
		});
		btnLer.setBounds(589, 288, 120, 23);
		contentPane.add(btnLer);
		
		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(100);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(420);
		
		// Coloca a janela no centro da tela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width /
				2, dim.height / 2 - this.getSize().height / 2);
		
		alimentaJcbLivro();
		
	}
	
	private void limpar () {
		cbLivro.setSelectedIndex(0);
		cbCapitulo.removeAll();
		tfVersiculoAte.setText("");
		tfVersiculoDe.setText("");
		tfTextoPesqusia.setText("");
	}
	
	private void alimentaJcbLivro (){
		
		try {
			livros = serviceBeanLocal.listarLivros();
			cbLivro.addItem(null);
			for(LivrosVO livroVO : livros) {
				cbLivro.addItem(livroVO);

			}
			
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	@SuppressWarnings("unchecked")
	private void alimentaJcbCapitulo (){
		cbCapitulo.removeAllItems();
		LivrosVO lvSelect = (LivrosVO)cbLivro.getSelectedItem();
		if (lvSelect != null) {
			for(long i = 1; i <= lvSelect.getQtde_capitulos().longValue(); i++) {
				cbCapitulo.addItem(i);	
			}
		}
	}
	
	private void pesquisar () {
		
		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();
		try {	
			
			LivrosVO lvSelect = null;
			if(cbLivro.getSelectedItem() != null) {
				lvSelect = (LivrosVO)cbLivro.getSelectedItem();
			}
			
			BigInteger capitulo = null;
			if(cbCapitulo.getItemCount() > 0) {			
				String sCapitulo = cbCapitulo.getSelectedItem().toString();
				capitulo = new BigInteger(sCapitulo);
			}
			
			String sVersiculoDe = tfVersiculoDe.getText().trim();
			BigInteger versiculoDe = null;
			try {
				if(!sVersiculoDe.trim().matches("[A-Z]*") || !sVersiculoDe.trim().matches("[a-z]*")){
					if(sVersiculoDe != null && sVersiculoDe.trim().length() > 0) {
						versiculoDe = new BigInteger(sVersiculoDe);
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, 
						"Versiculo de: erro de validação."
						+" O campo (versiculo de) deve ser preenchido somente com numeros.",
						"Mensagem de aviso",
						JOptionPane.WARNING_MESSAGE);
			}
			
			String sVersiculoAte = tfVersiculoAte.getText().trim();
			BigInteger versiculoAte = null;
			try {
				if(!sVersiculoAte.trim().matches("[A-Z]*") || !sVersiculoAte.trim().matches("[a-z]*")){
					if(sVersiculoAte != null && sVersiculoAte.trim().length() > 0) {
						versiculoAte = new BigInteger(sVersiculoAte);
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, 
						"Versiculo até: erro de validação."
						+" O campo (versiculo até) deve ser preenchido somente com numeros.",
						"Mensagem de aviso",
						JOptionPane.WARNING_MESSAGE);
			}
		
			biblia = serviceBeanLocal.listarBiblia(lvSelect, capitulo,versiculoDe, 
					versiculoAte, tfTextoPesqusia.getText());
			
			for (BibliaVO bb : biblia) {

				RowData rowData = new RowData();
				rowData.getValues().put(0, bb.getLivrosVO());
				rowData.getValues().put(1, bb.getCapitulo().toString());
				rowData.getValues().put(2, bb.getVersiculo().toString());
				rowData.getValues().put(3, bb.getTexto());
				rowData.setElement(bb);
				tableModel.addRow(rowData);
			}
		
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, 
					"Ocorreu um erro!.",
					"Mensagem de erro",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void ler() {
		
		if(table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, 
					"É necessario selecionar um registro!",
					"Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		}else {
			
			BibliaVO bbSelect = (BibliaVO)tableModel.getRows().get(table.getSelectedRow()).getElement();
			
			try {
				List<BibliaVO> biblias = serviceBeanLocal.listarBiblia(bbSelect);
				
				LeituraView lv = new LeituraView(this,bbSelect);
				
				lv.alimetaTextPane(bbSelect,biblias);
				
				lv.setVisible(true);
			} catch (BOException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
			
			
			
		}
		
	}
}

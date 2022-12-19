package br.com.empresa.projsenac.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import br.com.empresa.projsenac.vo.BibliaVO;
@SuppressWarnings("serial")
public class LeituraView extends JDialog{
	
	    private JPanel contentPane;
	    private JPanel panel;
	    private JRadioButton rdbtnLeft;
	    private JRadioButton rdbtnCenter;
	    private JRadioButton rdbtnRight;
	    private JRadioButton rdbtnJustified;
	    private JScrollPane scrollPane;
	    private final ButtonGroup buttonGroup = new ButtonGroup();
	    private JTextPane textPane;
	    private JButton btnFechar;
	    public void alimetaTextPane(BibliaVO bbSelect, List<BibliaVO> biblias) {
	    	for (BibliaVO bibliaVO : biblias) {
					if(bibliaVO.equals(bbSelect)) {
		        		appendToPane(textPane, "["+bbSelect.getVersiculo().toString()+"] ", Color.BLACK);
		        		appendToPane(textPane, bbSelect.getTexto().toString(), Color.RED);
					} else if (!bibliaVO.equals(bbSelect)){
						appendToPane(textPane, " ["+bibliaVO.getVersiculo().toString()+"] ", Color.BLACK);
						appendToPane(textPane, bibliaVO.getTexto().toString(), Color.DARK_GRAY);
					}
			}
	    }
	    
	    private void appendToPane(JTextPane tp, String msg, Color c)
		{
			
			StyleContext sc = StyleContext.getDefaultStyleContext();
		    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
		    int len = tp.getDocument().getLength();
		    tp.setCaretPosition(len);
		    tp.setCharacterAttributes(aset, false);
		    tp.replaceSelection(msg);
	    
		}
	    
	    public LeituraView(BibliaView jDialog, BibliaVO bbSelect) {
	    	
	    	super(jDialog, true);
	    	setIconImage(Toolkit.getDefaultToolkit().getImage(BibliaView.class.getResource("/br/com/empresa/projsenac/view/img/biblia.png")));
	    	setTitle("Leitura do livro: "+bbSelect.getLivrosVO().getEscritor()+
	    			 " - Capitulo: "+bbSelect.getCapitulo());
			setBounds(100, 100, 550, 450);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(new BorderLayout(0, 0));
	        setContentPane(contentPane);
	        contentPane.add(getPanel(), BorderLayout.SOUTH);
	        contentPane.add(getScrollPane(), BorderLayout.CENTER);
	        
	        // Coloca a janela no centro da tela.
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width / 2 - this.getSize().width /
					2, dim.height / 2 - this.getSize().height / 2);
	    }

	    private JPanel getPanel() {
	        if (panel == null) {
	            panel = new JPanel();
	            panel.add(getRdbtnLeft());
	            panel.add(getRdbtnCenter());
	            panel.add(getRdbtnRight());
	            panel.add(getRdbtnJustified());
	            panel.add(getBtnFechar());
	        }
	        return panel;
	    }

	    private JRadioButton getRdbtnLeft() {
	        if (rdbtnLeft == null) {
	            rdbtnLeft = new JRadioButton("Esquerda");
	            rdbtnLeft.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    StyledDocument doc = textPane.getStyledDocument();
	                    SimpleAttributeSet center = new SimpleAttributeSet();
	                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
	                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
	                }
	            });
	            buttonGroup.add(rdbtnLeft);
	        }
	        return rdbtnLeft;
	    }

	    private JRadioButton getRdbtnCenter() {
	        if (rdbtnCenter == null) {
	            rdbtnCenter = new JRadioButton("Centro");
	            rdbtnCenter.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    StyledDocument doc = textPane.getStyledDocument();
	                    SimpleAttributeSet center = new SimpleAttributeSet();
	                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
	                }
	            });
	            buttonGroup.add(rdbtnCenter);
	        }
	        return rdbtnCenter;
	    }

	    private JRadioButton getRdbtnRight() {
	        if (rdbtnRight == null) {
	            rdbtnRight = new JRadioButton("Direita");
	            rdbtnRight.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    StyledDocument doc = textPane.getStyledDocument();
	                    SimpleAttributeSet center = new SimpleAttributeSet();
	                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
	                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
	                }
	            });
	            buttonGroup.add(rdbtnRight);
	        }
	        return rdbtnRight;
	    }

	    private JRadioButton getRdbtnJustified() {
	        if (rdbtnJustified == null) {
	            rdbtnJustified = new JRadioButton("Justificado");
	            rdbtnJustified.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    StyledDocument doc = textPane.getStyledDocument();
	                    SimpleAttributeSet center = new SimpleAttributeSet();
	                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
	                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
	                }
	            });
	            buttonGroup.add(rdbtnJustified);
	        }
	        return rdbtnJustified;
	    }

	    private JScrollPane getScrollPane() {
	        if (scrollPane == null) {
	            scrollPane = new JScrollPane();
	            scrollPane.setViewportView(getTextPane());
	        }
	        return scrollPane;
	    }

	    public JTextPane getTextPane() {
	        if (textPane == null) {
	            textPane = new JTextPane();
	        }
	        return textPane;
	    }
	
	private JButton getBtnFechar() {
		if (btnFechar == null) {
			btnFechar = new JButton("Fechar");
			btnFechar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fechar();
				}
			});
		}
		return btnFechar;
	}
	
	private void fechar(){
		this.setVisible(false);
		this.dispose();
	}
	
	
}

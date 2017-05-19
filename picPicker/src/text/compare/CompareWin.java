package text.compare;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.InlineView;

import text.compare.Diff_match_patch.Diff;
import text.compare.Diff_match_patch.Operation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SizeRequirements;
import javax.swing.JTextArea;

public class CompareWin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompareWin frame = new CompareWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextArea taRsd;
	private JTextArea taDst;
	private JButton btCompare;
	private myJTextJPane tpCompareResult;
	private Diff_match_patch diff;
	

	/**
	 * Create the frame.
	 */
	public CompareWin(){
		setFont(new Font("Dialog", Font.PLAIN, 25));
		initView();
		diff = new Diff_match_patch();
		btCompare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tpCompareResult.setText("");
				// TODO Auto-generated method stub
				String text1 = taRsd.getText();
				String text2 = taDst.getText();
				if(""==text1||""==text2){
					return;
				}
//				System.out.println(p.diff_main("12352��2", "12332��2",true,10));
				try{
					LinkedList<Diff> list = diff.diff_main(text1, text2, true);
					if(null==list||0==list.size()){
						return;
					}
					for (Diff diff : list) {
						if(null==diff){
							continue;
						}
						Operation op = diff.operation;
						switch (op){
							case DELETE:
								tpCompareResult.setDocs(diff.text, Color.RED, true, 15);
								break;
							case INSERT:
								tpCompareResult.setDocs(diff.text, Color.BLUE, true, 15);
								break;
							case EQUAL:
								tpCompareResult.setDocs(diff.text, Color.BLACK, false, 15);
								break;
						}
						
					}
				}catch (Exception e1) {
					// TODO: handle exception
					System.out.println(e1);
					tpCompareResult.setDocs("比较出错，请重试", Color.BLACK, false, 15);
				}
			}
		});
	}
	
	public void initView() {
		setTitle("\u6587\u672C\u6BD4\u8F83");
		setBounds(100, 100, 1258, 962);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 3, 0, 2 };// ��ʼ������
		gridBagLayout.columnWeights = new double[] { 1, 1 };
		getContentPane().setLayout(gridBagLayout);

		/* ԭ�ı� */
		taRsd = new JTextArea();
		taRsd.setFont(new Font("Monospaced", Font.BOLD, 15));
		taRsd.setBounds(5, 5, 20, 20);
		taRsd.setBorder(new EmptyBorder(5, 5, 5, 5));
		taRsd.setLineWrap(true);// �����Զ����й���
		taRsd.setWrapStyleWord(true);// ������в����ֹ���

		JScrollPane spRsd = new JScrollPane(taRsd);

		JPanel plRsd = new JPanel();
		plRsd.setBackground(Color.WHITE);
		plRsd.setLayout(new BorderLayout());
		plRsd.add(spRsd);

		GridBagConstraints gbc_rsd = new GridBagConstraints(); // ���õ�Ԫ�������
		gbc_rsd.insets = new Insets(0, 0, 5, 0);
		gbc_rsd.fill = GridBagConstraints.BOTH;
		gbc_rsd.gridx = 0;
		gbc_rsd.gridy = 0;
		gbc_rsd.gridwidth = 1;
		gbc_rsd.gridheight = 1;
		getContentPane().add(plRsd, gbc_rsd);

		/* �Ƚϵ��ı� */
		taDst = new JTextArea();
		taDst.setFont(new Font("Monospaced", Font.BOLD, 15));
		taDst.setBorder(new EmptyBorder(5, 5, 5, 5));
		taDst.setBounds(5, 5, 20, 20);
		taDst.setLineWrap(true);// �����Զ����й���
		taDst.setWrapStyleWord(true);// ������в����ֹ���

		JScrollPane spDst = new JScrollPane(taDst);

		JPanel plDst = new JPanel();
		plDst.setLayout(new BorderLayout());
		plDst.setBackground(Color.WHITE);
		plDst.add(spDst);

		GridBagConstraints gbc_dst = new GridBagConstraints(); // ���õ�Ԫ�������
		gbc_dst.insets = new Insets(0, 0, 5, 0);
		gbc_dst.fill = GridBagConstraints.BOTH;
		gbc_dst.gridx = 1;
		gbc_dst.gridy = 0;
		gbc_dst.gridwidth = 1;
		gbc_dst.gridheight = 1;
		getContentPane().add(plDst, gbc_dst);

		/* �Ƚϰ�ť */
		btCompare = new JButton("比较");
		btCompare.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		GridBagConstraints gbc_compare = new GridBagConstraints(); // ���õ�Ԫ�������
		gbc_compare.insets = new Insets(0, 0, 5, 0);
		gbc_compare.fill = GridBagConstraints.BOTH;
		gbc_compare.gridx = 0;
		gbc_compare.gridy = 1;
		gbc_compare.gridwidth = 2;
		gbc_compare.gridheight = 1;
		getContentPane().add(btCompare, gbc_compare);

		/* �ȽϵĽ�� */
		tpCompareResult = new myJTextJPane();
		tpCompareResult.setBorder(new EmptyBorder(5, 5, 5, 5));
		tpCompareResult.setEditable(false);
		tpCompareResult.setFont(new Font("Monospaced", Font.BOLD, 15));
//		setDocs(
//				"ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",Color.red,false,20);
		// plCompareResult.add(tpCompareResult);
//		tpCompareResult.setText("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
//		((myJTextJPane) tpCompareResult).setDocs("111sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",Color.RED,false,20);
		JScrollPane spCompareResult = new JScrollPane(tpCompareResult);

		JPanel plCompareResult = new JPanel();
		plCompareResult.setLayout(new BorderLayout());
		plCompareResult.add(spCompareResult);
		
	      
		GridBagConstraints gbc_compareR = new GridBagConstraints(); // ���õ�Ԫ�������
		gbc_compareR.insets = new Insets(0, 0, 5, 0);
		gbc_compareR.fill = GridBagConstraints.BOTH;
		gbc_compareR.gridx = 0;
		gbc_compareR.gridy = 2;
		gbc_compareR.gridwidth = 2;
		gbc_compareR.gridheight = 1;
		getContentPane().add(plCompareResult, gbc_compareR);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


	
	public class myJTextJPane extends JTextPane{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;


		public void setDocs(String str, Color col, boolean bold, int fontSize) {
			SimpleAttributeSet attrSet = new SimpleAttributeSet();
			StyleConstants.setForeground(attrSet, col);
			// ��ɫ
			if (bold == true) {
				StyleConstants.setBold(attrSet, true);
			} // ��������
			StyleConstants.setFontSize(attrSet, fontSize);
//			StyleConstants.set
			// �����С
			
			Document doc = this.getDocument();
			str = str + "\n";
			try {
				doc.insertString(doc.getLength(), str, attrSet);
			} catch (BadLocationException e) {
				System.out.println("BadLocationException: " + e);
			}
		}
		
		public myJTextJPane(){
			super();
			this.setEditorKit(new HTMLEditorKit(){   
		           @Override   
		           public ViewFactory getViewFactory(){
		               return new HTMLFactory(){   
		                   public View create(Element e){   
		                      View v = super.create(e);   
		                      if(v instanceof InlineView){   
		                          return new InlineView(e){   
		                              public int getBreakWeight(int axis, float pos, float len) {   
		                                  return GoodBreakWeight;   
		                              }   
		                              public View breakView(int axis, int p0, float pos, float len) {   
		                                  if(axis == View.X_AXIS) {   
		                                      checkPainter();   
		                                      int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);   
		                                      if(p0 == getStartOffset() && p1 == getEndOffset()) {   
		                                          return this;   
		                                      }   
		                                      return createFragment(p0, p1);   
		                                  }   
		                                  return this;   
		                                }   
		                            };   
		                      }   
		                      else if (v instanceof ParagraphView) {   
		                          return new ParagraphView(e) {   
		                              protected SizeRequirements calculateMinorAxisRequirements(int axis, SizeRequirements r) {   
		                                  if (r == null) {   
		                                        r = new SizeRequirements();   
		                                  }   
		                                  float pref = layoutPool.getPreferredSpan(axis);   
		                                  float min = layoutPool.getMinimumSpan(axis);   
		                                  // Don't include insets, Box.getXXXSpan will include them.   
		                                    r.minimum = (int)min;   
		                                    r.preferred = Math.max(r.minimum, (int) pref);   
		                                    r.maximum = Integer.MAX_VALUE;   
		                                    r.alignment = 0.5f;   
		                                  return r;   
		                                }   
		   
		                            };   
		                        }   
		                      return v;   
		                    }   
		                };   
		            }   
		        }); 
			this.setContentType("text/html");
//			this.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, ",");
//			this.insertHTML(doc, doc.getLength(), "html code", 0, 0, null);
//			this.inser
		}
	}
}

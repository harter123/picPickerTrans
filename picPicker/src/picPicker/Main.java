package picPicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import java.awt.Component;

public class Main extends JFrame {
	private static final long serialVersionUID = -267804510087895906L;
	private GridBagLayout gridBagLayout;
	private JButton btStartPicker;
	private JPanel plImage;
	private JButton btIdentify;
	private JPanel plResult;
	private JScrollPane spImage;
	private JScrollPane spResult;

	private JTextArea taResult;
	private JLabel lbImage;

	public static Image img = null;
	private JComboBox<String> cbLanguages;
	ITesseract TessInstance = null;
	private JButton btTranslate;
	private JPanel plTranslate;
	private JScrollPane spTranslate;
	private JTextArea taTranslate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		initView();
		TessInstance = new Tesseract();

		btStartPicker.addActionListener(new ActionListener() {// 按钮添加事件，截图
			public void actionPerformed(ActionEvent e) {
				try {
					new ScreenWindow(lbImage);
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, "出现意外错误！", "系统提示", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btIdentify.addActionListener(new ActionListener() {//识别
			public void actionPerformed(ActionEvent arg0) {

				if (null == img) {
					JOptionPane.showMessageDialog(Main.this, "请首先截取图片");
					return;
				}
				btIdentify.setEnabled(false);
				btIdentify.setText("正在识别中..");

				BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
						BufferedImage.TYPE_INT_RGB);
				Graphics g = bufferedImage.createGraphics();
				g.drawImage(Main.img, 0, 0, null);
				g.dispose();

				// 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分
				BufferedImage textImage = ImageHelper.convertImageToGrayscale(bufferedImage);
				// 图片锐化,自己使用中影响识别率的主要因素是针式打印机字迹不连贯,所以锐化反而降低识别率
				// textImage = ImageHelper.convertImageToBinary(textImage);
				// 图片放大5倍,增强识别率(很多图片本身无法识别,放大5倍时就可以轻易识,但是考滤到客户电脑配置低,针式打印机打印不连贯的问题,这里就放大5倍)
				textImage = ImageHelper.getScaledInstance(textImage, textImage.getWidth() * 5,
						textImage.getHeight() * 5);
				// instance.setDatapath("C:\\Users\\cch\\workspace\\picPicker\\tessdata");

				try {
					String result = TessInstance.doOCR(textImage);
					taResult.setText(result);
					// System.out.println(result);
				} catch (TesseractException e) {
					System.err.println(e.getMessage());
					taResult.setText("识别失败..");
				} finally {
					btIdentify.setEnabled(true);
					btIdentify.setText("识别");
				}
			}
		});
		
		cbLanguages.addItemListener(new ItemListener() {//语言修改

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String lang = "eng";
					switch (cbLanguages.getSelectedIndex()) {
					case 0:
						lang = "eng";
						break;
					case 1:
						lang = "kor";
						break;
					case 2:
						lang = "tha";
						break;
					case 3:
						lang = "chi_sim";
						break;
					}
					TessInstance.setLanguage(lang);
				}
			}
		});
		
		btTranslate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btTranslate.setEnabled(false);
				btTranslate.setText("正在翻译..");
				String re = Languages.translate(taResult.getText());
				if(""==re || null==re){
					return;
				}
				taTranslate.setText(re);
				btTranslate.setEnabled(true);
				btTranslate.setText("翻译");
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void initView() {
		setTitle("文字识别工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 928, 718);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 0.0, 6.0, 0.0, 3.0, 0.0, 3.0 };// 初始化布局
		gridBagLayout.columnWeights = new double[] { 1, 18 };
		getContentPane().setLayout(gridBagLayout);

		btStartPicker = new JButton("\u622A\u5C4F");
		btStartPicker.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_btStartPicker = new GridBagConstraints();
		gbc_btStartPicker.fill = GridBagConstraints.BOTH;
		gbc_btStartPicker.insets = new Insets(0, 0, 5, 0);
		gbc_btStartPicker.gridx = 0;
		gbc_btStartPicker.gridy = 0;
		gbc_btStartPicker.gridwidth = 2;
		gbc_btStartPicker.gridheight = 1;
		getContentPane().add(btStartPicker, gbc_btStartPicker);// 添加截图按钮

		// 初始化面板
		plImage = new JPanel();
		plImage.setBackground(Color.WHITE);
		lbImage = new JLabel();
		plImage.add(lbImage);// 给面板添加一个图片标签

		spImage = new JScrollPane(plImage);// 初始化滑动面板

		GridBagConstraints gbc_plImage = new GridBagConstraints(); // 设置单元格的属性
		gbc_plImage.insets = new Insets(0, 0, 5, 0);
		gbc_plImage.fill = GridBagConstraints.BOTH;
		gbc_plImage.gridx = 0;
		gbc_plImage.gridy = 1;

		gbc_plImage.gridwidth = 2;
		gbc_plImage.gridheight = 1;

		getContentPane().add(spImage, gbc_plImage);// 把滑动面板添加到单元格
		
		btIdentify = new JButton("\u8BC6\u522B");// 添加翻译按钮
		
		btIdentify.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_btIdentify = new GridBagConstraints();
		gbc_btIdentify.fill = GridBagConstraints.BOTH;
		gbc_btIdentify.insets = new Insets(0, 0, 5, 0);
		gbc_btIdentify.gridx = 1;
		gbc_btIdentify.gridy = 2;
		gbc_btIdentify.gridwidth = 1;
		gbc_btIdentify.gridheight = 1;
		getContentPane().add(btIdentify, gbc_btIdentify);

		// 初始化结果面板
		plResult = new JPanel();
		plResult.setBackground(Color.WHITE);
		plResult.setLayout(new BorderLayout());

		taResult = new JTextArea();
		taResult.setFont(new Font("Monospaced", Font.BOLD, 18));
		taResult.setBounds(5, 5, 20, 20);
		taResult.setLineWrap(true);// 激活自动换行功能
		taResult.setWrapStyleWord(true);// 激活断行不断字功能
		// plResult.add(taResult);

		spResult = new JScrollPane(taResult);
		plResult.add(spResult);

		GridBagConstraints gbc_plResult = new GridBagConstraints();
		gbc_plResult.insets = new Insets(0, 0, 5, 0);
		gbc_plResult.fill = GridBagConstraints.BOTH;
		gbc_plResult.gridx = 0;
		gbc_plResult.gridy = 3;
		gbc_plResult.gridwidth = 2;
		gbc_plResult.gridheight = 1;

		getContentPane().add(plResult, gbc_plResult);

		cbLanguages = new JComboBox<String>();
		cbLanguages.setFont(new Font("宋体", Font.PLAIN, 20));
		cbLanguages.addItem("英语");
		cbLanguages.addItem("韩语");
		cbLanguages.addItem("泰语");
		cbLanguages.addItem("汉语简体");
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		cbLanguages.setRenderer(dlcr);
//		cbLanguages.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		GridBagConstraints gbc_cbLanguages = new GridBagConstraints();
		gbc_cbLanguages.insets = new Insets(0, 0, 5, 0);
		gbc_cbLanguages.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbLanguages.gridx = 0;
		gbc_cbLanguages.gridy = 2;
		gbc_cbLanguages.gridwidth = 1;
		gbc_cbLanguages.gridheight = 1;
		getContentPane().add(cbLanguages, gbc_cbLanguages);

		
		btTranslate = new JButton("翻译");
		btTranslate.setFont(new Font("宋体", Font.PLAIN, 20));
		GridBagConstraints gbc_btTranslate = new GridBagConstraints();
		gbc_btTranslate.fill = GridBagConstraints.BOTH;
		gbc_btTranslate.insets = new Insets(0, 0, 5, 0);
		gbc_btTranslate.gridx = 0;
		gbc_btTranslate.gridy = 4;
		gbc_btTranslate.gridwidth = 2;
		gbc_btTranslate.gridheight = 1;
		getContentPane().add(btTranslate, gbc_btTranslate);// 添加截图按钮


		taTranslate = new JTextArea();
		taTranslate.setWrapStyleWord(true);
		taTranslate.setLineWrap(true);
		taTranslate.setFont(new Font("Monospaced", Font.BOLD, 18));
		// plTranslate.add(taTranslate, BorderLayout.SOUTH);

		spTranslate = new JScrollPane(taTranslate);

		plTranslate = new JPanel();
		plTranslate.setLayout(new BorderLayout());
		plTranslate.setBackground(Color.WHITE);
		GridBagConstraints gbc_plTranslate = new GridBagConstraints();
		gbc_plTranslate.fill = GridBagConstraints.BOTH;
		gbc_plTranslate.gridx = 0;
		gbc_plTranslate.gridy = 5;
		gbc_plTranslate.gridwidth = 2;
		gbc_plTranslate.gridheight = 1;
		plTranslate.add(spTranslate);
		
		getContentPane().add(plTranslate, gbc_plTranslate);
	}

}

package picPicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class ScreenWindow extends JFrame {
	private static final long serialVersionUID = -3758062802950480258L;
	private boolean isDrag = false;
	private int x = 0;
	private int y = 0;
	private int xEnd = 0;
	private int yEnd = 0;
	
	private int xMove = 0;
	private int yMove = 0;
	
	private JLabel label;
//	private Image image;
	

	public ScreenWindow(final JLabel imgLabel) throws AWTException, InterruptedException {
		this.setUndecorated(true); 
		this.setBackground(new Color(0,0,0,0)); 
		
		Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
//		image = ScreenImage.getScreenImage(0, 0, screenDims.width, screenDims.height);
//		label = new drawJlabel(new ImageIcon(image));
		label = new drawJlabel();
		label.setOpaque(true);
		label.setBackground(new Color(0,0,0,0)); 
		label.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		label.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					dispose();
				}
			}

			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				
//				System.out.println("按下");
//				repaint();
			}

			public void mouseReleased(MouseEvent e) {
				y+=20;
//				System.out.println("起来");
				
				if (isDrag) {
					xEnd = e.getX();
					yEnd = e.getY()+25;
					if (x > xEnd) {
						int temp = x;
						x = xEnd;
						xEnd = temp;
					}
					if (y > yEnd) {
						int temp = y;
						y = yEnd;
						yEnd = temp;
					}
					try {
//						BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
//								BufferedImage.TYPE_INT_RGB);
//						Graphics g = bufferedImage.createGraphics();
//						g.drawImage(image, 0, 0, null);
//						g.dispose();
//						bufferedImage = bufferedImage.getSubimage(x, y, xEnd - x, yEnd - y);
//						Image imageT = new ImageIcon(bufferedImage).getImage();
//						
//						Main.img = imageT;			
//						imgLabel.setIcon(new ImageIcon(imageT));
						
						Image img = ScreenImage.getScreenImage(x, y, xEnd - x, yEnd - y);
						Main.img = img;
						imgLabel.setIcon(new ImageIcon(img));
						
					} catch (Exception ex) {
						JOptionPane.showConfirmDialog(null, "出现意外错误！", "系统提示", JOptionPane.DEFAULT_OPTION,
								JOptionPane.ERROR_MESSAGE);
					}
					dispose();
				}
			}
		});
		label.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
//				System.out.println("drag");
				if (!isDrag)
					isDrag = true;
				xMove = e.getX();
				yMove = e.getY();
				label.repaint();
			}

			public void mouseMoved(MouseEvent e) {
				/** �϶����̵�����ѡȡ�����Լ�ʵ�� */
//				Graphics gc = label.getGraphics();
//				label.repaint();
//				label.repaint();
//				System.out.println(isPress);
//				if(isPress){
//					System.out.println(e.getX());
//					System.out.println(e.getY());
//				}
				
			}
		});
		this.setUndecorated(true);
		this.getContentPane().add(label);
		this.setSize(screenDims.width, screenDims.height);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	class drawJlabel extends JLabel  
	{  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public drawJlabel(ImageIcon imageIcon) {
			// TODO Auto-generated constructor st
	    	super(imageIcon);
		}
	    public drawJlabel() {
			// TODO Auto-generated constructor st
	    	super();
		}

		public void paint(Graphics g)  
	    {  	
//			g.drawImage(image, 0, 0, null);
//			this.setUndecorated(true); 
//			setBackground(new Color(0,0,0,0)); 
			g.setColor(Color.RED);
			int xT,yT,xMoveT,yMoveT;
			if(x > xMove){
				xT = xMove;
				xMoveT = x;
			}else{
				xT = x;
				xMoveT = xMove;
			}
			
			if(y > yMove){
				yT = yMove;
				yMoveT = y;
			}else{
				yT = y;
				yMoveT = yMove;
			}
			g.drawRect(xT, yT, xMoveT-xT, yMoveT-yT);
//			System.out.println(x);
//			System.out.println(y);
//			System.out.println(xEnd);
//			System.out.println(yEnd);
////			g.drawLine(x, y, xEnd, yEnd); 
////			g.drawLine(x, y, xEnd, y);
////			g.drawLine(xEnd, yEnd, xEnd, y);
////			g.drawLine(xEnd, yEnd, x, yEnd);
//			g.drawRect(100, 100, 200, 200);
	    }
	} 
}


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
	private Image image;
	

	public ScreenWindow(final JLabel imgLabel) throws AWTException, InterruptedException {
		Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
		image = ScreenImage.getScreenImage(0, 0, screenDims.width, screenDims.height);
		label = new drawJlabel(new ImageIcon(image));
		
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
			}

			public void mouseReleased(MouseEvent e) {
				if (isDrag) {
					xEnd = e.getX();
					yEnd = e.getY();
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
				if (!isDrag)
					isDrag = true;
				xMove = e.getX();
				yMove = e.getY();
				label.repaint();
			}

			public void mouseMoved(MouseEvent e) {
				/** 拖动过程的虚线选取框需自己实现 */
//				Graphics gc = label.getGraphics();
//				label.repaint();
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

		public void paint(Graphics g)  
	    {  
//	        g.drawLine(100, 100, 200, 200);  
			g.drawImage(image, 0, 0, null);
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
	    }  
	} 
}


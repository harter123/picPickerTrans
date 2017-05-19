package picPicker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {
	
	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("删除单个文件" + fileName + "成功！");
			return true;
		} else {
			System.out.println("删除单个文件" + fileName + "失败！");
			return false;
		}
	}
	
	public static void savePic(Image img, String path) {
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		// 首先创建一个BufferedImage变量，因为ImageIO写图片用到了BufferedImage变量。
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		// 再创建一个Graphics变量，用来画出来要保持的图片，及上面传递过来的Image变量
		Graphics g = bi.getGraphics();
		try {
			g.drawImage(img, 0, 0, null);
			// 将BufferedImage变量写入文件中。
			ImageIO.write(bi, "png", new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_RGB);
//		Graphics g = bufferedImage.createGraphics();
//		g.drawImage(img, 0, 0, null);
//		g.dispose();
//		// // 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分
//		 BufferedImage textImage = ImageHelper.convertImageToGrayscale(bufferedImage);
//		 // 图片锐化,自己使用中影响识别率的主要因素是针式打印机字迹不连贯,所以锐化反而降低识别率
//		 // textImage = ImageHelper.convertImageToBinary(textImage);
//		 //
//		// 图片放大5倍,增强识别率(很多图片本身无法识别,放大5倍时就可以轻易识,但是考滤到客户电脑配置低,针式打印机打印不连贯的问题,这里就放大5倍)
//		textImage = ImageHelper.getScaledInstance(textImage,textImage.getWidth() * 2,textImage.getHeight() * 2);
//		
//		try {
//			// 将BufferedImage变量写入文件中。
//			ImageIO.write(textImage, "png", new File(path));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
    /** 
     * Created on 2010-7-2  
     * <p>Discription:[isImage,判断文件是否为图片]</p> 
     * @param file 
     * @return true 是 | false 否 
     * @author:[shixing_11@sina.com] 
     */  
    public static final boolean isImage(File file){  
        boolean flag = false;  
        try  
        {  
            BufferedImage bufreader = ImageIO.read(file);  
            int width = bufreader.getWidth();  
            int height = bufreader.getHeight();  
            if(width==0 || height==0){  
                flag = false;  
            }else {  
                flag = true;  
            }  
        }  
        catch (IOException e)  
        {  
            flag = false;  
        }catch (Exception e) {  
            flag = false;  
        }  
        return flag;  
    }  
}

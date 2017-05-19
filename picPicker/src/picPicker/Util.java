package picPicker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {
	
	/**
	 * ɾ�������ļ�
	 * 
	 * @param fileName
	 *            ��ɾ���ļ����ļ���
	 * @return �����ļ�ɾ���ɹ�����true,���򷵻�false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
			return true;
		} else {
			System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
			return false;
		}
	}
	
	public static void savePic(Image img, String path) {
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		// ���ȴ���һ��BufferedImage��������ΪImageIOдͼƬ�õ���BufferedImage������
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		// �ٴ���һ��Graphics����������������Ҫ���ֵ�ͼƬ�������洫�ݹ�����Image����
		Graphics g = bi.getGraphics();
		try {
			g.drawImage(img, 0, 0, null);
			// ��BufferedImage����д���ļ��С�
			ImageIO.write(bi, "png", new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_RGB);
//		Graphics g = bufferedImage.createGraphics();
//		g.drawImage(img, 0, 0, null);
//		g.dispose();
//		// // �����ͼƬ�ڰ״���,��ǿʶ����.������ͨ����ͼ,��ȡͼƬ����Ҫʶ��Ĳ���
//		 BufferedImage textImage = ImageHelper.convertImageToGrayscale(bufferedImage);
//		 // ͼƬ��,�Լ�ʹ����Ӱ��ʶ���ʵ���Ҫ��������ʽ��ӡ���ּ�������,�����񻯷�������ʶ����
//		 // textImage = ImageHelper.convertImageToBinary(textImage);
//		 //
//		// ͼƬ�Ŵ�5��,��ǿʶ����(�ܶ�ͼƬ�����޷�ʶ��,�Ŵ�5��ʱ�Ϳ�������ʶ,���ǿ��˵��ͻ��������õ�,��ʽ��ӡ����ӡ�����������,����ͷŴ�5��)
//		textImage = ImageHelper.getScaledInstance(textImage,textImage.getWidth() * 2,textImage.getHeight() * 2);
//		
//		try {
//			// ��BufferedImage����д���ļ��С�
//			ImageIO.write(textImage, "png", new File(path));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
    /** 
     * Created on 2010-7-2  
     * <p>Discription:[isImage,�ж��ļ��Ƿ�ΪͼƬ]</p> 
     * @param file 
     * @return true �� | false �� 
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

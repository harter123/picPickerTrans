package picPicker;

import java.util.HashMap;
import java.util.Map;

import com.lsj.trans.LANG;
import com.lsj.trans.factory.*;

public class Languages {
	private static final Map<String, String> LANGUAGE = new HashMap<String, String>();
	static {
		LANGUAGE.put("������������", "sq");
		LANGUAGE.put("��������", "ar");
		LANGUAGE.put("�����ݽ���", "az");
		LANGUAGE.put("��������", "ga");
		LANGUAGE.put("��ɳ������", "et");
		LANGUAGE.put("��˹����", "eu");
		LANGUAGE.put("�׶���˹��", "be");
		LANGUAGE.put("����������", "bg");
		LANGUAGE.put("������", "is");
		LANGUAGE.put("������", "pl");
		LANGUAGE.put("��˹��", "fa");
		LANGUAGE.put("������", "af");
		LANGUAGE.put("�ϷǺ�����", "af");
		LANGUAGE.put("������", "da");
		LANGUAGE.put("����", "de");
		LANGUAGE.put("����", "ru");
		LANGUAGE.put("����", "fr");
		LANGUAGE.put("���ɱ���", "tl");
		LANGUAGE.put("������", "fi");
		LANGUAGE.put("��³������", "ka");
		LANGUAGE.put("�ż�������", "gu");
		LANGUAGE.put("���ؿ���¶���", "ht");
		LANGUAGE.put("����", "ko");
		LANGUAGE.put("������", "nl");
		LANGUAGE.put("����������", "gl");
		LANGUAGE.put("��̩��������", "ca");
		LANGUAGE.put("�ݿ���", "cs");
		LANGUAGE.put("���ɴ���", "kn");
		LANGUAGE.put("���޵�����", "hr");
		LANGUAGE.put("������", "la");
		LANGUAGE.put("����ά����", "lv");
		LANGUAGE.put("������", "lo");
		LANGUAGE.put("��������", "lt");
		LANGUAGE.put("����������", "ro");
		LANGUAGE.put("�������", "mt");
		LANGUAGE.put("������", "ms");
		LANGUAGE.put("�������", "mk");
		LANGUAGE.put("�ϼ�����", "bn");
		LANGUAGE.put("Ų����", "no");
		LANGUAGE.put("��������", "pt");
		LANGUAGE.put("����", "ja");
		LANGUAGE.put("�����", "sv");
		LANGUAGE.put("����ά����", "sr");
		LANGUAGE.put("������", "eo");
		LANGUAGE.put("˹�工����", "sk");
		LANGUAGE.put("˹����������", "sl");
		LANGUAGE.put("˹��ϣ����", "sw");
		LANGUAGE.put("̩¬����", "te");
		LANGUAGE.put("̩�׶���", "ta");
		LANGUAGE.put("̩��", "th");
		LANGUAGE.put("��������", "tr");
		LANGUAGE.put("����ʿ��", "cy");
		LANGUAGE.put("�ڶ�����", "ur");
		LANGUAGE.put("�ڿ�����", "uk");
		LANGUAGE.put("ϣ������", "iw");
		LANGUAGE.put("ϣ����", "el");
		LANGUAGE.put("��������", "es");
		LANGUAGE.put("��������", "hu");
		LANGUAGE.put("����������", "hy");
		LANGUAGE.put("�������", "it");
		LANGUAGE.put("�������", "yi");
		LANGUAGE.put("ӡ����", "hi");
		LANGUAGE.put("ӡ����", "id");
		LANGUAGE.put("Ӣ��", "en");
		LANGUAGE.put("Խ����", "vi");
		LANGUAGE.put("���ķ���", "zh-TW");
		LANGUAGE.put("���ļ���", "zh-CN");
	}

	public static String translate(String origin) {
		if(""==origin || null==origin){
			return "";
		}
		TFactory factory = null;
		String re = "";
		try {
			factory = new TranslatorFactory();
			re = factory.get("google").trans(LANG.AUTO, LANG.ZH, origin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
}

package picPicker;

import java.util.HashMap;
import java.util.Map;

import com.lsj.trans.LANG;
import com.lsj.trans.factory.*;

public class Languages {
	private static final Map<String, String> LANGUAGE = new HashMap<String, String>();
	static {
		LANGUAGE.put("阿尔巴尼亚语", "sq");
		LANGUAGE.put("阿拉伯语", "ar");
		LANGUAGE.put("阿塞拜疆语", "az");
		LANGUAGE.put("爱尔兰语", "ga");
		LANGUAGE.put("爱沙尼亚语", "et");
		LANGUAGE.put("巴斯克语", "eu");
		LANGUAGE.put("白俄罗斯语", "be");
		LANGUAGE.put("保加利亚语", "bg");
		LANGUAGE.put("冰岛语", "is");
		LANGUAGE.put("波兰语", "pl");
		LANGUAGE.put("波斯语", "fa");
		LANGUAGE.put("布尔语", "af");
		LANGUAGE.put("南非荷兰语", "af");
		LANGUAGE.put("丹麦语", "da");
		LANGUAGE.put("德语", "de");
		LANGUAGE.put("俄语", "ru");
		LANGUAGE.put("法语", "fr");
		LANGUAGE.put("菲律宾语", "tl");
		LANGUAGE.put("芬兰语", "fi");
		LANGUAGE.put("格鲁吉亚语", "ka");
		LANGUAGE.put("古吉拉特语", "gu");
		LANGUAGE.put("海地克里奥尔语", "ht");
		LANGUAGE.put("韩语", "ko");
		LANGUAGE.put("荷兰语", "nl");
		LANGUAGE.put("加利西亚语", "gl");
		LANGUAGE.put("加泰罗尼亚语", "ca");
		LANGUAGE.put("捷克语", "cs");
		LANGUAGE.put("卡纳达语", "kn");
		LANGUAGE.put("克罗地亚语", "hr");
		LANGUAGE.put("拉丁语", "la");
		LANGUAGE.put("拉脱维亚语", "lv");
		LANGUAGE.put("老挝语", "lo");
		LANGUAGE.put("立陶宛语", "lt");
		LANGUAGE.put("罗马尼亚语", "ro");
		LANGUAGE.put("马耳他语", "mt");
		LANGUAGE.put("马来语", "ms");
		LANGUAGE.put("马其顿语", "mk");
		LANGUAGE.put("孟加拉语", "bn");
		LANGUAGE.put("挪威语", "no");
		LANGUAGE.put("葡萄牙语", "pt");
		LANGUAGE.put("日语", "ja");
		LANGUAGE.put("瑞典语", "sv");
		LANGUAGE.put("塞尔维亚语", "sr");
		LANGUAGE.put("世界语", "eo");
		LANGUAGE.put("斯洛伐克语", "sk");
		LANGUAGE.put("斯洛文尼亚语", "sl");
		LANGUAGE.put("斯瓦希里语", "sw");
		LANGUAGE.put("泰卢固语", "te");
		LANGUAGE.put("泰米尔语", "ta");
		LANGUAGE.put("泰语", "th");
		LANGUAGE.put("土耳其语", "tr");
		LANGUAGE.put("威尔士语", "cy");
		LANGUAGE.put("乌尔都语", "ur");
		LANGUAGE.put("乌克兰语", "uk");
		LANGUAGE.put("希伯来语", "iw");
		LANGUAGE.put("希腊语", "el");
		LANGUAGE.put("西班牙语", "es");
		LANGUAGE.put("匈牙利语", "hu");
		LANGUAGE.put("亚美尼亚语", "hy");
		LANGUAGE.put("意大利语", "it");
		LANGUAGE.put("意第绪语", "yi");
		LANGUAGE.put("印地语", "hi");
		LANGUAGE.put("印尼语", "id");
		LANGUAGE.put("英语", "en");
		LANGUAGE.put("越南语", "vi");
		LANGUAGE.put("中文繁体", "zh-TW");
		LANGUAGE.put("中文简体", "zh-CN");
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

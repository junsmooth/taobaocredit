package com.dj.web.credit;

import java.net.URLDecoder;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dj.entity.Credit;
import com.dj.utils.Escape;

public class CreditProcessor implements Callable<Credit> {
	private String nickCode;
	private String token;

	public CreditProcessor(String nickCode, String token) {
		super();
		this.nickCode = nickCode;
		this.token = token;
	}

	@Override
	public Credit call() throws Exception {

		Credit c = new Credit();
		c.setUserName(nickCode);
		try {
			String baseUrl = "http://www.131458.com/handler/TaobaoInfo.ashx";
			String url1 = baseUrl + "?nickCode=" + nickCode + "&token=" + token;
			Document doc = Jsoup.connect(url1).timeout(0).get();

			Element e1 = doc.select("p.inq_02_L_002").first();
			String regTime = e1.text();
			regTime = StringUtils.substringAfter(regTime, "：");
			regTime = StringUtils.substring(regTime, 0, 10);

			c.setRegisterTime(regTime);

			Elements els = doc.select("div.inq_04_L").first().children();
			for (Element e : els) {
				String txt = e.text();
				if (txt.indexOf("买家信用：") != -1) {
					txt = StringUtils.substringBetween(txt, "：", "点");
					c.setBuyerPoints(txt);
				} else if (txt.indexOf("最近一周") != -1) {
					txt = StringUtils.substringBetween(txt, "：", "点");
					c.setB_pointOfLastWeek(txt);

				} else if (txt.indexOf("最近一月") != -1) {
					txt = StringUtils.substringBetween(txt, "：", "点");
					c.setB_pointOfLastMonth(txt);
				}

			}
			String url2 = baseUrl + "?tbNickInfoJson=" + nickCode + "&token="
					+ token;
			Document doc2 = Jsoup.connect(url2).timeout(0).get();
			String txt2 = doc2.text();
			String result = Escape.unescape(txt2);
			String img = StringUtils.substringBetween(result, "src='", ".jpg");
			c.setSecurityLevel("http://www.131458.com/" + img + ".jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}

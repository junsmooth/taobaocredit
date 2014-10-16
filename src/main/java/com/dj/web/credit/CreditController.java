package com.dj.web.credit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dj.entity.Credit;

@Controller
@RequestMapping(value = "/credit")
public class CreditController {
	private static ExecutorService pool = Executors.newCachedThreadPool();

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "credit/credit";
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public @ResponseBody Object query(@RequestParam Map<String, String> map, Model model) throws InterruptedException, ExecutionException {
		List<Future> futureList=new ArrayList();
		Object o=map.get("data");
		Object obj=JSON.parse(o.toString());
		Map m=(Map) obj;
		
		List<UserToken> userList=new ArrayList();
		for(int i=0;i<m.keySet().size();i++){
			String str=m.get(i+"").toString();
			UserToken ut=null;
			try {
				ut = JSON.parseObject(str, UserToken.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			userList.add(ut);
		}
		System.out.println(userList);
		for(UserToken ut:userList){
			if(StringUtils.isBlank(ut.getNick())){
				continue;
			}
			CreditProcessor p = new CreditProcessor(ut.getNick(),ut.getToken());
			Future<Credit> futuer = pool.submit(p);
			futureList.add(futuer);
		}
//		
//		for (String key : map.keySet()) {
//			if (StringUtils.isBlank(key)) {
//				continue;
//			}
//			CreditProcessor p = new CreditProcessor(key, map.get(key));
//			Future<Credit> futuer = pool.submit(p);
//			futureList.add(futuer);
//			
//		}
		List creditList=new ArrayList();
		
		for(Future<Credit> f:futureList){
			Credit cc=f.get();
			creditList.add(cc);
		}
		
		List result=sortResult(creditList,userList);
		
		return creditList;
	}

	private List sortResult(List creditList, List<UserToken> userList) {
		List result=new ArrayList();
		for(UserToken ut:userList){
			Credit c=getCreditByNick(ut.getNick(),creditList);
			if(c!=null){
				result.add(c);
			}
		}
		
		return result;
	}

	private Credit getCreditByNick(String nick, List<Credit> creditList) {
		for(Credit c:creditList){
			if(c.getUserName().equals(nick)){
				return c;
			}
		}
		
		return null;
	}
}

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
		for (String key : map.keySet()) {
			if (StringUtils.isBlank(key)) {
				continue;
			}
			CreditProcessor p = new CreditProcessor(key, map.get(key));
			Future<Credit> futuer = pool.submit(p);
			futureList.add(futuer);
			
		}
		List creditList=new ArrayList();
		
		for(Future<Credit> f:futureList){
			Credit cc=f.get();
			creditList.add(cc);
		}
		
		return creditList;
	}
}

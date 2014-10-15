package com.dj.service.taobao;

import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JSEngine {

	public void exec() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		engine.put("age", 26);
		engine.put("noClaims", Boolean.TRUE);
//		Object result = engine
//				.eval(new FileReader("E:/ScriptEngine/test02.js"));
	}
}

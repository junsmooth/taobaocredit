package com.dj.service.taobao;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Before;
import org.junit.Test;

public class TaobaoServiceTest {

	private ScriptEngine engine;

	@Before
	public void setUp() {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("js");
	}

	@Test
	public void getToken() {
		
	}
}

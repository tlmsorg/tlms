package com.test.lua;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaTest {

	public void fromNuaSciptFile(){
		Globals globals = JsePlatform.standardGlobals();
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			is = new FileInputStream(new File("lua"+File.separator + "luaScript.lua"));
			isr = new InputStreamReader(is, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LuaValue luaValue =  globals.load(isr, "chunkname");
		luaValue.call();
		LuaValue funValue = globals.get(LuaValue.valueOf("hi"));
		funValue.invoke(new LuaValue[]{LuaValue.valueOf("brighttang")});
		
		LuaValue htableValue = globals.get(LuaValue.valueOf("hTable"));
		System.out.println(htableValue);
		
		LuaValue nilValue = LuaValue.NIL;
//		System.out.println(nilValue);
		Varargs varargs = htableValue.next(nilValue);
		LuaValue v1 = varargs.arg(1);
		System.out.println(v1.tostring());
		
		LuaValue v2 = varargs.arg(2);
		System.out.println(v2.toString());

		LuaTable luaTable = (LuaTable) CoerceLuaToJava.coerce(v2, LuaTable.class);
		
		
		try {
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fromNuaScriptString(){
		String nuaStr = "print '100'";
		Globals globals = JsePlatform.standardGlobals();
		LuaValue luaValue = globals.load(nuaStr);
		luaValue.call();
	}
	
	public static void main(String[] args) throws Exception {
		LuaTest luaTest = new LuaTest();
		luaTest.fromNuaSciptFile();
	}
}

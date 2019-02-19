package pers.clw.orderweb.command.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
		put("msg","操作成功");
		put("data","");
	}
	
	public static R error() {
		return error(500, "网络异常,请稍后重试!","");
	}
	
	public static R error(String msg) {
		return error(500, msg,"");
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	public static R error(int code, String msg,Object data) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",data);
		return r;
	}
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R wxOk(Map<String, String> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}

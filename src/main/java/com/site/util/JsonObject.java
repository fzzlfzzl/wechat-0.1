package com.site.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonObject {

	private Map<String, JsonObject> map = null;
	private ArrayList<JsonObject> list = null;
	private Object obj = null;

	public JsonObject get(String key) {
		return this.mapGet(key);
	}

	public JsonObject get(int index) {
		return this.listGet(index);
	}

	public void add(JsonObject obj) {
		this.listAdd(obj);
	}

	public void set(int value) {
		this.objAssign(value);
	}

	public void set(long value) {
		this.objAssign(value);
	}

	public void set(double value) {
		this.objAssign(value);
	}

	public void set(boolean value) {
		this.objAssign(value);
	}

	public void set(String value) {
		this.objAssign(value);
	}

	public void setNull() {
		this.objAssign(null);
	}

	/**
	 * 相对于原生数据类型来说set的方式比较特殊
	 * 
	 * @param obj
	 *            @
	 */
	public void set(JsonObject obj) {
		this.map = obj.map;
		this.list = obj.list;
		this.obj = obj.obj;
	}

	public boolean isMap() {
		try {
			assertMustMap();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean isList() {
		try {
			assertMustList();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean isObj() {
		try {
			assertMustObj();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean isNull() {
		return null == map && null == list && null == obj;
	}

	public int getLength() {
		assertMaybeList();
		if (null == list) {
			return 0;
		} else {
			return list.size();
		}
	}

	public Set<String> getKeySet() {
		assertMaybeMap();
		if (null == map) {
			return new HashSet<String>();
		} else {
			return map.keySet();
		}
	}

	public String toString() {
		return obj.toString();
	}

	public int toInt() {
		return Integer.parseInt(obj.toString());
	}

	public long toLong() {
		return Long.parseLong(obj.toString());
	}

	public double toDouble() {
		return Double.parseDouble(obj.toString());
	}

	private Object toValue() {
		if (isMap()) {
			return toMapValue();
		}
		if (isList()) {
			return toListValue();
		}
		if (isObj()) {
			return obj;
		}
		return null;
	}

	private Object toMapValue() {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		Iterator<Entry<String, JsonObject>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, JsonObject> entry = iter.next();
			String key = entry.getKey();
			JsonObject val = entry.getValue();
			ret.put(key, val.toValue());
		}
		return ret;
	}

	private Object toListValue() {
		ArrayList<Object> ret = new ArrayList<Object>();
		for (JsonObject jo : list) {
			ret.add(jo.toValue());
		}
		return ret;
	}

	public String toJsonString() {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		try {
			return mapper.writeValueAsString(toValue());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static JsonObject toJsonObject(String json) {
		if (null == json || json.length() == 0) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userData;
		try {
			userData = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		JsonObject ret = new JsonObject();
		ret.fromMapValue(userData);
		return ret;

	}

	@SuppressWarnings("unchecked")
	private void fromValue(Object obj) {
		if (obj instanceof Map) {
			fromMapValue((Map<String, Object>) obj);
		} else if (obj instanceof List) {
			fromListValue((List<Object>) obj);
		} else {
			objAssign(obj);
		}
	}

	private void fromListValue(List<Object> list) {
		for (Object item : list) {
			JsonObject obj = new JsonObject();
			obj.fromValue(item);
			listAdd(obj);
		}
	}

	private void fromMapValue(Map<String, Object> map) {
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			String key = (String) entry.getKey();
			Object val = entry.getValue();
			JsonObject obj = new JsonObject();
			obj.fromValue(val);
			mapPut(key, obj);
		}
	}

	private void mapPut(String key, JsonObject value) {
		assertMaybeMap();
		if (null == map) {
			map = new HashMap<String, JsonObject>();
		}
		map.put(key, value);
	}

	private JsonObject mapGet(String key) {
		assertMaybeMap();
		if (null == map) {
			map = new HashMap<String, JsonObject>();
		}
		JsonObject ret = map.get(key);
		if (null == ret) {
			ret = new JsonObject();
			this.mapPut(key, ret);
		}
		return ret;
	}

	private void listAdd(JsonObject value) {
		assertMaybeList();
		if (null == list) {
			list = new ArrayList<JsonObject>();
		}
		list.add(value);
	}

	private JsonObject listGet(int index) {
		assertMaybeList();
		if (null == list) {
			list = new ArrayList<JsonObject>();
		}
		if (list.size() < index) {
			throw new RuntimeException("Index Out Of Bound");
		}
		if (list.size() == index) {
			JsonObject obj = new JsonObject();
			this.listAdd(obj);
		}
		return this.list.get(index);
	}

	private void objAssign(Object value) {
		assertMaybeObj();
		obj = value;
	}

	private void assertMaybeMap() {
		if (null != list || null != obj) {
			throw new RuntimeException("Assert Maybe Map Type Fail");
		}
	}

	private void assertMustMap() {
		assertMaybeMap();
		if (null == map) {
			throw new RuntimeException("Assert Must Map Type Fail");
		}
	}

	private void assertMaybeList() {
		if (null != map || null != obj) {
			throw new RuntimeException("Assert Maybe List Type Fail");
		}
	}

	private void assertMustList() {
		assertMaybeList();
		if (null == list) {
			throw new RuntimeException("Assert Must List Type Fail");
		}
	}

	private void assertMaybeObj() {
		if (null != list || null != map) {
			throw new RuntimeException("Assert Maybe Obj Type Fail");
		}
	}

	private void assertMustObj() {
		assertMaybeObj();
		if (null == obj) {
			throw new RuntimeException("Assert Must Obj Type Fail");
		}
	}

	public Iterator<String> keyIterator() {
		assertMustMap();
		return map.keySet().iterator();
	}

	public Iterator<JsonObject> listIterator() {
		assertMustList();
		return list.iterator();
	}

	public static void main(String[] args) {
		JsonObject obj1 = new JsonObject();
		JsonObject msg = new JsonObject();
		msg.get("ecCode").set("xdja");
		obj1.get("msg").set(msg);
		System.out.println(obj1.toJsonString());
	}
}

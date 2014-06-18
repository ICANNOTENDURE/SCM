/**
 * 
 */
package com.dhcc.framework.util;

import java.text.SimpleDateFormat;

import com.dhcc.framework.common.log.logconfig.dto.logconfig.LogConfigDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * json工具类
 * 
 * @author liuyg
 * 
 */
public class JsonUtils {
	/**
	 * 对象转化为Json串
	 * 
	 * @param o
	 *            要转化成Json串的对象
	 * @return
	 */
	private static ObjectMapper mapper = new ObjectMapper().registerModule(new Hibernate4Module());
	static {
		
		
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static String toJson(Object obj) {
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return json;
	}

	/**
	 * json串转化为指定类型的对象
	 * @param str          json串
	 * @param objClass       要转化为的对象的类型
	 * @return 返回转化的对象
	 */
	public static <T> T toObject(String str, Class<T> objClass) {

		T obj = null;
		try {
			obj = mapper.readValue(str, objClass);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return obj;
	}
	
	/**
	 * json串转化为指定类型且带有泛型的对象
	 * @param str json串
	 * @param objClass  要转化为的泛型对象的类型 ,如new TypeReference<PagerModel<ListObject>>() { }一个这样的参数
	 * @return 返回转化的对象
	 */
	@SuppressWarnings({"rawtypes" })
	public static <T> T toObject(String str, TypeReference valueTypeRef) {

		T obj = null;
		try {
			obj = mapper.readValue(str, valueTypeRef);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return obj;
	}

	public static void main(String[] args) {
		LogConfigDto dto = new LogConfigDto();
		dto.initTradeAccount();
		System.out.println(dto.getTradeAccount());
		String json = JsonUtils.toJson(dto);
		dto = JsonUtils.toObject(json, LogConfigDto.class);
		System.out.println(dto.getTradeAccount());
		System.out.println(dto.getTradeAccount());
		
	}
//	public static void main(String[] args) throws Exception  {
//		PagerModel<ListObject> pm = new PagerModel<ListObject>();
//		List<ListObject> list = new ArrayList<ListObject>();
//		ListObject lo = new ListObject();
//		lo.setMyDate(new Date());
//		lo.setKeyObj("key");
//		list.add(lo);
//		ListObject lo2 = new ListObject();
//		lo2.setMyDate(new Date());
//		lo2.setKeyObj("key");
//		list.add(lo2);
//		pm.setPageData(list);
//		String str = JsonUtils.toJson(pm);
//		System.out.println(str);
//		pm = mapper.readValue(str, new TypeReference<PagerModel<ListObject>>() { });
//		System.out.println(((ListObject) pm.getPageData().get(0)).getKeyObj());
//		System.out.println( JsonUtils.toJson(pm));
//	}
}

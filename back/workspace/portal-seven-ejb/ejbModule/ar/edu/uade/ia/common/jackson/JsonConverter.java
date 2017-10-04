package ar.edu.uade.ia.common.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object convertToObject(String jsonInString,  Class clazz) throws Exception {
		return JsonConverter.mapper.readValue(jsonInString, clazz);
	}
	
	public static String convertToJson(Object object)throws Exception {
		return JsonConverter.mapper.writeValueAsString(object);
	}
}

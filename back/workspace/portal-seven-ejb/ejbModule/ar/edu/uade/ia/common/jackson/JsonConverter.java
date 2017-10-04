package ar.edu.uade.ia.common.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object convertToObject(String jsonInString,  Class clazz) throws Exception {
		return JsonConverter.mapper.readValue(jsonInString, clazz);
	}
}

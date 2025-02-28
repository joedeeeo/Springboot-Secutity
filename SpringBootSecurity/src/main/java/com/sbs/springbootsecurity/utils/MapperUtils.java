package com.sbs.springbootsecurity.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtils {

	public static <T> T convertValue(Object fromvalue,Class<T> toValueType) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(fromvalue, toValueType);
	}
	
	public static <T> List<T> convertListValue(List<?> fromValue,Class<T> toValueType) {
		List<T> listValue = new ArrayList<>();
		for(Object o : fromValue) {
			ObjectMapper objMapper = new ObjectMapper();
			T convertValue = objMapper.convertValue(o, toValueType);
			listValue.add(convertValue);
		}
		return listValue;
	}
}

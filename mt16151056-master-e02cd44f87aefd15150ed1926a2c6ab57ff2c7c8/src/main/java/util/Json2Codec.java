package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ztang
 *         generated at : Dec 30, 2014 - 9:26:48 AM
 */
public final class Json2Codec {

	private static ObjectMapper jsonMapper;

	public static ObjectMapper getJSonMapper() {
		if (jsonMapper == null) {
			jsonMapper = new ObjectMapper();
			AnnotationIntrospector annoIntrospector = new JacksonAnnotationIntrospector();
			jsonMapper.setAnnotationIntrospector(annoIntrospector);
			jsonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

			//ZTDOD: in the production, Indent_Output could be set as false
			jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			jsonMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
			jsonMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
			jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		}
		return jsonMapper;
	}



	@SuppressWarnings("unchecked")
	public static Map<String, Object> marshalToMap(Object xbean) throws IOException {
		if (xbean == null) {
			throw new IllegalArgumentException("Object to be marshalled must not be null");
		}
		return getJSonMapper().convertValue(xbean, LinkedHashMap.class);
	}

	public static byte[] marshalToByte(Object xbean) throws IOException {
		if (xbean == null) {
			throw new IllegalArgumentException("Object to be marshalled must not be null");
		}
		return getJSonMapper().writeValueAsBytes(xbean);
	}

	public static String marshal(Object xbean) throws IOException {
		if (xbean == null) {
			throw new IllegalArgumentException("Object to be marshalled must not be null");
		}
		return getJSonMapper().writeValueAsString(xbean);
	}

	public static void marshal(Object xbean, File jsonFile) throws IOException {
		if (xbean == null) {
			throw new IllegalArgumentException("Object to be marshalled must not be null");
		}
		if (jsonFile == null) {
			throw new IllegalArgumentException("Data file must not be null");
		}
		getJSonMapper().writeValue(jsonFile, xbean);
	}

	public static <T> T unmarshal(Class<T> clazz, byte[] jsonPayload) throws IOException {
		if (jsonPayload == null || jsonPayload.length < 1) {
			throw new IllegalArgumentException("JSon payload to be unmarshalled must not be empty");
		}
		return getJSonMapper().readValue(jsonPayload, clazz);
	}

	public static <T> T unmarshal(Class<T> clazz, String jsonPayload) throws IOException {
		if (jsonPayload == null || jsonPayload.length() < 1) {
			throw new IllegalArgumentException("JSon payload to be unmarshalled must not be empty");
		}
		StringReader reader = new StringReader(jsonPayload);
		return getJSonMapper().readValue(reader, clazz);
	}

	public static <T> T unmarshal(Class<T> clazz, File jsonFile) throws IOException {
		if (jsonFile == null || !jsonFile.exists()) {
			throw new IllegalArgumentException("JSon file to be unmarshalled must exist");
		}
		return getJSonMapper().readValue(jsonFile, clazz);
	}

	public static <T> T unmarshal(Class<T> clazz, Map<String, Object> pojoMap) throws IOException {
		if (pojoMap == null || pojoMap.size() < 1) {
			throw new IllegalArgumentException("Map<String, Object> to be unmarshalled must exist");
		}
		return getJSonMapper().convertValue(pojoMap, clazz);
	}
	
	public static <T> List<T> unmarshalToList(Class<T> clazz, String jsonVal) throws IOException {
		TypeFactory t = TypeFactory.defaultInstance();
		List<T> list = getJSonMapper().readValue(jsonVal, t.constructCollectionType(ArrayList.class, clazz));
		return list;
	}	

	public static <T> T unmarshal(TypeReference<T> typeReference, byte[] jsonPayload) throws IOException {
		if (jsonPayload == null || jsonPayload.length < 1) {
			throw new IllegalArgumentException("JSon payload to be unmarshalled must not be empty");
		}
		return getJSonMapper().readValue(jsonPayload, typeReference);
	}

	public static <T> T unmarshal(TypeReference<T> typeReference, String jsonPayload) throws IOException {
		if (jsonPayload == null || jsonPayload.length() < 1) {
			throw new IllegalArgumentException("JSon payload to be unmarshalled must not be empty");
		}
		StringReader reader = new StringReader(jsonPayload);
		return getJSonMapper().readValue(reader, typeReference);
	}

	public static <T> T unmarshal(TypeReference<T> typeReference, File jsonFile) throws IOException {
		if (jsonFile == null || !jsonFile.exists()) {
			throw new IllegalArgumentException("JSon file to be unmarshalled must exist");
		}
		return getJSonMapper().readValue(jsonFile, typeReference);
	}

}

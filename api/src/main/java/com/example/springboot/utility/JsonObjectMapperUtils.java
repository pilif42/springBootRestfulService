package com.example.springboot.utility;

import com.example.springboot.error.OurException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Slf4j
public class JsonObjectMapperUtils {

    public static final ObjectMapper jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /** This is what Spring MVC uses, configure MockMvc as:.setMessageConverters(jacksonMessageConverter) in test setup. */
    public static final MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter(jsonMapper);

    public static <T> T readValue(String body, Class<T> tClass) {
        try {
            return jsonMapper.readValue(body, tClass);
        } catch (Exception ex) {
            log.error("exception while rendering json", ex);
            throw new OurException(OurException.Fault.GENERIC_SERVER_SIDE_ERROR);
        }
    }
}

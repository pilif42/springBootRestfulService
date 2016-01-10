package com.example.springboot.utility;

import com.example.springboot.error.OurException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Slf4j
public class JsonObjectMapperUtils {
    /** This Object Mapper ignores unknown fields in JSON and implements CamelCase to underscore name mapping. */
    public static final ObjectMapper jsonMapper = new ObjectMapper()
        .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /** This is what Spring MVC uses, configure MockMvc as:             .setMessageConverters(jacksonMessageConverter) in test setup. */
    public static final MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter(jsonMapper);

    public static <T> T readValue(String body, Class<T> tClass) {
        try {
            return jsonMapper.readValue(body, tClass);
        } catch (Exception ex) {
            log.error("exception while redaering json", ex);
            throw new OurException(OurException.Fault.GENERIC_SERVER_SIDE_ERROR);
        }
    }
}

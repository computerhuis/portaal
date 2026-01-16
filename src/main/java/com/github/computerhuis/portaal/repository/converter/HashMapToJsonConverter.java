package com.github.computerhuis.portaal.repository.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.github.computerhuis.portaal.util.MapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = true)
public class HashMapToJsonConverter implements AttributeConverter<Map<String, String>, String> {

    private static final ObjectMapper objectMapper = MapperUtils.createJsonMapper();

    @Override
    public String convertToDatabaseColumn(final Map<String, String> value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert map to JSON string", e);
        }
    }

    @Override
    public Map<String, String> convertToEntityAttribute(final String value) {
        if (value == null || value.isEmpty()) {
            return new HashMap<>();
        }

        try {
            return objectMapper.readValue(value, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string to map", e);
        }
    }
}

package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@Service
public class ClimateDataService {

    private final DynamoDbClient dynamoDbClient;

    public ClimateDataService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public List<Map<String, Object>> getAllClimateData() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("ClimateData") // Nombre de tu tabla
                .build();
    
        ScanResponse response = dynamoDbClient.scan(scanRequest);
    
        List<Map<String, Object>> data = response.items().stream()
                .map(this::convertAttributeValueMap)
                .collect(Collectors.toList());
    
        // Seleccionar 10 registros al azar
        Collections.shuffle(data);
        return data.stream().limit(10).collect(Collectors.toList());
    }
    

    private static final Map<String, String> MONTHS_MAP = Map.ofEntries(
        Map.entry("01", "Enero"),
        Map.entry("02", "Febrero"),
        Map.entry("03", "Marzo"),
        Map.entry("04", "Abril"),
        Map.entry("05", "Mayo"),
        Map.entry("06", "Junio"),
        Map.entry("07", "Julio"),
        Map.entry("08", "Agosto"),
        Map.entry("09", "Septiembre"),
        Map.entry("10", "Octubre"),
        Map.entry("11", "Noviembre"),
        Map.entry("12", "Diciembre")
    );

    private Map<String, Object> convertAttributeValueMap(Map<String, AttributeValue> attributeValueMap) {
        return attributeValueMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            AttributeValue value = entry.getValue();
                            if ("time".equals(entry.getKey())) {
                                String[] parts = value.s().split("-");
                                String year = parts[0];
                                String month = MONTHS_MAP.getOrDefault(parts[1], parts[1]); // Usa el nombre del mes
                                Map<String, String> dateMap = new HashMap<>();
                                dateMap.put("year", year);
                                dateMap.put("month", month);
                                return dateMap;
                            }
                            if (value.s() != null) return value.s(); // String
                            if (value.n() != null) return Double.valueOf(value.n()); // Número
                            if (value.bool() != null) return value.bool(); // Boolean
                            return null; // Otros tipos no manejados
                        }
                ))
                .entrySet().stream()
                .flatMap(entry -> {
                    if ("time".equals(entry.getKey())) {
                        Map<String, Object> yearMonthMap = (Map<String, Object>) entry.getValue();
                        return yearMonthMap.entrySet().stream();
                    }
                    return Stream.of(entry);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    
    
}

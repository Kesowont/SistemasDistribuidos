package com.example.demo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClimateWebSocketHandler extends TextWebSocketHandler {

    private final ClimateDataService climateDataService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClimateWebSocketHandler(ClimateDataService climateDataService) {
        this.climateDataService = climateDataService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        new Thread(() -> {
            try {
                while (session.isOpen()) {
                    // Obtener datos reales desde DynamoDB
                    List<Map<String, Object>> data = climateDataService.getAllClimateData();

                    // Asignar colores basados en las condiciones
                    for (Map<String, Object> record : data) {
                        double temperature = (double) record.get("temperature");
                        double precipitation = (double) record.get("precipitation");

                        record.put("temperatureColor", getTemperatureColor(temperature));
                        record.put("precipitationColor", getPrecipitationColor(precipitation));
                    }

                    // Enviar datos en formato JSON al cliente
                    String json = objectMapper.writeValueAsString(data);
                    session.sendMessage(new TextMessage(json));

                    // Esperar un minuto antes de enviar datos nuevamente
                    Thread.sleep(60000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String getTemperatureColor(double temperature) {
        if (temperature < 10) return "blue"; // Baja temperatura
        if (temperature <= 25) return "green"; // Temperatura normal
        return "red"; // Alta temperatura
    }

    private String getPrecipitationColor(double precipitation) {
        if (precipitation < 50) return "yellow"; // Baja precipitación
        if (precipitation <= 100) return "orange"; // Precipitación normal
        return "purple"; // Alta precipitación
    }
}

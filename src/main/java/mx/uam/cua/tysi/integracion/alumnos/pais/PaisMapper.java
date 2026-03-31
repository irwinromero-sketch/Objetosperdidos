package mx.uam.cua.tysi.integracion.alumnos.pais;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class PaisMapper {

    public PaisDTO fromCountriesJson(JsonNode json) {
        JsonNode pais = json.get(0);
        PaisDTO dto = new PaisDTO();

        dto.setNombre(pais.path("name").path("common").asText());
        dto.setCapital(pais.path("capital").get(0).asText());
        dto.setRegion(pais.path("region").asText());
        dto.setArea(pais.path("area").asDouble());
        dto.setBanderaUrl(pais.path("flags").path("png").asText());
        dto.setMapaUrl(pais.path("maps").path("openStreetMaps").asText());

        JsonNode latlng = pais.path("latlng");
        dto.setLatitud(latlng.get(0).asDouble());
        dto.setLongitud(latlng.get(1).asDouble());

        JsonNode monedas = pais.path("currencies");
        if (monedas.fields().hasNext()) {
            String codigoMoneda = monedas.fields().next().getKey();
            dto.setMoneda(codigoMoneda);
        }

        return dto;
    }

    public void agregarClima(PaisDTO dto, JsonNode json) {
        JsonNode current = json.path("current");
        dto.setTemperatura(current.path("temperature_2m").asDouble());
        dto.setHumedad(current.path("relative_humidity_2m").asDouble());

        int weatherCode = current.path("weather_code").asInt();
        dto.setClima(interpretarClima(weatherCode));
    }

    public void agregarCrypto(PaisDTO dto, JsonNode json) {
        dto.setBitcoinEnMxn(json.path("bitcoin").path("mxn").asDouble());
        dto.setBitcoinEnUsd(json.path("bitcoin").path("usd").asDouble());

        // 1 USD en MXN usando el precio de bitcoin como referencia
        if (dto.getBitcoinEnUsd() > 0) {
            dto.setDolarEnMxn(dto.getBitcoinEnMxn() / dto.getBitcoinEnUsd());
        }
    }

    private String interpretarClima(int code) {
        if (code == 0) return "Despejado";
        if (code <= 3) return "Parcialmente nublado";
        if (code <= 48) return "Nublado o con niebla";
        if (code <= 67) return "Lluvia";
        if (code <= 77) return "Nieve";
        if (code <= 82) return "Chubascos";
        if (code <= 99) return "Tormenta";
        return "Desconocido";
    }
}
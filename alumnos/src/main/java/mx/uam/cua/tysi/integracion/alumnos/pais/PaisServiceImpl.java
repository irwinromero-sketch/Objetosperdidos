package mx.uam.cua.tysi.integracion.alumnos.pais;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaisServiceImpl implements PaisService {

    private final WebClient webClient;
    private final PaisMapper paisMapper;

    public PaisServiceImpl(PaisMapper paisMapper) {
        this.webClient = WebClient.create();
        this.paisMapper = paisMapper;
    }

    @Override
    public PaisDTO obtenerInfoPais(String nombre) {

        // 1. REST Countries
        JsonNode countriesJson = webClient.get()
                .uri("https://restcountries.com/v3.1/name/" + nombre)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        PaisDTO dto = paisMapper.fromCountriesJson(countriesJson);

        // 2. Open Meteo
        JsonNode meteoJson = webClient.get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude=" + dto.getLatitud()
                        + "&longitude=" + dto.getLongitud()
                        + "&current=temperature_2m,relative_humidity_2m,weather_code")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        paisMapper.agregarClima(dto, meteoJson);

        // 3. CoinGecko
        JsonNode cryptoJson = webClient.get()
                .uri("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=mxn,usd")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        paisMapper.agregarCrypto(dto, cryptoJson);

        return dto;
    }
}
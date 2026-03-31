package mx.uam.cua.tysi.integracion.alumnos.pais;

public class PaisDTO {

    // REST Countries
    private String nombre;
    private String capital;
    private String region;
    private double area;
    private String banderaUrl;
    private double latitud;
    private double longitud;
    private String moneda;
    private String mapaUrl;

    // Open Meteo
    private double temperatura;
    private double humedad;
    private String clima;

    // CoinGecko
    private double bitcoinEnMxn;
    private double bitcoinEnUsd;
    private double dolarEnMxn;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public String getBanderaUrl() { return banderaUrl; }
    public void setBanderaUrl(String banderaUrl) { this.banderaUrl = banderaUrl; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public String getMapaUrl() { return mapaUrl; }
    public void setMapaUrl(String mapaUrl) { this.mapaUrl = mapaUrl; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public double getHumedad() { return humedad; }
    public void setHumedad(double humedad) { this.humedad = humedad; }

    public String getClima() { return clima; }
    public void setClima(String clima) { this.clima = clima; }

    public double getBitcoinEnMxn() { return bitcoinEnMxn; }
    public void setBitcoinEnMxn(double bitcoinEnMxn) { this.bitcoinEnMxn = bitcoinEnMxn; }

    public double getBitcoinEnUsd() { return bitcoinEnUsd; }
    public void setBitcoinEnUsd(double bitcoinEnUsd) { this.bitcoinEnUsd = bitcoinEnUsd; }

    public double getDolarEnMxn() { return dolarEnMxn; }
    public void setDolarEnMxn(double dolarEnMxn) { this.dolarEnMxn = dolarEnMxn; }
}
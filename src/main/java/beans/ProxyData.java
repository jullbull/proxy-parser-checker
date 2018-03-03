package beans;


public class ProxyData {
    String host;
    int port;
    String country;
    String city;
    int zip;


    public ProxyData() {
    }


    public ProxyData(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ProxyData(String host, int port, String country, String city, int zip) {
        this.host = host;
        this.port = port;
        this.country = country;
        this.city = city;
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

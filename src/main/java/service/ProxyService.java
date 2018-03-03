package service;


import beans.ProxyData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;


public class ProxyService {


    private Set<ProxyData> details = new HashSet<>();

    private Set<ProxyData> proxyAlive = new HashSet<>();


    public void checkStatusCode(Set<String> hostSet, Set<String> portSet) throws IOException {

        for (String host : hostSet) {
            for (String portString : portSet) {
                int port = Integer.parseInt(portString);

                try {

                    InetAddress addr = InetAddress.getByName(host);
                    if (addr.isReachable(1000) == true) {
                        HttpClient client = new HttpClient();
                        client.getHostConfiguration().setProxy(host, port);
                        HttpMethod methodGet = new GetMethod("https://duckduckgo.com");
                        methodGet.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                                new DefaultHttpMethodRetryHandler(0, false));
                        int status = client.executeMethod(methodGet);
                        if (status == 200) {

                            proxyAlive.add(new ProxyData(host, port));
                            continue;
                        }

                    }
                } catch (IOException e) {
                    continue;
                }
            }
        }

    }


    private String getJsonFormat(String host) throws IOException {


        String url = "http://ip-api.com/json/" + host + "?lang=en";


        HttpClient client = new HttpClient();
        HttpMethod get = new GetMethod(url);
        client.executeMethod(get);

        String response = getResponseAsString(get.getResponseBodyAsStream());
        return response;


    }


    public Set<ProxyData> convert(String host, int port) throws IOException {


        ObjectMapper mapper = new ObjectMapper();


        JsonNode rootNode = mapper.readTree(getJsonFormat(host));
        String country = rootNode.path("country").asText();
        String city = rootNode.path("city").asText();
        int zip = rootNode.path("zip").asInt();

        details.add(new ProxyData(host, port, country, city, zip));
        return details;


    }


    private String getResponseAsString(InputStream inputStream) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public Set<ProxyData> getDetails() {
        return details;
    }


    public Set<ProxyData> getProxyAlive() {
        return proxyAlive;
    }

}

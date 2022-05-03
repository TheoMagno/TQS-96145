package tqs5.hw1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private String login = "covid-193.p.rapidapi.com";
    private String key = "395b307781msh862decfcdba0d35p1bc91fjsn7cbce7a8282f";
    private String apiHost = "X-RapidAPI-Host";
    private String apiKey = "X-RapidAPI-Key";

    public String getAllCountries() throws NullPointerException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/countries"))
                .header(apiHost, login)
                .header(apiKey, key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String getCountries(String country) throws NullPointerException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/countries?search="+country))
                .header(apiHost, login)
                .header(apiKey, key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String getCountryStats(String country) throws NullPointerException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/statistics?country="+country))
                .header(apiHost, login)
                .header(apiKey, key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}

package nttdata.com.bootcampbc48.clientcompanyaccount.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientService {

    private WebClient webClient;
    private final WebClient.Builder webBuilder;

    public WebClientService(WebClient.Builder webBuilder) {
        this.webBuilder = webBuilder;
        this.webClient = null;
    }

    public WebClient getWebClient() {
        if (webClient == null) {
            this.webClient = webBuilder
                    .baseUrl("http://localhost:8085/client/company")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }
        return webClient;
    }

    public WebClient getWebClient(String message) {
        System.out.println(message);
        return getWebClient();
    }

}

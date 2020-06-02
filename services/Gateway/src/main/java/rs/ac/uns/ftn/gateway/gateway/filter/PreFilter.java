package rs.ac.uns.ftn.gateway.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class PreFilter extends AbstractGatewayFilterFactory<PreFilter.Config> {

    public PreFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("Request header is: " + exchange.getRequest().getHeaders());

            if(exchange.getRequest().getHeaders().get("Authorization") == null) {
                return chain.filter(exchange);
            }

            String authorizationHeader = (Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization"))).get(0);

            String token = authorizationHeader.substring(7);

            RestTemplate restTemplate = new RestTemplate();
            String username = restTemplate.getForObject("http://localhost:8081/auth/username/{token}", String.class, token);
            String role = restTemplate.getForObject("http://localhost:8081/auth/role/{token}", String.class, token);

            System.out.println("Username is:" + username);

            ServerHttpRequest request = exchange.getRequest().mutate().header("Username", username).header("Role", role).build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

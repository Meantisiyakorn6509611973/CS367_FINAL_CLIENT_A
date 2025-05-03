package dev.client.bakebox_client;

import dev.client.bakebox_client.model.Box;
import dev.client.bakebox_client.model.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BakeboxClientApplication {

    private static final String BASE_URL = "http://172.20.10.2:8080/api";

    public static void main(String[] args) {
        SpringApplication.run(BakeboxClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner runTests(RestTemplate restTemplate) {
        return args -> {
            // A-TC01
            System.out.println("\n=== [A-TC01] Create a new box with full item list (valid) ===");
            Box newBox = new Box("Japanese Snack Box", 59, List.of(
                    new Item("Tokyo Banana", 85, 4),
                    new Item("Matcha Pocky", 45, 6),
                    new Item("Yuzu Citrus Candy", 50, 5),
                    new Item("Shiroi Koibito Cookies", 90, 3),
                    new Item("Ume Plum Rice Crackers", 65, 4)
            ));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Box> tc01Request = new HttpEntity<>(newBox, headers);
            ResponseEntity<Box> tc01Response = restTemplate.postForEntity(BASE_URL + "/boxes", tc01Request, Box.class);
            System.out.println(tc01Response.getBody());

            // A-TC02
            System.out.println("\n=== [A-TC02] Create a box with empty item list ===");
            Box emptyBox = new Box("Empty Box", 10, List.of());
            HttpEntity<Box> tc02Request = new HttpEntity<>(emptyBox, headers);
            try {
                ResponseEntity<Box> tc02Response = restTemplate.postForEntity(BASE_URL + "/boxes", tc02Request, Box.class);
                System.out.println(tc02Response.getBody());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // A-TC03
            System.out.println("\n=== [A-TC03] Update and delete items with valid actions ===");
            List<Map<String, Object>> actions = List.of(
                    Map.of("itemId", 3, "action", "delete"),
                    Map.of("itemId", 4, "action", "update", "deltaAmount", -2),
                    Map.of("itemId", 5, "action", "update", "deltaAmount", 1000)
            );
            HttpEntity<List<Map<String, Object>>> tc03Request = new HttpEntity<>(actions, headers);
            ResponseEntity<String> tc03Response = restTemplate.exchange(BASE_URL + "/items/quantity", HttpMethod.PUT, tc03Request, String.class);
            System.out.println(tc03Response.getBody());

            // A-TC04
            System.out.println("\n=== [A-TC04] Update item with invalid action keyword ===");
            List<Map<String, Object>> invalidAction = List.of(
                    Map.of("itemId", 5, "action", "explode")
            );
            HttpEntity<List<Map<String, Object>>> tc04Request = new HttpEntity<>(invalidAction, headers);
            try {
                ResponseEntity<String> tc04Response = restTemplate.exchange(BASE_URL + "/items/quantity", HttpMethod.PUT, tc04Request, String.class);
                System.out.println(tc04Response.getBody());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // A-TC05
            System.out.println("\n=== [A-TC05] Update non-existent itemId ===");
            List<Map<String, Object>> nonexistentItem = List.of(
                    Map.of("itemId", 9999, "action", "update", "deltaAmount", 2)
            );
            HttpEntity<List<Map<String, Object>>> tc05Request = new HttpEntity<>(nonexistentItem, headers);
            try {
                ResponseEntity<String> tc05Response = restTemplate.exchange(BASE_URL + "/items/quantity", HttpMethod.PUT, tc05Request, String.class);
                System.out.println(tc05Response.getBody());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // A-TC06
            System.out.println("\n=== [A-TC06] Delete box by valid ID ===");
            try {
                ResponseEntity<String> tc06Response = restTemplate.exchange(BASE_URL + "/boxes/1", HttpMethod.DELETE, null, String.class);
                System.out.println(tc06Response.getBody());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // A-TC07
            System.out.println("\n=== [A-TC07] Delete non-existent box by ID ===");
            try {
                ResponseEntity<String> tc07Response = restTemplate.exchange(BASE_URL + "/boxes/9999", HttpMethod.DELETE, null, String.class);
                System.out.println(tc07Response.getBody());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // A-TC08
            System.out.println("\n=== [A-TC08] Delete with invalid ID type (string instead of number) ===");
            try {
                ResponseEntity<String> tc08Response = restTemplate.exchange(BASE_URL + "/boxes/abc", HttpMethod.DELETE, null, String.class);
                System.out.println(tc08Response.getBody());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        };
    }
}

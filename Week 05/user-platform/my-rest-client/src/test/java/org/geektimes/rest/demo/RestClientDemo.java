package org.geektimes.rest.demo;

import org.geektimes.rest.domain.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClientDemo {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
//        Response response = client
//                .target("http://127.0.0.1:8080/hello/world")      // WebTarget
//                .request() // Invocation.Builder
//                .get();                                     //  Response

        User user = new User();
        user.setId(0L);
        user.setName("test");
        user.setPassword("123");
        user.setPhoneNumber("18600897890");

        Response response = client
                .target("http://127.0.0.1:8080/hello/world")      // WebTarget
                .request() // Invocation.Builder
                .post(Entity.json(user));

        String content = response.readEntity(String.class);

        System.out.println(content);

    }
}

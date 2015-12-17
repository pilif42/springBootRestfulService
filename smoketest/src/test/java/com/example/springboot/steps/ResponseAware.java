package com.example.springboot.steps;

import com.example.springboot.util.World;
import org.apache.commons.io.IOUtils;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;

public class ResponseAware {

    private World world;

    // For processing a response
    private String body;
    private int status;
    private CookieStore cookieStore = new BasicCookieStore();

    public ResponseAware(final World world) {
        this.world = world;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public void invokeDefaultHealthcheckEndpoint() throws IOException, AuthenticationException {
        final String url = "/healthcheck";
        invokeGet(world.getEndpoint(url));
    }

    public void invokeGreetingEndpointWithName(String name) throws IOException, AuthenticationException {
        final String url = String.format("/greeting?name=%s", name);
        invokeGet(world.getEndpoint(url));
    }

    private void invokeGet(final String endpoint) throws IOException, AuthenticationException {
        executeRequest(new HttpGet(URI.create(endpoint)));
    }

    private void invokePost(final String endpoint, String data, ContentType contentType) throws IOException, AuthenticationException {
        final HttpPost post = new HttpPost(URI.create(endpoint));
        post.setEntity(new StringEntity(data, contentType == null ? ContentType.DEFAULT_TEXT : contentType));
        executeRequest(post);
    }

    private void executeRequest(HttpUriRequest request) throws AuthenticationException, IOException {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        try {
            System.out.format("----------------------------- Request ----------------------------\n%s\n----------------------------------------------------------------\n", request.getRequestLine().toString());
            client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
            response = client.execute(request);

            body = IOUtils.toString(response.getEntity().getContent());
            System.out.format("the body is: " + body);

            status = response.getStatusLine().getStatusCode();
            System.out.format("----------------------------- Response ----------------------------\n%s\n----------------------------------------------------------------\n", body);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
    }
}

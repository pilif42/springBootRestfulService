package com.example.springboot.steps;

import com.example.springboot.util.World;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class ResponseAware {

    private World world;

    // For building requests
    Properties properties = new Properties();

    // If present will be munged into request headers for all requests
    private String xAnId;

    private String basicAuthUsername;
    private String basicAuthPassword;

    private String googleReceipt;

    // For processing a response
    private String body;
    private int status;
    private CookieStore cookieStore = new BasicCookieStore();

    public ResponseAware(final World world) {
        this.world = world;
    }

    public void setXAndId(final String xAnId) { this.xAnId = xAnId; }

    public void setGoogleReceipt(final String googleReceipt) {
        this.googleReceipt = googleReceipt;
    }

    public void postToGreetingEndpoint() throws IOException, AuthenticationException {
        invokePost(world.getEndpoint("/greeting"), googleReceipt, ContentType.APPLICATION_JSON);
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

    public void invokeCustomerEndpointWithId(int id) throws IOException, AuthenticationException {
        final String url = String.format("/customer?id=%s", id);
        invokeGet(world.getEndpoint(url));
    }

    public void invokeProductEndpointWithId(int id) throws IOException, AuthenticationException {
        final String url = String.format("/product?id=%s", id);
        invokeGet(world.getEndpoint(url));
    }

    public void invokeGrabJsonAsIsEndpointWithId() throws IOException, AuthenticationException {
        final String url = String.format("/takejsonin");
        invokeGet(world.getEndpoint(url));
    }

    public void enableBasicAuth(String username, String password) {
        basicAuthUsername = username;
        basicAuthPassword = password;
    }

    public void addRequestProperty(final String name, final String value) {
        properties.setProperty(name, value);
    }

    public void invokePostCustomerEndpoint() throws IOException, AuthenticationException {
        invokeJsonPost(world.getEndpoint("/customer"));
    }

    public void invokePostGrabJsonAsIsEndpoint() throws IOException, AuthenticationException {
        invokeJsonPost(world.getEndpoint("/takejsonin"));
    }

    protected StringEntity propertiesToJsonEntity(final Properties properties) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        StringEntity entity = new StringEntity(om.writeValueAsString(properties), ContentType.APPLICATION_JSON);
        return entity;
    }

    private void invokeGet(final String endpoint) throws IOException, AuthenticationException {
        executeRequest(new HttpGet(URI.create(endpoint)));
    }

    private void invokePost(final String endpoint, String data, ContentType contentType) throws IOException, AuthenticationException {
        final HttpPost post = new HttpPost(URI.create(endpoint));
        post.setEntity(new StringEntity(data, contentType == null ? ContentType.DEFAULT_TEXT : contentType));
        executeRequest(post);
    }

    private void invokeJsonPost(final String endpoint) throws IOException, AuthenticationException {
        final HttpPost post = new HttpPost(URI.create(endpoint));
        post.setEntity(propertiesToJsonEntity(properties));
        executeRequest(post);
    }

    private void executeRequest(HttpUriRequest request) throws AuthenticationException, IOException {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;

        try {
            applyBasicAuth(request);
            addRequestHeaders(request);

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

    /**
     * If basic authentication credentials have been provided for this invocation then apply them to the request by
     * adding an appropriate header.
     *
     * @param request
     */
    private void applyBasicAuth(final HttpRequest request) throws AuthenticationException {
        if (basicAuthUsername != null) {
            final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(basicAuthUsername, basicAuthPassword);
            request.addHeader(new BasicScheme().authenticate(credentials, request, null));
        }
    }

    private void addRequestHeaders(final HttpRequest request) {
        if(xAnId != null) {
            request.addHeader("X-An-Id", xAnId);
        }
    }
}

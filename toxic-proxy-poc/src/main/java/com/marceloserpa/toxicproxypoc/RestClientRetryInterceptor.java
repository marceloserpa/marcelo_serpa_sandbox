package com.marceloserpa.toxicproxypoc;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;

public class RestClientRetryInterceptor implements ClientHttpRequestInterceptor {

    private final int maxAttempts;
    private final long backoffMillis;

    public RestClientRetryInterceptor(int maxAttempts, long backoffMillis) {
        this.maxAttempts = maxAttempts;
        this.backoffMillis = backoffMillis;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        int attempts = 0;

        while (true) {
            try {
                attempts++;
                // Execute request
                return execution.execute(request, body);
            } catch (IOException | ResourceAccessException e) {
                if (attempts >= maxAttempts) {
                    throw e;
                }
                System.out.printf("Request failed (attempt %d/%d). Retrying in %dms...%n", attempts, maxAttempts, backoffMillis);

                try {
                    Thread.sleep(backoffMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Retry interrupted", ie);
                }
            }
        }
    }
}

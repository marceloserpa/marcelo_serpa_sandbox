package com.marceloserpa.toxicproxypoc;

import eu.rekawek.toxiproxy.Proxy;
import org.junit.jupiter.api.BeforeAll;
import org.mockserver.client.MockServerClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.ToxiproxyContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;

@SpringBootTest
@Testcontainers
public class AbstractIntegrationTest {

    protected static final Network network = Network.newNetwork();

    protected static final GenericContainer<?> redis =
            new GenericContainer<>("redis:7.2")
                    .withExposedPorts(6379)
                    .withNetwork(network);

    protected static final MockServerContainer mockServer =
            new MockServerContainer().withNetwork(network);

    protected static final ToxiproxyContainer toxiproxy =
            new ToxiproxyContainer("ghcr.io/shopify/toxiproxy:2.12.0")
                    .withNetwork(network);

    protected static ToxiproxyContainer.ContainerProxy starwarsApiProxy;
    protected static ToxiproxyContainer.ContainerProxy redisProxy;

    protected static MockServerClient mockServerClient;

    @BeforeAll
    static void setUp() throws IOException {
        mockServer.start();
        toxiproxy.start();
        redis.start();

        starwarsApiProxy = toxiproxy.getProxy(mockServer, MockServerContainer.PORT);
        redisProxy = toxiproxy.getProxy(redis, 6379) ;

        mockServerClient = new MockServerClient(
                mockServer.getHost(),
                mockServer.getServerPort());
    }
}


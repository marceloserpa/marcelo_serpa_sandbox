package springboot.api.simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.util.List;
import java.util.Map;


public class PersonAPISimulation extends Simulation {

    FeederBuilder<String> feeder = csv("people.csv").random();

    ScenarioBuilder create = scenario("Post Request with Feeder").feed(feeder).exec(
        http("Create")
            .post("/person")
            .header("Content-Type", "application/json")
            .body(StringBody("{\"name\": \"#{name}\", \"lastname\":  \"#{lastname}\" }"))
            .check(bodyString().saveAs("requestBody"))
            .check(status().is(200)),
        pause(1)
    ).exec(session -> {
        System.out.println("Request Body: " + session.getString("requestBody"));
        return session;
    });

    FeederBuilder<Object> idFeeder2 = listFeeder(List.of(
        Map.of("id", "1"),
        Map.of("id", "2"),
        Map.of("id", "3")
    )).circular();

    ScenarioBuilder getusers = scenario("Get users").feed(idFeeder2).exec(
            http("Get Person")
                    .get("/person/#{id}")
                    .check(status().is(200)),
            pause(1)
    );

    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0");
    {
        setUp(
            create.injectOpen(constantUsersPerSec(10).during(20)),
            getusers.injectOpen(
                    constantUsersPerSec(500).during(60))
        ).protocols(httpProtocol);
    }


}

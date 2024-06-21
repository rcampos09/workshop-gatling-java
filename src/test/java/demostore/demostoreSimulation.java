package demostore;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class demostoreSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://demostore.gatling.io")
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
            .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

    FeederBuilder<String>  usersFeeder = csv("users.csv").random();

    // Define actions as methods

    ChainBuilder HomePage =
            exec(
                    http("GET - HomePage")
                            .get("/")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder LoginPage =
            exec(
                    http("GET - Login")
                            .get("/login")
                            .check(status().is(200))
                            .check(
                                    css("meta[name='_csrf']", "content").saveAs("csrfToken")
                            ),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder LoginPageUser =
            exec(
                    http("POST - LoginPageUser")
                            .post("/login")
                            .formParam("_csrf", "#{csrfToken}")
                            .formParam("username", "#{username}")
                            .formParam("password", "#{password}")
                            .check(status().in(302, 200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder Category =
            exec(
                    http("GET - Category All")
                            .get("/category/all")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder Product =
            exec(
                    http("GET - Product")
                            .get("/product/bright-yellow-glasses")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();
    ChainBuilder AddProductCart =
            exec(
                    http("GET - Add Product Cart")
                            .get("/cart/add/20")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder CartView =
            exec(
                    http("GET - Cart View")
                            .get("/cart/view")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder AddCartView =
            exec(
                    http("GET - Add Cart View")
                            .get("/cart/add/20?cartPage=true")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder CartCheckout =
            exec(
                    http("GET - Cart Checkout")
                            .get("/cart/checkout")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    ChainBuilder CheckoutConfirmation =
            exec(
                    http("GET - Checkout Confirmation")
                            .get("/cart/checkoutConfirmation")
                            .check(status().is(200)),
                    pause(5, 10)
            )
                    .exitHereIfFailed();

    // Define the Scenarios
    private ScenarioBuilder scnFlow = scenario("Demo Store Simulation")
            .exec(
                    feed(usersFeeder),
                    HomePage,
                    LoginPage,
                    LoginPageUser,
                    HomePage,
                    Category,
                    Product,
                    AddProductCart,
                    CartView,
                    AddCartView,
                    CartCheckout,
                    CheckoutConfirmation
            );

    {
        setUp(scnFlow.injectOpen(
                rampUsers(60).during(60)
                )
        ).protocols(httpProtocol);
    }
}

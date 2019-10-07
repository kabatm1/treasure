package treasure

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class TreasureControllerTest extends Specification {


    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    void "should result with found result"() {
        given:
        HttpRequest<String> request = HttpRequest.GET("/treasure/11");

        when:
        String body = client.toBlocking().retrieve(request);

        then:
        body == "[11, 55, 15, 21, 44, 32, 13, 25, 43]"
    }


    void "should result bad request when out of scope input"() {
        given:
        HttpRequest<String> request = HttpRequest.GET("/treasure/00");

        when:
        HttpResponse response = client.toBlocking().exchange(request);

        then:
        def ex = thrown(HttpClientResponseException)
        ex.status == HttpStatus.BAD_REQUEST
        ex.message == "startCell: value out of matrix scope"
    }

    void "should result bad request when not integer input"() {
        given:
        HttpRequest<String> request = HttpRequest.GET("/treasure/bad");

        when:
        HttpResponse response = client.toBlocking().exchange(request);

        then:
        def ex = thrown(HttpClientResponseException)
        ex.status == HttpStatus.BAD_REQUEST
        ex.message == "Failed to convert argument [startCell] for value [bad] due to: For input string: \"bad\""
    }

}


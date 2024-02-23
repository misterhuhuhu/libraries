package blockingnonblocking;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Slf4j
public class BlockingClientUnitTest {
    private static final String REQUESTED_RESOURCE = "/test.json";
    
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    
    @Before
    public void setup() {
        stubFor(get(urlEqualTo(REQUESTED_RESOURCE)).willReturn(aResponse()
                                                                       .withStatus(200)
                                                                       .withBody("{ \"response\" : \"It worked!\" }\r\n\r\n")));
    }
    
    @Test
    public void givenJavaIOSocket_whenReadingAndWritingWithStreams_thenSuccess() throws IOException {
        // given an IO socket and somewhere to store our result
        log.info(String.valueOf(wireMockRule.port()));
        Socket socket = new Socket("localhost", wireMockRule.port());
        StringBuilder ourStore = new StringBuilder();
        
        // when we write and read (using try-with-resources so our resources are auto-closed)
        try (
                InputStream serverInput = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(serverInput));
                OutputStream clientOutput = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientOutput))
        ) {
            writer.print("GET " + REQUESTED_RESOURCE + " HTTP/1.0\r\n\r\n"); //等于 get  http://localhost:7087/test.json
            writer.flush(); // important - without this the request is never sent, and the test will hang on readLine()
            
            for (String line; (line = reader.readLine()) != null; ) {
                ourStore.append(line);
                ourStore.append(System.lineSeparator());
            }
        }
        
        log.info(ourStore
                         .toString());
    }
}
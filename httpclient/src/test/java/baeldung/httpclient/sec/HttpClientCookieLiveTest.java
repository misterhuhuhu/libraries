package baeldung.httpclient.sec;


import baeldung.handler.CustomHttpClientResponseHandler;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.protocol.BasicHttpContext;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * 设置cookie
 */
class HttpClientCookieLiveTest {

    private static final Logger logger =  LoggerFactory.getLogger(HttpClientCookieLiveTest.class);
    @Test
    final void whenSettingCookiesOnARequest_thenCorrect() throws IOException {
        final HttpGet request = new HttpGet("http://www.github.com");
        request.setHeader("Cookie", "JSESSIONID=1234");
        try (CloseableHttpClient client = HttpClients.createDefault();

            CloseableHttpResponse response = (CloseableHttpResponse) client
                .execute(request, new CustomHttpClientResponseHandler())) {
            logger.info(Integer.toString(response.getCode()));
        }
    }

    @Test
    final void whenSettingCookiesOnTheHttpClient_thenCookieSentCorrectly() throws IOException {
        final BasicCookieStore cookieStore = new BasicCookieStore();
        final BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
        cookie.setDomain(".github.com");
        cookie.setAttribute("domain", "true");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        final HttpGet request = new HttpGet("http://www.github.com");
        cookieStore.getCookies().forEach(k-> logger.info("{}::{}",k.getName(),k.getValue()));
        try (CloseableHttpClient client = HttpClientBuilder.create()
            .setDefaultCookieStore(cookieStore)
            .build();

            CloseableHttpResponse response = (CloseableHttpResponse) client
                .execute(request, new CustomHttpClientResponseHandler())) {
            Header[] headers = response.getHeaders();
            for (int i = 0; i < headers.length; i++) {
                logger.info("{}::{}",headers[i].getName(),headers[i].getValue());
            }
           
            assertThat(response.getCode(), equalTo(200));
        }

    }

    @Test
    final void whenSettingCookiesOnTheRequest_thenCookieSentCorrectly() throws IOException {
        final BasicCookieStore cookieStore = new BasicCookieStore();
        final BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
        cookie.setDomain(".github.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        final HttpGet request = new HttpGet("http://www.github.com");
        final HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        // localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore); // before 4.3

        try (CloseableHttpClient client = HttpClientBuilder.create().build();

            CloseableHttpResponse response = (CloseableHttpResponse) client
                .execute(request, localContext, new CustomHttpClientResponseHandler())) {
            assertThat(response.getCode(), equalTo(200));
        }
    }

}

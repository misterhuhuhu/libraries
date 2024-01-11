package baeldung.handler;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomHttpClientResponseHandler implements HttpClientResponseHandler<ClassicHttpResponse> {
    private static final Logger logger = LoggerFactory.getLogger(CustomHttpClientResponseHandler.class);
    private static String entity;
    
    @Override
    public ClassicHttpResponse handleResponse(ClassicHttpResponse response) throws IOException, ParseException {
        CustomHttpClientResponseHandler.entity = EntityUtils.toString(response.getEntity());
//        logger.info(CustomHttpClientResponseHandler.entity);
        return response;
    }
    
    public static String getEntity() {
        return entity;
    }
}
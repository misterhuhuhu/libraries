package blockingnonblocking;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertTrue;

public class NonBlockingClientUnitTest {
    private String REQUESTED_RESOURCE = "/test.json";

    @Rule public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Before
    public void setup() {
        stubFor(get(urlEqualTo(REQUESTED_RESOURCE)).willReturn(aResponse()
          .withStatus(200)
          .withBody("{ \"response\" : \"It worked!\" }")));
    }
    
    /**
     * 使用大内存
     * @throws IOException
     */
    @Test
    public void givenJavaNIOSocketChannel_whenReadingAndWritingWithBuffers_thenSuccess() throws IOException {
        // given a NIO SocketChannel and a charset
        InetSocketAddress address = new InetSocketAddress("localhost", wireMockRule.port());
        SocketChannel socketChannel = SocketChannel.open(address);
        

        // when we write and read using buffers
        socketChannel.write(StandardCharsets.UTF_8.encode(CharBuffer.wrap("GET " + REQUESTED_RESOURCE + " HTTP/1.0\r\n\r\n")));

        ByteBuffer byteBuffer = ByteBuffer.allocate(8192); // or allocateDirect if we need direct memory access
        CharBuffer charBuffer = CharBuffer.allocate(8192);
        CharsetDecoder charsetDecoder = StandardCharsets.UTF_8.newDecoder();
        StringBuilder ourStore = new StringBuilder();
        while (socketChannel.read(byteBuffer) != -1 || byteBuffer.position() > 0) {
            byteBuffer.flip();
            storeBufferContents(byteBuffer, charBuffer, charsetDecoder, ourStore);
            byteBuffer.compact();
        }
        socketChannel.close();

        // then we read and saved our data
        assertTrue(ourStore
          .toString()
          .contains("It worked!"));
    }
    
    /**
     * 使用小内存
     * @throws IOException
     */
    @Test
    public void givenJavaNIOSocketChannel_whenReadingAndWritingWithSmallBuffers_thenSuccess() throws IOException {
        // given a NIO SocketChannel and a charset
        InetSocketAddress address = new InetSocketAddress("localhost", wireMockRule.port());
        SocketChannel socketChannel = SocketChannel.open(address);//打开 SocketChannel

        // when we write and read using buffers that are too small for our message
        // UTF-8 字符集来编码消息
        socketChannel.write(StandardCharsets.UTF_8.encode(CharBuffer.wrap("GET " + REQUESTED_RESOURCE + " HTTP/1.0\r\n\r\n")));

        ByteBuffer byteBuffer = ByteBuffer.allocate(8); // or allocateDirect if we need direct memory access
        CharBuffer charBuffer = CharBuffer.allocate(8);
        CharsetDecoder charsetDecoder = StandardCharsets.UTF_8.newDecoder();//UTF-8 字符集来解码消息
        StringBuilder ourStore = new StringBuilder();
        while (socketChannel.read(byteBuffer) != -1 || byteBuffer.position() > 0) {
            byteBuffer.flip();
            storeBufferContents(byteBuffer, charBuffer, charsetDecoder, ourStore);
            byteBuffer.compact();
        }
        socketChannel.close();

        // then we read and saved our data
        assertTrue(ourStore
          .toString()
          .contains("It worked!"));
    }
    
    /**
     * 从我们的缓冲区存储数据
     * @param byteBuffer
     * @param charBuffer
     * @param charsetDecoder
     * @param ourStore
     */
    void storeBufferContents(ByteBuffer byteBuffer, CharBuffer charBuffer, CharsetDecoder charsetDecoder, StringBuilder ourStore) {
        charsetDecoder.decode(byteBuffer, charBuffer, true);
        charBuffer.flip();
        ourStore.append(charBuffer);
        charBuffer.clear();
    }

}
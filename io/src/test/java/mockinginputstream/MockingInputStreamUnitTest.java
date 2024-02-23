package mockinginputstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class MockingInputStreamUnitTest {
    /**
     * 简单的InputStream实现
     * @throws IOException
     */
    @Test
    public void givenSimpleImplementation_shouldProcessInputStream() throws IOException {
        int byteCount = readAndLogInputStream(new InputStream() {
            private final byte[] msg = "Hello World".getBytes();
            private int index = 0;
            
            @Override
            public int read() {
                if (index >= msg.length) {
                    return -1;
                }
                return msg[index++] & 0xFF;
            }
        });
        assertThat(byteCount).isEqualTo(11);
    }
    
    @Test
    public void givenByteArrayInputStream_shouldProcessInputStream() throws IOException {
        String msg = "Hello World";
        int bytesCount = readAndLogInputStream(new ByteArrayInputStream(msg.getBytes()));
        assertThat(bytesCount).isEqualTo(11);
    }
    
    @Test
    public void givenFileInputStream_shouldProcessInputStream() throws IOException {
        InputStream inputStream = MockingInputStreamUnitTest.class.getResourceAsStream("/mockinginputstreams/msg.txt");
        int bytesCount = readAndLogInputStream(inputStream);
        assertThat(bytesCount).isEqualTo(11);
        inputStream.close();
    }
    
    @Test
    public void givenGeneratingInputStream_shouldProcessInputStream() throws IOException {
        InputStream inputStream = new GeneratingInputStream(10_000, "Hello World");
        int bytesCount = readAndLogInputStream(inputStream);
        assertThat(bytesCount).isEqualTo(10_000);
        inputStream.close();
    }
    
    int readAndLogInputStream(InputStream inputStream) throws IOException {
        int count = 0;
        int c;
        while ((c = inputStream.read()) != -1) {
            log.info(String.valueOf((char)c));
            count++;
        }
        return count;
    }
}

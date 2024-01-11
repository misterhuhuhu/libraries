package baeldung.httpclient;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.HttpEntityWrapper;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 将 FilterOutputStream 扩展到“CountingOutputStream”时，我们将重写 write（） 方法来计算写入（传输）的字节数
 * 将 HttpEntityWrapper 扩展到“ProgressEntityWrapper”时，我们将重写 writeTo（） 方法以使用“CountingOutputStream”
 */
public class ProgressEntityWrapper extends HttpEntityWrapper {
    private final ProgressListener listener;

    ProgressEntityWrapper(final HttpEntity entity, final ProgressListener listener) {
        super(entity);
        this.listener = listener;
    }

    @Override
    public void writeTo(final OutputStream outstream) throws IOException {
        super.writeTo(new CountingOutputStream(outstream, listener, getContentLength()));
    }

    public interface ProgressListener {
        void progress(float percentage);
    }

    public static class CountingOutputStream extends FilterOutputStream {

        private final ProgressListener listener;
        private long transferred;
        private long totalBytes;

        CountingOutputStream(final OutputStream out, final ProgressListener listener, final long totalBytes) {
            super(out);
            this.listener = listener;
            transferred = 0;
            this.totalBytes = totalBytes;
        }

        @Override
        public void write(final byte[] b, final int off, final int len) throws IOException {
            out.write(b, off, len);
            transferred += len;
            listener.progress(getCurrentProgress());
        }

        @Override
        public void write(final int b) throws IOException {
            out.write(b);
            transferred++;
            listener.progress(getCurrentProgress());
        }

        private float getCurrentProgress() {
            return ((float) transferred / totalBytes) * 100;
        }
    }
}

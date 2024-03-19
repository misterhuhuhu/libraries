package mrwho.mybatisjava.plugins;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class OkHttpClientFactory {
    private static final ThreadLocal<ConcurrentHashMap<Long, OkHttpClient>> clientMapThreadLocal = new ThreadLocal<>();
    
    public OkHttpClient getInstance(long threadId) {
        ConcurrentHashMap<Long, OkHttpClient> threadMap = clientMapThreadLocal.get();
        if (threadMap == null) {
            threadMap = new ConcurrentHashMap<>();
            clientMapThreadLocal.set(threadMap);
        }
        OkHttpClient value = threadMap.computeIfAbsent(threadId, k -> new OkHttpClient().newBuilder()
                                                                              
                                                                              .connectTimeout(2, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                                                                              .readTimeout(5, TimeUnit.SECONDS) //读取超时时间设置为30秒
                                                                              .connectionPool(new ConnectionPool(0,1,TimeUnit.SECONDS))
                                                                              .build());
        if (threadMap.size() == 1) {
            // 如果这是唯一剩下的（threadId -> value），则删除 ThreadLocal
            clientMapThreadLocal.remove();
        }
        return value;
    }
}

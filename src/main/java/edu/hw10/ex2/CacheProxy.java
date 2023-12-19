package edu.hw10.ex2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CacheProxy implements InvocationHandler {

    private final Map<String, Object> innerCache = new HashMap<>();
    private final Map<String, Object> discCache = new DiskMap(Files.createTempDirectory("cache"));
    private final Object target;

    public CacheProxy(Object target) throws IOException {
        this.target = target;
    }

    @SneakyThrows
    public static <T> T create(Object target, Class<?>... interfaces) {
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            interfaces,
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, Object> cache;
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        } else {
            if (method.getAnnotation(Cache.class).persist()) {
                cache = discCache;
            } else {
                cache = innerCache;
            }
        }
        String cacheKey = method.getName() + Arrays.toString(args);
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        Object result = method.invoke(target, args);
        cache.put(cacheKey, result);
        return result;
    }
}

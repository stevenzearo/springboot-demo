package com.demo.zookeeper.service;

import com.demo.zookeeper.registry.Registry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author steve
 */
public class BeanFactory {
    private static final Registry registry = new Registry("127.0.0.1:2181", 5, "/service");

    public <T> void addBean(Class<T> aClass, T instance) throws Exception {
        registry.registerService(aClass, instance);
    }

    public <T> T getBean(Class<T> tClass) throws Exception {
        T instance = registry.getClassInstance(tClass);
        if (instance == null) {
            throw new Exception("server error......");
        }
        @SuppressWarnings("unchecked")
        T bean = (T) Proxy.newProxyInstance(BeanFactory.class.getClassLoader(), instance.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                T instance = registry.getClassInstance(tClass);
                if (instance == null) {
                    throw new Exception("server error......");
                }
                return method.invoke(instance, args);
            }
        });
        return bean;
    }

    public <T> void removeBean(Class<T> tClass) throws Exception {
        registry.removeService(tClass);
    }
}

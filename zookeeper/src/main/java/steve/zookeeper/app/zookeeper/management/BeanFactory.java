package steve.zookeeper.app.zookeeper.management;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author steve
 */
public class BeanFactory {
    private static final ConcurrentHashMap<String, Class<?>> classMap = new ConcurrentHashMap<>();


    public void addBean(String name, Class<?> aClass) {
        classMap.put(name, aClass);
    }

    public <T> T getBean(String beanName, Object[] params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = classMap.get(beanName);
        @SuppressWarnings(value = "unchecked")
        T instance = (T) aClass.getDeclaredConstructor().newInstance();
        return instance;
    }

    public <T> void removeBean(String beanName, Class<T> tClass) throws Exception {
        Class<?> aClass = classMap.get(beanName);
        if (aClass.isInstance(tClass)) classMap.remove(beanName);
        else throw new Exception(String.format("class miss match with the class name, beanName = %s", beanName));
    }
}

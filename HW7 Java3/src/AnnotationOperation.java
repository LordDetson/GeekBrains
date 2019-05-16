import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationOperation {
    private static Map<String, Object> stringObjectMap = new HashMap<>();

    public static void main(String[] args) {
        inspectService(SimpleService.class);
        System.out.println();
        inspectService(LazyService.class);
        System.out.println();
        inspectService(String.class);

        stringObjectMap.keySet().forEach(System.out::println);
    }

    private static void inspectService(Class<?> aClass) {
        System.out.println(aClass.getName());
        if (aClass.isAnnotationPresent(Service.class)) {
            Service serviceAnnotation = aClass.getAnnotation(Service.class);
            System.out.println(serviceAnnotation);
            Object o = loadService(aClass, serviceAnnotation.lazyLoad());
            if (o != null)
                stringObjectMap.put(serviceAnnotation.name(), o);
        } else System.out.println("No annotation @Service");
    }

    private static Object loadService(Class<?> aClass, boolean isLazy) {
        Object o = null;
        try {
            Constructor<?> constructor = aClass.getConstructor();
            o = constructor.newInstance();
            if (!isLazy)
                initService(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    private static Object initService(Object o) {
        Object result = null;
        Method[] methods = o.getClass().getDeclaredMethods();
        if (methods.length != 0) {
            for (Method method : methods) {
                System.out.println(method.getName());
                if (method.isAnnotationPresent(Init.class)) {
                    Init initAnnotation = method.getAnnotation(Init.class);
                    System.out.println(initAnnotation);
                    try {
                        method.setAccessible(true);
                        result = method.invoke(o);
                    } catch (Exception e) {
                        if (initAnnotation.suppressException()) e.printStackTrace();
                        else throw new RuntimeException(e);
                    }
                } else System.out.println("No annotation @Init");
            }
        } else System.out.println("No methods");
        return result;
    }
}

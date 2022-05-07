import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationDemo {

    public static void main(String[] args) {
        getAllClassAnnotations();
        printClassAnnotations(AnnotatedClass.class);
        printFieldAnnotations(AnnotatedClass.class);
        printMethodAnnotations(AnnotatedClass.class);
    }

    public static void getAllClassAnnotations() {
        System.out.println("\nAll class annotations:");
        Class annotatedClass = AnnotatedClass.class;
        Annotation[] annotations = annotatedClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("- @type: " + myAnnotation.type());
            }
        }
    }

    public static void printClassAnnotations(Class annotatedClass) {
        System.out.println("\nClass annotations:");
        MyAnnotation myAnnotation = (MyAnnotation) annotatedClass.getAnnotation(MyAnnotation.class);
        System.out.println("- @type: " + myAnnotation.type());
    }

    public static void printFieldAnnotations(Class annotatedClass) {
        System.out.println("\nField annotations:");
        Field[] fields = annotatedClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("- Field: " + field.getName());
            MyAnnotation[] myAnnotations = field.getAnnotationsByType(MyAnnotation.class);
            for (MyAnnotation myAnnotation : myAnnotations) {
                System.out.println("-- @type: " + myAnnotation.type());
                System.out.println("-- @value: " + myAnnotation.value());
            }
        }
    }

    public static void printMethodAnnotations(Class annotatedClass) {
        System.out.println("\nMethod annotations:");
        Method[] methods = annotatedClass.getDeclaredMethods();
        for (Method method : methods) {
            MyAnnotation[] myAnnotations = method.getDeclaredAnnotationsByType(MyAnnotation.class);
            if (myAnnotations.length == 0) {
                continue;
            }
            System.out.println("- Method: " + method.getName());
            for (MyAnnotation myAnnotation : myAnnotations) {
                System.out.println("-- @type: " + myAnnotation.type());
                System.out.println("-- @value: " + myAnnotation.value());
            }

            printMethodParameterAnnotations(method);
        }
    }

    public static void printMethodParameterAnnotations(Method method) {
        System.out.println("-- Parameter annotations:");
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();

        int i = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            Class parameterType = parameterTypes[i++];

            for (Annotation annotation : annotations) {
                if (annotation instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) annotation;
                    System.out.println("--- Param: " + parameterType.getName());
                    System.out.println("---- @type : " + myAnnotation.type());
                    System.out.println("---- @value : " + myAnnotation.value());
                }
            }
        }
    }
}

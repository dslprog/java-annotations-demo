@MyAnnotation(type = "type")
public class AnnotatedClass {

    @MyAnnotation(type = "field")
    private String name = "Name";

    @MyAnnotation(type = "constructor")
    public AnnotatedClass() {

    }

    @MyAnnotation(type = "method")
    private void printText(@MyAnnotation(type = "parameter") String text, @MyAnnotation(type = "parameter", value = 33) int number) {
        @MyAnnotation(type = "localVariable")
        String localVar = "Some text";
    }
}

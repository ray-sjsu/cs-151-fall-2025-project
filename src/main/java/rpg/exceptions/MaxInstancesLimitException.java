package rpg.exceptions;

public class MaxInstancesLimitException extends RuntimeException {
    public static final int CLASS_INSTANCE_LIMIT = 100;

    public MaxInstancesLimitException(String className) {
        super("Cannot create more than " + CLASS_INSTANCE_LIMIT + " instances of class: " + className);
    }
    public MaxInstancesLimitException(String className, int maxLimit) {
        super("Cannot create more than " + maxLimit + " instances of class: " + className);
    }
}
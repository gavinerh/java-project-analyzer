package methodInvocation.testPractice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestFramework {

    public static void main(String[] args) throws Throwable {
//        Object testFrameworkInstance = TestFramework.class.getDeclaredConstructor().newInstance();
//        Method runTestSuiteMethod = testFrameworkInstance.getClass().getMethod("runTestSuite", Class.class);
//        runTestSuiteMethod.invoke(testFrameworkInstance, TestFramework.class);
        TestFramework testFramework = new TestFramework();
        testFramework.runTestSuite(PaymentServiceTest.class);
    }

    public void runTestSuite(Class<?> testClass) throws Throwable {
        Method[] methods = testClass.getDeclaredMethods();
        Object paymentServiceTest = testClass.getDeclaredConstructor().newInstance();
        Method beforeClassMethod = findMethodByName(methods, "beforeClass");
        if(beforeClassMethod != null) {
            beforeClassMethod.invoke(null);
        }
        startTests(methods, paymentServiceTest);
    }

    private void startTests(Method[] methods, Object instance) throws InvocationTargetException, IllegalAccessException {
        List<Method> testMethods = findMethodsByPrefix(methods, "test");
        Method setupTestMethod = findMethodByName(methods, "setupTest");
        int falseCount = 0;
        for(Method testMethod : testMethods) {
            if(setupTestMethod != null) {
                setupTestMethod.invoke(instance);
            }
            Boolean returnVal = (Boolean) testMethod.invoke(instance);

//            if(returnVal.getClass().equals(boolean.class)) {
//                if(returnVal.equals(false)){
//                    falseCount++;
//                }
//            }
        }
        System.out.println(String.format("Number of false is: %d", falseCount));
        Method afterClassMethod = findMethodByName(methods, "afterClass");
        if(afterClassMethod != null) {
            afterClassMethod.invoke(null);
        }
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName(Method[] methods, String name) {
        for(Method method : methods) {
            if(method.getName().equals(name)
            && method.getParameterCount() == 0
            && method.getReturnType() == void.class){
                return method;
            }
        }
        return null;
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
        List<Method> methodsContainingPrefix = new ArrayList<>();
        for(Method method : methods) {
            String methodName = method.getName();
            if(methodName.toLowerCase().startsWith(prefix.toLowerCase())
            && method.getParameterCount() == 0
            && method.getReturnType() == void.class){
                methodsContainingPrefix.add(method);
            }
        }
        return methodsContainingPrefix;
    }
}

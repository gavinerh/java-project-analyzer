package MARMSUI.fieldTracker;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;

public class MethodCallProcessor extends AbstractProcessor<CtInvocation<?>> {
    @Override
    public void process(CtInvocation<?> invocation) {
        // Get the method that contains the invocation
        CtMethod<?> parentMethod = invocation.getParent(CtMethod.class);
        
        if (parentMethod != null) {
            System.out.println("Method " + parentMethod.getSimpleName() + 
                               " calls " + invocation.getExecutable().getSimpleName());
        } else {
            System.out.println("Found a method call outside of any method: " + 
                               invocation.getExecutable().getSimpleName());
        }
    }
}
package MARMSUI.fieldTracker;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.factory.Factory;

public class SpoonAnalysis {
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.addInputResource("/Users/macuser/Documents/lsl-marmsui-profile/src/main/java"); // Adjust this path to your source code
        launcher.buildModel();
        
        Factory factory = launcher.getFactory();
        CtModel model = factory.getModel();
        
        // Add and run your processor
        model.processWith(new MethodCallProcessor());
    }
}
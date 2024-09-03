package MARMSUI.migration.hierarchyGenerator;

public class Main {
    // 1. start off with mapping the methods of all the classes
    // 2. map the interfaces to their implementations
    // 3. start off with file path of starting method
    // 4. list the method calls in the method and store it in a list
    // 5. for each method call in the current method, find that method be it inner methods or methods from field variables
    // 6. if the method is from the field name, then use map in part 2 to identify if it is an interface or implementation
    // 7. if it is an interface, then find the implementation of the interface, if cannot find then hierarchy stops there for that method call
    // 8. if it is an implementation, then find the method in the implementation class, and repeat the process from step 4
}

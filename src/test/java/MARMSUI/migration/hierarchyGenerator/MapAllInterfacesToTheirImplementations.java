package MARMSUI.migration.hierarchyGenerator;

public class MapAllInterfacesToTheirImplementations {
    // upon entering the class file, will call the CheckIfClassOrInterface class to determine if it is a class or interface
    // if it is an interface, leave it, if it is a class, try to find the interface it implements
    // by calling the CheckIfThereAreInterfacesImplemented class
    // store the mapping of implementation to interface in a map
}

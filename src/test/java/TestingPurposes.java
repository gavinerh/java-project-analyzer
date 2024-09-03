import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestingPurposes {


    private static MapperClone mapperClone = Mappers.getMapper(MapperClone.class);

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String val1 = "test";
        modify(val1);
        System.out.println(val1);
    }

    private static void modify(String val) {
        System.out.println("Old: " + val);
        val = "modified";
        System.out.println("New: " + val);
    }

}
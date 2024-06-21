import org.mapstruct.Mapper;

@Mapper
public interface MapperClone {

    Mod clone(Mod mod);
}

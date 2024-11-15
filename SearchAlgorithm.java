import java.util.List;


////كي يكون مرجعا اساسيا لجميع خوارزميات البحث التي سنطبقها
public interface SearchAlgorithm {
    List<State> search(State initialState);
}

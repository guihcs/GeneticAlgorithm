

public interface CreatureGenerator<T, E> {
    Creature<T, E> generate(E objective);
}

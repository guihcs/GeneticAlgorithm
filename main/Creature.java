

public abstract class Creature<T, E> {

    private final DNA dna;
    private final E objective;

    public Creature(DNA dna, E objective) {

        this.dna = dna;
        this.objective = objective;
    }

    public abstract float getFitness();
    public abstract T getPhenotype();
    public abstract Creature<T, E> create(DNA dna, E objective);

    public Creature<T, E> mate(Creature<T, E> creature){
        var childDNA = dna.crossover(creature.dna);
        return create(childDNA, creature.objective);
    }

    public void mutate(float rate){
        dna.mutate(rate);
    }

    public DNA getDNA() {
        return dna;
    }

    public E getObjective(){
        return objective;
    }

    public float[] getGenome(){
        return dna.getGenome();
    }

}

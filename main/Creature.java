package genalg;

public abstract class Creature<T> {

    private final DNA dna;

    public Creature(DNA dna) {
        this.dna = dna;
    }

    public abstract float getFitness();
    public abstract T getPhenotype();
    public abstract Creature<T> create(DNA dna);

    public Creature<T> mate(Creature<T> creature){
        var childDNA = dna.crossover(creature.dna);
        return create(childDNA);
    }

    public void mutate(float rate){
        dna.mutate(rate);
    }

    public DNA getDNA() {
        return dna;
    }

}

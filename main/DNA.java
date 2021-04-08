

public class DNA {

    private final float[] genome;

    public DNA(int genomeSize) {
        genome = new float[genomeSize];

        for (int i = 0; i < genomeSize; i++) {
            genome[i] = (float) Math.random();
        }

    }


    public DNA(float[] genome) {
        this.genome = genome;
    }


    public DNA crossover(DNA other) {

        int midPoint = (int) (Math.random() * genome.length);
        float[] newGenome = new float[genome.length];

        for (int i = 0; i < genome.length; i++) {
            newGenome[i] = i < midPoint ? genome[i] : other.genome[i];
        }

        return new DNA(newGenome);
    }

    public void mutate(float rate) {

        for (int i = 0; i < genome.length; i++) {
            if (Math.random() < rate) genome[i] = (float) Math.random();
        }
    }


    public float[] getGenome() {
        return genome;
    }
}

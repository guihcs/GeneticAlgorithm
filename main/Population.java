package genalg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population<T> {

    private final float mutationRate;
    private final float populationSize;
    private List<Creature<T>> creatureList;
    private int currentGeneration = 0;


    public Population(float mutationRate, float populationSize, CreatureGenerator<T> creatureGenerator) {
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
        creatureList = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            creatureList.add(creatureGenerator.generate());
        }
    }

    private void sortByFitness(){

        creatureList.sort(Comparator.comparing(Creature::getFitness));
    }


    public Creature<T> simulate(int iterations){
        for (int i = 0; i < iterations; i++) {
            iterate();
        }

        return getFittest();
    }


    public void iterate() {

        List<Creature<T>> nextGeneration = new ArrayList<>();

        sortByFitness();

        for (int i = 0; i < populationSize; i++) {
            Creature<T> parent1 = selectRandomByFitness();
            Creature<T> parent2 = selectRandomByFitness(parent1);

            Creature<T> child = parent1.mate(parent2);
            child.mutate(mutationRate);
            nextGeneration.add(child);
        }

        creatureList = nextGeneration;
        currentGeneration++;
    }


    private Creature<T> selectRandomByFitness(){

        float totalSum = populationSize * (populationSize + 1) / 2f;

        while (true){
            int index = (int) (Math.random() * populationSize);

            float selectChance = (populationSize - ((populationSize - 1) - index)) / totalSum;

            if (Math.random() < selectChance) return creatureList.get(index);
        }

    }


    private Creature<T> selectRandomByFitness(Creature<T> creatureToCompare){

        Creature<T> creature;

        do {
            creature = selectRandomByFitness();
        } while (creature.equals(creatureToCompare));

        return creature;
    }


    public Creature<T> getFittest(){
        var fittest = creatureList.stream()
                .max(Comparator.comparing(Creature::getFitness));

        return fittest.orElse(null);
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public float getPopulationSize() {
        return populationSize;
    }
}



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population<T, E> {

    private final float mutationRate;
    private final float populationSize;
    private List<Creature<T, E>> creatureList;
    private int currentGeneration = 0;


    public Population(float mutationRate, float populationSize, CreatureGenerator<T, E> creatureGenerator, E objective) {
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
        creatureList = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            creatureList.add(creatureGenerator.generate(objective));
        }
    }

    private void sortByFitness(){

        creatureList.sort(Comparator.comparing(Creature::getFitness));
    }


    public Creature<T, E> simulate(int iterations){
        for (int i = 0; i < iterations; i++) {
            iterate();
        }

        return getFittest();
    }


    public void iterate() {

        List<Creature<T, E>> nextGeneration = new ArrayList<>();

        sortByFitness();

        for (int i = 0; i < populationSize; i++) {
            Creature<T, E> parent1 = selectRandomByFitness();
            Creature<T, E> parent2 = selectRandomByFitness(parent1);

            Creature<T, E> child = parent1.mate(parent2);
            child.mutate(mutationRate);
            nextGeneration.add(child);
        }

        creatureList = nextGeneration;
        currentGeneration++;
    }


    private Creature<T, E> selectRandomByFitness(){

        float totalSum = populationSize * (populationSize + 1) / 2f;

        while (true){
            int index = (int) (Math.random() * populationSize);

            float selectChance = (populationSize - ((populationSize - 1) - index)) / totalSum;

            if (Math.random() < selectChance) return creatureList.get(index);
        }

    }


    private Creature<T, E> selectRandomByFitness(Creature<T, E> creatureToCompare){

        Creature<T, E> creature;

        do {
            creature = selectRandomByFitness();
        } while (creature.equals(creatureToCompare));

        return creature;
    }


    public Creature<T, E> getFittest(){
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

    public List<Creature<T, E>> getCreatureList() {
        return creatureList;
    }
}

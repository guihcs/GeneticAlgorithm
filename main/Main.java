public class Main {


    static class TextCreature extends Creature<String, String> {
        public TextCreature(DNA dna, String objective) {
            super(dna, objective);
        }

        @Override
        public float getFitness() {
            String phenotype = getPhenotype();
            int correctCount = 0;
            for (int i = 0; i < phenotype.length(); i++) {
                if(phenotype.charAt(i) == getObjective().charAt(i)) correctCount++;
            }
            return correctCount / (float)phenotype.length();
        }

        @Override
        public String getPhenotype() {
            char[] genomeChar = new char[getGenome().length];
            for (int i = 0; i < getGenome().length; i++) {
                genomeChar[i] = (char) (getGenome()[i] * 255);
            }
            return new String(genomeChar);
        }

        @Override
        public Creature<String, String> create(DNA dna, String objective) {
            return new TextCreature(dna, objective);
        }
    }

    public static void main(String[] args) {
        final String finalObjective = "ola galerinha do meu canal";
        Population<String, String> creaturePopulation = new Population<>(
                0.01f,
                150,
                 (objective) -> new TextCreature(new DNA(objective.length()), objective),
                finalObjective
        );

        while(true){
            creaturePopulation.iterate();
            TextCreature fittest = (TextCreature) creaturePopulation.getFittest();

            System.out.println(fittest.getPhenotype());
            System.out.println(fittest.getFitness());
            System.out.println("----------------------");
        }




    }
}

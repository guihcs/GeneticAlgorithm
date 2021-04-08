import java.util.Arrays;

public class NeuralMain {

    static class NeuralCreature extends Creature<float[], TrainData> {

        public NeuralCreature(DNA dna, TrainData objective) {
            super(dna, objective);
        }

        @Override
        public float getFitness() {
            float[] weights = getPhenotype();
            float[][] train = getObjective().x;

            float[] results = forwardPass(train, weights);

            int count = 0;
            for (int i = 0; i < results.length; i++) {
                if (results[i] == getObjective().y[i]) count++;
            }

            return count / (float) train.length;
        }


        private float[] forwardPass(float[][] train, float[] weights) {
            float[] results = new float[train.length];

            for (int i = 0; i < train.length; i++) {
                float ws = 0;
                for (int j = 0; j < train[0].length; j++) {
                    ws += weights[j] * train[i][j];
                }
                ws += weights[weights.length - 1];
                results[i] = sigmoid(ws) > 0.5 ? 1 : 0;
            }
            return results;
        }

        private float sigmoid(float x) {
            return (float) (1 / (1 + Math.pow(Math.E, -x)));
        }

        @Override
        public float[] getPhenotype() {
            float[] genome = getGenome();
            float[] phenotype = new float[genome.length];
            for (int i = 0; i < genome.length; i++) {
                phenotype[i] = genome[i] * (200) - 100;
            }
            return phenotype;
        }

        @Override
        public Creature<float[], TrainData> create(DNA dna, TrainData objective) {
            return new NeuralCreature(dna, objective);
        }
    }

    static class TrainData {
        private final float[][] x;
        private final float[] y;

        public TrainData(float[][] x, float[] y) {
            this.x = x;
            this.y = y;
        }

    }

    static TrainData getTrainingData() {
        float[][] x = {
                {0.28774f,0.29139f},
                {0.40714f,0.17878f},
                {0.2923f,0.4217f},
                {0.50922f,0.35256f},
                {0.27785f,0.10802f},
                {0.27527f,0.33223f},
                {0.43999f,0.31245f},
                {0.33557f,0.42984f},
                {0.23448f,0.24986f},
                {0.0084492f,0.13658f},
                {0.12419f,0.33595f},
                {0.25644f,0.42624f},
                {0.4591f,0.40426f},
                {0.44547f,0.45117f},
                {0.42218f,0.20118f},
                {0.49563f,0.21445f},
                {0.30848f,0.24306f},
                {0.39707f,0.44438f},
                {0.32945f,0.39217f},
                {0.40739f,0.40271f},
                {0.3106f,0.50702f},
                {0.49638f,0.45384f},
                {0.10073f,0.32053f},
                {0.69907f,0.37307f},
                {0.29767f,0.69648f},
                {0.15099f,0.57341f},
                {0.16427f,0.27759f},
                {0.33259f,0.055964f},
                {0.53741f,0.28637f},
                {0.19503f,0.36879f},
                {0.40278f,0.035148f},
                {0.21296f,0.55169f},
                {0.48447f,0.56991f},
                {0.25476f,0.34596f},
                {0.21726f,0.28641f},
                {0.67078f,0.46538f},
                {0.3815f,0.4622f},
                {0.53838f,0.32774f},
                {0.4849f,0.26071f},
                {0.37095f,0.38809f},
                {0.54527f,0.63911f},
                {0.32149f,0.12007f},
                {0.42216f,0.61666f},
                {0.10194f,0.060408f},
                {0.15254f,0.2168f},
                {0.45558f,0.43769f},
                {0.28488f,0.52142f},
                {0.27633f,0.21264f},
                {0.39748f,0.31902f},
                {0.5533f,1f},
                {0.44274f,0.59205f},
                {0.85176f,0.6612f},
                {0.60436f,0.86605f},
                {0.68243f,0.48301f},
                {1f,0.76815f},
                {0.72989f,0.8107f},
                {0.67377f,0.77975f},
                {0.78761f,0.58177f},
                {0.71442f,0.7668f},
                {0.49379f,0.54226f},
                {0.78974f,0.74233f},
                {0.67905f,0.60921f},
                {0.6642f,0.72519f},
                {0.79396f,0.56789f},
                {0.70758f,0.76022f},
                {0.59421f,0.61857f},
                {0.49364f,0.56224f},
                {0.77707f,0.35025f},
                {0.79785f,0.76921f},
                {0.70876f,0.96764f},
                {0.69176f,0.60865f},
                {0.66408f,0.92075f},
                {0.65973f,0.66666f},
                {0.64574f,0.56845f},
                {0.89639f,0.7085f},
                {0.85476f,0.63167f},
                {0.62091f,0.80424f},
                {0.79057f,0.56108f},
                {0.58935f,0.71582f},
                {0.56846f,0.7406f},
                {0.65912f,0.71548f},
                {0.70938f,0.74041f},
                {0.59154f,0.62927f},
                {0.45829f,0.4641f},
                {0.79982f,0.74847f},
                {0.60974f,0.54757f},
                {0.68127f,0.86985f},
                {0.76694f,0.64736f},
                {0.69048f,0.83058f},
                {0.68122f,0.96541f},
                {0.73229f,0.64245f},
                {0.76145f,0.60138f},
                {0.58985f,0.86955f},
                {0.73145f,0.74516f},
                {0.77029f,0.7014f},
                {0.73156f,0.71782f},
                {0.44556f,0.57991f},
                {0.85275f,0.85987f},
                {0.51912f,0.62359f},
        };

        float[] y = {
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
        };

        return new TrainData(x, y);
    }

    public static void main(String[] args) {


        Population<float[], TrainData> population = new Population<>(
                0.01f,
                150,
                objective -> new NeuralCreature(
                        new DNA(getTrainingData().x[0].length + 1),
                        objective
                ),
                getTrainingData()
        );

        NeuralCreature creature = (NeuralCreature) population.simulate(200);
        System.out.println(Arrays.toString(creature.getPhenotype()));
        System.out.println(creature.getFitness());
    }
}

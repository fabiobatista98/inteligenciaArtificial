package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class BinaryMutation<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public BinaryMutation(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {

        for (int i = 0; i < ind.getNumGenes(); i++) {

            if (GeneticAlgorithm.random.nextDouble() < probability) {
                ind.setGene(i, GeneticAlgorithm.random.nextInt(ind.getNumGenes()));
                for (int j = 0; j < ind.getNumGenes(); j++) {
                    if (ind.getGene(i) == ind.getGene(j) && i!=j) {
                        i--;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        return "Binary";
    }
}
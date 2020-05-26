package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class SwapMutation<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public SwapMutation(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {

        int num1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int num2 = num1;
        while(num1==num2){
            num2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }

        int aux = ind.getGene(num1);
        ind.setGene(num1, ind.getGene(num2));
        ind.setGene(num2, aux);
    }

    @Override
    public String toString() {

        return "Swap";
    }
}
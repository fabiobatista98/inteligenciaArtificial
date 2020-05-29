package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class SwapMutation<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public SwapMutation(double probability) {
        super(probability);
    }

    int num1 = 0, num2 = 0, aux = 0;

    @Override
    public void mutate(I ind) {

        num1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        num2 = num1;
        while(num1==num2){
            num2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }

        aux = ind.getGene(num1);
        ind.setGene(num1, ind.getGene(num2));
        ind.setGene(num2, aux);
    }

    @Override
    public String toString() {
        return "Swap";
    }
}
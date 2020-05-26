package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class UniformRecombination<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public UniformRecombination(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {

        int aux1 = 0, aux2 = 0;
        boolean flag1, flag2;
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            flag1 = false;
            flag2 = false;
            if (GeneticAlgorithm.random.nextBoolean()) {
                aux1 = ind1.getGene(i);
                aux2 = ind2.getGene(i);
                ind1.swapGenes(ind2, i);
                for (int j = 0; j < ind1.getNumGenes(); j++) {
                    if(ind1.getGene(j) == aux2 && i!=j){
                        ind1.setGene(j, aux1);
                        flag1 = true;
                    }
                    if(ind2.getGene(j) == aux1 && i!=j){
                        ind2.setGene(j, aux2);
                        flag2 = true;
                    }
                    if(flag1 && flag2){
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString(){

        return "Uniform recombination (" + probability + ")";
    }
}
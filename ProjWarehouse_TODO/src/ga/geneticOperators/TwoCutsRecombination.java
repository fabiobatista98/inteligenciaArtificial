package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class TwoCutsRecombination<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public TwoCutsRecombination(double probability) {
        super(probability);
    }

    int cut1 = 0, cut2 = 0;
    int aux1 = 0, aux2 = 0;
    boolean flag1, flag2;

    @Override
    public void recombine(I ind1, I ind2) {


        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }
        aux1 = 0;
        aux2 = 0;

        for (int i = cut1; i < cut2; i++) {
            aux1 = ind1.getGene(i);
            aux2 = ind2.getGene(i);
            flag1 = false;
            flag2 = false;
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

    @Override
    public String toString(){

        return "TwoCuts";
    }
}
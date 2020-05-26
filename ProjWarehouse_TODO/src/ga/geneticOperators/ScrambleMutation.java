package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class ScrambleMutation<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public ScrambleMutation(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {

        int cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int cut2;
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (cut1==cut2);

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        int[] aux = new int[cut2-cut1];

        for (int i = 0; i < aux.length; i++) {
            aux[i] = ind.getGene(i + cut1);
        }

        for (int i = cut1; i < cut2; i++) {
            int r = GeneticAlgorithm.random.nextInt(aux.length);
            ind.setGene(i, aux[r]);
            for (int j = cut1; j < cut2; j++) {
                if(ind.getGene(i) == ind.getGene(j) && i != j){
                    i--;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {

        return "Scramble";
    }
}
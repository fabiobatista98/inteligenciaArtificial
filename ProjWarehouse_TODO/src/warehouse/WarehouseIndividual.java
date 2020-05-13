package warehouse;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;

import java.util.Arrays;

public class WarehouseIndividual extends IntVectorIndividual<WarehouseProblemForGA, WarehouseIndividual> {

    //TODO this class might require the definition of additional methods and/or attributes

    public WarehouseIndividual(WarehouseProblemForGA problem, int size) {
        super(problem, size);
        //TODO
        for (int i = 0; i < genome.length; i++) {
            //preencher genome
            //1-size
            genome[i] = GeneticAlgorithm.random.nextInt(size)+1;
        }
        System.out.println(Arrays.toString(genome));
    }

    public WarehouseIndividual(WarehouseIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        fitness = 0;
        //calcular
        return fitness;
    }

    public static int getShelfPos(int[] genome, int value) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    //Return the product Id if the shelf in cell [line, column] has some product and 0 otherwise
    public int getProductInShelf(int line, int column){
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i < genome.length; i++) {
            sb.append(genome[i]).append(" ");
            //this method might require changes
        }

        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(WarehouseIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public WarehouseIndividual clone() {
        return new WarehouseIndividual(this);

    }
}

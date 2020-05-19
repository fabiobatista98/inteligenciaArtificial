package warehouse;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;

import java.util.Arrays;

public class WarehouseIndividual extends IntVectorIndividual<WarehouseProblemForGA, WarehouseIndividual> {

    private double value;
    private double weight;

    public WarehouseIndividual(WarehouseProblemForGA problem, int size) {
        super(problem, size);

        for (int i = 0; i < genome.length; i++) {
            genome[i] = GeneticAlgorithm.random.nextInt(size)+1;
            for (int j = 0; j < i-1; j++) {
                 if(genome[i] == genome[j] && i!=0){
                     i--;
                     break;
                 }
            }
        }
        System.out.println(Arrays.toString(genome));
    }

    public WarehouseIndividual(WarehouseIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        //calcular

        fitness = weight = value = 0;
        for (int i = 0; i < genome.length; i++) {

            value += problem.getItem(i).value;
            weight += problem.getItem(i).weight;

        }
        switch (problem.getFitnessType()) {
            case Knapsack.SIMPLE_FITNESS:
                fitness = weight <= problem.getMaximumWeight() ? value : 0;
                break;
            case Knapsack.PENALTY_FITNESS:
                double penalty = 0;
                if (weight > problem.getMaximumWeight())
                    penalty = problem.getMaxVP() * (weight - problem.getMaximumWeight());
                fitness = value - penalty;
        }
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

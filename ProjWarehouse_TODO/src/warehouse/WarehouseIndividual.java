package warehouse;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;

import java.util.NoSuchElementException;

public class WarehouseIndividual extends IntVectorIndividual<WarehouseProblemForGA, WarehouseIndividual> {

    public WarehouseIndividual(WarehouseProblemForGA problem, int size) {
        super(problem, size);

        for (int i = 0; i < genome.length; i++) {
            genome[i] = GeneticAlgorithm.random.nextInt(size)+1;
            for (int j = 0; j < i; j++) {
                if(genome[i] == genome[j]){
                    i--;
                    break;
                }
            }
        }
        //System.out.println(Arrays.toString(genome));
    }

    public WarehouseIndividual(WarehouseIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        fitness = 0;

        for (Request r : problem.getRequests()) {
            for (int i = 0; i < r.getRequest().length; i++) {
                if (i == 0){
                    fitness += distancia(problem.getCellAgent(), problem.getShelves().get(getShelfPos(genome, r.getRequest()[0])));
                }
                if (i == r.getRequest().length-1){
                    fitness += distancia(problem.getShelves().get(getShelfPos(genome, r.getRequest()[i])), problem.getExit());
                }
                else{
                    fitness += distancia(problem.getShelves().get(getShelfPos(genome, r.getRequest()[i])), problem.getShelves().get(getShelfPos(genome, r.getRequest()[i+1])));
                }
            }
        }
        return fitness;
    }

    public int distancia(Cell cell1, Cell cell2){

        //alterar para usar a hash map

        for (Pair p : problem.getPairs()) {
            if (p.getCell1().equals(cell1) && p.getCell2().equals(cell2) ||
                p.getCell1().equals(cell2) && p.getCell2().equals(cell1)) {
                return p.getValue();
            }
        }

        throw new NoSuchElementException("Erro: Não foi encontrado o caminho entre " + cell1.toString() + " e " + cell2.toString());
    }

    public static int getShelfPos(int[] genome, int value) {

        for (int i = 0; i < genome.length; i++) {
            if(genome[i]==value){
                return i;
            }
        }
        return 0;
    }

    //Return the product Id if the shelf in cell [line, column] has some product and 0 otherwise
    public int getProductInShelf(int line, int column){

        for (int i = 0; i < problem.getShelves().size(); i++) {
            if(problem.getShelves().get(i).getLine() == line && problem.getShelves().get(i).getColumn() == column){
                if (genome[i] <= problem.getNumProducts()){
                    return genome[i];
                }
            }
        }
        return 0;
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

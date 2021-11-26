package Repositories;

import Contracts.AbstractContract;
import Sorters.ISorter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Repository{
    private int size;
    private int capacity;
    private AbstractContract[] contracts;


    public  Repository(){
        this(5);
    }

    public int getSize() {
        return size;
    }

    public Repository(int capacity) {
        this.capacity = Math.max(capacity, 5);
        size = 0;
        contracts = new AbstractContract[capacity];
    }

    public Repository(AbstractContract[] contracts){
        capacity = contracts.length;
        size = capacity;
        this.contracts = contracts;
    }

    /**
     * Adds a new contract to the repository.
     * Throws runtime exception if the repository already contains a contract with a new contract's id.
     */
    public void add(@NonNull AbstractContract contract){
        if (findIndexById(contract.getID()) != -1){
            throw new RuntimeException("Contract with ID = " + contract.getID() + " already exists.");
        }

        if (size == capacity) {
            extendRepository();
        }

        contracts[size] = contract;
        size++;
    }

    /**
     * Finds a contract by id. 
     * Returns an empty Optional if the repository doesn't have a contract with the necessary id or 
     *                           if classType doesn't match contract's class.
     * */
    public <T extends AbstractContract> Optional<T> findById(int ID, Class<T> classType){
        int index = findIndexById(ID);
        if (index == -1 || classType != contracts[index].getClass())
           return Optional.empty();
        return Optional.of((T)contracts[index]);
    }


    public Repository find(Predicate<AbstractContract> p){
        List<AbstractContract> resultList = Arrays.stream(contracts).filter(p).collect(Collectors.toList());
        AbstractContract[] resultArray = new AbstractContract[resultList.size()];
        int i = 0;
        for (AbstractContract contract : resultList){
            resultArray[i] = contract;
            i++;
        }
        return new Repository(resultArray);
    }


    public void sort(ISorter sorter, Comparator<AbstractContract> comparator){
        sorter.sort(contracts, comparator);
    }


    public AbstractContract[] toArray(){
        return contracts;
    }

    /**
     * In case there's a contract with id == ID in the repository - removes it, otherwise - does nothing.
     * */
    public void deleteById(int ID){
        int index = findIndexById(ID);
        if (index != -1){
            contracts[index] = contracts[size - 1];
            size--;
        }
    }

    
    /**
     * Returns index of a contract with id == ID.
     * Returns -1 if such contract doesn't exist.
     * */
    private int findIndexById(int ID){
        int index = 0;
        while(index < size && contracts[index].getID() != ID) {
            index++;
        }
        return index == size ? -1 : index;
    }

    
    /**
     * Adds extra space to the repository.
     * */
    private void extendRepository(){
        capacity = (int)(capacity * 1.2) + 1;
        AbstractContract[] new_contracts = new AbstractContract[capacity];
        System.arraycopy(contracts, 0, new_contracts, 0, size);
        contracts = new_contracts;
    }
}


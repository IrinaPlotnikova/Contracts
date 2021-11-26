package Sorters;

import Contracts.AbstractContract;

import java.util.Comparator;

public class BubbleSorter implements ISorter{
    @Override
    public void sort(AbstractContract[] contracts, Comparator<AbstractContract> comparator) {
        int n = contracts.length;
        for (int i = n; i > 0; i--){
            for (int j = 1; j < i; j++){
                if (comparator.compare(contracts[j - 1], contracts[j]) > 0){
                    AbstractContract tmp = contracts[j - 1];
                    contracts[j - 1] = contracts[j];
                    contracts[j] = tmp;
                }
            }
        }
    }
}

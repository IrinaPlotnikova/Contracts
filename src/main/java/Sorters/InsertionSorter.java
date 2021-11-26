package Sorters;

import Contracts.AbstractContract;

import java.util.Comparator;

public class InsertionSorter implements ISorter{

    @Override
    public void sort(AbstractContract[] contracts, Comparator<AbstractContract> comparator) {
        int n = contracts.length;
        for (int i = 1; i < n; i++){
            AbstractContract current = contracts[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(current, contracts[j]) < 0){
                contracts[j + 1] = contracts[j];
                j--;
            }
            contracts[j + 1] = current;
        }
    }
}

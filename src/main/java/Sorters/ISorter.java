package Sorters;

import Contracts.AbstractContract;

import java.util.Comparator;

public interface ISorter {
    void sort(AbstractContract[] contracts, Comparator<AbstractContract> comparator);
}

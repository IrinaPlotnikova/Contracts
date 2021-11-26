package Repositories;


import Contracts.AbstractContract;
import Contracts.InternetContract;
import Contracts.PhoneContract;
import Contracts.TVContract;
import Persons.Person;
import Sorters.BubbleSorter;
import Sorters.InsertionSorter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Person[] persons = new Person[]{
            new Person(1, "Алиса", "Максимовна", "Виноградова",
                    LocalDate.of(1982, 7,15), false, "2001 615168"),
            new Person(2, "Анастасия", "Сергеевна", "Рыжова",
                    LocalDate.of(2000, 10,17), false, "2002 165684"),
            new Person(3, "Лея", "Михайловна", "Литвинова",
                    LocalDate.of(1997, 11,28), false, "2003 166846"),
            new Person(4, "Андрей", "Федорович", "Андреев",
                    LocalDate.of(1998, 8,28), true, "2004 168645"),
            new Person(5, "Иван", "Данилович", "Беляев",
                    LocalDate.of(1997, 8,12), true, "2005 648978"),
            new Person(5, "Михаил", "Михайлович", "Кузнецов",
                    LocalDate.of(1998, 3,9), true, "2006 16879")
    };

    private AbstractContract[] contracts = new AbstractContract[]{
            new InternetContract(1, 1, LocalDate.of(2017,5,10),
                    LocalDate.of(2020,10,2), persons[0], 30),
            new InternetContract(3, 3, LocalDate.of(2016,3,17),
                    LocalDate.of(2017,5,29), persons[1], 10),
            new InternetContract(5, 5, LocalDate.of(2017,3,6),
                    LocalDate.of(2017,4,10), persons[2], 20),
            new PhoneContract(7, 7, LocalDate.of(2018,6,4),
                    LocalDate.of(2020,4,8), persons[3], 600, 210, 1024),
            new PhoneContract(9, 9, LocalDate.of(2016,12,20),
                    LocalDate.of(2017,11,29), persons[4], 600, 210, 1024),
            new PhoneContract(8, 8, LocalDate.of(2016,3,10),
                    LocalDate.of(2019,8,22), persons[5], 600, 210, 1024),
            new TVContract(6, 6, LocalDate.of(2016,4,8),
                    LocalDate.of(2019,4,1), persons[0], "package A"),
            new TVContract(4, 4, LocalDate.of(2018,5,17),
                    LocalDate.of(2019,11,13), persons[1], "package B"),
            new TVContract(2, 2, LocalDate.of(2019,5,9),
                    LocalDate.of(2019,6,27), persons[2], "package A"),
    };



    @Test
    void add() {
        Repository repository = new Repository();

        Person person = new Person(1, "Nick", "", "James",
                LocalDate.of(1990, 10, 10), true, "15 FW53");

        InternetContract contract1 = new InternetContract(1, 10, LocalDate.of(2000, 10, 20),
                LocalDate.of(2008, 10, 20), person, 50);

        int n = 10;
        for (int i = 0; i < n; i++){
            InternetContract contract = new InternetContract(i, 10, LocalDate.of(2000, 10, 20),
                    LocalDate.of(2008, 10, 20), person, 50);
            repository.add(contract);
        }

        assertEquals(n, repository.getSize());
        assertThrows(RuntimeException.class, () -> {
            InternetContract contract = new InternetContract(0, 10, LocalDate.of(2000, 10, 20),
                    LocalDate.of(2008, 10, 20), person, 50);
            repository.add(contract);
        });
    }

    @Test
    void findById() {
        Repository repository = new Repository();

        Person person = new Person(1, "Nick", "", "James",
                LocalDate.of(1990, 10, 10), true, "15 FW53");

        InternetContract contract = new InternetContract(1, 10, LocalDate.of(2000, 10, 20),
                LocalDate.of(2008, 10, 20), person, 50);

        repository.add(contract);
        assertEquals(contract, repository.findById(1, InternetContract.class).orElse(null));
        assertNull(repository.findById(2, InternetContract.class).orElse(null));
    }

    @Test
    void deleteById() {
        Repository repository = new Repository();

        Person person = new Person(1, "Nick", "", "James",
                LocalDate.of(1990, 10, 10), true, "15 FW53");

        InternetContract contract = new InternetContract(1, 10, LocalDate.of(2000, 10, 20),
                LocalDate.of(2008, 10, 20), person, 50);

        repository.add(contract);
        repository.deleteById(contract.getID());

        assertNull(repository.findById(contract.getID(), InternetContract.class).orElse(null));
    }

    @Test
    void find() {
        Predicate<AbstractContract> pInternetContracts = value -> value.getClass() == InternetContract.class;
        Predicate<AbstractContract> pTVPackageA = value -> value.getClass() == TVContract.class &&
                ((TVContract) value).getPackageName().equals("package A");

        Repository repository = new Repository(contracts).find(pInternetContracts);
        assertTrue(repository.findById(1, InternetContract.class).isPresent() &&
                repository.findById(3, InternetContract.class).isPresent() &&
                repository.findById(5, InternetContract.class).isPresent());

        repository = new Repository(contracts).find(pTVPackageA);
        assertTrue(repository.findById(2, TVContract.class).isPresent() &&
                repository.findById(6, TVContract.class).isPresent());
    }

    @Test
    void sort() {
        Comparator<AbstractContract> comparatorId = Comparator.comparing(AbstractContract::getID);
        Comparator<AbstractContract> comparatorEndDate = Comparator.comparing(AbstractContract::getEndDate);

        Repository repository = new Repository(contracts);
        repository.sort(new BubbleSorter(), comparatorId);
        AbstractContract[] sortedContracts = repository.toArray();
        boolean isSorted = true;
        int n = sortedContracts.length;

        for (int i = 1; i < n && isSorted; i++)
            isSorted = sortedContracts[i - 1].getID() <= sortedContracts[i].getID();
        assertTrue(isSorted);

        repository.sort(new InsertionSorter(), comparatorEndDate);
        sortedContracts = repository.toArray();
        for (int i = 1; i < n && isSorted; i++)
            isSorted = sortedContracts[i - 1].getEndDate().compareTo(sortedContracts[i].getEndDate()) <= 0;
        assertTrue(isSorted);
    }
}
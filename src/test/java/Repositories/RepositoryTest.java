package Repositories;


import Contracts.InternetContract;
import Persons.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

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
}
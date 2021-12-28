package Repositories;

import Contracts.AbstractContract;
import Contracts.InternetContract;
import Contracts.PhoneContract;
import Contracts.TVContract;
import Persons.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    @Test
    void loadFromCSVFile() {
        Repository initialRepository = new Repository(contracts);
        Repository loadedRepository = new Repository();
        String filePath = "D:\\example.txt";

        Loader.saveAsCSVFile(initialRepository, filePath);
        Loader.loadFromCSVFile(loadedRepository, filePath);


        boolean equals = initialRepository.getSize() == loadedRepository.getSize();
        AbstractContract[] contracts = initialRepository.toArray();
        for (int i = 0; i < contracts.length && equals; i++){
            int id = contracts[i].getID();
            AbstractContract loadedContract = loadedRepository.findById(id, contracts[i].getClass()).orElse(null);
            equals = contracts[i].equals(loadedContract);

        }
        assertTrue(equals);
    }



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


}
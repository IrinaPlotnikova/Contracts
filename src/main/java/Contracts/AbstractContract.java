package Contracts;

import Persons.Person;
import lombok.NonNull;

import java.time.LocalDate;


public class AbstractContract {
    protected int ID;
    protected int number;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Person owner;

    public AbstractContract(int ID, int number, LocalDate startDate, LocalDate endDate, Person owner) {
        this.ID = ID;
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (startDate.compareTo(endDate) <= 0)
            this.endDate = endDate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}

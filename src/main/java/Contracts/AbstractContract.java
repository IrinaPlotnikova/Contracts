package Contracts;

import Persons.Person;
import lombok.NonNull;

import java.time.LocalDate;


public class AbstractContract {
    private int ID;

    private int number;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    @NonNull
    private Person owner;

    public AbstractContract(int ID, int number, @NonNull LocalDate startDate, @NonNull LocalDate endDate, @NonNull Person owner) {
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

    @NonNull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NonNull LocalDate startDate) {
        this.startDate = startDate;
    }

    @NonNull
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NonNull LocalDate endDate) {
        if (startDate.compareTo(endDate) <= 0)
            this.endDate = endDate;
    }

    @NonNull
    public Person getOwner() {
        return owner;
    }

    public void setOwner(@NonNull Person owner) {
        this.owner = owner;
    }
}

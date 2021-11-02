package Contracts;

import Persons.Person;
import lombok.NonNull;

import java.time.LocalDate;

public class PhoneContract extends AbstractContract {
    private int numberOfMinutes;
    private int numberOfTexts;
    private int amountOfData;

    public PhoneContract(int ID, int number, @NonNull LocalDate startDate, @NonNull LocalDate endDate,
                         @NonNull Person owner, int numberOfMinutes, int numberOfTexts, int amountOfData) {
        super(ID, number, startDate, endDate, owner);

        if (numberOfMinutes >= 0)
            this.numberOfMinutes = numberOfMinutes;
        if (numberOfTexts >= 0)
            this.numberOfTexts = numberOfTexts;
        if (amountOfData >= 0)
            this.amountOfData = amountOfData;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(int numberOfMinutes) {
        if (numberOfMinutes >= 0)
            this.numberOfMinutes = numberOfMinutes;
    }

    public int getNumberOfTexts() {
        return numberOfTexts;
    }

    public void setNumberOfTexts(int numberOfTexts) {
        if (numberOfTexts >= 0)
            this.numberOfTexts = numberOfTexts;
    }

    public int getAmountOfData() {
        return amountOfData;
    }

    public void setAmountOfData(int amountOfData) {
        if (amountOfData >= 0)
            this.amountOfData = amountOfData;
    }
}

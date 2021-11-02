package Contracts;

import Persons.Person;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneContract)) return false;
        PhoneContract that = (PhoneContract) o;
        return getID() == that.getID() && getNumber() == that.getNumber() && getNumberOfMinutes() == that.getNumberOfMinutes() &&
                getNumberOfTexts() == that.getNumberOfTexts() && getAmountOfData() == that.getAmountOfData() &&
                getStartDate() == that.getStartDate() && getEndDate() == that.getEndDate() && getOwner() == getOwner();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getNumber(), getNumberOfMinutes(), getNumberOfTexts(), getAmountOfData(),
                getStartDate(), getEndDate(), getOwner());
    }

    @Override
    public String toString() {
        return "PhoneContract{" +
                "ID=" + ID +
                ", number=" + number +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", owner=" + owner +
                ", numberOfMinutes=" + numberOfMinutes +
                ", numberOfTexts=" + numberOfTexts +
                ", amountOfData=" + amountOfData +
                '}';
    }
}

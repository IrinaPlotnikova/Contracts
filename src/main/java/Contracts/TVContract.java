package Contracts;

import Persons.Person;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;

public class TVContract extends AbstractContract{
    String packageName;

    public TVContract(int ID, int number, LocalDate startDate, LocalDate endDate, Person owner, String packageName) {
        super(ID, number, startDate, endDate, owner);
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "TVContract{" +
                "ID=" + ID +
                ", number=" + number +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", owner=" + owner +
                ", packageName='" + packageName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TVContract)) return false;
        TVContract that = (TVContract) o;
        return getID() == that.getID() && getNumber() == that.getNumber() && getPackageName().equals(that.getPackageName()) &&
                getStartDate().equals(that.getStartDate()) && getEndDate().equals(that.getEndDate()) && getOwner().equals(that.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getNumber(), getPackageName(), getStartDate(), getEndDate(), getOwner());
    }
}

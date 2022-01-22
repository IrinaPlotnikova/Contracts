package Contracts;

import Persons.Person;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;

public class InternetContract extends AbstractContract{
    private int speed;

    public InternetContract(int ID, int number, LocalDate startDate, LocalDate endDate, Person owner, int speed) {
        super(ID, number, startDate, endDate, owner);
        if (speed >= 0)
            this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed >= 0)
            this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternetContract)) return false;
        InternetContract that = (InternetContract) o;
        return getID() == that.getID() && getNumber() == that.getNumber() && getSpeed() == that.getSpeed() &&
                getStartDate().equals(that.getStartDate()) && getEndDate().equals(that.getEndDate()) && getOwner().equals(that.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getNumber(), getSpeed(), getStartDate(), getEndDate(), getOwner());
    }

    @Override
    public String toString() {
        return "InternetContract{" +
                "ID=" + ID +
                ", number=" + number +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", owner=" + owner +
                ", speed=" + speed +
                '}';
    }
}

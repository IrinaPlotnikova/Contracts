package Persons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
public class Person {
    private int ID;

    @NonNull
    private String firstName;

    @NonNull
    private String patronymicName;

    @NonNull
    private String lastName;

    @NonNull
    private LocalDate dateOfBirth;

    private boolean isMale;

    @NonNull
    private String passport;


    public void setFirstName(@NonNull String firstName) {
        if (firstName.length() > 0) {
            this.firstName = firstName;
        }
    }

    public void setLastName(@NonNull String lastName) {
        if (lastName.length() > 0) {
            this.lastName = lastName;
        }
    }

    public void setPassport(@NonNull String passport) {
        if (passport.length() > 0) {
            this.passport = passport;
        }
    }

    /**
     * Returns person's age. If date of birth is before date 'now' - returns 0.
     *
     * @param now the date on which the person's age was requested.
     */
    public int getAge(@NonNull LocalDate now) {

        return Math.max(0, (int) ChronoUnit.YEARS.between(dateOfBirth, now));
    }
}

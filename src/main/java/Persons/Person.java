package Persons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Calendar;

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
    private Calendar birthday;

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
}

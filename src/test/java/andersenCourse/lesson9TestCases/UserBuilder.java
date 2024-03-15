package andersenCourse.lesson9TestCases;

public class UserBuilder {

    public static Builder emptyUser() {
        return new Builder();
    }

    public static Builder defaultUser() {
        return emptyUser().withFirstName("NameName")
                .withLastName("SurnameSurname")
                .withEmail("usertesting@gmail.com")
                .withPassword("userUser123")
                .withDayOfBirth("6")
                .withMonthOfBirth("March")
                .withYearOfBirth("2024");
    }

    public static Builder registerTestsUser() {
        return emptyUser().withFirstName("NameName")
                .withLastName("SurnameSurname")
                .withEmail("usertestingregister@gmail.com")
                .withPassword("userUser123")
                .withDayOfBirth("6")
                .withMonthOfBirth("March")
                .withYearOfBirth("2024");
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String dayOfBirth;
        private String monthOfBirth;
        private String yearOfBirth;

        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withDayOfBirth(String dayOfBirth) {
            this.dayOfBirth = dayOfBirth;
            return this;
        }

        public Builder withMonthOfBirth(String monthOfBirth) {
            this.monthOfBirth = monthOfBirth;
            return this;
        }

        public Builder withYearOfBirth(String yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public User build() {
            return new User(firstName, lastName, email, password, dayOfBirth, monthOfBirth, yearOfBirth);
        }
    }
}

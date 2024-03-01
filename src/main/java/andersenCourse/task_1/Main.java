package andersenCourse.task_1;


public class Main {
    public static void main(String[] args) {
        //Task 1 - Автоматизируйте по два тест-кейса из каждого модуля, которые вы писали для предыдущего домашнего задания.
        RegistrationTest.validEmailScenario();
        RegistrationTest.emptyFieldsScenario();
        LoginTest.withValidData();
        LoginTest.withInvalidData();
        EditionTest.nameSurnameChange("Albert", "Albertov");
        EditionTest.accountDeletion();
    }
}

import java.util.StringJoiner;

/**
 * Java 1. Lessons 5.
 *
 * @author Babanin Dmitry
 * @version 15.01.2019
 */
public class Homework5 {
    private static class Employee {
        private String fio;
        private String position;
        private String email;
        private String phoneNumber;
        private Double wage;
        private Integer age;

        public Employee(String fio, String position, String email, String phoneNumber, Double wage, Integer age) {
            this.fio = fio;
            this.position = position;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.wage = wage;
            this.age = age;
        }

        public String getFio() {
            return fio;
        }

        public void setFio(String fio) {
            this.fio = fio;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Double getWage() {
            return wage;
        }

        public void setWage(Double wage) {
            this.wage = wage;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                    .add("fio='" + fio + "'")
                    .add("position='" + position + "'")
                    .add("email='" + email + "'")
                    .add("phoneNumber='" + phoneNumber + "'")
                    .add("wage=" + wage)
                    .add("age=" + age)
                    .toString();
        }
    }

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee(
                "Иванов Иван Иванович",
                "разработчик",
                "ivan@ivan.ru",
                "+375291111111",
                756.54,
                24
        );
        employees[1] = new Employee(
                "Петров Петр Петорович",
                "дизайнер",
                "petr@petr.ru",
                "+375294444444",
                690.57,
                22
        );
        employees[2] = new Employee(
                "Александров Александр Александрович",
                "руководитель",
                "alex@alex.ru",
                "+375441111111",
                1154.84,
                28
        );
        employees[3] = new Employee(
                "Степанов Степан Степанович",
                "логист",
                "stepa@stepa.ru",
                "+375298888888",
                659.49,
                25
        );
        employees[4] = new Employee(
                "Васильев Василий Васильевич",
                "менеджер",
                "vasia@vasia.ru",
                "+375446666666",
                721.54,
                23
        );
        StringJoiner joiner = new StringJoiner("\n");
        for (Employee employee : employees)
            joiner.add(employee.toString());
        System.out.println(joiner.toString());
    }
}

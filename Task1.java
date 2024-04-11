
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}


public class Task1 {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные: Фамилия Имя Отчество дата_рождения номер_телефона пол");

            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length == 6) {
                String surname = data[0];
                String name = data[1];
                String patronymic = data[2];
                String birthDate = data[3];
                String phoneNumber = data[4];
                String gender = data[5];


                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                sdf.setLenient(false);
                try {
                    sdf.parse(birthDate);
                } catch (ParseException e) {
                    throw new InvalidDataException("Неверный формат даты. Пожалуйста, введите дату в формате dd.MM.yyyy.");
                }
                try {
                    Long.parseLong(phoneNumber);
                } catch (NumberFormatException e) {
                    throw new InvalidDataException("Неверный формат номера телефона. Номер телефона должен быть целым числом.");
                }

                if (!gender.equals("f") && !gender.equals("m")) {
                    throw new InvalidDataException("Неверный формат пола. Пол должен быть 'f' или 'm'.");
                }

                System.out.println("Фамилия: " + surname);
                System.out.println("Имя: " + name);
                System.out.println("Отчество: " + patronymic);
                System.out.println("Дата рождения: " + birthDate);
                System.out.println("Номер телефона: " + phoneNumber);
                System.out.println("Пол: " + gender);

                try (PrintWriter writer = new PrintWriter(new FileWriter(surname + ".txt", true))) {
                    writer.println(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new Exception("Вы ввели больше данных, чем требуется. Пожалуйста, попробуйте еще раз.");

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}




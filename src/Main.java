import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Подтвердите пароль: ");
        String confirmPassword = scanner.nextLine();

        if (check(login, password, confirmPassword)) {
            System.out.println("Проверка учетных данных успешно пройдена");
        } else {
            System.out.println("Проверка учетных данных не пройдена");
        }
    }

    public static boolean check(String login, String password, String confirmPassword) {
        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Неверный логин");
            return false;
        } catch (WrongPasswordException e) {
            System.out.println("Неверный пароль");
            return false;
        } finally {
            System.out.println("Функция check() отработала");
        }
        return true;
    }

    private static void checkLogin(String login) {
        if (login == null) {
            throw new WrongLoginException();
        }
        if (login.length() > 20) {
            throw new WrongLoginException();
        }
        if (!verifySymbols(login)) {
            throw new WrongLoginException();
        }
    }
    private static void checkPassword(String password, String confirmPassword) {
        if (password == null || confirmPassword == null) {
            throw new WrongPasswordException();
        }
        if (password.length() >= 20) {
            throw new WrongPasswordException();
        }
        if (!verifySymbols(password)) {
            throw new WrongPasswordException();
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException();
        }
    }
    private static boolean verifySymbols(String str) {
        String patternString = "^[a-zA-Z_0-9]+$";
        return str.matches(patternString);
    }
}
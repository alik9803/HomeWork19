public class Main {
    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9]+$";

    public static void main(String[] args) {
        check("login", "pass", "pass");
        check("login***", "pass", "pass");
        check("login", "pasword@@@", "pass");
        check("login", "pass", "pasword");
    }

    private static boolean check(String login, String password, String confirmPassword) {
        boolean isValid = true;
        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println(" Ошибка с веденным логином " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println(" Ошибка с веденным паролем " + e.getMessage());
            isValid = false;
        }
        if (isValid) {
            System.out.println(" Логин и пароль коректны ");
        }
        return isValid;
    }

    private static void checkLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException(" Логин должен содержать лотинские буквы и цыфры ");
        } else if (login.length() > 20) {
            throw new WrongLoginException(" Логин должен быть не больше 20 символов ");
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException(" Пароль должен содержать лотинские буквы и цыфры ");
        } else if (password.length() > 20) {
            throw new WrongPasswordException(" пароль должен быть не больше 20 символов ");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException(" Пароли должены совпадать ");
        }
    }
}
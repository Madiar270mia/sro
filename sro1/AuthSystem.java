import java.util.*;
import java.security.MessageDigest;

class User {
    String email;
    String passwordHash;

    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }
}

public class AuthSystem {

    static List<User> users = new ArrayList<>();

    // 🔐 HASH FUNCTION
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }

    // 📝 REGISTER
    public static void register(String email, String password) {
        String hashed = hashPassword(password);
        users.add(new User(email, hashed));
        System.out.println("Тіркелу сәтті өтті ✅");
    }

    // 🔑 LOGIN
    public static void login(String email, String password) {
        String hashed = hashPassword(password);

        for (User user : users) {
            if (user.email.equals(email) && user.passwordHash.equals(hashed)) {
                System.out.println("Кіру сәтті ✅");
                return;
            }
        }
        System.out.println("Қате email немесе пароль ❌");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1 - Тіркелу");
            System.out.println("2 - Кіру");
            System.out.println("3 - Шығу");
            System.out.print("Таңдаңыз: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Пароль: ");
                String password = sc.nextLine();

                register(email, password);

            } else if (choice == 2) {
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Пароль: ");
                String password = sc.nextLine();

                login(email, password);

            } else {
                System.out.println("Шығу...");
                break;
            }
        }
    }
}
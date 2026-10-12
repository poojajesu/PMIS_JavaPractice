package Day7;

class UserAccount {
    private String username;
    private static int totalUsers = 0;

    UserAccount(String username) {
        this.username = username;
        totalUsers++;
    }

    public String getUsername() {
        return username;
    }

    public static int getTotalUsers() {
        return totalUsers;
    }
}


public class PQ1 {
    public static void main(String[] args) {

        System.out.println("Initial user count: " + UserAccount.getTotalUsers());

        UserAccount u1 = new UserAccount("Pooja");
        UserAccount u2 = new UserAccount("Harshada");
        UserAccount u3 = new UserAccount("Sonali");

        System.out.println("Username: " + u1.getUsername());
        System.out.println("Username: " + u2.getUsername());
        System.out.println("Username: " + u3.getUsername());

        System.out.println("Total users registered: " + UserAccount.getTotalUsers());
    }
}
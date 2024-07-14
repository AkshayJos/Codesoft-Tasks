import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

class User {
  String accn;
  String pwd;
  double balance;
  ArrayList<String> Thistory = new ArrayList<>();

  public User(String accn, String pwd, double balance) {
    this.accn = accn;
    this.pwd = pwd;
    this.balance = balance;
  }
}

class ATM {
  public HashMap<String, User> users = new HashMap<>();

  public void createUser() {
    System.out.println("\n==================================================\n");
    System.out.println("\t\tEnter the following details : ");
    Scanner sc = new Scanner(System.in);
    System.out.print("\nAccount Number : ");
    String accn = sc.nextLine();
    System.out.print("Password : ");
    String pwd = sc.nextLine();
    System.out.print("Amount : ");
    double balance = sc.nextDouble();

    User u = new User(accn, pwd, balance);
    users.put(accn.substring(accn.length() - 4), u);

    System.out.println("\n\t\tUser created successfully !!");
    System.out.println("\n==================================================");
  }

  public boolean login() {
    Scanner sc = new Scanner(System.in);
    System.out.print("\nAccount Number : ");
    String accn = sc.nextLine();
    System.out.print("Password : ");
    String pwd = sc.nextLine();
    if (users.containsKey(accn.substring(accn.length() - 4))
        && users.get(accn.substring(accn.length() - 4)).pwd.equals(pwd)) {

      System.out.println("\n\t\tLogined Successfully !!");
      System.out.println("\n==================================================");
      while (true) {
        System.out.println(
            "\nEnter the key to perform the following options : \n1) Deposite \n2) Withdraw \n3) Tranfer Money \n4) Transection History \n5) Show Balance \n6)Exit");
        int c = sc.nextInt();
        switch (c) {
          case 1:
            deposite(users.get(accn.substring(accn.length() - 4)));
            break;

          case 2:
            withdraw(users.get(accn.substring(accn.length() - 4)));
            break;

          case 3:
            tranfer(users.get(accn.substring(accn.length() - 4)));
            break;

          case 4:
            transectionHistory(users.get(accn.substring(accn.length() - 4)));
            break;

          case 5:
            showBalance(users.get(accn.substring(accn.length() - 4)));
            break;

        }
        if (c == 6) {
          System.out.println("\n==================================================");
          break;
        }
        if (c > 6) {
          System.out.println("\n\t\tInvalid Option !!,Try Again.\n");
        }
        System.out.println("\n==================================================");

      }
      return true;
    }
    return false;
  }

  public boolean checkBalance(User u, double amt) {
    return u.balance >= amt;
  }

  public void transectionHistory(User u) {
    System.out.println("\n\t\tTransection History : \n");
     if(u.Thistory.size() == 0){
        System.out.println("\tNo transections have been made till now !!");
        return;
     }
    for (int i = u.Thistory.size() -1; i >-1 ; i--) {
      char ch = u.Thistory.get(i).charAt(0);
      switch (ch) {
        case 'd':
          System.out.println("Amount " + u.Thistory.get(i).substring(1) + " Rs. is deposited.");
          break;

        case 'w':
          System.out.println("Amount " + u.Thistory.get(i).substring(1) + " Rs. is withdrawed.");
          break;

        case 't':
          if (u.Thistory.get(i).charAt(1) == 's') {
            System.out.println("Amount " + u.Thistory.get(i).substring(6) + " Rs. is transfered to Account No. "
                + users.get(u.Thistory.get(i).substring(2, 6)).accn + ".");
          } else {
            System.out.println("Amount " + u.Thistory.get(i).substring(6) + " Rs. is recieved from Account No. "
                + users.get(u.Thistory.get(i).substring(2, 6)).accn + ".");
          }
      }
    }
  }

  public void showBalance(User u) {
    System.out.println("\n\t\tBalance : ");
    System.out.print("\nAccount no. : " + u.accn);
    System.out.print("\nBalance : " + u.balance + " Rs.");
    System.out.println();
  }

  public void deposite(User u) {
    Scanner sc = new Scanner(System.in);
    System.out.print("\nEnter Amount : ");
    double amt = sc.nextDouble();
    u.balance += amt;
    u.Thistory.add("d" + String.valueOf(amt));
    System.out.println("\n\t\tAmount Deposited, Successfully !!");
  }

  public void withdraw(User u) {
    Scanner sc = new Scanner(System.in);
    System.out.print("\nEnter Amount : ");
    double amt = sc.nextDouble();
    if (checkBalance(u, amt)) {
      u.balance -= amt;
      u.Thistory.add("w" + String.valueOf(amt));
      System.out.println("\n\t\tAmount Withdrawed, Successfully !!");
    } else {
      System.out.println("\n\t\tInsufficient Balance !!");
    }
  }

  public void tranfer(User us) {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n\t\tEnter the details of user2 : ");
    System.out.print("\nAccount Number : ");
    String accn2 = sc.nextLine();
    if (users.containsKey(accn2.substring(accn2.length() - 4))) {
      User ur = users.get(accn2.substring(accn2.length() - 4)); 
      System.out.print("Enter Amount : ");
      double amt = sc.nextDouble();
      if (checkBalance(us, amt)) {
        us.balance -= amt;
        ur.balance += amt;
        us.Thistory.add("ts" + accn2.substring(accn2.length() - 4) + String.valueOf(amt));
        ur.Thistory.add("tr" + us.accn.substring(accn2.length() - 4) + String.valueOf(amt));
        System.out.println("\n\t\tTransaction Successful !!");
      } else {
        System.out.println("\n\t\tInsufficient Balance !!");
      }
    } else {
      System.out.println("\n\t\tInvalid Account number !!,Try Again.");
    }
  }
}

public class ATM_Interface {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ATM b = new ATM();
    System.out.println("\n\n\t\tWelcome to ATM Interface\n");
    System.out.println("\n==================================================");
    while (true) {
      System.out
          .println("\nEnter the choices to perform the operations : \n1)Create Account \n2)Login Account \n3)Exit");
      int c = sc.nextInt();

      switch (c) {
        case 1:
          b.createUser();
          break;

        case 2:
          if (!b.login()) {
            System.out.println("\n\t\tInvalid Account No. or Password. Try Again.");
          }
          break;

      }

      if (c == 3) {
        System.out.println("\n\t\tThank You!!\n");
        System.out.println("==================================================\n");
        break;
      }

      if (c > 3) {
        System.out.println("\n\t\tInvalid Option !!,Try Again.\n");
      }
      System.out.println("==================================================");
    }
  }
}

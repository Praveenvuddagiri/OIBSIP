import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
    
class authentication{
    Scanner sc;
    String name;
    String password;
    double balance;
    String accno;
    authentication(){
        sc = new Scanner(System.in);
        balance=0;
        start();
    }
    public double getBalance(){
        return balance;
    }
    public void start() {
        int res;
        System.out.println("-------- Welcome To ATM --------");
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("1. For Registration");
        System.out.println("2. To Exit\n");
        System.out.print("Enter Your Choice : ");
        res = sc.nextInt();
        sc.nextLine();
        System.out.println();

        switch (res) {
            case 1:
                registration();
                break;
            case 2:
                System.out.println("Bye, Good Day\n");
                System.exit(0);
                break;
            default:
                System.out.println("\nSelect a Valid option!!!\n");
                start();
                break;
        }
    }
    public void registration() {
        System.out.println("-------- ATM User Registration--------");
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.print("Enter user name : ");
        name = sc.nextLine();
        System.out.println();
        takePassword();
        System.out.println();
        takeAccountNumber();
        System.out.println();
        System.out.print("Enter your account balance : ");
        balance = sc.nextDouble();
        sc.nextLine();
        
        System.out.println("\nRegistration successful !!!\n");
        login();
    }
    private void login() {
        System.out.println();
        System.out.println("------- Login To ATM -------");
        System.out.println("-----------------------------\n");
        System.out.print("Enter your user name : ");
        String tempUser = sc.nextLine();
        if(!name.equals(tempUser)){
            System.out.println("\nNo such user name found!!");
            System.out.println();
            login();
        }
        System.out.print("Enter your password : ");
        String temppass = sc.nextLine();
        if(!password.equals(temppass)){
            System.out.println("\nWrong Password!!");
            System.out.println();
            login();
        }
    }
    private void takeAccountNumber() {
        System.out.print("Enter your Account number : ");
        accno = sc.nextLine();
        if(accno.length() < 9 || accno.length() >18){
            System.out.println("Enter a valid Account number(9-18 digits)!!");
            takeAccountNumber();
        }
        else{
            for (int i = 0; i < accno.length(); i++) {
                if(!(accno.charAt(i)>='0' && accno.charAt(i)<='9')){
                    System.out.println("Account number should only have numeric values!!!");
                    takeAccountNumber();
                }
                
            }
        }
    }
    private void takePassword() {
        System.out.print("Enter your password : ");
        password = sc.nextLine();
        System.out.println();
        System.out.print("Re-enter your password : ");
        String temp = sc.nextLine();
        System.out.println();
        if(!password.equals(temp)){
            System.out.println("Both passwords should be same!!!");
            takePassword();
        }
    }
    
}
class withdrawal {
    double balance;
    Scanner sc;
    withdrawal(double balance){
        sc = new Scanner(System.in);
        this.balance = balance;
    }
    public double withdraw(ArrayList<String> transactions) {
        System.out.print("\nEnter the amount for withdrawl : ");
        double temp = sc.nextDouble();
        sc.nextLine();
        if(temp > balance){
            System.out.println("Insufficient fund !!!");
        }
        else{
            balance-=temp;
            System.out.println("\nAmount withdrawl is successfully!!!!");
            transactions.add("withdrawl amount at "+getTime()+" : -"+ temp);
        }
        
        System.out.print("Enter any key to continue...");
        sc.nextLine();
        return balance;
    }
    private String getTime() {
        LocalTime time = java.time.LocalTime.now();
        return "("+time.getHour()+":"+time.getMinute()+":"+time.getSecond()+") ";
    }
}

class Deposit {
    double balance;
    Scanner sc;
    Deposit(double balance){
        sc = new Scanner(System.in);
        this.balance = balance;
    }
    public double deposit(ArrayList<String> transactions) {
        System.out.print("\nEnter the amount to be deposited : ");
        double temp = sc.nextDouble();
        sc.nextLine();
        balance+=temp;
        System.out.println("\nAmount deposited successfully!!!!");
        transactions.add("Desposit amount at "+getTime()+" : +"+ temp);
        System.out.print("Enter any key to continue...");
        sc.nextLine();
        return balance;
    
    }
    private String getTime() {
        LocalTime time = java.time.LocalTime.now();
        return "("+time.getHour()+":"+time.getMinute()+":"+time.getSecond()+") ";
    }
}

class fundTransfer {
    double balance;
    Scanner sc;
    fundTransfer(double balance){
        sc = new Scanner(System.in);
        this.balance = balance;
    }
    public double transfer(ArrayList<String> transactions) {
        System.out.print("\nEnter the account number to trasfer money :");
        String tempAcc = sc.nextLine();
        System.out.print("\nFetching account");
        try {
            Thread.sleep(500);
            System.out.print("..");
            Thread.sleep(500);
            System.out.print("..");
            Thread.sleep(500);
            System.out.print("..");
        } catch (InterruptedException e) {
            
        }

        if(tempAcc.length()<9 || tempAcc.length() >18){
            System.out.println("Invalid Account number!!!");
            System.out.print("Enter any key to continue...");
            sc.nextLine();
            return balance;
        }
        System.out.println("\nAccount Details : ");
        System.out.println("Accountant name : <name>");
        System.out.println("Account number : "+ tempAcc);
        System.out.print("\nDo you want to transfer (Enter 1 for OK):");
        int flag = sc.nextInt();
        sc.nextLine();
        if(flag==1){
            System.out.print("\nEnter the amount to be transfered :");
            int money = sc.nextInt();
            sc.nextLine();
            if(money > balance){
                System.out.println("\nInsufficient fund !!!");
            }
            else{
                balance-=money;
                System.out.println("\nFund transfer is successfully!!!!");
                transactions.add("Fund transfer to "+tempAcc+" at "+getTime()+" : -"+ money);
            }
            System.out.print("Enter any key to continue...");
            sc.nextLine();
        }
        return balance;
    }
    private String getTime() {
        LocalTime time = java.time.LocalTime.now();
        return "("+time.getHour()+":"+time.getMinute()+":"+time.getSecond()+") ";
    }
}

class transactionHistory{
    Scanner sc;
    double balance;
    ArrayList<String> transactions = new ArrayList<>();
    transactionHistory(ArrayList<String> transactions, double balance){
        sc = new Scanner(System.in);
        this.balance = balance;
        this.transactions = transactions;
    }
    public void transactionDisplay() {
        System.out.println("Transactions : ");
        for (String key: transactions) {
            System.out.println(key);
        }
        System.out.println("Total balance :"+balance);
        System.out.print("Enter any key to continue...");
        sc.nextLine();
    }
}

class atm{
    Scanner sc;
    double balance;
    ArrayList<String> transactions = new ArrayList<>();
    atm(){
        sc = new Scanner(System.in);
        authentication auth = new authentication();
        balance = auth.getBalance();
        transactions.add("Account Creation at "+getTime()+": +"+balance);
        banking();
    }
    
    private void banking() {
        System.out.println();
        System.out.println("---------- Banking ----------");
        System.out.println("-----------------------------\n");
        System.out.println("1. For Transaction History");
        System.out.println("2. For Withdraw");
        System.out.println("3. For Deposit");
        System.out.println("4. For Fund Transfer");
        System.out.println("5. For Balance Enquiry");
        System.out.println("6. Exit");
        System.out.print("\nEnter your choice : ");
        int res = sc.nextInt();
        sc.nextLine();
        switch (res) {
            case 1:
                transactionHistory tr = new transactionHistory(transactions, balance);
                tr.transactionDisplay();
                break;
            case 2:
                withdrawal wi = new withdrawal(balance);
                balance = wi.withdraw(transactions);
                break;
            case 3:
                Deposit de = new Deposit(balance);
                balance = de.deposit(transactions);
                break;
            case 4:
                fundTransfer fu = new fundTransfer(balance);
                balance = fu.transfer(transactions);
                break;
            case 5:
                balanceEnquiry();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("\nSelect a valid option!!!\n");
                System.out.print("Enter any key to continue...");
                sc.nextLine();
                banking();
                break;
            
        }
        banking();

    }
    private void balanceEnquiry() {
        System.out.println("\nYour current account balance is : "+balance+"\n");
        System.out.print("Enter any key to continue...");
        sc.nextLine();
        banking();
    }
    private String getTime() {
        LocalTime time = java.time.LocalTime.now();
        return "("+time.getHour()+":"+time.getMinute()+":"+time.getSecond()+") ";
    }
    
    public static void main(String[] args) {
        new atm();
    }
}

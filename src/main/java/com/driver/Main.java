package com.driver;

public class Main {
    public static void main(String[] args) {
        try{
            BankAccount acc1 = new BankAccount("Shubham", 5000, 4000);
            System.out.println(acc1.getName());
            acc1.withdraw(1000);
            System.out.println(acc1.getBalance());
            System.out.println(acc1.generateAccountNumber(3, 24));

            CurrentAccount currAcc = new CurrentAccount("Abhishek", 7000, "AAACC");
            currAcc.validateLicenseId();
            System.out.println(currAcc.getTradeLicenseId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
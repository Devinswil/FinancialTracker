package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTracker {

    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void main(String[] args) {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();
    }

    public static void loadTransactions(String fileName) {
        // This method should load transactions from a file with the given file name.
        // If the file does not exist, it should be created.
        // The transactions should be stored in the `transactions` ArrayList.
        // Each line of the file represents a single transaction in the following format:
        // <date>|<time>|<description>|<vendor>|<amount>
        // For example: 2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
        // After reading all the transactions, the file should be closed.
        // If any errors occur, an appropriate error message should be displayed.

        //Created this method to read from file and split information into its corresponding variable
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] tranParts = line.split("\\|");
                if (tranParts.length == 5) {
                    LocalDate date = LocalDate.parse(tranParts[0], DATE_FORMATTER);
                    LocalTime time = LocalTime.parse(tranParts[1], TIME_FORMATTER);
                    String description = tranParts[2];
                    String vendor = tranParts[3];
                    double amount = Double.parseDouble(tranParts[4]);
                    Transaction nTransaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(nTransaction);
                }
            }


        } catch (Exception e) {
            System.err.println("That file does not exist.");


        }
    }

    private static void addDeposit(Scanner scanner) {
        // This method should prompt the user to enter the date, time, description, vendor, and amount of a deposit.
        // The user should enter the date and time in the following format: yyyy-MM-dd HH:mm:ss
        // The amount should be a positive number.
        // After validating the input, a new `Transaction` object should be created with the entered values.
        // The new deposit should be added to the `transactions` ArrayList.
        System.out.println("What is the date of this date of this deposit?");
        LocalDate dDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
        System.out.println("What is the time of this deposit?");
        LocalTime dTime = LocalTime.parse(scanner.nextLine(), TIME_FORMATTER);
        System.out.println("What is the description of this deposit?");
        String dDesc = scanner.nextLine();
        System.out.println("Who is the Vendor of this deposit?");
        String dVend = scanner.nextLine();
        System.out.println("How much would you like to deposit?");
        double dAmount = scanner.nextDouble();
        if (dAmount <= 0) {
            System.err.println("Error! You can not deposit a negative amount!");
            return;

        }
        transactions.add(new Transaction(dDate, dTime, dDesc, dVend, dAmount));
        try (BufferedWriter br = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String output = String.format("%s|%s|%s|%s|%.2f%n", dDate.format(DATE_FORMATTER), dTime.format(TIME_FORMATTER), dDesc, dVend, dAmount);
            System.out.println(output);
            br.write(output);
            System.out.println("payment was successful");
            scanner.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void addPayment(Scanner scanner) {
        // This method should prompt the user to enter the date, time, description, vendor, and amount of a payment.
        // The user should enter the date and time in the following format: yyyy-MM-dd HH:mm:ss
        // The amount received should be a positive number then transformed to a negative number.
        // After validating the input, a new `Transaction` object should be created with the entered values.
        // The new payment should be added to the `transactions` ArrayList.
        System.out.println("What is the date of this date of this payment");
        LocalDate pDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
        System.out.println("What is the time of this payment?");
        LocalTime pTime = LocalTime.parse(scanner.nextLine(), TIME_FORMATTER);
        System.out.println("What is the description of this payment?");
        String pDesc = scanner.nextLine();
        System.out.println("Who is the Vendor of this payment?");
        String pVend = scanner.nextLine();
        System.out.println("How much would you like to pay?");
        double pAmount = scanner.nextDouble();
        double dudAmount = pAmount * -1;
        transactions.add(new Transaction(pDate, pTime, pDesc, pVend, dudAmount));
        try (BufferedWriter br = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String output = String.format("%s|%s|%s|%s|%.2f%n", pDate.format(DATE_FORMATTER), pTime.format(TIME_FORMATTER), pDesc, pVend, dudAmount);
            System.out.println(output);
            br.write(output);
            System.out.println("payment was successful");
            scanner.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) A`ll");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void displayLedger() {
        // This method should display a table of all transactions in the `transactions` ArrayList.
        // The table should have columns for date, time, description, vendor, and amount.
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void displayDeposits() {
        // This method should display a table of all deposits in the `transactions` ArrayList.
        // The table should have columns for date, time, description, vendor, and amount.
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }

        }
    }

    private static void displayPayments() {
        // This method should display a table of all payments in the `transactions` ArrayList.
        // The table should have columns for date, time, description, vendor, and amount.
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }

        }
    }

    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Generate a report for all transactions within the current month,
                    // including the date, time, description, vendor, and amount for each transaction.

                    LocalDate sD=LocalDate.now();
                    LocalDate sM=LocalDate.now().withDayOfMonth(1);
                    filterTransactionsByDate(sM,sD);
                    break;
                case "2":
                    // Generate a report for all transactions within the previous month,
                    // including the date, time, description, vendor, and amount for each transaction.
                    LocalDate sD2 =LocalDate.now();
                    LocalDate sLM=LocalDate.now().minusMonths(1).withDayOfMonth(1);
                    filterTransactionsByDate(sLM,sD2);
                    break;
                case "3":
                    // Generate a report for all transactions within the current year,
                    // including the date, time, description, vendor, and amount for each transaction.
                    LocalDate sD3=LocalDate.now();
                    LocalDate sY=LocalDate.now().withDayOfYear(1);
                    filterTransactionsByDate(sY,sD3);
                    break;

                case "4":
                    // Generate a report for all transactions within the previous year,
                    // including the date, time, description, vendor, and amount for each transaction.
                    LocalDate sD4=LocalDate.now();
                    LocalDate sLY=LocalDate.now().minusYears(1).withDayOfYear(1);
                    filterTransactionsByDate(sLY,sD4);
                    break;
                case "5":
                    // Prompt the user to enter a vendor name, then generate a report for all transactions
                    // with that vendor, including the date, time, description, vendor, and amount for each transaction.
                    System.out.println("What vendor would you like to search for?");
                    String vendor = scanner.nextLine();
                    filterTransactionsByVendor(vendor);

                    break;
                case "0":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
        // This method filters the transactions by date and prints a report to the console.
        // It takes two parameters: startDate and endDate, which represent the range of dates to filter by.
        // The method loops through the transactions list and checks each transaction's date against the date range.
        // Transactions that fall within the date range are printed to the console.
        // If no transactions fall within the date range, the method prints a message indicating that there are no results.
        // Adjust endDate to the end of the month if needed
       // endDate = endDate.withDayOfMonth(endDate.lengthOfMonth());

        boolean found = false;

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();

            // Check if the transaction date is within the range
            if ((transactionDate.isAfter(startDate) || (transactionDate.isEqual(startDate)) &&
                    (transactionDate.isBefore(endDate) || transactionDate.isEqual(endDate)))) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found within the specified date range.");
        }

    }


    private static void filterTransactionsByVendor(String vendor) {
        // This method filters the transactions by vendor and prints a report to the console.
        // It takes one parameter: vendor, which represents the name of the vendor to filter by.
        // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
        // Transactions with a matching vendor name are printed to the console.
        // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.
        boolean found = false;

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
                found = true;

            }
        }
        if (!found) {
            System.out.println("Vendor not found!");
        }
    }
}
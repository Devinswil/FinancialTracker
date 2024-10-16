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
        //Created a buffered writer for both deposit and payment in order to write to the .csv file
        // Set the parameter to true in order to save it to the file after code runs
    private static void addDeposit(Scanner scanner) {

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
            br.write(output);
            System.out.println("payment was successful");
            scanner.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void addPayment(Scanner scanner) {

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

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
            // transaction >0 because all deposits should be positive
    private static void displayDeposits() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }

        }
    }
            // transaction <0 because we made the transaction amount negative in addPayment method
    private static void displayPayments() {
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

                    LocalDate startMonth2Date = LocalDate.now();
                    LocalDate endMonth2Date = LocalDate.now().withDayOfMonth(1);
                    filterTransactionsByDate(endMonth2Date, startMonth2Date);
                    break;

                case "2":

                    LocalDate startPerviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                    LocalDate endPerviousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth());
                    filterTransactionsByDate(startPerviousMonth, endPerviousMonth);
                    break;
                case "3":

                    LocalDate startYear2Date = LocalDate.now();
                    LocalDate endYear2Date = LocalDate.now().withDayOfYear(1);
                    filterTransactionsByDate(endYear2Date, startYear2Date);
                    break;

                case "4":
                    //
                    LocalDate startPerviousYear = LocalDate.now().minusYears(1).withDayOfYear(1);
                    LocalDate endPerviousYear = LocalDate.now().minusYears(1).withDayOfMonth(LocalDate.now().minusYears(1).lengthOfMonth());
                    filterTransactionsByDate(startPerviousYear, endPerviousYear);
                    break;

                case "5":

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

            //Added .isEqual() || .isAfter/Before() to bypass dates being skipped
    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {

        boolean found = false;

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();

            // Check if the transaction date is within the range
            if ((transactionDate.isEqual(startDate) || (transactionDate.isAfter(startDate)) &&
                    (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate)))) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found within the specified date range.");
        }

    }


    private static void filterTransactionsByVendor(String vendor) {

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
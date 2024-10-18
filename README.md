# Devin Wilson's Financial Tracker

## Description of the Project

My financial tracker is an application designed for financial account owners to manage their money and access bank statements easily. Users can navigate through two key menus: the main menu, where they can make payments and add deposits, and the ledger menu, which displays reports based on dates and vendors. This setup allows users to access everything they need with just a few inputs.


## User Stories

- As a user, As the owner I want a method that will allow users to make a payment. This will allow the users to easily be able to make payments within the app.
- As the owner I would like to have a deposit feature where date, vendor, and amount of money is asked. This will allow for money to be put into accounts.
- As a User I want to be able to load transactions from a file or create one if it does not exist. This will allow for all transactions to be logged.
- As the user I would like a list of all transactions to be shown with the description of the date, time, amount, and vendor. This will allow for users to go back and check the account history.
- As the user I would also like to have payments filter where I am shown only the payments I have made. This will allow me to really see what is going out of my account.
- As the user I want to be able to filter transactions by the dates they were processed. This will allow for me to see how often and when I am making transactions.
- As the owner method where I am able to select a number, and the corresponding action will begin. This will make it easier to navigate the menu.
- As the user I should be able to filter the transaction ledger and only show the deposits I have made. This will allow for more efficient searches within the app.
- As the user I want to be able to filter transactions by the vendor I am making the transactions to. This will give the users a better overlook at where the money is going.
- As the owner I would like to create a "transaction" that holds date, time, description, vendor, and amount. This will allow for the system to run easier and already hold the information that is needed for other tasks.
- * As the owner I would like to ask the user in the menu if they would like to make a new account. This will ask for an email, username, and password.
- * As the owner I would like to prompt users to sign win with their accounts just to make sure their account information is being stored correctly and more personalized monthly updates.
- * As the owner I want to give the user the option to see their monthly account summary, this will show the amount of money they added, deducted, and where it was going.

## Setup

For Payments and Deposit
1. Insert date following this format "yyyy-MM-dd"
2. Insert time following this format "HH:mm:ss"
3. Next list description, vendor, and amount of a deposit.

Ledger Menu
1. Press "L" in main menu.
2. "D" and "P" will show payment and deposit history.
3. "R" will allow to see reports based vendors and dates.
4. Dates range current month to pervious year.
5. "5" will allow for you to enter the vendor to want to search for.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'YourMainClassName.main()'' to start the application.

## Technologies Used

- Java: Intellij IDEA Community Edition 2022.3.2


## Demo

https://yearuptemp-my.sharepoint.com/:w:/g/personal/dwilson_pgh_yearup_org/EcyRkEl03gFOhj15xA1JvN0B3MU_ALhYAYm1eNfuScUx2g?e=rCUsgM

## Future Work



- *  I would like to ask the user in the menu if they would like to make a new account. This will ask for an email, username, and password.
- *  I would like to prompt users to sign win with their accounts just to make sure their account information is being stored correctly and more personalized monthly updates.
- *  I want to give the user the option to see their monthly account summary, this will show the amount of money they added, deducted, and where it was going.

## Resources



- Raymond Git Hub https://github.com/RayMaroun
- .dayOfMonth/Year(https://www.tutorialspoint.com/javatime/javatime_localdate_withdayofmonth.htm)
- .minusMonths(https://www.geeksforgeeks.org/localdate-minusyears-method-in-java-with-examples/)
- .thisAfter(https://www.geeksforgeeks.org/localdate-isafter-method-in-java-with-examples/)
- Chat GPT for dumbing down methods.

## Team Members

- **Brandon** - Helped with questions and concerns
- **Cameron** - Helped with loading transaction.csv
- **Emre** - Answered various questions

## Thanks

Thank you to Raymond for continuous support and guidance no matter how dumb the question can be.
I would also like to thank Brandon, Cameron, and Emre for going out their way and helping me!
Once again thank you for all the help it is greatly appreciated.
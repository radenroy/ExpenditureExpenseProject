package main;

import numberlist.InvalidIndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.ObjectArrayList;
import numberlist.objectlist.UncopiableException;
import numberlist.primitivelist.IntegerArrayList;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * This class is a business class of the project. It maintains the data base of
 * the project.
 *
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @author Verena Girgis
 * @version 3/12/2018
 *
 */
public class Expenditures {

    private IntegerArrayList datesList;
    private ObjectArrayList amountList;
    private ArrayList<String> descriptionsList;
    private ArrayList<String> referenceList;

    private String fileRef = "ref.txt";
    private String fileAmount = "amount.txt";
    private String fileDescription = "descriptions.txt";
    private String fileDate = "dates.txt";

    /**
     * This is a constructor which calls the setUp method to set up the fields
     * we need in the class.
     */
    public Expenditures() {
        setUp();
    }

    /**
     * This method reads in the referenceList number file,money amount file,
     * date file,and description file .It uses Scanner to scan in all lines from
     * each file.
     */
    public void read() {
        setUp();

        File file = new File(fileRef);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                referenceList.add(input.nextLine());
            }
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        }

        file = new File(fileAmount);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                amountList.add(amountList.size(), Money.parseMoney(input.nextLine()));
            }//show me the money saved file
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        } catch (InvalidIndexException ex) {
            System.out.println("Invalid insertion index");

        } catch (UncopiableException ex) {
            System.out.println("Uncopiable exception");
        }

        file = new File(fileDescription);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                descriptionsList.add(input.nextLine());
            }
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        }

        file = new File(fileDate);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                datesList.add(Long.parseLong(input.nextLine()));
            }
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        } catch (InvalidIndexException ex) {
            System.out.println("Invalid insertion index");
        }

    }

    /**
     * This method converts the local date to Milliseconds which is data type of
     * long.
     *
     * @param date the local date converts to milliseconds
     * @return the milliseconds of the local date
     */
    private static long localDateToMilliseconds(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * This is a set up method for instantiating needed objects, array lists to
     * each fields of the Expenditures class.
     */
    public void setUp() {
        datesList = new IntegerArrayList();
        amountList = new ObjectArrayList();
        descriptionsList = new ArrayList<>();
        referenceList = new ArrayList<>();
    }

    /**
     * This is a save method which saves the data of the references numbers,
     * amount of money, description of the expenditure, and the dates that
     * entered by the user to the corresponding files.
     */
    public void save() {
        //create a referenceList file. 
        //Then use PrintWriter to write the referenceList numbers saved in the referenceList ArrayList to the file.  
        File file = new File(fileRef);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
            for (int i = 0; i < referenceList.size(); i++) {
                pw.println(referenceList.get(i));
            }
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        } catch (IOException exp) {
            System.out.println("IO Exception");
        }

        //amount save to file
        file = new File(fileAmount);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
            for (int i = 0; i < amountList.size(); i++) {
                pw.println(((Money) amountList.get(i)).toString());
            }
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        } catch (IOException exp) {
            System.out.println("IO Exception");
        } catch (InvalidIndexException ex) {
            System.out.println("Invalid Index Exception");
        }

        //descriptions save to file
        file = new File(fileDescription);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
            for (int i = 0; i < descriptionsList.size(); i++) {
                pw.println(descriptionsList.get(i));
            }
        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        } catch (IOException exp) {
            System.out.println("IO Exception");
        }

        //dates save to file
        file = new File(fileDate);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
            for (int i = 0; i < datesList.size(); i++) {
                pw.println(datesList.get(i));
            }

        } catch (FileNotFoundException exp) {
            System.out.println("File not Found");
        } catch (IOException exp) {
            System.out.println("IO Exception");
        } catch (InvalidIndexException ex) {
            System.out.println("Invalid Index Exception");
        }
    }

    /**
     * This method adds money, date, and the description to amountList,
     * datesList, and descriptionsList.
     *
     * @param money the money amount added to the amountList.
     * @param date the date added to the datesList.
     * @param type the description added to the descriptionsList.
     */
    public void add(String money, LocalDate date, String type) {
        try {
            amountList.add(amountList.size(), Money.parseMoney(money));
        } catch (InvalidIndexException | UncopiableException ex) {
            System.out.println(amountList.size() - 1);
        }

        //add the description
        descriptionsList.add(type);

        //add time
        try {
            datesList.add(localDateToMilliseconds(date));
        } catch (InvalidIndexException ex) {

        }
        //add references
        String ref = UUID.randomUUID().toString();
        referenceList.add(ref.substring(10, 13));
    }

    /**
     * This is a getter method of the date.It gives the date in the specific
     * index. This method returns a long for easier conversion into a date format of your choosing.
     *
     * @param index the index location of the datesList where the date will be
     * returned.
     * @return the date found in the index given.
     */
    public long getDate(int index) {
        try {
            return datesList.get(index);
        } catch (InvalidIndexException ex) {

        }
        return 0;
    }

    /**
     * This is a getter method of the date.
     *
     * @return the date saved in the last index position of the datesList.
     */
    public long getDate() {
        return getDate(datesList.size() - 1);
    }

    /**
     * This is a getter method of the description.It gives the description in
     * the specific index.
     *
     * @param index the index location of the descriptionsList where the
     * description will be returned.
     * @return the description found in the index given.
     */
    public String getDescription(int index) {
        return descriptionsList.get(index);
    }

    /**
     * This is a getter method of the description.
     *
     * @return the description saved in the last index position of the
     * descriptionsList.
     */
    public String getDescription() {
        return getDescription(descriptionsList.size() - 1);
    }

    /**
     * This is a getter method of the money amount.It gives the money amount in
     * the specific index. If the index does not exist, it returns an empty instance of Money.
     *
     * @param index the index location of the amountList where the money amount
     * will be returned.
     * @return the money amount found in the index given.
     */
    public Money getAmount(int index) {
        try {
            return (Money) amountList.get(index);
        } catch (InvalidIndexException ex) {

        }
        return new Money();
    }

    /**
     * This is a getter method of the money amount.
     *
     * @return the money amount saved in the last index position of the
     * amountList.
     */
    public Money getAmount() {
        return getAmount(amountList.size() - 1);
    }

    /**
     * This is a getter method of the referenceList number.It gives the
     * referenceList number in the specific index.
     *
     * @param index the index location of the referenceList where the
     * referenceList number will be returned.
     * @return the referenceList number found in the index given.
     */
    public String getReference(int index) {
        return referenceList.get(index);
    }

    /**
     * This is a getter method of the referenceList number.
     *
     * @return the referenceList number saved in the last index position of the
     * referenceList.
     */
    public String getReference() {
        return getReference(referenceList.size() - 1);
    }

    /**
     * This is a getter method of referenceList index.It gives the index of the
     * specific referenceList number.
     *
     * @param s the referenceList number whose index will be returned.
     * @return the index of the given referenceList number.
     */
    public int getIndexRef(String s) {
        return referenceList.indexOf(s);
    }

    /**
     * This is a setter method of the date.It sets the date of the specific
     * index position.
     *
     * @param index the index position where the date is set.
     * @param date the date to be set in the index given.
     */
    public void setDate(int index, LocalDate date) {
        try {
            datesList.set(index, localDateToMilliseconds(date));
        } catch (InvalidIndexException ex) {
        }
    }

    /**
     * This is a setter method of the money amount.It sets the money amount of
     * the specific index position.
     *
     * @param index the index position where the money amount is set.
     * @param money the money amount to be set in the index given.
     */
    public void setMoney(int index, Money money) {
        try {
            amountList.set(index, money);
        } catch (InvalidIndexException ex) {
        }
    }

    /**
     * This is a setter method of the money amount.It convert the String to
     * Money by using Money.parseMoney and calls the overloaded setMoney method
     * to sets the money amount of the specific index position.
     *
     * @param index the index position where the money amount is set.
     * @param moneyString the amount of money entered as String.
     */
    public void setMoney(int index, String moneyString) {
        setMoney(index, Money.parseMoney(moneyString));
    }

    /**
     * This is a setter method of the description.It sets the description of the
     * specific index position.
     *
     * @param index the index position where the description is set.
     * @param desc the description to be set in the index given.
     */
    public void setDescription(int index, String desc) {
        descriptionsList.set(index, desc);
    }

    /**
     * This method removes description,referenceList number, date, and the money
     * amount in the specific index of each List.
     *
     * @param index the index where the description,referenceList number, date,
     * and the money amount to be deleted is located.
     */
    public void removeAt(int index) {
        descriptionsList.remove(index);
        referenceList.remove(index);
        try {
            datesList.removeAt(index);
            amountList.removeAt(index);
        } catch (InvalidIndexException ex) {
        }
    }

    /**
     * This method gives the size of the list.
     *
     * @return the size of the referenceList which also represent the size of
     * the list.
     */
    public int size() {
        return referenceList.size();
    }

    /**
     * This is a swapping method.It swaps the value in index1 with the value in
     * index2 for amountList, datesList, descriptionsList, and referenceList.
     */
    private void swap(int index1, int index2) {
        try {
            Money moneyTemp = getAmount(index1);
            amountList.set(index1, getAmount(index2));
            amountList.set(index2, moneyTemp);

            long dateTemp = getDate(index1);
            datesList.set(index1, getDate(index2));
            datesList.set(index2, dateTemp);

            String descTemp = getDescription(index1);
            descriptionsList.set(index1, getDescription(index2));
            descriptionsList.set(index2, descTemp);

            String refTemp = referenceList.get(index1);
            referenceList.set(index1, referenceList.get(index2));
            referenceList.set(index2, refTemp);
        } catch (InvalidIndexException ie) {
        }
    }

    // Quick sort
    /**
     * These are quick sorts for money amount, dates, and description with
     * O(n*log(n)) time complexity and O(n) space complexity. This is a good
     * sorting algorithm for our application because our data are randomly
     * entered and quick sorting has a quite good time and space complexity for
     * random input data.
     *
     */
    public void sortAmount() {
        //quick sort for money amount
        quickSortAmount(0, amountList.size() - 1);

    }

    /**
     * This is a quick sort method which sorts the money amount from low to
     * high.
     *
     * @param low the lower index of the money list.
     * @param high the higher index of the money list.
     */
    public void quickSortAmount(int low, int high) {
        if (low < high) {
            int p = partitionAmount(low, high);
            quickSortAmount(low, p - 1);
            quickSortAmount(p + 1, high);
        }
    }

    /**
     * This is a partition method for money sorting which finds a pivot used in
     * the quick sort method.Ï
     *
     * @param low the lower index
     * @param high the higher index
     * @return the index of pivot
     */
    public int partitionAmount(int low, int high) {
        Money pivot = getAmount(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (getAmount(j).compareTo(pivot) <= 0) {
                i = i + 1;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * quick sort for date
     */
    public void sortDate() {
        quickSortDate(0, datesList.size() - 1);

    }

    /**
     * This is a quick sort method which sorts the date chronologically.
     *
     * @param low the lower index of the date list.
     * @param high the higher index of the date list.
     */
    public void quickSortDate(int low, int high) {
        if (low < high) {
            int p = partitionDate(low, high);
            quickSortDate(low, p - 1);
            quickSortDate(p + 1, high);
        }
    }

    /**
     * This is a partition method for dates sorting which finds a pivot used in
     * the quick sort method.Ï
     *
     * @param low the lower index of the date list.
     * @param high the higher index of the date list.
     * @return the index of pivot
     */
    public int partitionDate(int low, int high) {
        Long pivot = getDate(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (getDate(j) <= pivot) {
                i = i + 1;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * quick sort for description
     */
    public void sortDescription() {
        quickSortDescription(0, descriptionsList.size() - 1);

    }

    /**
     * This is a quick sort method which sorts the description chronologically.
     *
     * @param low the lower index of the description list.
     * @param high the higher index of the description list.
     */
    public void quickSortDescription(int low, int high) {
        if (low < high) {
            int p = partitionDescription(low, high);
            quickSortDescription(low, p - 1);
            quickSortDescription(p + 1, high);
        }
    }

    /**
     * This is a partition method for description sorting which finds a pivot
     * used in the quick sort method.Ï
     *
     * @param low the lower index of the description list
     * @param high the higher index of the description list
     * @return the index of pivot
     */
    public int partitionDescription(int low, int high) {
        String pivot = getDescription(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (getDescription(j).compareToIgnoreCase(pivot) <= 0) {
                i = i + 1;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }
}

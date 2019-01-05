package numberlist.objectlist;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * This program represents U.S. currency in dollars and cents. The class is
 * immutable. The constructor can accept positive or negative dollars but only
 * positive cents. It implements Copiable and Comparable of Money.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
public final class Money implements Copiable, Comparable<Money>, Serializable {

    private long dollars;
    private byte cents;

    /**
     * Default constructor. This represents $0.00, setting dollars and cents to
     * 0.
     */
    public Money() {
        this(0, (byte) 0);
    }

    /**
     * Full constructor. Represents U.S. currency in dollars and cents. It can
     * accept positive or negative dollars but only positive cents. If you want
     * a negative number, please input a negative for both the Dollars and
     * Cents. Otherwise, the constructor will recalculate based on the Negatives
     * you have inputed. On assignment, please do not assign the byte more than
     * 127 as it will wrap around.
     *
     * @param dollars the U.S. dollar amount
     * @param cents the U.S. cent amount
     */
    public Money(long dollars, byte cents) {
        this(dollars * 100 + cents);
    }

    /**
     * New Constructor. Represents U.S. currency in dollars and cents. It can
     * accept positive or negative dollars but only positive cents. Takes a
     * specific number of cents and turns the cents into a new Money object.
     *
     * @param totalCents the U.S. cent amount
     */
    public Money(long totalCents) {
        if (totalCents < 0) {
            return;
        }
        this.dollars = totalCents / 100;
        this.cents = (byte) (totalCents % 100);
    }

    /**
     * Gives the dollar amount in the instance of money
     *
     * @return the amount in dollars.
     */
    public long getDollars() {
        return dollars;
    }

    /**
     * Gives the cent amount in the instance of money
     *
     * @return the amount in cents.
     */
    public byte getCents() {
        return cents;
    }

    /**
     * This method sum the money's values with another money
     *
     * @param other Money object that its values will be added the this money
     * @return new money object with new value from the addition process
     */
    public Money add(Money other) {
        long tempValue = ((dollars + other.getDollars()) * 100) + (cents + other.getCents());

        return new Money(tempValue / 100, (byte) (tempValue % 100));

    }

    /**
     * This method subtract the money's values with another
     *
     * @param other Money object that its values will subtract this money value
     * @return new money object with new value from the subtraction process
     */
    public Money subtract(Money other) {
        long tempValue = ((dollars - other.getDollars()) * 100) + (cents - other.getCents());
        
        return new Money(tempValue / 100, (byte) (tempValue % 100));
    }

    /**
     * Provides a string representation of the money in dollars and cents.
     *
     * @return the string form of money
     */
    @Override
    public String toString() {
        DecimalFormat twoDec = new DecimalFormat(".00");
        double centsDouble = cents;
        centsDouble /= 100;

        if (dollars >= 0 && cents >= 0) {
            return "$" + dollars + twoDec.format(centsDouble);
        }

        return "($" + (-1 * dollars) + twoDec.format(centsDouble) + ")";
    }

    /**
     * This method compares a Money object with a specified object for order.
     *
     * @param other the specified Money as an object
     * @return a negative integer for less than, a zero for equal to, or a
     * positive integer if greater than.
     */
    @Override
    public int compareTo(Money other) {
        int compare = Long.compare(dollars, other.getDollars());
        if (compare != 0) {
            return compare;
        }
        return Long.compare(cents, other.getCents());
    }

    /**
     * Copies all fields and makes copies of dynamically allocated memory
     * pointed to by the fields. In other words, it copies an object Money along
     * with the dollars and cents fields.
     *
     * @return the new copied Money to be used for the Copiable interface
     */
    @Override
    public Copiable deepCopy() {
        return new Money(dollars, cents);
    }

    /**
     * Parses the money from a string with a period in it. Please note that the
     * validity of the string is tested before the calling of this method.
     *
     * @param moneyString the string of money given from front-end
     * @return a new money object
     */
    public static Money parseMoney(String moneyString) {
        long dollars = 0, cents = 0;

        if (moneyString.startsWith("$")) {
            moneyString = moneyString.substring(1);
        }

        int index = moneyString.indexOf('.');
        if (index > -1) {
            if (index > 0) {
                String dollarsStr = moneyString.substring(0, index);
                dollars = Long.parseLong(dollarsStr);
            }

            String centsStr = moneyString.substring(index + 1);
            cents = Long.parseLong(centsStr);

        } else {
            dollars = Long.parseLong(moneyString);
        }

        return new Money(dollars * 100 + cents);
    }
}

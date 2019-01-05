package numberlist.objectlist;

/**
 * This class represents complex numbers and how they are used. The class is
 * immutable so once the complex number is created, it cannot be changed. It
 * implements Copiable and Comparable of Complex.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
public final class Complex implements Copiable, Comparable<Complex> {

    //fields
    private double real;
    private double imaginary;

    /**
     * Default Constructor. Sets real and imaginary numbers to zero.
     */
    public Complex() {
        this(0, 0);

    }

    /**
     * Full Constructor. Represents a complex number made up of real and
     * imaginary numbers.
     *
     * @param real the real part of the complex number. These are the numbers
     * that represent a quantity on the number line and include positive,
     * negative, and decimals.
     * @param imaginary the imaginary part of the complex number, the number
     * whose square is negative.
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Gives you the current real part of the complex number.
     *
     * @return the real number of the complex number as a double
     */
    public double getReal() {
        return real;
    }

    /**
     * Gives you the current imaginary part of the complex number.
     *
     * @return the real imaginary of the complex number as a double.
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * This method creates a new object Complex by adding real and imaginary
     * numbers within the overloaded constructor.
     *
     * @param other the given complex number
     * @return the newly added number
     */
    public Complex add(Complex other) {
        return new Complex(this.real + other.getReal(), this.imaginary
                + other.getImaginary());
    }

    /**
     * This method creates a new object Complex by subtracting real and
     * imaginary numbers within the overloaded constructor.
     *
     * @param other The given complex number
     * @return the newly subtracted number
     */
    public Complex subtract(Complex other) {
        return new Complex(this.real - other.getReal(), this.imaginary
                - other.getImaginary());
    }

    /**
     * Provides a string representation of the Complex number using real and
     * imaginary numbers. A complex number has the form a + bi, where a is the
     * real number and b is the imaginary number.
     *
     * @return the Complex number as a String.
     */
    @Override
    public String toString() {
        if (imaginary == 0) {
            return String.valueOf(real);
        }
        if (real == 0) {
            return String.valueOf(imaginary) + "i";
        }
        if (imaginary > 0) {
            return String.valueOf(real) + "+" + String.valueOf(imaginary) + "i";
        }
        return String.valueOf(real) + String.valueOf(imaginary) + "i";
    }

    /**
     * This method compares Complex numbers. It compares the real number of the
     * complex number first, and if it is the same, it will then compare the
     * complex, including the imaginary number, and return the result as an int.
     *
     * @param other the given Complex number as an object
     * @return a negative integer for less than, a zero for equal to, or a
     * positive integer if greater than.
     */
    @Override
    public int compareTo(Complex other) {
        int compare = Double.compare(real, other.getReal());
        if (compare != 0) {
            return compare;
        }

        return Double.compare(imaginary, other.getImaginary());
    }

    /**
     * Copies all fields and makes copies of dynamically allocated memory
     * pointed to by the fields. In other words, it copies an object Complex
     * along with the real and imaginary fields.
     *
     * @return the new copied Complex to be used for the Copiable interface
     */
    @Override
    public Copiable deepCopy() {
        return new Complex(real, imaginary);
    }

}

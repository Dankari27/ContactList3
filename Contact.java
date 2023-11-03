/**
 * The Contact class represents a contact with a name and phone number.
 * It implements the Comparable interface to allow comparison of Contact objects
 * based on their names.
 */
public class Contact implements Comparable<Contact> {
    private String name; // The name of the contact
    private String phoneNumber; // The phone number of the contact

    /**
     * Constructs a Contact object with the specified name and phone number.
     *
     * @param name        The name of the contact.
     * @param phoneNumber The phone number of the contact.
     */
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the name of the contact.
     *
     * @return The name of the contact.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the phone number of the contact.
     *
     * @return The phone number of the contact.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Compares this contact with another contact based on their names.
     *
     * @param other The other contact to compare to.
     * @return A negative value if this contact comes before the other contact, zero
     *         if they have the same name,
     *         or a positive value if this contact comes after the other contact in
     *         alphabetical order.
     */
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.getName());
    }

    /**
     * Returns a string representation of the contact, including its name and phone
     * number.
     *
     * @return A string in the format "Name: [name], Phone Number: [phone number]".
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber;
    }
}

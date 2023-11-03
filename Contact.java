public class Contact implements Comparable<Contact> {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber;
    }
}

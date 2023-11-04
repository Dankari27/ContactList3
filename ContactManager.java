import java.util.Scanner;
import java.io.File;

public class ContactManager {
    private static BST<Contact> contactsBST;

    public static void main(String[] args) {

        // Create a BST of type Integer and load from seprate test file
        BST<Integer> intBST = new BST<>("integers.txt");

        // Insert integers and display them in ascending order
        testIntegerBST(intBST);

        Scanner scanner = new Scanner(System.in);
        boolean validFileName = false;
        String fileName = null;

        while (!validFileName) {
            System.out.print("Enter the filename to store contacts: ");
            fileName = scanner.nextLine();

            // Check if the file exists and is readable
            File file = new File(fileName);
            if (file.exists() && file.canRead()) {
                contactsBST = new BST<>(fileName); // Initialize ContactsBST with the specified filename
                validFileName = true;
            } else {
                System.out.println("Invalid file. Please enter a valid filename.");
            }
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("\nContact Manager Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search for Contact");
            System.out.println("4. Display Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    removeContact(scanner);
                    break;
                case 3:
                    searchContact(scanner);
                    break;
                case 4:
                    displayContacts();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        // Save contacts to the file before exiting
        contactsBST.saveContactsToFile();
        System.out.println("Contact Manager closed.");
    }

    // method for testing different types
    private static void testIntegerBST(BST<Integer> intBST) {
        // Inserting integers into the tree
        intBST.insert(10);
        intBST.insert(5);
        intBST.insert(15);
        intBST.insert(3);
        intBST.insert(7);

        // Printing the integers in ascending order
        System.out.println("Generic Test-Integers in ascending order:");
        intBST.print();

        // Save the updated integers to the "integers.txt" file
        intBST.saveContactsToFile();
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = new Contact(name, phone);
        contactsBST.insert(newContact);
        System.out.println("Contact added successfully.");

        // Save the updated contacts to the file immediately
        contactsBST.saveContactsToFile();
    }

    private static void removeContact(Scanner scanner) {
        System.out.print("Enter the name of the contact you want to remove: ");
        String name = scanner.nextLine();

        // Use the Search method to find the contact
        Contact contact = contactsBST.search(new Contact(name, ""));

        if (contact != null) {
            // Remove the contact from the BST
            contactsBST.remove(new Contact(name, ""));

            System.out.println("Removed contact: " + contact);

            // Save the updated contacts to the file immediately
            contactsBST.saveContactsToFile();
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter the name of the contact you want to search for: ");
        String name = scanner.nextLine();
        Contact contact = contactsBST.search(new Contact(name, ""));
        if (contact != null) {
            System.out.println("Contact found: " + contact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void displayContacts() {
        System.out.println("\nAll Contacts:");
        for (Contact contact : contactsBST.getAllContacts()) {
            System.out.println(contact);
        }
    }
}

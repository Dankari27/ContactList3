import java.io.*;
import java.util.ArrayList;

public class BST<T extends Comparable<T>> {
	private Tree<T> root;
	private String filename;

	/**
	 * Constructs a binary search tree for managing contacts, using a specified
	 * file.
	 *
	 * @param filename The name of the file where contacts are stored and loaded.
	 */
	public BST(String filename) {
		root = null;
		this.filename = filename;
		loadContactsFromFile();
	}

	/**
	 * Searches for a contact in the tree by its name.
	 *
	 * @param target The name of the contact to search for.
	 * @return The contact if found, or null if not found.
	 */
	public T search(T target) {
		if (root == null) {
			return null;
		} else {
			return searchContact(root, target);
		}
	}

	private T searchContact(Tree<T> node, T target) {
		if (node == null) {
			return null;
		}

		int cmp = target.compareTo(node.data);
		if (cmp == 0) {
			return node.data;
		} else if (cmp < 0) {
			return searchContact(node.left, target);
		} else {
			return searchContact(node.right, target);
		}
	}

	/**
	 * Adds a new contact to the binary search tree.
	 *
	 * @param value The contact to be added.
	 */
	public void insert(T value) {
		if (root == null) {
			root = new Tree<>(value);
		} else {
			root = insertContact(root, value);
		}
	}

	private Tree<T> insertContact(Tree<T> node, T value) {
		if (node == null) {
			return new Tree<>(value);
		}

		int cmp = value.compareTo(node.data);
		if (cmp < 0) {
			node.left = insertContact(node.left, value);
		} else if (cmp > 0) {
			node.right = insertContact(node.right, value);
		}

		return node;
	}

	/**
	 * Prints the contacts in ascending order.
	 */
	public void print() {
		printInOrder(root);
	}

	private void printInOrder(Tree<T> node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.println(node.data);
			printInOrder(node.right);
		}
	}

	/**
	 * Retrieves all contacts in the tree.
	 *
	 * @return An ArrayList containing all contacts in the tree.
	 */
	public ArrayList<T> getAllContacts() {
		ArrayList<T> allContacts = new ArrayList<>();
		collectContactsInOrder(root, allContacts);
		return allContacts;
	}

	private void collectContactsInOrder(Tree<T> node, ArrayList<T> contacts) {
		if (node != null) {
			collectContactsInOrder(node.left, contacts);
			contacts.add(node.data);
			collectContactsInOrder(node.right, contacts);
		}
	}

	private void loadContactsFromFile() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					String name = parts[0];
					String phone = parts[1];
					T contact = (T) new Contact(name, phone);
					insert(contact);
				}
			}

			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Error reading contacts from file: " + e.getMessage());
		}
	}

	/**
	 * Saves the contacts to the specified file.
	 */
	public void saveContactsToFile() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
			saveContactsInOrder(root, bufferedWriter);
			bufferedWriter.close();
		} catch (IOException e) {
			System.err.println("Error saving contacts to file: " + e.getMessage());
		}
	}

	private void saveContactsInOrder(Tree<T> node, BufferedWriter bufferedWriter) throws IOException {
		if (node != null) {
			saveContactsInOrder(node.left, bufferedWriter);
			bufferedWriter.write(((Contact) node.data).getName() + "," + ((Contact) node.data).getPhoneNumber());
			bufferedWriter.newLine();
			saveContactsInOrder(node.right, bufferedWriter);
		}
	}

	/**
	 * Removes a contact from the tree by its name.
	 *
	 * @param target The name of the contact to remove.
	 * @return The removed contact if found, or null if not found.
	 */
	public T remove(T target) {
		Tree<T> newRoot = removeContact(root, target);

		if (newRoot != null) {
			root = newRoot;
			return search(target);
		} else {
			root = newRoot;
			return search(target);
		}
	}

	private Tree<T> removeContact(Tree<T> node, T target) {
		if (node == null) {
			return null;
		}

		int cmp = target.compareTo(node.data);
		if (cmp < 0) {
			node.left = removeContact(node.left, target);
		} else if (cmp > 0) {
			node.right = removeContact(node.right, target);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				Tree<T> inOrderSuccessor = findInOrderSuccessor(node.right);
				node.data = inOrderSuccessor.data;
				node.right = removeContact(node.right, inOrderSuccessor.data);
			}
		}
		return node;
	}

	private Tree<T> findInOrderSuccessor(Tree<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
}

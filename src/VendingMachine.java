import java.util.Scanner;

public class VendingMachine {

	private Item[] items;

	public VendingMachine() {
		items = new Item[3];
		items[0] = new Item("Coke", 25, 20);
		items[1] = new Item("Pepsi", 35, 20);
		items[2] = new Item("Soda", 45, 20);

	}

	public void displayInventory() {
		for (Item item : items) {
			System.out.print(item.getName());
			System.out.print('\t');
			System.out.print(item.getQty());
			System.out.println();
		}
	}

	public void dispenseItem(int itemCode) {
		Scanner in = new Scanner(System.in);
		if (items[itemCode].getQty() <= 0) {
			System.out.println("Sorry, out of stock");
		} else {
			System.out.println("Pence: " + items[itemCode].getPrice());
			System.out.print("Enter money: ");
			double amt = in.nextDouble();
			if (amt < items[itemCode].getPrice()) {
				System.out.println("Insufficient money paid, can't dispense " + items[itemCode].getName());
				System.out.println("Refunding " + amt);
			} else {
				System.out.println("Dispensing one " + items[itemCode].getName());
				double changeAmt = amt - items[itemCode].getPrice();
				if (changeAmt > 0)
					System.out.println("Here is your change amount of " + changeAmt);
				items[itemCode].reduceQty();
			}
		}
	}

	Item[] getItems() {
		return items;
	}

	public static void main(String args[]) {

		Scanner in = new Scanner(System.in);
		VendingMachine vm = new VendingMachine();
		Item[] vmItems = vm.getItems();

		System.out.println("Vending Machine Menu");
		for (int i = 0; i < vmItems.length; i++) {
			System.out.println("Enter " + (i + 1) + " for " + vmItems[i].getName());
		}

		System.out.println("Enter 4 to stop the Vending Machine");

		int choice;
		do {
			System.out.print("Enter your choice: ");
			choice = in.nextInt();

			if (choice < 1 || choice > 4) {
				System.out.println("Incorrect choice");
			} else if (choice == 4) {
				System.out.println("Stopping Vending Machine...");
			} else {
				vm.dispenseItem(choice - 1);
			}
		} while (choice != 4);

	}

}

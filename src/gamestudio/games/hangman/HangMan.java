package gamestudio.games.hangman;


import java.util.*;

public class HangMan {

	public static void startHangMan() {

		char name[] = {};
		int life = 13, win = 0;
		
		System.out.println("Enter the word by characters");
	
		List<String> list = new ArrayList<String>();
        Scanner stdin = new Scanner(System.in);

        do {
            System.out.println("Current list is " + list);
            System.out.println("Add more? (y/n)");
            if (stdin.next().startsWith("y")) {
                System.out.println("Enter : ");
                list.add(stdin.next());
            } else {
                break;
            }
        } while (true);

        System.out.println("List is " + list);
        String[] arr = list.toArray(new String[0]);
        System.out.println("Array is " + Arrays.toString(arr));
		
		

		while (life > 0) {

			System.out.println("Lifes: " + life);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a character: ");
			char c = scanner.next().charAt(0);
			System.out.println("You have entered: " + c);

			if (life == 1) {
				System.out.println("You lost!");
				break;
			}

			for (int i = 0; i < name.length; i++) {

				if (c == name[i]) {
					System.out.println("Good job! Your guess " + c + " was right!");
					i = 0;
					win++;

					if (win == name.length) {
						System.out.println("You won!");
						break;
					}

					break;
				}
				else{
					System.out.println("Bad one! Your guess " + c + " was wrong!");

				}
				if (i == (name.length - 1)) {
					life--;
				}
			}
			if (win == name.length) {
				break;
			}
		}
	}
}
package collegeCareer.ui;

import java.util.Scanner;

public class GeneralUI {
	Scanner input = new Scanner(System.in);
	
	public GeneralUI() {
		int menuNum = 0;
		while (true) {
			printMainMenu();
			
			try {
				menuNum = input.nextInt();
				switch (menuNum) {
					case 1: new AdminUI();	break;
					case 2: new StudentUI();	break;
					case 0: exit();				return;
					default:
						System.out.println("[����] �ٽ� �����ϼ���.");
				}
			}
			catch (Exception e) {
				System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
				input.nextLine();
			}
		}		
	}
	
	public void printMainMenu() {
		System.out.println();
		System.out.println("[ CC�� �����մϴ�. ]");
		System.out.println("1. ������ ����");
		System.out.println("2. �л� ����");
		System.out.println("0. ���α׷� ����");
		System.out.print("** ��ȣ ���� > ");
	}
	
	public void exit() {
		System.out.println("���α׷��� �����մϴ�.");
		return;
	}
	
}

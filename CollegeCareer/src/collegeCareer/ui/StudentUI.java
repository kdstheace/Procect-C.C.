package collegeCareer.ui;

import java.util.ArrayList;
import java.util.Scanner;

import collegeCareer.manager.CCManager;
import collegeCareer.vo.GradReq;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Major;
import collegeCareer.vo.Student;

public class StudentUI {
	Scanner sc = new Scanner(System.in);
	CCManager mgr = new CCManager();
	Student student = null;
	
	public StudentUI() {
		boolean res = login();
		if(!res) return;
		while(true) {
			int menuNum = 0;
			printMainMenu();
			
			try {
				menuNum = sc.nextInt();
				switch (menuNum) {
					case 1: updatePW(); 		break;
					case 2: getGradReq(); 		break;
					case 3: recommendMajor();	break;
					case 4: recommendLibArts();	break;
					case 0: 					return;
					default:
					System.out.println("[����] �ٽ� �����ϼ���.");
				}
			} catch (Exception e) {
				System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
				sc.nextLine();
			}
		}
	}
	/*
	 * �α��� �޴�
	 */
	public boolean login() {
		String studentID = null;
		String password = null;
		
		while(true) {
			System.out.println();
			System.out.println("[ �л� �������� �α��� ]");
			System.out.print("ID: ");
			studentID = sc.nextLine().toUpperCase();
			System.out.print("PW: ");
			password = sc.nextLine();
			
			student = mgr.getStudent(studentID);
					
			if(student == null || !student.getPassword().equals(password)) {
				System.out.println("[�˸�] ���̵� �Ǵ� �н����尡 �߸��Ǿ����ϴ�.");
				return false;
			}else if(studentID.equalsIgnoreCase(student.getStudentID())){
				System.out.println();
				System.out.println("======= "+ student.getStudentName() + "�� ȯ���մϴ�. =======");
				return true;
			}
		}
	}
	/*
	 * ���θ޴�
	 */
	public void printMainMenu() {
		System.out.println();
		System.out.println("[ �л� ���� ]");
		System.out.println("== �������� ==");
		System.out.println("1. ��й�ȣ ����");
		System.out.println("2. ������� ��");
		System.out.println("3. ��������");
		System.out.println("4. ���缳��");
		System.out.println("0. ���� �޴���");
		System.out.print("** ��ȣ ���� > ");
	}
	
	/*
	 * 1. ��й�ȣ ����
	 */
	public void updatePW() {
		String password = null;
		String newPassword = null;
		String newPassword2 = null;
		while(true) {
			System.out.println();
			System.out.println("[ ��й�ȣ ���� ]");
			System.out.print("���� ��й�ȣ �Է�: ");
			password = sc.next();
			System.out.print("���ο� ��й�ȣ �Է�: ");
			newPassword = sc.next();
			System.out.print("���ο� ��й�ȣ �ٽ� �Է�: ");
			newPassword2 = sc.next();
			
			if(!password.equals(student.getPassword())) {
				System.out.println("[����] ���� ��й�ȣ�� �߸� �ԷµǾ����ϴ�.");
				return;
			} else if(!newPassword.equals(newPassword2)) {
				System.out.println("[����] �ٽ� �Է��� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return;
			} else {
				student.setPassword(newPassword);
				boolean res = mgr.updateStudent(student);
				if(res) {
					System.out.println("[�˸�] ��й�ȣ�� ����Ǿ����ϴ�.");
				} else {
					System.out.println("[�˸�] ��й�ȣ ������ �����߽��ϴ�.");
				}
				return;
			}
		}
	}
	/*
	 * 2. ������� ��
	 */
	public void getGradReq() {
		double version = student.getVersion();
		GradReq gradReq = mgr.getGradReq(version);
		System.out.println();
		System.out.println("[ ���� ������� : ver." + version + " ]");
		System.out.print("�� ���� ����: " + gradReq.getGradMajorCredit());
		if(gradReq.getGradMajorCredit() > student.getGrossCreditMajor()) 
				System.out.println("\t(" + (gradReq.getGradMajorCredit() - student.getGrossCreditMajor()) +"�� ����)");
		else	System.out.println("\t(�޼�!)");
		System.out.print("�� ���� ����: " + gradReq.getGradLiberalCredit());
		if(gradReq.getGradLiberalCredit() > student.getGrossCreditLiberal()) 
				System.out.println("\t(" + (gradReq.getGradLiberalCredit() - student.getGrossCreditLiberal()) +"�� ����)");
		else	System.out.println("\t(�޼�!)");
		System.out.print("�� �̼� ���� : " + gradReq.getGradGrossCredit());
		if(gradReq.getGradGrossCredit() > student.getGrossCredit()) 
				System.out.println("\t(" + (gradReq.getGradGrossCredit() - student.getGrossCredit()) +"�� ����)");
		else	System.out.println("\t(�޼�!)");
		System.out.print("���� ���: " + gradReq.getToeic());
		if(gradReq.getToeic() > student.getToeic()) 
				System.out.println("\t(" + (gradReq.getToeic() - student.getToeic()) +"�� ����)");
		else	System.out.println("\t(�޼�!)");
		System.out.print("HSK ���: " + gradReq.getHsk());
		if(gradReq.getHsk() > student.getHsk()) 
				System.out.println("\t(�̴޼�)");
		else	System.out.println("\t(�޼�!)");
		System.out.print("JPT ���: " + gradReq.getJpt());
		if(gradReq.getJpt() > student.getJpt()) 
				System.out.println("\t(" + (gradReq.getJpt() - student.getJpt()) +"�� ����)");
		else	System.out.println("\t(�޼�!)");
	} 
	/*
	 * ������õ
	 */
	public void recommendMajor() {
		System.out.println();
		System.out.println("[ ���� ���� ]");
		
		ArrayList<Major> list = mgr.recommendMajor(student);
		if(list.size() == 0 || list == null) {
			System.out.println("[�˸�] �˻� ����� �����ϴ�.");
			return;
		}
		System.out.println("���� " + student.getStudentName() + "�Բ� �� �´� ���� " + list.size() + "���� ��õ�մϴ�!.");
		System.out.println("������ȣ\t�����\t\t������\t��������\tƮ��\t�䱸�г�\t����");
		System.out.println("=============================================================================");
		for(Major major : list) {
			System.out.print(major.getCourseNo() + "\t");
			System.out.print(major.getCourseName() + "\t\t");
			System.out.print(major.getProfName() + "\t");
			System.out.print(major.getCourseCredit() + "\t");
			System.out.print(major.getTrack() + "\t");
			System.out.print(major.getGradeRequired() + "\t");
			System.out.println(major.getScore());
		}
	}
	/*
	 * ������õ
	 */
	public void recommendLibArts() {
		int menuNum = 0;
		
		System.out.println();
		System.out.println("[ ���� ���� ]");
		System.out.println("� �˰����� ����Ͻðڽ��ϱ�?");
		System.out.println("1.�ܱ��� ��� ����");
		System.out.println("2.�ܰ�");
		System.out.println("3.���� ���ʼ����");
		System.out.print("** ��ȣ ���� > ");
		menuNum = sc.nextInt();
		try {
			switch (menuNum) {
//			case 1: recommendLang(); break;
//			case 2: recommendScore();break;
//			case 3: recommendBasic();break;
			default:
				System.out.println("[����] �ٽ� �����ϼ���.");
			}
		} catch (Exception e) {
			System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
			sc.nextLine();
		}
	}

}

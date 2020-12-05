package collegeCareer.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import collegeCareer.vo.Course;
import collegeCareer.vo.GradReq;
import collegeCareer.vo.Major;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Student;
import collegeCareer.manager.CCManager;

public class AdminUI {
	Scanner sc = new Scanner(System.in);
	CCManager mgr = new CCManager();
	
	public AdminUI() {
		boolean res = login();
		if(!res) return;
		while (true) {
			int menuNum = 0;
			printMainMenu();
			
			try {
				menuNum = sc.nextInt();
				switch (menuNum) {
					case 20: getCourse();		 break;
					case 1:  addCourse();		 break;
					case 2:  listCourse();	 	 break;
					case 3:  updateCourse();	 break;
					case 4:  deleteCourse();	 break;
					case 5:  insertStudent();	 break;
					case 6:  searchStudent();	 break;
					case 7:  listStudent();		 break;
					case 8:  updateStudent();	 break;
					case 9:  deleteStudent();	 break;
					case 10: insertGradReq(); 	 break;
					case 11: printGradReq();	 break;
					case 12: updateGradReq();	 break;
					case 13: deleteGradReq();	 break;
					case 0: 				   	 return;
					default:
						System.out.println("[����] �ٽ� �����ϼ���.");
				}
			}
			catch (Exception e) {
				System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
				sc.nextLine();
			}
		}		
	}
	
	public boolean login() {
		String studentID = null;
		String password = null;
		Student student = null;
		
		while(true) {
			System.out.println();
			System.out.println("[ ������ �������� �α��� ]");
			System.out.print("ID: ");
			studentID = sc.nextLine().toUpperCase();
			System.out.print("PW: ");
			password = sc.nextLine();
			
			student = mgr.getStudent(studentID);
					
			if(student == null || !student.getPassword().equals(password)) {
				System.out.println("[�˸�] ���̵� �Ǵ� �н����尡 �߸��Ǿ����ϴ�.");
				return false;
			}else if(student.getStudentID().substring(0,5).equalsIgnoreCase("ADMIN")){
				System.out.println();
				System.out.println("======= "+ student.getStudentName() + "�� ȯ���մϴ�. =======");
				return true;
			}else{
				System.out.println("[�˸�] �л��� ������ �������� ������ �� �����ϴ�.");
				return false;
			}
		}
	}
	
	public void printMainMenu() {
		System.out.println();
		System.out.println("[ ������ ���� ]");
		System.out.print("== �������� ==");
		System.out.print("\t== �л����� ==");
		System.out.println("\t== �������� ==");
		System.out.print("1. ���� ���");
		System.out.print("\t5. �л� ���");
		System.out.println("\t10. ������� ���");
		//
		System.out.print("2. ���� ��ȸ");
		System.out.print("\t6. �л� �˻�");
		System.out.println("\t11. ������� ��ȸ");
		//
		System.out.print("3. ���� ����");
		System.out.print("\t7. �л� ��ü ��ȸ");
		System.out.println("\t12. ������� ����");
		//
		System.out.print("4. ���� ����");
		System.out.print("\t8. �л� ����");
		System.out.println("\t13. ������� ����");
		//
		System.out.println("\t\t9. �л� ����");
		//
		System.out.println("0. ���� �޴���");
		System.out.print("** ��ȣ ���� > ");
	}
	
	public void getCourse() {
		System.out.println();
		System.out.println("[ �����˻� ]");
		sc.nextLine();
		String courseNo = sc.nextLine().toUpperCase();
		Course course = mgr.getCourse(courseNo);
		if(course instanceof Major) {
			Major major = (Major)course;
			System.out.println(major);
		}else if(course instanceof LibArts) {
			LibArts libArts = (LibArts)course;
			System.out.println(libArts);
		}else {
			System.out.println("[�˸�]�ش� ���� ����");
		}
	}
	
	/**
	 * 1. �������
	 */
	public void addCourse() {
		int num = 0;
		Major major = null;
		LibArts libArts = null;
		String courseNo;
		String courseName;
		String profName;
		int courseCredit;
		int track;
		int gradeRequired;
		double score;
		
		//�������� ����
		try {
			System.out.println();
			System.out.println("[ ���� ��� ]");
			System.out.println("1. ��������");
			System.out.println("2. �������");
			System.out.print("** ���� ���� ���� > ");
			num = sc.nextInt();
			 
			if(num < 1 || num > 2) {
				System.out.println("[����] �߸� �����߽��ϴ�.");
				return;
			}
			while(true) {
				System.out.print("������ȣ > ");
				sc.nextLine();
				courseNo = sc.nextLine().toUpperCase();
				if(mgr.getCourse(courseNo) == null) break; 
				System.out.println("[����] �̹� �����ϴ� ������ȣ�Դϴ�.");
			}
			
			System.out.print("����� > ");
			courseName = sc.nextLine().toUpperCase();
			System.out.print("������ > ");
			profName = sc.nextLine().toUpperCase();
			System.out.print("���� > ");
			courseCredit = sc.nextInt();
			System.out.print("Ʈ�� > ");
			track = sc.nextInt();
			System.out.print("�г� ���� > ");
			gradeRequired = sc.nextInt();
			System.out.print("���� > ");
			score = sc.nextDouble();
			boolean res;
			switch(num) {
				case 1: major = new Major(courseNo, courseName, profName, courseCredit, track, gradeRequired, score);
						res = mgr.insertMajor(major);
						if(res) {
							System.out.println("[����] ���� �Ϸ�");
						} else {
							System.out.println("[����] ���� ����");
						}
						break;
				case 2: libArts = new LibArts(courseNo, courseName, profName, courseCredit, track, gradeRequired, score);
						res = mgr.insertLibArts(libArts);
						if(res) {
							System.out.println("[����] ���� �Ϸ�");
						} else {
							System.out.println("[����] ���� ����");
						}
						break;
			}
		} catch (InputMismatchException e){
			System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
			sc.nextLine();
			return;
		}
	}

	/*
	 * 2. ������ȸ
	 * */
	public void listCourse() {
		int num = 0;

		try {
			System.out.println();
			System.out.println("[ ���� ��ȸ  ]");
			System.out.println("1. ���� ��ȸ");
			System.out.println("2. ���� ��ȸ");
			System.out.print("** ��ȣ ���� > ");
			num = sc.nextInt();
			
			switch(num) {
				case 1: ArrayList<Major> majorList = mgr.listMajor();
						if(majorList.size() == 0 || majorList == null) {
							System.out.println("[�˸�] �˻� ����� �����ϴ�.");
							return;
						}
						System.out.println("��" + majorList.size() + "���� �˻� ����� �ֽ��ϴ�.");
						System.out.println("������ȣ\t�����\t\t������\t��������\tƮ��\t�䱸�г�\t����");
						System.out.println("=============================================================================");
						for(Major major : majorList) {
							System.out.print(major.getCourseNo() + "\t");
							System.out.print(major.getCourseName() + "\t\t");
							System.out.print(major.getProfName() + "\t");
							System.out.print(major.getCourseCredit() + "\t");
							System.out.print(major.getTrack() + "\t");
							System.out.print(major.getGradeRequired() + "\t");
							System.out.println(major.getScore());
						}
						break;
				case 2: ArrayList<LibArts> libList = mgr.listLibArts();
						if(libList.size() == 0 || libList == null) {
							System.out.println("[�˸�] �˻� ����� �����ϴ�.");
							return;
						}
						System.out.println("��" + libList.size() + "���� �˻� ����� �ֽ��ϴ�.");
						System.out.println("������ȣ\t�����\t\t������\t��������\tƮ��\t�䱸�г�\t����");
						System.out.println("=============================================================================");
						for(LibArts libArts : libList) {
							System.out.print(libArts.getCourseNo() + "\t");
							System.out.print(libArts.getCourseName() + "\t\t");
							System.out.print(libArts.getProfName() + "\t");
							System.out.print(libArts.getCourseCredit() + "\t");
							System.out.print(libArts.getTrack() + "\t");
							System.out.print(libArts.getGradeRequired() + "\t");
							System.out.println(libArts.getScore());
						}
						break;
				default:
					System.out.println("[����] �߸� �����߽��ϴ�.");
					return;
			}
		} catch (InputMismatchException ex) {
			System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
			sc.nextLine();
		}
	}
	
	/*
	 * 2-1 ��������Ʈ ���
	 * */
	public void printCourse(ArrayList<Object> list) {
		
		if(list.size() == 0 || list == null) {
			System.out.println("[�˸�] �˻� ����� �����ϴ�.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("��" + list.size() + "���� �˻� ����� �ֽ��ϴ�.");
	}

	/*
	 * 3.�������
	 * */
	public void updateCourse() {
		String courseNo = null;
		
		System.out.println();
		System.out.println("[ ���� ���� ]");
		sc.nextLine();
		System.out.print("** ������ ������ ������ȣ > ");
		courseNo = sc.nextLine().toUpperCase();
		
		Course course = mgr.getCourse(courseNo);
		if(course == null) {
			System.out.println("[����] �ش� ������ȣ�� ���� ������ �������� �ʽ��ϴ�.");
			return;
		}else {
			System.out.println();
			System.out.println("* ���� ����");
			System.out.println("������ȣ: " + course.getCourseNo());
			System.out.println("������: " + course.getCourseName());
			System.out.println("������: " + course.getProfName());
			System.out.println("��������: " + course.getCourseCredit());
			System.out.println("Ʈ��: " + course.getTrack());
			System.out.println("�䱸�г�: " + course.getGradeRequired());
			System.out.println("����: " + course.getScore());
			System.out.println("=======================");
			System.out.println("* ������ ����");
			System.out.print("������: ");
			course.setCourseName(sc.nextLine().toUpperCase());
			System.out.print("������: ");
			course.setProfName(sc.nextLine().toUpperCase());
			System.out.print("��������: ");
			course.setCourseCredit(sc.nextInt());
			System.out.print("Ʈ��: ");
			course.setTrack(sc.nextInt());
			System.out.print("�䱸�г�: ");
			course.setGradeRequired(sc.nextInt());
			System.out.print("����: ");
			course.setScore(sc.nextDouble());
			
			boolean res = mgr.updateCourse(course);
			if(res) {
				System.out.println("[����] �����Ǿ����ϴ�.");
			} else {
				System.out.println("[����] ���� �����߽��ϴ�.");
			}
		}
	}

	/*
	 * 4.��������
	 * */
	public void deleteCourse() {
		String courseNo = null;
		System.out.println();
		System.out.println("[ ���� ���� ]");
		sc.nextLine();
		System.out.print("** ������ ������ ������ȣ > ");
		courseNo = sc.nextLine().toUpperCase();
		
		boolean res = mgr.deleteCourse(courseNo);
		if(res) {
			System.out.println("[����] �����Ǿ����ϴ�.");
		} else {
			System.out.println("[����] ���� ����� �������� �ʽ��ϴ�.");
		}	
	}
	
	/*
	 * 5.�л����
	 * */
	public void insertStudent() {
		Student student = new Student();
		String studentID = null;
		System.out.println();
		try {
			System.out.println("[ �л� ��� ]");
			sc.nextLine();
			System.out.print("�й� �Է� > ");
			studentID = sc.nextLine().toUpperCase();
			if(mgr.getStudent(studentID) != null) {
				System.out.println("[����] �̹� �����ϴ� �й��Դϴ�.");
				return;
			}else {
				student.setStudentID(studentID);
				System.out.print("�̸� �Է� > ");
				student.setStudentName(sc.nextLine().toUpperCase());
				System.out.print("������� ���� > ");
				student.setVersion(sc.nextDouble());
				System.out.println("**1.�濵�Ϲ� 2.ȸ�� 3.�Ŵ�����Ʈ 4.���� 5.������ 6.â�� 7.�ֳθ�ƽ��");
				System.out.print("Ʈ�� �Է� > ");
				student.setTrack(sc.nextInt());
				System.out.print("�г� �Է� > ");
				student.setGrade(sc.nextInt()); 
				System.out.print("�� ������� > ");
				student.setGrossCredit(sc.nextInt());
				System.out.print("���� ������� > ");
				student.setGrossCreditMajor(sc.nextInt());
				System.out.print("���� ������� > ");
				student.setGrossCreditLiberal(sc.nextInt());
				System.out.print("���� (���� �� 0)> ");
				student.setToeic(sc.nextInt());
				System.out.print("hsk (���� �� 0)> ");
				student.setHsk(sc.nextInt());
				System.out.print("jpt (���� �� 0)> ");
				student.setJpt(sc.nextInt());
			}
		} catch (InputMismatchException e) {
			System.out.println("[����] �Է������� �߸��Ǿ����ϴ�.");
			sc.nextLine();
			return;
		}
		boolean res = mgr.insertStudent(student);
		if(res) {
			System.out.println("[����] ����Ǿ����ϴ�.");
		}else {
			System.out.println("[����] ���� �����߽��ϴ�.");
		}
	}
	/*
	 * 6.�л��˻�
	 * */
	public void searchStudent() {
		String studentID = null;
		
		System.out.println();
		System.out.println("[ �л� �˻� ]");
		sc.nextLine();
		System.out.print("�й� �Է� > ");
		studentID = sc.nextLine();
		Student student = mgr.getStudent(studentID);
		if(student != null) {
			System.out.println(student);
		}else {
			System.out.println("[�˸�] �ش� �й��� �л��� �����ϴ�.");
		}
		
	}
	/*
	 * 7.�л� ��ü ��ȸ
	 */
	public void listStudent(){
		System.out.println();
		System.out.println("[ ��ü �л� ��ȸ ]");
		ArrayList<Student> list = mgr.listStudent();
		if(list != null && list.size() != 0) {
			System.out.println("�й�\t\t�̸�\t�������\tƮ��\t�г�\t�� �������\t��������\t��������\t����\thsk\tjpt");
			System.out.println("===================================================================================================");
			for(Student student : list) {
				System.out.print(student.getStudentID() + "\t");
				System.out.print(student.getStudentName() + "\t");
				System.out.print(student.getVersion() + "\t");
				System.out.print(student.getTrack() + "\t");
				System.out.print(student.getGrade() + "\t");
				System.out.print(student.getGrossCredit() + "\t\t");
				System.out.print(student.getGrossCreditMajor() + "\t");
				System.out.print(student.getGrossCreditLiberal() + "\t");
				System.out.print(student.getToeic() + "\t");
				System.out.print(student.getHsk() + "\t");
				System.out.println(student.getJpt());
			}
		}else {
			System.out.println("[����] ����� �л��� �����ϴ�.");
		}
		
	}
	
	/*
	 * 8.�л�����
	 * */
	public void updateStudent() {
		String studentID = null;
		System.out.println();
		System.out.println("[ �л����� ���� ]");
		sc.nextLine();
		System.out.print("������ �л� ��ȣ: ");
		studentID = sc.nextLine().toUpperCase();
		Student student = mgr.getStudent(studentID);
		if(student == null) {
			System.out.println("[�˸�] ������ �����Ͱ� �����ϴ�.");
			return;
		}else {
			System.out.println();
			System.out.println("* ���� ����");
			System.out.println("�й�: " + studentID);
			System.out.println("�̸�: " + student.getStudentName());
			System.out.println("����: " + student.getVersion());
			System.out.println("Ʈ��: " + student.getTrack());
			System.out.println("�г�: " + student.getGrade());
			System.out.println("�� ���� ����: " + student.getGrossCreditMajor());
			System.out.println("�� ���� ����: " + student.getGrossCreditLiberal());
			System.out.println("����: " + student.getToeic());
			System.out.println("HSK: " + student.getHsk());
			System.out.println("JPT: " + student.getJpt());
			System.out.println("=======================");
			System.out.println("* ������ ����");
			System.out.print("�̸�: ");
			student.setStudentName(sc.nextLine().toUpperCase());
			System.out.print("����: ");
			student.setVersion(sc.nextDouble());
			System.out.print("Ʈ��: ");
			student.setTrack(sc.nextInt());
			System.out.print("�г�: ");
			student.setGrade(sc.nextInt());
			System.out.print("�� ���� ����: ");
			student.setGrossCreditMajor(sc.nextInt());
			System.out.print("�� ���� ����: ");
			student.setGrossCreditLiberal(sc.nextInt());
			System.out.print("�� �̼� ����: ");
			student.setGrossCredit(mgr.sumCredit(student.getGrossCreditMajor(), student.getGrossCreditLiberal()));
			System.out.println(student.getGrossCredit());
			System.out.print("����: ");
			student.setToeic(sc.nextInt());
			System.out.print("HSK: ");
			student.setHsk(sc.nextInt());
			System.out.print("JPT: ");
			student.setJpt(sc.nextInt());
		}
		
		boolean res = mgr.updateStudent(student);
		if(res) {
			System.out.println("[�˸�] �����Ǿ����ϴ�.");
		} else {
			System.out.println("[�˸�] ���� �����Ͽ����ϴ�.");
		}
		
	}
	
	/*
	 * 9.�л�����
	 * */
	public void deleteStudent() {
		String studentID = null;
		System.out.println();
		System.out.println("[ �л� ���� ���� ]");
		sc.nextLine();
		System.out.print("�й� �Է� > ");
		studentID = sc.nextLine().toUpperCase();
		boolean res = mgr.deleteStudent(studentID);
		if(res) {
			System.out.println("[�˸�] �����Ǿ����ϴ�.");
		}else {
			System.out.println("[�˸�] ������ �����Ͱ� �����ϴ�.");
		}
		
	}
	
	/*
	 * 10.������� ���
	 * */
	public void insertGradReq() {
		GradReq gradReq = new GradReq();
		System.out.println();
		System.out.println("[ ������� ��� ]");
		sc.nextLine();
		System.out.print("���� ���� > ");
		gradReq.setVersion(sc.nextDouble());
		System.out.print("�� �䱸 ���� > ");
		gradReq.setGradGrossCredit(sc.nextInt());
		System.out.print("�� ���� ����  > ");
		gradReq.setGradMajorCredit(sc.nextInt());
		System.out.print("�� ���� ���� > ");
		gradReq.setGradLiberalCredit(sc.nextInt());
		System.out.print("���� ���� > ");
		gradReq.setToeic(sc.nextInt());
		System.out.print("hsk ���� > ");
		gradReq.setHsk(sc.nextInt());
		System.out.print("jpt ���� > ");
		gradReq.setJpt(sc.nextInt());
		
		boolean res = mgr.insertGradReq(gradReq);
		if(res) System.out.println("[����] ����Ǿ����ϴ�.");
		else System.out.println("[�˸�] ���� �����߽��ϴ�.");
	}
	
	/*
	 * 11.������� ��ȸ
	 * */
	public void printGradReq() {
		ArrayList<GradReq> list = null;
		System.out.println();
		System.out.println("[ ������� ��� ]");
		list = mgr.listGradReq();
		if(list != null && list.size() != 0) {
			System.out.println("����\t�� �䱸 ����\t�� ���� ����\t�� ���� ����\t����\thsk\tjpt");
			System.out.println("===========================================================================");
			for(GradReq grad : list) {
				System.out.print(grad.getVersion() + "\t");
				System.out.print(grad.getGradGrossCredit() + "\t\t");
				System.out.print(grad.getGradMajorCredit() + "\t\t");
				System.out.print(grad.getGradLiberalCredit() + "\t\t");
				System.out.print(grad.getToeic() + "\t");
				System.out.print(grad.getHsk() + "\t");
				System.out.println(grad.getJpt());
			}
		} else {
			System.out.println("[����] ����� ��������� �����ϴ�.");
		}
	}
	
	/*
	 * 12.������� ����
	 * */
	public void updateGradReq() {
		double version = 0;
		System.out.println();
		System.out.println("[ ������� ���� ]");
		sc.nextLine();
		System.out.print("������ ������� ����: ");
		version = sc.nextDouble();
		
		GradReq gradReq = mgr.getGradReq(version);
		if(gradReq == null) {
			System.out.println("[�˸�] ������ �����Ͱ� �����ϴ�.");
			return;
		}else {
			System.out.println();
			System.out.println("* ���� ����");
			System.out.println("����: " + version);
			System.out.println("�� ���� ����: " + gradReq.getGradMajorCredit());
			System.out.println("�� ���� ����: " + gradReq.getGradLiberalCredit());
			System.out.println("�� �̼� ���� : " + gradReq.getGradGrossCredit());
			System.out.println("���� ���: " + gradReq.getToeic());
			System.out.println("HSK ���: " + gradReq.getHsk());
			System.out.println("JPT ���: " + gradReq.getJpt());
			System.out.println("=======================");
			System.out.println("* ������ ����");
			sc.nextLine();
			System.out.print("�� ���� ����: ");
			gradReq.setGradMajorCredit(sc.nextInt());
			System.out.print("�� ���� ����: ");
			gradReq.setGradLiberalCredit(sc.nextInt());
			System.out.print("�� �̼� ����: ");
			gradReq.setGradGrossCredit(mgr.sumCredit(gradReq.getGradMajorCredit(), gradReq.getGradLiberalCredit()));
			System.out.println(gradReq.getGradGrossCredit());
			System.out.print("���� ���: ");
			gradReq.setToeic(sc.nextInt());
			System.out.print("HSK ���: ");
			gradReq.setHsk(sc.nextInt());
			System.out.print("JPT ���: ");
			gradReq.setJpt(sc.nextInt());
		}
		
		boolean res = mgr.updateGradReq(gradReq);
		if(res) {
			System.out.println("[�˸�] �����Ǿ����ϴ�.");
		} else {
			System.out.println("[�˸�] ���� �����Ͽ����ϴ�.");
		}
	}
	
	/*
	 * 13.������� ����
	 * */
	public void deleteGradReq() {
		double version = 0;
	
		System.out.println();
		System.out.println("[ ������� ���� ]");
		sc.nextLine();
		System.out.print("������ ������� ����: ");
		version = sc.nextDouble();
		
		boolean res = mgr.deleteGradReq(version);
		if(res) {
			System.out.println("[�˸�] �����Ǿ����ϴ�.");
		}else {
			System.out.println("[�˸�] ������ �����Ͱ� �����ϴ�.");
		}
	}
}

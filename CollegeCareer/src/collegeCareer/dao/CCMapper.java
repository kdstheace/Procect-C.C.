package collegeCareer.dao;

import java.util.ArrayList;

import collegeCareer.vo.GradReq;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Major;
import collegeCareer.vo.Student;

public interface CCMapper {

	//1-1. �������
	public int insertMajor(Major major);
	//1-2. ������
	public int insertLibArts(LibArts libArts);
	//0-1. �����˻�
	public Major getMajor(String courseNo);
	//0-2. ����˻�
	public LibArts getLibArts(String courseNo);
	//2-1. �������� ��ȸ
	public ArrayList<Major> listMajor();
	//2-2. ������� ��ȸ
	public ArrayList<LibArts> listLibArts();
	//3-1. �������� ����
	public int updateMajor(Major major);
	//3-2. ������� ����
	public int updateLibArts(LibArts libArts);
	//4-1. �������� ����
	public int deleteMajor(String courseNo);
	//4-2. ������� ����
	public int deleteLibArts(String courseNo);
	//5. �л� ���
	public int insertStudent(Student student);
	//6. Ư���л� �˻�
	public Student getStudent(String studentID);
	//7. �л� ��ü ��ȸ
	public ArrayList<Student> listStudent();
	//8. �л�����
	public int updateStudent(Student student);
	//9. �л�����
	public int deleteStudent(String studentID);
	//10. ������� �߰�
	public int insertGradReq(GradReq gradReq);
	//11. ������� ��ü��ȸ
	public ArrayList<GradReq> listGradReq();
	//11-1. Ư�� ������Ǹ� ��������
	public GradReq getGradReq(double version);
	//12. ������� ����
	public int updateGradReq(GradReq gradReq);
	//13. ������� ����
	public int deleteGradReq(double version);
	//3.������õ
	public ArrayList<Major> recommendMajor(Student student);

	

}

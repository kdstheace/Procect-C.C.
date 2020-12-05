package collegeCareer.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import collegeCareer.vo.Course;
import collegeCareer.vo.GradReq;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Major;
import collegeCareer.vo.Student;

public class CCDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	//1-1. �������� ���
	public int insertMajor(Major major) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.insertMajor(major);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return count;
		
	}
	
	//1-2. ������� ���
	public int insertLibArts(LibArts libArts) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.insertLibArts(libArts);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return count;
	}
	
	//00. ����˻�
	public Course getCourse(String courseNo) {
		SqlSession ss = null;
		Course course = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			if(mapper.getMajor(courseNo) != null) {
				course = mapper.getMajor(courseNo);
			}else if(mapper.getLibArts(courseNo) != null) {
				course = mapper.getLibArts(courseNo);
			}else {
				course = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return course;
	}
	
	//2-1. �������� ��ȸ
	public ArrayList<Major> listMajor(){
		SqlSession ss = null;
		ArrayList<Major> list = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			list = mapper.listMajor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) ss.close();
		}
		return list;
	}
	
	//2-2. ������� ��ȸ
	public ArrayList<LibArts> listLibArts(){
		SqlSession ss = null;
		ArrayList<LibArts> list = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			list = mapper.listLibArts();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) ss.close();
		}
		return list;
	}
	//3. ��������
	public int updateCourse(Course course) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			if(course instanceof Major) {
				count = mapper.updateMajor((Major)course);
			}
			if(course instanceof LibArts) {
				count = mapper.updateLibArts((LibArts)course);
			}
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) ss.close();
		}
		return count;
	}
	
	//4. �������
	public int deleteCourse(String courseNo) {
		SqlSession ss = null;
		int count = 0, count1 = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.deleteMajor(courseNo);
			count1 = mapper.deleteLibArts(courseNo);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) ss.close();
		}
		return count > count1 ? count : count1;
	}
	
	//5. �л� ���
	public int insertStudent(Student student) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.insertStudent(student);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) {
				ss.close();
			}
		}
		return count;
	}
	
	//5-1. Ư���л� �˻�
	public Student getStudent(String studentID) {
		SqlSession ss = null;
		Student student = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			student = mapper.getStudent(studentID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) {
				ss.close();
			}
		}
		return student;
	}
	//7. �л� ��ü ��ȸ
	public ArrayList<Student> listStudent(){
		SqlSession ss = null;
		ArrayList<Student> list = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			list = mapper.listStudent();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) {
				ss.close();
			}
		}
		return list;
	}
	
	//8. �л�����
	public int updateStudent(Student student) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.updateStudent(student);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ss != null) ss.close();
		}
		return count;
	}
	
	//9. �л�����
	public int deleteStudent(String studentID) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.deleteStudent(studentID);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss != null) ss.close();
		}
		return count;
		
	}
	//10. ������� �߰�
	public int insertGradReq(GradReq gradReq) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.insertGradReq(gradReq);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return count;
	}
	
	//11. ������� ��ü��ȸ
	public ArrayList<GradReq> listGradReq() {
		SqlSession ss = null;
		ArrayList<GradReq> list = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			list = mapper.listGradReq();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return list;
	}
	
	//12.������� ����
	public int updateGradReq(GradReq gradReq) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.updateGradReq(gradReq);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return count;
	}
	
	//11-1. Ư�� ������Ǹ� ��������
	public GradReq getGradReq(double version) {
		SqlSession ss = null;
		GradReq gradReq = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			gradReq = mapper.getGradReq(version);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		return gradReq;
	}
	//13. ������� ����
	public int deleteGradReq(double version) {
		SqlSession ss = null;
		int count = 0;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			count = mapper.deleteGradReq(version);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ss!=null) {
				ss.close();
			}
		}
		return count;
	}
	
	//3.������õ
	public ArrayList<Major> recommendMajor(Student student){
		SqlSession ss = null;
		ArrayList<Major> list = null;
		try {
			ss = factory.openSession();
			CCMapper mapper = ss.getMapper(CCMapper.class);
			list = mapper.recommendMajor(student);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ss!=null) {
				ss.close();
			}
		}
		return list;
	}
}

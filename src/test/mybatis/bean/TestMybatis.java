package test.mybatis.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.TestMybatisDTO;

@Controller
public class TestMybatis {
	
	@Autowired
	private SqlSessionTemplate sqlSession = null;	// ������ �ۼ�, ������ ��������
	
	@RequestMapping("mybatis.do")
	public String helloMybatis() {
		
		System.out.println();
		return "spring01/hello";
	}
	
	@RequestMapping("testTable.do")
	public String testTable(Model md) {
		
		// sqlSession -> DB -> sql������ ȣ�� ������ ��������
		int count = (Integer)sqlSession.selectOne("test.userCount");		//test(table �̸�), userCount (testSQL �� select �� id �־��ذ�)
		// age�� ���� ���� �� ��������
		int maxAge = (Integer)sqlSession.selectOne("test.maxAge");
		// ���̺� ��ü ���ڵ� ��������
		List<TestMybatisDTO> userList = sqlSession.selectList("test.selectAll");
		//List userList = sqlSession.selectList("test.selectAll");
		// id�� �ְ� �ش� id�� ��ġ�ϴ� ���ڵ� ��������
		// �Ķ���ʹ� �Ѱ��� ������ �ִ�.
		// �������� �Ķ���͸� ������ ������ Map ����� ������ �ְ� ��������.
		String id = "123";
		TestMybatisDTO dto = (TestMybatisDTO)sqlSession.selectOne("test.getUser",id);
		// id�ְ� reg ��� �ϳ� ��������
		Timestamp reg = (Timestamp)sqlSession.selectOne("test.getReg", id);
		
		md.addAttribute("count", count);
		md.addAttribute("maxAge", maxAge);
		md.addAttribute("userList", userList);
		md.addAttribute("dto", dto);
		md.addAttribute("reg", reg);
		
		return "spring04/test";
	}
	
	@RequestMapping("insertForm.do")
	public String insertForm() {
		
		
		return "spring04/form";
	}
	
	@RequestMapping("insertPro.do")
	public String insertPro(TestMybatisDTO dto, Model md) {
		
		//DB�� insert
		sqlSession.insert("testInsertUser", dto);
		
		md.addAttribute("dto", dto);
		
		
		return "spring04/pro";
	}
	
	@RequestMapping("updateForm.do")
	public String updateForm(Model md) {
		
		//id �ϳ� �����ְ� �ش� ��ü ���� ��������
		String id = "123";
		TestMybatisDTO dto = (TestMybatisDTO)sqlSession.selectOne("test.getUser", id);
		md.addAttribute("dto", dto);
		
		return "spring04/update";
	}
	
	@RequestMapping("updatePro.do")
	public String updatePro(String pw, int age, Model md) {
		
		HashMap map = new HashMap();
		map.put("mapId", "123");
		map.put("mapPw", pw);
		map.put("mapAge", age);
		
		sqlSession.update("test.updateUser", map);
		
		TestMybatisDTO dto = (TestMybatisDTO)sqlSession.selectOne("test.getUser", "123");
		md.addAttribute("dto", dto);
		
		return "spring04/updatePro";
	}
	
	@RequestMapping("delete.do")
	public String delete() {
		
		return "spring04/delete";
	}
	
	
	@RequestMapping("deletePro.do")
	public String deletePro(String id, Model md) {
		
		sqlSession.delete("test.deleteUser", id);
		md.addAttribute("id", id);
		
		return "spring04/deletePro";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

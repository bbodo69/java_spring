package member.model.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import member.model.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{

	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertMember(MemberVO vo) throws Exception {
			sqlSession.insert("member.insertMember", vo);		
	}

	@Override
	public int idPwCheck(MemberVO vo) throws Exception {
		int check = sqlSession.selectOne("member.idPwCheck", vo);
		
		return check;
	}

	@Override
	public List selectAll() throws Exception {
		
		return null;
	}

	@Override
	public MemberVO selectMember(String id) throws Exception {
				
		MemberVO vo = (MemberVO)sqlSession.selectOne("member.selectMember", id);
		
		return vo;
	}

	@Override
	public void updateMember(MemberVO vo) throws Exception {
		System.out.println("DAOImple");
		sqlSession.update("updateMember", vo);
		
		
	}

	@Override
	public void deleteMember(String id) throws Exception {

		
	}

	@Override
	public int idAvailCheck(String id) throws Exception {
		int check = sqlSession.selectOne("member.idAvailCheck", id);
		System.out.println("1123123123");
		// check = 0 DB 에 존재하지 않음, check = 1 DB 에 존재하는 값
		return check;
	}		
}

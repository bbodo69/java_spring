package board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.model.vo.BoardVO;

public class BoardDAOImpl implements BoardDAO{
	
	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertArticle(BoardVO vo) throws Exception {
		
		sqlSession.insert("board.insertArticle", vo);
		if(vo.getRe_step().equals(null) && !vo.getRe_step().equals("0")) {
			sqlSession.insert("board.insertArticle", vo);
		}else{
			// 기존 글의 ref 받기
			String ref = vo.getRef();	
			String oriRe_step = sqlSession.selectOne("getRefMaxNum", ref);
			int reRe_step = Integer.parseInt(oriRe_step)+1;
			System.out.println(reRe_step + " = reRe_step");
			String stringReRe_step = Integer.toString(reRe_step); 
			System.out.println(stringReRe_step + " = stringReRe_step");
			vo.setRe_step(stringReRe_step);
			sqlSession.insert("board.insertReple", vo);
			// 받은 ref 그대로 ref 에 붙여 놓고 re_step 만 +1 씩 해주기
			
//			System.out.println("1111");
//			System.out.println(vo.getRef() + " = vo.getRef");
//			int ref = vo.getNum();						
//			System.out.println(ref + " = ref");			
//			String oriRe_step = sqlSession.selectOne("getRefMaxNum", ref);
//			System.out.println("2222");
//			int reRe_step = Integer.parseInt(oriRe_step)+1;
//			System.out.println("3333");
//			String stringReRe_step = Integer.toString(reRe_step); 
//			System.out.println("4444");
//			vo.setRe_step(stringReRe_step);
//			System.out.println(vo.getRef());
//			sqlSession.insert("board.insertReple", vo);
		}
		
	}

	@Override
	public int getArticleCount() throws Exception {
		
		int count = (Integer)sqlSession.selectOne("board.countAll");
		
		return count;
	}

	@Override
	public List getArticles(int start, int end) throws Exception {

		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List list = sqlSession.selectList("board.selectAll", map);
		
		return list;
	}

	@Override
	public BoardVO getArticle(int num) throws Exception {

		BoardVO vo = new BoardVO();
		
		vo = sqlSession.selectOne("board.getArticle", num);
		
		int readCount = vo.getreadCount() + 1;
		System.out.println(readCount + " = DAO readCount");
		
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("readCount", readCount);
		sqlSession.update("updateReadCount", map);				
		
		return vo;
	}

	@Override
	public BoardVO getArticleForUpdate(int num) throws Exception {

		return null;
	}

	@Override
	public int updateArticle(BoardVO vo) throws Exception {
		
		int num = vo.getNum();
		String pw = vo.getPw();
		System.out.println(num + " = num");
		System.out.println(pw + " = pw");
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("pw", pw);
		
		int check = (Integer)sqlSession.selectOne("board.numPwCheck", map);
		System.out.println(check + " = check");
		sqlSession.update("updateArticle", vo);

		return check;
	}

	@Override
	public int deleteArticle(int num, String pw) throws Exception {
				
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("pw", pw);
		
		int result = (Integer)sqlSession.selectOne("board.numPwCheck", map);
		sqlSession.update("board.deleteArticle", map);
		System.out.println(result + " = result");
		
		return result;
	}

}
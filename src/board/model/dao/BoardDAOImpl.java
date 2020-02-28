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
		
	}

	@Override
	public int getArticleCount() throws Exception {
		
		System.out.println("BoardDAO");
		int count = (Integer)sqlSession.selectOne("board.countAll");
		System.out.println(count+"boardDAO");
		
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
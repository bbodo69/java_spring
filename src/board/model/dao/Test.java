package board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.model.vo.BoardVO;

public class Test {
	
	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void setRef() throws Exception {

		int count = sqlSession.selectOne("board.countAll");
		
		BoardDAO dao = new boardDAO();
		List list = dao.getArticles(0, count);
		BoardVO vo = new BoardVO();
		
		for(int i = 0 ; i < count ; i++) {
			vo = (BoardVO)list[i];
		}
			
		
		}
		
	
	}
	


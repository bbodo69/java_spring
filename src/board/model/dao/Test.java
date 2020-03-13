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
				
		List list = sqlSession.selectList("board.selectAllNum");
		
		for(int i = 0 ; i < list.size() ; i ++) {
			System.out.println(i + " = i");
			System.out.println(list.get(i) + " list.get(i)");
			int num = (Integer)list.get(i);
			sqlSession.update("board.setRef", num);
		}
						
		}
	
	}
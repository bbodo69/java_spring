package board.model.dao;

import java.util.List;

import board.model.vo.BoardVO;

public interface BoardDAO {
	// �Խñ� ����
	public void insertArticle(BoardVO vo) throws Exception;
	// ��ü�� ���� �˻�
	public int getArticleCount() throws Exception;
	// �Խñ� ���������� ��������
	public List getArticles(int start, int end) throws Exception;
	// �Խñ� �Ѱ� ���� ��������
	public BoardVO getArticle(int num) throws Exception;
	// �Խñ� ���������� �� �Ѱ� ���� �������� (��ȸ�� �ȿö�)
	public BoardVO getArticleForUpdate(int num) throws Exception;
	// �Խñ� �����ϱ�
	public int updateArticle(BoardVO vo) throws Exception;
	// �Խñ� ����
	public int deleteArticle(int num, String passwd) throws Exception;
}

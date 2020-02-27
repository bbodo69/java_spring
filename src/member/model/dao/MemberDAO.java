package member.model.dao;

import java.util.List;
import member.model.vo.MemberVO;

/* ��������..
  DB : Oracle
  SID : ORCL
  IPADD : @nullmaster.iptime.org
  PORT : 3000
  ...
*/

public interface MemberDAO {
	// CRUD �°� �ۼ� 
	//ȸ�� ����
	public void insertMember(MemberVO vo) throws Exception;
	//���̵� ��� Ȯ��
	public int idPwCheck(MemberVO vo) throws Exception;
	//��ü ȸ�� ������ ��ȸ
	public List selectAll() throws Exception;
	//ȸ�� �Ѹ� ������ ��ȸ
	public MemberVO selectMember(String id) throws Exception;
	//ȸ��  ������ ����
	public void updateMember(MemberVO vo) throws Exception;
	//ȸ�� ������ ����
	public void deleteMember(String id) throws Exception;
	//���̵� ��밡�� ���� ��ȸ
	public int idAvailCheck(String id) throws Exception;
}

package teamD.publicParking.search.Repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import teamD.publicParking.search.Model.SearchEntity;

@Repository
public class SearchMybatisDaoImpl implements SearchMybatisDao {

	private SearchMybatisDao mapper;

	@Autowired
	public SearchMybatisDaoImpl(SqlSession sqlSession) {
		this.mapper = sqlSession.getMapper(SearchMybatisDao.class);
	}

	@Override
	public List<SearchEntity> select() {
		return mapper.select();
	}
	
	@Override
	public List<SearchEntity> searchByCondition(String area, String address) {
		
		// 여기서 처리해줘야함 !!
		/*
		All 모두
        New Village	공영주차장
        Old Town 전기차 충전소
        Modern City 게시판
        */
		
		if(area.equals("All")) {
        	area = "";
        } else if (area.equals("New Village")) {
        	area = "공영%주차장";
        } else if (area.equals("Old Town")) {
        	area = "전기차%충전소";
        } else if (area.equals("Modern City")) {
        	area = "게시판";
        } else {
        	throw new IllegalArgumentException("잘못된 인자입니다 : area = "+ area );
        }
		
		return mapper.searchByCondition(area, address);
	}
}

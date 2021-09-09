package teamD.publicParking.search.Repository;

import java.util.List;

import teamD.publicParking.search.Model.SearchEntity;

public interface SearchMybatisDao {

	List<SearchEntity> select();
	
	List<SearchEntity> searchByCondition(String area, String address);
}

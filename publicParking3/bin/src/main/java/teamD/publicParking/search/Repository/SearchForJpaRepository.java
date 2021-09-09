package teamD.publicParking.search.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import teamD.publicParking.search.Model.SeoulParking;

@Repository
@RequiredArgsConstructor
public class SearchForJpaRepository{
	
	private final EntityManager em;
	
	// 아직 미해결
	@SuppressWarnings("unchecked")
	public List<SeoulParking> findAll() {
		return 
				// em.createQuery("select t.parking_id, t.parking_name, t.parking_address, t.parking_phone from seoul_parking m", SearchEntityForJpa.class)
				em.createQuery("select m from SeoulParking m", SeoulParking.class)
				.getResultList();
	}
	
	// ok
	@SuppressWarnings("unchecked")
	public SeoulParking findOne() {
		return 
				em.find(SeoulParking.class, "1025695");
	}
}

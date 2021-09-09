package teamD.publicParking.search.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "seoul_parking")
public class SearchEntity_Backup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private String parking_id;
	@NotNull
	private String parking_name;
	@NotNull
	private String parking_address;
	
	private String parking_phone;
	
	private String parking_vehicles;
	
	private String parking_free;
	
	private String start_time;
	
	private String weekend_starttime;
	
	private String weekend_endtime;
	
	private String holiday_starttime;
	
	private String holiday_endtime;
	
	private String saturday_free;
	
	private String parking_price;
	
	private String parking_time;
	
	private String parking_addprice;
	
	private String parking_addtime;
	
	private String max_price;
	
	private String parking_mapl;
	
	
}

package teamD.publicParking.search.Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "seoul_parking")
public class SeoulParking {
	
	@Id @GeneratedValue
	@Column(name = "parking_id")
	private String parkingId;
	
	@Column(name = "parking_name")
	private String parkingName;
	
	@Column(name = "parking_address")
	private String parkingAddress;
	
	@Column(name = "parking_phone")
	private String parkingPhone;
	
	@Column(name = "parking_vehicles")
	private String parkingVehicles;
	
	@Column(name = "parking_free")
	private String parkingFree;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "weekend_starttime")
	private String weekendStarttime;
	
	@Column(name = "weekend_endtime")
	private String weekendEndtime;
	
	@Column(name = "holiday_starttime")
	private String holidayStarttime;
	
	@Column(name = "holiday_endtime")
	private String holidayEndtime;
	
	@Column(name = "saturday_free")
	private String saturdayFree;
	
	@Column(name = "parking_price")
	private String parkingPrice;
	
	@Column(name = "parking_time")
	private String parkingTime;
	
	@Column(name = "parking_addprice")
	private String parkingAddprice;
	
	@Column(name = "parking_addtime")
	private String parkingAddtime;
	
	@Column(name = "max_price")
	private String maxPrice;
	
	@Column(name = "parking_mapl")
	private String parkingMapl;
}

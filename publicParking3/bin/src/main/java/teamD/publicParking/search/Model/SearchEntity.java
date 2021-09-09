package teamD.publicParking.search.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class SearchEntity {
	@NotNull
	private String parkingId;
	@NotNull
	private String parkingName;
	@NotNull
	private String parkingAddress;
	
	private String parkingPhone;
	
	private String parkingVehicles;
	
	private String parkingFree;
	
	private String startTime;
	
	private String weekendStarttime;
	
	private String weekendEndtime;
	
	private String holidayDtarttime;
	
	private String holidayEndtime;
	
	private String saturdayFree;
	
	private String parkingPrice;
	
	private String parkingTime;
	
	private String parkingAddprice;
	
	private String parkingAddtime;
	
	private String maxPrice;
	
	private String parkingMapl;
	
	
}

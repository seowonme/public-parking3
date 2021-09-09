package teamD.publicParking.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import teamD.publicParking.search.Model.SearchEntity;
import teamD.publicParking.search.Model.SeoulParking;
import teamD.publicParking.search.Repository.SearchForJpaRepository;
import teamD.publicParking.search.Repository.SearchMybatisDao;

@Controller
@Slf4j
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	SearchForJpaRepository parkingRepositoryJpa;
	
	@Autowired
	SearchMybatisDao parkingRepository;
	
	@GetMapping({"/index", "/"})
	public String home(Model model) {
		log.debug("log SearchController.foo");
//		List<SearchEntity> parkinglist = parkingRepository.select();
//		model.addAttribute("parkinglist", parkinglist);
//		log.debug("parkinglist = {}", parkinglist);
		return "/index";
	}
	
	/**
	 * 파라미터를 받아서 목록을 찾은 후에 /index 화면에 재전송합니다
	 * 예시:
	 * ?area=New+Village&address=대림
	 */
	
	@GetMapping("/search")
	public String parkingcon(@RequestParam String area, @RequestParam String address, Model model) {
		

		
		log.info(">>>>>>> SearchController.parkingcon");
		log.info("로깅 출력대야함 area, address = {}, {}", area, address);
		// 화면에 표시되는 데이터를 가져오기 위함
//		List<SearchEntity> parkinglist = parkingRepository.select();
		List<SearchEntity> parkinglist = parkingRepository.searchByCondition(area, address);
		model.addAttribute("parkinglist", parkinglist);
		return "/listing";
	}
	
	// 밑에 부분 보류
	// @RequestMapping(value="/", method = RequestMethod.GET)
	public String parkingcon_보류(@RequestParam(name="parking_id") String parking_id , Model model) {
		log.debug("SearchController.search");
		// 화면에 표시되는 데이터를 가져오기 위함
		List<SearchEntity> parkinglist = parkingRepository.select();
		model.addAttribute("parkinglist", parkinglist);
		return "/index";
	}
	
	@GetMapping("/wanna-data")
	public String wannaData(Model model) {
		log.debug("wannaData");
		List<SearchEntity> parkings = parkingRepository.select();
		log.debug("parkinglist = {}", parkings);
		model.addAttribute("parkings", parkings);
		model.addAttribute("whyNot", "바인딩 왜 안해줌");
		
		SearchEntity foo = new SearchEntity();
		
		model.addAttribute("data", foo);
		
		return "/searchDataBinding";
	}
	
	@GetMapping("/just-data")
	@ResponseBody
	public List<SearchEntity> justData(Model model) {
		log.debug("justData");
		List<SearchEntity> parkinglist = parkingRepository.select();
		log.debug("parkinglist = {}", parkinglist);
		model.addAttribute("parkinglist", parkinglist);
		return parkinglist;
	}
	
	@Autowired
	SearchForJpaRepository searchForJpaRepository;
	
	@GetMapping("/jpa/wanna-data")
	public String wanaDataJpa(Model model) {
		log.debug("wanaDataJpa");
		List<SeoulParking> parkings = searchForJpaRepository.findAll();
		log.debug("parkinglist = {}", parkings);
		return "/searchDataBinding";
	}
	
	//findOne
	
	@GetMapping("/jpa/just-one")
	public String wanaDataOneJpa(Model model) {
		log.debug(">>>>>>>>>> wanaDataOne");
		SeoulParking parking = searchForJpaRepository.findOne();
		model.addAttribute("data", parking);
		log.debug("parkinglist = {}", parking);
		return "/searchDataBindingOne";
	}
	
}
package pl.yeloon.magisterium.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.yeloon.magisterium.controller.bean.MapRankDTO;
import pl.yeloon.magisterium.controller.bean.OverallRankDTO;
import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.service.MapService;
import pl.yeloon.magisterium.service.RankService;

@Controller
public class RankController {

	@Autowired
	RankService rankService;

	@Autowired
	MapService mapService;
	
	@RequestMapping(value="/overall-rank")
	public String showOverallRank(Model model, HttpServletRequest request){
		List<OverallRankDTO> scores = rankService.getOverallRank();
		model.addAttribute("scores", scores);
		if(request.isSecure()){
			for(OverallRankDTO score : scores){
				if(score.getImageUrl()!=null){
					score.setImageUrl(changeImageUrlToSecure(score.getImageUrl()));
				}
			}
		}
		return "overallrank";
	}

	@RequestMapping(value = "/rank/{mapKey}")
	public String showMapRank(@PathVariable("mapKey") String mapKey, Model model, HttpServletRequest request) {

		Map map = mapService.getMapByKey(mapKey);
		if (map != null) {
			List<MapRankDTO> scores = rankService.getMapRank(map.getId());
			if (request.isSecure()) {
				// jesli jest poprzez https to podmien urle w grafikach z facbooka http->https
				for (MapRankDTO score : scores) {
					if (score.getImageUrl() != null)
						score.setImageUrl(changeImageUrlToSecure(score.getImageUrl()));
				}
			}
			model.addAttribute("startBattery", map.getBatteryLevel());
			model.addAttribute("bestBattery", map.getBestBatteryLevel());
			model.addAttribute("bestCommand", map.getBestCommandCounter());
			model.addAttribute("mapKey", mapKey);
			model.addAttribute("scores", scores);
			return "rank";
		}
		return "error404";
	}
	
	private String changeImageUrlToSecure(String imageUrl){
		return "https" + imageUrl.substring(4);
	}
}

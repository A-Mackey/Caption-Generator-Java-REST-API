package panterix.CaptionGeneratorJavaAPI;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class CaptionsController {
	
	@GetMapping("/health")
	public String health()  {
		return "Running";
	}
	
	@PostMapping("/refreshCaptions")
	public String refresh() {
		int size = GetCaption.update();
		return "Refreshed. Cache size: " + size;
	}
	
	@PostMapping("/getCaption")
	public CaptionResponse get() {
		CaptionResponse res = new CaptionResponse();
		res.caption = GetCaption.get();
		
		return res;
	}
	
	@PostMapping("/addCaption")
	public String add(@RequestBody AddCaptionRequestModel body) {
		try {
			if (GetCaption.add(body.caption, body.password)) {
				return "Added " + body.caption;
			}
			else {
				return "Unable to add " + body.caption;
			}
		} catch (Exception e) {
			return "Unable to add it";
		}
	}
	
	@PostMapping("/removeCaption")
	public String remove(@RequestBody AddCaptionRequestModel body) {
		try {
			if (GetCaption.remove(body.caption, body.password)) {
				return "Removed " + body.caption;
			}
			else {
				return "Unable to remove " + body.caption;
			}	
		} catch (Exception e) {
			return "Unable to remove it";
		}	
	}
}

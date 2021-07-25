package panterix.CaptionGeneratorJavaAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public String get() {
		System.out.println("Getting!");
		return GetCaption.get();
	}
	
	@PostMapping("/addCaption")
	public String add(@RequestBody AddCaptionRequestModel body) {
		if (GetCaption.add(body.caption, body.password)) {
			return "Added " + body.caption;
		}
		else {
			return "Unable to add " + body.caption;
		}
				
	}
}

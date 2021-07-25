package panterix.CaptionGeneratorJavaAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return GetCaption.get();
	}
}

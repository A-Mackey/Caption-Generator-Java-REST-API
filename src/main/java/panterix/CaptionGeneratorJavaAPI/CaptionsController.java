package panterix.CaptionGeneratorJavaAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptionsController {
	
	@GetMapping("/health")
	public String greeting()  {
		return "Running";
	}
	
	@PostMapping("/getCaption")
	public String post() {
		return GetCaption.get();
	}
}

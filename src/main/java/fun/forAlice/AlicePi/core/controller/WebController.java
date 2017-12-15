package fun.forAlice.AlicePi.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/")
public class WebController {

	@RequestMapping("index")
	public String index(Model model) {
		return "home";
	}
}

package edu.cuny.brooklyn.web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
	private static final String GREETING_FMT = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	@ResponseBody
	public Greeting doGreeting(
			@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name
			) {
		return new Greeting(counter.incrementAndGet(), String.format(GREETING_FMT, name));
	}
}

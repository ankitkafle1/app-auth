package com.auth.app;

import java.time.Instant;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-locations")
public class UserLocationController {

	private static final Instant CAPTURED_AT = Instant.parse("2026-05-11T17:55:00Z");

	@GetMapping("/current")
	public List<UserLocationResponse> getCurrentUserLocations() {
		return List.of(
				new UserLocationResponse("1", "Aarav Mehta", "+9779801230128", 27.7154, 85.3123, 15, CAPTURED_AT),
				new UserLocationResponse("2", "Sophia Chen", "+9779801230173", 27.717, 85.3287, 12, CAPTURED_AT),
				new UserLocationResponse("3", "Maya Iyer", "+9779801230199", 27.6928, 85.342, 18, CAPTURED_AT),
				new UserLocationResponse("4", "Noah Williams", "+9779801230112", 27.6788, 85.3206, 20, CAPTURED_AT),
				new UserLocationResponse("5", "Nisha Karki", "+9779801230144", 27.7041, 85.3079, 14, CAPTURED_AT),
				new UserLocationResponse("6", "Daniel Kim", "+9779801230155", 27.7102, 85.3331, 11, CAPTURED_AT),
				new UserLocationResponse("7", "Priya Shah", "+9779801230166", 27.6887, 85.3172, 16, CAPTURED_AT),
				new UserLocationResponse("8", "Samir Gurung", "+9779801230188", 27.7215, 85.3194, 13, CAPTURED_AT),
				new UserLocationResponse("9", "Elena Rossi", "+9779801230211", 27.6976, 85.3369, 19, CAPTURED_AT),
				new UserLocationResponse("10", "Kiran Lama", "+9779801230222", 27.6829, 85.3038, 17, CAPTURED_AT));
	}
}

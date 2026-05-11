package com.auth.app;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-locations")
public class UserLocationController {

    private final List<UserLocationInfo> locationInfo = new ArrayList<>();

    private static final LocalDateTime CAPTURED_AT = LocalDateTime.now();

    public UserLocationController() {
        locationInfo.add(new UserLocationInfo(27.7154, 85.3123, CAPTURED_AT, "Aarav Mehta", "+9779801230128", "number1"));
        locationInfo.add(new UserLocationInfo(27.717, 85.3287, CAPTURED_AT, "Sophia Chen", "+9779801230173", "number2"));
        locationInfo.add(new UserLocationInfo(27.6928, 85.342, CAPTURED_AT, "Maya Iyer", "+9779801230199", "number3"));
        locationInfo.add(new UserLocationInfo(27.6788, 85.3206, CAPTURED_AT, "Noah Williams", "+9779801230112", "number4"));
        locationInfo.add(new UserLocationInfo(27.7041, 85.3079, CAPTURED_AT, "Nisha Karki", "+9779801230144", "number5"));
        locationInfo.add(new UserLocationInfo(27.7102, 85.3331, CAPTURED_AT, "Daniel Kim", "+9779801230155", "number6"));
        locationInfo.add(new UserLocationInfo(27.6887, 85.3172, CAPTURED_AT, "Priya Shah", "+9779801230166", "number7"));
        locationInfo.add(new UserLocationInfo(27.7215, 85.3194, CAPTURED_AT, "Samir Gurung", "+9779801230188", "number8"));
        locationInfo.add(new UserLocationInfo(27.6976, 85.3369, CAPTURED_AT, "Elena Rossi", "+9779801230211", "number9"));
        locationInfo.add(new UserLocationInfo(27.6829, 85.3038, CAPTURED_AT, "Kiran Lama", "+9779801230222", "number10"));
    }




	@GetMapping("/current")
	public List<UserLocationInfo> getCurrentUserLocations() {
        return locationInfo;
    }
}

package com.auth.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"https://sumo-haice.ankit-kafle19.workers.dev",
"http://localhost:8081",
        "http://192.168.1.131:8081"})
public class UserLocationController {

    private static final Logger log = LoggerFactory.getLogger(UserLocationController.class);
    ConcurrentMap<String, List<UserLocationInfo>> map = new ConcurrentHashMap<>();
    ConcurrentMap<String, UserLocationInfo> driverLocationMap = new ConcurrentHashMap<>();

	@GetMapping("/user-locations/{id}")
	public List<UserLocationInfo> getCurrentUserLocations(@PathVariable String id) {
        log.info("Entry: {}", id );
        List<UserLocationInfo> currentLocations;
        if (!map.keySet().contains(id)) {
            currentLocations =  Collections.emptyList();
        } else {
            currentLocations = map.get(id);
        }
        log.info("Exit {}", currentLocations);
        return  currentLocations;

    }

   @PostMapping("/user-locations")
    public UserLocationInfo addUserLocationInfo(@RequestBody UserLocationInfo info) {
        log.info("Entry: {}" , info);
        String key = info.vehicleNumber();
        if (map.keySet().contains(key)) {
            var info2 = map.get(key)
                    .stream()
                    .filter(x -> !x.phoneNumber().equals(info.phoneNumber()))
                    .collect(Collectors.toList());
            info2.add(info);
            map.put(key, info2);
        } else {
            List<UserLocationInfo> list = new ArrayList<>();
            list.add(info);
            map.put(key, list);
        }
        return info;
   }

    @PostMapping("/driver-locations")
    public UserLocationInfo addDriverLocationInfo(@RequestBody UserLocationInfo info) {
        log.info("Entry: {}" , info);
        driverLocationMap.put(info.vehicleNumber(),info);
        return info;
    }

    @GetMapping("/driver-locations/{id}")
    public  UserLocationInfo getDriverLocationInfo(@PathVariable String id) {
        log.info("Entry: {}" , id);
        return  driverLocationMap.get(id);
    }

   @DeleteMapping("/user-locations")
    public void deleteUserLocationInfo(@RequestBody UserLocationInfo info) {
        log.info("Entry: {}" , info);
       System.out.println(map.keySet());
        if (map.keySet().contains(info.vehicleNumber())) {
           List<UserLocationInfo> list = map.get(info.vehicleNumber()).stream()
                   .filter(x -> !x.phoneNumber().equals(info.phoneNumber()))
                   .toList();
           map.put(info.vehicleNumber(), list);
        }
       }
}

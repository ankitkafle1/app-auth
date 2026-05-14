package com.auth.app;

import java.time.Instant;

public record UserLocationInfo(double latitude,
                               double longitude,
                               Instant instant,
                               String userName,
                               String phoneNumber,
                               String vehicleNumber) {
}

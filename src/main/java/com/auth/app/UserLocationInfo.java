package com.auth.app;

import java.time.LocalDateTime;

public record UserLocationInfo(double latitude,
                               double longitude,
                               LocalDateTime currentDate,
                               String userName,
                               String phoneNumber,
                               String vehicleNumber) {
}

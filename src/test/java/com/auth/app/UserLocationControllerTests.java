package com.auth.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserLocationControllerTests {

	private final UserLocationController controller = new UserLocationController();

	@Test
	void returnsCurrentUserLocations() {
		var locations = controller.getCurrentUserLocations();

		assertThat(locations).hasSize(10);
		assertThat(locations.getFirst()).isEqualTo(new UserLocationResponse(
				"1",
				"Aarav Mehta",
				"+9779801230128",
				27.7154,
				85.3123,
				15,
				java.time.Instant.parse("2026-05-11T17:55:00Z")));
		assertThat(locations.getLast().userName()).isEqualTo("Kiran Lama");
	}
}

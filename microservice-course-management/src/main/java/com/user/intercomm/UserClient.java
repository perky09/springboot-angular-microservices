package com.user.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {

	@PostMapping("/service/names")
	List<String> getUserNames(@RequestBody List<Long> useridList);
}

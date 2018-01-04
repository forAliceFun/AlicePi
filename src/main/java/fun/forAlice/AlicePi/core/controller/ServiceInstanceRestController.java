package fun.forAlice.AlicePi.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import fun.forAlice.AlicePi.core.entity.Gpio;
import fun.forAlice.AlicePi.core.service.impl.GpioServiceImpl;


@RestController
public class ServiceInstanceRestController {
	
	@Autowired
	private GpioServiceImpl gpioService;
	
	@Autowired
	private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ServiceInstanceRestController.class);

    
    @GetMapping("/demo")
    public Integer demo() {
    	gpioService.pinDemo();
    	return 1;
    }
    
    

}
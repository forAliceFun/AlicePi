package fun.forAlice.AlicePi.core.controller.rest;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import fun.forAlice.AlicePi.core.entity.Gpio;
import fun.forAlice.AlicePi.core.service.impl.GpioServiceImpl;


@RestController
public class GpioController {
	@Autowired
	private GpioServiceImpl gpioService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(GpioController.class);
    @Autowired
    private DiscoveryClient discoveryClient; //服务发现客户端
    
    @GetMapping("/gpios")
    public List<Gpio>  getGpios() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("/gpios, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + "gpios ");
        return gpioService.getGpioList();
    }
    
    @GetMapping("/gpios/{id}")
    public Gpio getGpioById(@PathVariable("id") Integer id) {
    	return gpioService.getGpioById(id);
    }
    
    @PostMapping("/gpios/{id}")
    public Gpio upsetGpioById(@PathVariable("id") Integer id,
    							@RequestBody String json,
    							HttpServletRequest request,
    							HttpServletResponse response) {
    	try {
			Gpio myGpio = objectMapper.readValue(json, Gpio.class);
			gpioService.updateGpio(myGpio);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return gpioService.getGpioById(id);
    }
}

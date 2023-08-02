package com.example.Group.Package;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1/test")
public class NameApplication {

//	public MeterRegistry registry = Metrics.globalRegistry;
	public MeterRegistry registry = new SimpleMeterRegistry();
	public Map<String, Counter> testCount = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(NameApplication.class, args);
	}

	@GetMapping
	public String test() {
		Integer n =1;
			incrementRandCount(n);

		System.out.println("test");
		return "Hello";
	}


	public void incrementRandCount(Integer n){
		this.testCount.computeIfAbsent("Flow_1", t -> Counter.builder("api_http_request")
				.description("counts api request")
				.tags("tags1", "tags2", "asd2", "asd3")
				.register(registry));


		this.testCount.get("Flow_1").increment();

		System.out.println("metricscalled");

	}

}

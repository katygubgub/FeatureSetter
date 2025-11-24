package com.example.demo;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeaturesController {
	
	private static final Map<String, Boolean> features = new HashMap<>();

	/**
	 * Set a feature to be enabled/disabled
	 */
	@PostMapping(path = "/setFeatures", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String setFeature(@RequestBody FeatureRequest req) throws FeatureException {
		if (req.featureName() == null || req.featureName().isBlank())
			throw new FeatureException("Feature name must be specified");
		features.put(req.featureName(), req.enabled());
		return String.format("%s setting set to %s", req.featureName(), req.enabled());
	}

	/**
	 * Return the enabled status of a feature
	 */
	@GetMapping("/getFeatures/{feat}")
	public boolean getFeature(@PathVariable("feat") String feat) throws FeatureException {
		if (!features.containsKey(feat))
			throw new FeatureException(String.format("Feature %s not found",  feat));
		return features.get(feat);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeatureException.class)
	public String handleException1(FeatureException e) {
		return e.getMessage();
	}

}
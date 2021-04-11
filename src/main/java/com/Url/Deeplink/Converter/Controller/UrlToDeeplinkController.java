package com.Url.Deeplink.Converter.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Url.Deeplink.Converter.Dto.RequestDTO;
import com.Url.Deeplink.Converter.Dto.ResponseDTO;
import com.Url.Deeplink.Converter.Services.ConvertStrategy;

@RestController
public class UrlToDeeplinkController {
	
	@Autowired
	@Qualifier("urlstrategy")
	ConvertStrategy UrlStrategy;
	
	@Autowired
	@Qualifier("deeplinkstrategy")
	ConvertStrategy DeeplingStrategy;


	@PostMapping("/url-to-deeplink")
	@ResponseBody
	public ResponseDTO UrlToDeepLink(@RequestBody RequestDTO request) {
	  return UrlStrategy.Convert(request.Url);
	}
	
	@PostMapping("/deeplink-to-url")
	@ResponseBody
	public ResponseDTO DeepLinkToUrl(@RequestBody RequestDTO request) {
	  return DeeplingStrategy.Convert(request.Url);
	}

}

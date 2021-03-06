package com.Url.Deeplink.Converter.Logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Url.Deeplink.Converter.Models.Log;
import com.Url.Deeplink.Converter.Repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component("loggingserviceimpl")
public class LoggingServiceImpl implements LoggingService {
    
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private LogRepository logRepository;
	
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        
        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");
        
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }
        
        if (body != null) {
        	ObjectMapper objectMapper = new ObjectMapper();
        	String bodyAsString = null;
			try {
				bodyAsString = objectMapper.writeValueAsString(body);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            stringBuilder.append("body=[" + bodyAsString + "]");
        }
        
        Log log = new Log();
        log.setInfo(stringBuilder.toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        log.setLogDate(dtf.format(now));
        logRepository.save(log);
        //log.info(stringBuilder.toString());
    }
    
    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("RESPONSE ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = null;
		try {
			bodyAsString = objectMapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        stringBuilder.append("responseBody=[").append(bodyAsString).append("] ");
        
        Log log = new Log();
        log.setInfo(stringBuilder.toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        log.setLogDate(dtf.format(now));
        logRepository.save(log);
        //log.info(stringBuilder.toString());
    }
    
    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        
        return resultMap;
    }
    
    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        return map;
    }
    
    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        
        return map;
    }
}

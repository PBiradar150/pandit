package com.spring.poc;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.logger.ServiceLogger;

@Controller
public class HelloController extends ServiceLogger {
	private static final Logger logger = LogManager.getLogger(HelloController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getWelcome(HttpServletRequest request) {
			logServiceRequest(request);
		// logs debug message

		logger.debug("getWelcome is executed!");

		// logs exception
		// logger.error("This is Error message", new Exception("Testing"));

		logger.info("patil");
		ModelAndView model = new ModelAndView("svgtopdf");
		model.addObject("msg", "Hello Spring MVC + Log4j");
		clearMap();
		return model;

	}

}

package com.lenovo.deletefiles;

import java.io.IOException;
import java.text.ParseException;

import com.lenovo.deletefiles.core.GetLogFIles;
import com.lenovo.deletefiles.properties.PropertiesListener;
import com.lenovo.deletefiles.properties.PropertiesListenerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private static Environment env;

	public static void main(String[] args) {
		// FilePath filePath = new FilePath();
		SpringApplication application = new SpringApplication(DemoApplication.class);
		application.addListeners(new PropertiesListener("application.properties"));
		application.run(args);
		// System.out.println(PropertiesListenerConfig.getProperty("filepath.activeLogPath"));
		GetLogFIles logFIles = new GetLogFIles(PropertiesListenerConfig.getProperty("filepath.activeLogPath"), PropertiesListenerConfig.getProperty("filepath.endDate"));
		System.out.println(PropertiesListenerConfig.getProperty("filepath.activeLogPath"));
		System.out.println(PropertiesListenerConfig.getProperty("filepath.endDate"));
		// logFIles.getFiles(PropertiesListenerConfig.getProperty("filepath.activeLogPath"));
		try {
			logFIles.run();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Delete completed.");
		
	}

}

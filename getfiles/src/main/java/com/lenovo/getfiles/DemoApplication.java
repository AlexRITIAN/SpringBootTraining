package com.lenovo.getfiles;

import java.io.IOException;
import java.text.ParseException;

import com.lenovo.getfiles.core.GetLogFIles;
import com.lenovo.getfiles.properties.PropertiesListener;
import com.lenovo.getfiles.properties.PropertiesListenerConfig;

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
		GetLogFIles logFIles = new GetLogFIles(PropertiesListenerConfig.getProperty("filepath.activeLogPath"),
				PropertiesListenerConfig.getProperty("filepath.startDate"),
				PropertiesListenerConfig.getProperty("filepath.endDate"),
				PropertiesListenerConfig.getProperty("filepath.storePath"));
		System.out.println("startDate : " + PropertiesListenerConfig.getProperty("filepath.startDate"));
		System.out.println("endDate : " + PropertiesListenerConfig.getProperty("filepath.endDate"));

		// logFIles.getFiles(PropertiesListenerConfig.getProperty("filepath.activeLogPath"));
		try {
			logFIles.run();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("copy completed.");
		
	}

}

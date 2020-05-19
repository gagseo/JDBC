package com.uclass.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uclass.bm.model.service.MemberService;

public class BmLogFactory {

	Logger log = Logger.getLogger(MemberService.class);

	private BmLogFactory() {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream("log4j.properties");
			Properties logProperty = new Properties();
			logProperty.load(fis);
			PropertyConfigurator.configure(logProperty);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static class BmLogHolder {

		private static final BmLogFactory instance = new BmLogFactory();

	}

	public static BmLogFactory getInstance() {

		return BmLogHolder.instance;
	}

	public Logger getLogger(Class className) {

		return Logger.getLogger(className);
	}

}

/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

import logic.dao.FacebookLoginDao;
import logic.dao.LoginInformation;
import logic.dao.RegistrationDao;
import logic.exceptions.LoginFacebookException;


public class FacebookLoginController {
	
	public String login() throws LoginFacebookException {

		String userId;
		String domain;
		
		domain = "https://www.facebook.com/connect/login_success.html";
		String appId = "117657769607083";
		
		String authUrl = "https://www.facebook.com/v5.0/dialog/oauth?"
						+	"client_id="+appId+""
			  			+	"&redirect_uri="+domain+""
			  			+	"&state=strings"
						+   "&response_type=token"
						+	"&scope=user_about_me";
		
		WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver79");
		driver = new ChromeDriver();
		driver.get(authUrl);

		
		String accessToken;
		while (true) {
			
			if (driver.getCurrentUrl().contains(domain)) {
				
				int partialTokenLen; 
				String url = driver.getCurrentUrl();
				String partialAccessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
				partialTokenLen = partialAccessToken.length();
				char[] partialAccessTokenArray;
				char[] accessTokenArray = new char[partialTokenLen];
				partialAccessTokenArray = partialAccessToken.toCharArray();
			
				int i=0;
				while (partialAccessTokenArray[i] != '&' && i<partialAccessTokenArray.length) {
					accessTokenArray[i] = partialAccessTokenArray[i];
					i++;
				}
				accessToken = String.copyValueOf(accessTokenArray);
				
				User user = null;
				try {
					FacebookClient fbClient = new DefaultFacebookClient (accessToken, Version.VERSION_3_1);
					user = fbClient.fetchObject("me", User.class);
				} catch(FacebookException e) {
					throw new LoginFacebookException("Unable to login with this facebook account");
				}
				
				userId = user.getId();
				
				driver.quit();
				return userId;
			}
		}
	}
	
	public LoginInformation facebookLogin(String userId) throws SQLException {

		FacebookLoginDao dao = new FacebookLoginDao();
		LoginInformation loginInformation = null;
		
		if(dao.userExistsByFbId(userId)) {
			loginInformation = dao.retrieveUserInformationById(userId);
		}
		
		return loginInformation;		
	}
	
	public boolean createAccount(String username, double startDeposit, String userId) throws SQLException {
		
		FacebookLoginDao dao = new FacebookLoginDao();
		RegistrationDao regDao = new RegistrationDao();

		boolean expression;
		if (!regDao.userExists(username))
			expression = dao.facebookRegister(username, startDeposit, userId);
		else 
			expression = false;
			
		return expression;
	}
}

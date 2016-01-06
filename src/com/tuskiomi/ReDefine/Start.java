package com.tuskiomi.ReDefine;

import java.io.File;
import java.util.Scanner;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.http.oauth.OAuthHelper;

public class Start {

	private static Scanner s;
	public static void main(String[] args) throws InterruptedException {
		print("initiating reddit bot V0.0");
		print("reading Config...");
		
		File config = new File("config.txt");
		FileHelper configwrapper = new FileHelper(config);
		
		s = new Scanner(System.in);
		
		UserAgent me = UserAgent.of("Desktop","Bot","0.0","/u/-u-words");//userclient is an identifier for apps
		Thread.sleep(500);
		RedditClient rc = new RedditClient(me);//make a new app instance
		
		String username = "-u-words";//reddit.com/u/-u-words
		String password;
		String secret = "ykqb0wDTmg-gPAIvsJyrs5k_blE";
		String public_id = "5JOflxp1eq3yPw";
		String red_uri = "http://localhost:80";//unused
		password = s.nextLine();//type the password
		
		Credentials c = Credentials.script(username, password, public_id, secret);
		try {
			
			OAuthData authData = rc.getOAuthHelper().easyAuth(c);
			rc.authenticate(authData);
			System.out.println(rc.me().getCommentKarma());
			
		} catch (NetworkException e) {
			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		

	}
	private static void print(String s){
		System.out.println(s);
	}

}

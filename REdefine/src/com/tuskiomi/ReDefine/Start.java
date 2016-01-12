package com.tuskiomi.ReDefine;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Subreddit;

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
		System.out.println("enter password:");
		password = s.nextLine();//type the password
		
		Credentials c = Credentials.script(username, password, public_id, secret);
		try {
			
			OAuthData authData = rc.getOAuthHelper().easyAuth(c);
			rc.authenticate(authData);
			System.out.println("agent ID: "+rc.me().getFullName());
			System.out.println("Link Karma: "+rc.me().getLinkKarma());
			System.out.println("Comment Karma:"+rc.me().getCommentKarma());
			System.out.println("Number of Users: ");
			System.out.println("Startup complete. initiating Loop");
			System.out.println("----Press enter to quit----");
			Thread t = new Thread(new ListeningThread());
			t.start();
			long startMillis = System.currentTimeMillis();
			long lastMillis = System.currentTimeMillis();
			int AgregateSeconds = (int) ((System.currentTimeMillis()-startMillis)/1000);
			int AgregateMinutes = 0;
			int AgregateHours = 0;
			int AgregateDays = 0;
			int AgregateMonths = 0;
			
			while(t.isAlive()){
				AgregateSeconds += (System.currentTimeMillis()-lastMillis)/1000;
				lastMillis = System.currentTimeMillis();
				if(AgregateSeconds >= 60){
					AgregateSeconds -=60;
					AgregateMinutes +=1;
				}
				if(AgregateMinutes >= 60){
					AgregateMinutes -= 60;
					AgregateHours++;
				}
				if(AgregateHours >=24){
					AgregateHours -=24;
					AgregateDays++;
				}
				System.out.println(AgregateSeconds+":"+AgregateMinutes+":"+AgregateHours+" & "+AgregateDays+" Days");
				Thread.sleep(1000);
				//when the planets align (once a week)
				if((AgregateDays % 7 == 0) && (AgregateHours == 0) && (AgregateMinutes == 0) && (AgregateSeconds == 0)){
					System.out.println("Bot waking up....  *STREEEETCH*!");
					//rc.deauthenticate();
				    authData = rc.getOAuthHelper().easyAuth(c);
					rc.authenticate(authData);
					Subreddit mod = rc.getSubreddit("unFinite");
					System.out.println(mod.getSubscriberCount());
				}
			}
			System.out.println("----Terminated----\n"+System.currentTimeMillis());
			
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

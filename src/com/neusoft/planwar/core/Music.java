package com.neusoft.planwar.core;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music {
	
	
	URI uri;
    URL url;
	String namespath;

	public Music() {
		
	}
	public Music(String spath) {
		this.namespath = spath;
		//playBackground();
		play();
	}
	public void play() {

		try {
			FileInputStream fileau = new FileInputStream(namespath);
			AudioStream as = new AudioStream(fileau);
			AudioPlayer.player.start(as);
			
			/*AudioPlayer.player.start(as);
			AudioPlayer.player.start(as);*/
		} catch (Exception e) {
			//System.out.println("DFGHJKL");
		}
	}

	public void playBackground() {

		
		try {
			File f = new File("src/com/neusoft/planwar/music/game_music.wav");
			/*AudioStream as = new AudioStream(fileau);
			AudioPlayer.player.start(as);*/
			uri = f.toURI();
		    url = uri.toURL();
			AudioClip aau; 
			aau = Applet.newAudioClip(url);
			aau.loop();//循环播放
			/*AudioPlayer.player.start(as);
			AudioPlayer.player.start(as);*/
		} catch (Exception e) {
			//System.out.println("DFGHJKL");
		}
	}
	
	public static void main(String[] args) {
		//new Music("src/com/neusoft/planewar/music/game_music.wav");
	}


}




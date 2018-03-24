package util;
import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.util.*;
public class MusicPlayer extends PlaybackListener implements Runnable{

	//汽箭
	private MusicPlayer(){}
	private static MusicPlayer mp =new MusicPlayer();
    public static MusicPlayer makePlayer(){
        return mp;
    }
	
	private AdvancedPlayer player=null;
	private List<String> musicList = new ArrayList<>(Arrays.asList(
			"敏虻誦 (さわの ひろゆき) - eye-water (凛節).mp3",
			"Felix Mendelssohn - Auf Flugeln Des Gesanges.mp3"));

	public void play(int index){
		if(player!=null) {
			player.close();
		}
		try {
			BufferedInputStream buffer = 
				new BufferedInputStream(
				new FileInputStream("rse/"+musicList.get(index)));
		
			player=new AdvancedPlayer(buffer);
			player.setPlayBackListener(this);
			new Thread(this).start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void close() {
		if(player!=null) {
			player.close();
		}
	}
	public void playbackFinished(PlaybackEvent evt) {
		player.close();
	}
	@Override
	public void run() {
		try {
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
}

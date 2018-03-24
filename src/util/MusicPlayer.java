package util;
import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import view.ManageMain;

import java.util.*;
public class MusicPlayer extends PlaybackListener implements Runnable{

	//单例
	private MusicPlayer(){}
	private static MusicPlayer mp =new MusicPlayer();
    public static MusicPlayer makePlayer(){
        return mp;
    }
	
	private AdvancedPlayer player=null;
	private List<String> musicList = new ArrayList<>(Arrays.asList(
			"澤野弘之 (さわの ひろゆき) - eye-water (眼泪).mp3",
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
			player.stop();
			ManageMain.index = -1;
		}
	}
	public void playbackFinished(PlaybackEvent evt) {
		if(evt.getSource() == player) {
			player.close();
//			System.out.println("播放完毕");
		}
//		System.out.println("播放完毕");
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

package me.timo.hw18.utils;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import me.timo.hw18.Game;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MusicPlayer {

	private Game game;

	private EmbeddedMediaPlayer emp;
	private String track;
	private boolean playing;

	public MusicPlayer(Game game) {
		this.game = game;

		track = "res\\Saw - Theme.mp3";
		setupPlayer();
	}

	private void setupPlayer() {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		MediaPlayerFactory mpf = new MediaPlayerFactory();
		emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(game.getWindow().getFrame()));

		emp.setVideoSurface(mpf.newVideoSurface(game));
		emp.setRepeat(true);
//		emp.setEnableKeyInputHandling(false);
//
//		emp.setEnableKeyInputHandling(false);
	}

	public void playMusic() {
		emp.prepareMedia(track);
		emp.play();
		playing = true;
	}

	public void stopMusic() {
		emp.stop();
		playing = false;
	}

	public EmbeddedMediaPlayer getMusicPlayer() {
		return emp;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}
}

package Physics;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
	
	public static void Sounds(String x){
		try {
	        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(x));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audio);
	        clip.start();
	    }
	    catch(UnsupportedAudioFileException uae) {
	        System.out.println("1");
	    }
	    catch(IOException ioe) {
	        System.out.println("2");
	    }
	    catch(LineUnavailableException lua) {
	        System.out.println("3");
	    }
	}

}

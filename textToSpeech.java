package dict;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author 16021228
 */
public class textToSpeech {

    static String apiKey = "9a5b1c70e883442dabe0e761adeb800e";
    /**
     * Ham get voice tu voicerss
     * @param w tu ngu cam get voice
     * @return true
     * @throws Exception 
     */
    public static boolean speechIt(String w) throws Exception {
        VoiceProvider tts = new VoiceProvider(apiKey);

        VoiceParameters params = new VoiceParameters(w, Languages.English_UnitedStates);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);
        
        byte[] voice = tts.speech(params);

        FileOutputStream fos = new FileOutputStream("voice.mp3");
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();
        return true;
    }
    /**
     * Ham phat am file da get
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void play() throws FileNotFoundException, IOException {
        InputStream in = new FileInputStream("voice.mp3");
        AudioStream as = new AudioStream(in);
        AudioPlayer.player.start(as);
    }
}

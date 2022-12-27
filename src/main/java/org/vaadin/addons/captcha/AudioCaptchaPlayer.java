package org.vaadin.addons.captcha;


import com.vaadin.flow.component.*;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Tag("audio")
public class AudioCaptchaPlayer extends Component implements HasSize {

    private static final PropertyDescriptor<String, String> srcDescriptor = PropertyDescriptors.attributeWithDefault(
            "src",
            ""
    );

    /**
     * Constructor. (Data can be set later).
     */
    public AudioCaptchaPlayer() {
        super();
        getElement().setProperty("controls", true);
    }


    /**
     * Constructor.
     *
     * @param sourceStream Data for audio stream.
     */
    public AudioCaptchaPlayer(AudioInputStream sourceStream) {
        setSrc(sourceStream);
        getElement().setProperty("controls", true);
    }

    /**
     * set the audio stream to be played in the web component
     * @param sourceStream Data for audio stream.
     */
    public void setSrc(AudioInputStream sourceStream) {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            AudioSystem.write(sourceStream, AudioFileFormat.Type.WAVE, outputStream);

            byte[] arrayOfBytes = outputStream.toByteArray();
            String encodedStream = Base64.getEncoder().encodeToString(arrayOfBytes);
            String src = "data:audio/wav;base64," + encodedStream;
            set(srcDescriptor, src);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

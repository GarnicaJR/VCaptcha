package org.vaadin.addons.captcha;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import net.logicsquad.nanocaptcha.audio.AudioCaptcha;

import javax.sound.sampled.AudioInputStream;


@Route("")
public class DemoView extends VerticalLayout {


    public DemoView(){
        AudioCaptcha audioCaptcha = new AudioCaptcha.Builder()
                                                    .addContent()
                                                    .build();

        AudioCaptchaPlayer player = new AudioCaptchaPlayer();
        AudioInputStream audioStream = audioCaptcha.getAudio().getAudioInputStream();
        player.setSrc(audioStream);
        add(player);
    }
}

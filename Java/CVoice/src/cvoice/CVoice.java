/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvoice;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.DataInputStream;
/**
 *
 * @author Hare Krishna
 */

public class CVoice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text;
        DataInputStream inp= new DataInputStream(System.in);
        try{
            System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            Synthesizer  synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            do {           
                text=inp.readLine();
                
                synthesizer.speakPlainText(text, null);
                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                
            }while (!text.equals("stop"));
            synthesizer.deallocate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class receiver {
    public static void prn(byte[] a){
        for(int i=0; i< a.length ; i++) {
            System.out.print(a[i] +" ");
        }
        System.out.println("");
        String s = new String(a, StandardCharsets.US_ASCII);
        System.out.println(s);
    }

    public static void main(String[] args) {
        int totalFramesRead = 0;
        File fileIn = new File("1.wav");
// somePathName is a pre-existing string whose value was
// based on a user selection.
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
            int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
            // Set an arbitrary buffer size of 1024 frames.
            int numBytes = 1024 * bytesPerFrame;
            byte[] audioBytes = new byte[numBytes];
            try {
                int numBytesRead = 0;
                int numFramesRead = 0;
                // Try to read numBytes bytes from the file.
                while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
                    // Calculate the number of frames actually read.
                    numFramesRead = numBytesRead / bytesPerFrame;
                    totalFramesRead += numFramesRead;
                    // Here, do something useful with the audio data that's
                    // now in the audioBytes array...
                   System.out.println("numBytesRead="+numBytesRead+"; numFramesRead="+numFramesRead);
                    prn(audioBytes);
                }

            } catch (Exception ex) {
                // Handle the error...
            }
        } catch (Exception e) {
            // Handle the error...
        }
    }

}

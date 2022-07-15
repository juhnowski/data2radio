import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.File;
import java.nio.file.Files;

public class sender {
    public static final int REPEATS = 3;
    public static void main(String[] args) {
        try {
            byte[] buf = new byte[44100];
            AudioFormat af = new AudioFormat( (float )44100, 8, 1, true, false );
            SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
            sdl.open();
            sdl.start();

            File file = new File("C:\\WL_DATA\\02_06_2022.txt");
            byte[] buf1 = Files.readAllBytes(file.toPath());
            byte[] buf2 = new byte[buf1.length*REPEATS];
            for (int i = 0; i < buf1.length; i++){
                for (int j=0;j<REPEATS;j++) {
                    buf2[i*REPEATS+j] = buf1[i];
                }
            }

            for( int i = 0; i < 1000 * (float )44100 / 1000; i++ ) {
                double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
                buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
                sdl.write( buf, 0, 1 );
            }

            sdl.write( buf2, 0, buf2.length );

            for( int i = 0; i < 1000 * (float )44100 / 1000; i++ ) {
                double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
                buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
                sdl.write( buf, 0, 1 );
            }


            sdl.drain();
            sdl.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

package one.empty3.library;
import java.io.File;
public class VideoDecoderFactory {
     public static VideoDecoderMontemedia createInstance(File f, TextureMov m)
      {
          return new DecodeMonte(f,m);
       }


}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyect;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Edgar
 *
 */
public class Proyect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //System.loadLibrary("C:\\opencv\\build\\x64\\vc15\\bin\\opencv_ffmpeg410_64.dll");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //Read image from file first param:file location ,second param:color space 
        Mat img = Imgcodecs.imread("C:\\Users\\Edgar\\Documents\\NetBeansProjects\\Proyect\\src\\proyect\\imagen.jpg");

        
        System.out.println("Image Loaded: "+img);
        Mat hls = new Mat(img.rows(),img.cols(), CvType.CV_8UC3);
        Imgproc.cvtColor(img, hls, Imgproc.COLOR_BGR2HLS);
        
        List<Mat> channels = new ArrayList();
        Core.split(hls, channels);
        
      
        
        
        for(int i=0; i<hls.rows(); i++){
            for(int j = 0; j<hls.cols(); j++){
                if( channels.get(0).get(i, j)[0] >=  (40/2) && channels.get(0).get(i, j)[0] <=  (50/2) ){                 
                    channels.get(2).put(i, j,(1.0*255));
                    channels.get(1).put(i, j,(0.5*255));   
                }else{
                    channels.get(0).put(i, j,0);
                    channels.get(2).put(i, j,(0.0*255));
                    channels.get(1).put(i, j,(0.0*255));   
                }
            }//System.out.println(i+" , "+j+": "+channels.get(1).get(i,j)[0]+" "+channels.get(2).get(i,j)[0]);
        }
        Core.merge(channels, hls);
        Imgcodecs.imwrite("C:\\Users\\Edgar\\Desktop\\imagenhsl.png", hls);
   
      

    }

}

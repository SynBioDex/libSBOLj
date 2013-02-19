package org.cidarlab.pigeon;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Janoo Fernandes
 */
public class WeyekinPoster {

    //*****WebGraphViz variables*****\\
    
    private static String mGraphVizString;
    private static String mGraphVizImageIdentifier = "Weyekin output image";
    private static URI mGraphVizURI;
//    private static String mGraphVizPath = "http://128.197.164.27/graphviz/";
    private static String mGraphVizPath = "http://cidar1.bu.edu:5801/graphviz/";
    private static String mGraphVizDotText = "digraph { a [shape=pentagon]; a -> b }";
    private static String mGraphVizAspectRatioString="";
    
    
    //*****Pigeon variables**********\\
    private static String mPigeonString;
    private static String mPigeonImageIdentifier = "Weyekin output image";
    private static URI mPigeonURI;
//    private static String mPigeonPath = "http://128.197.164.27/";
    private static String mPigeonPath = "http://cidar1.bu.edu:5801/";
    private static String mPigeonText = "| foo \n | bar";
    private static String mPigeonBackgroundColorHexString="";
    
    public static void postMyVision() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://128.197.164.27/graphviz/graphviz.php");
        HttpPost httpPost = new HttpPost("http://cidar1.bu.edu:5801/graphviz/graphviz.php");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("gviz", mGraphVizDotText));
        if (!mGraphVizAspectRatioString.isEmpty()) {
            nvps.add(new BasicNameValuePair("aratio", mGraphVizAspectRatioString));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try {
                HttpResponse response2 = httpclient.execute(httpPost);
                try {
//                    System.out.println(response2.getStatusLine());
                    HttpEntity entity2 = response2.getEntity();
//                    entity2.writeTo(System.out);
                    // do something useful with the response body
//                    System.out.println();
                    // and ensure it is fully consumed
                    EntityUtils.consume(entity2);
                } finally {
                    httpPost.releaseConnection();
                }
            } catch (ClientProtocolException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
//            httpPost.setURI(new URI("http://128.197.164.27/graphviz/graphviz1.php"));
            httpPost.setURI(new URI("http://cidar1.bu.edu:5801/graphviz/graphviz1.php"));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                try {
                    HttpResponse response2 = httpclient.execute(httpPost);
                    try {
                        //System.out.println(response2.getStatusLine());
                        HttpEntity entity2 = response2.getEntity();
                        mGraphVizString = EntityUtils.toString(entity2);
                        entity2.writeTo(System.out);
                        // do something useful with the response body
                        System.out.println();
                        // and ensure it is fully consumed
                        EntityUtils.consume(entity2);
                    } finally {
                        httpPost.releaseConnection();
                    }
                } catch (ClientProtocolException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!mGraphVizString.isEmpty()) {
            parseGraphVizResponse();
        }
    }
    
    public static void postMyBird() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://128.197.164.27/pigeon1.php");
        HttpPost httpPost = new HttpPost("http://cidar1.bu.edu:5801/pigeon1.php");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("desc", mPigeonText));
        if (!mPigeonBackgroundColorHexString.isEmpty()) {
            nvps.add(new BasicNameValuePair("bgcolor", mPigeonBackgroundColorHexString));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try {
                HttpResponse response2 = httpclient.execute(httpPost);
                try {
//                    System.out.println(response2.getStatusLine());
                    HttpEntity entity2 = response2.getEntity();
//                    entity2.writeTo(System.out);
                    // do something useful with the response body
//                    System.out.println();
                    // and ensure it is fully consumed
                    EntityUtils.consume(entity2);
                } finally {
                    httpPost.releaseConnection();
                }
            } catch (ClientProtocolException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
//            httpPost.setURI(new URI("http://128.197.164.27/pigeon.php"));
            httpPost.setURI(new URI("http://cidar1.bu.edu:5801/pigeon.php"));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                try {
                    HttpResponse response2 = httpclient.execute(httpPost);
                    try {
                        //System.out.println(response2.getStatusLine());
                        HttpEntity entity2 = response2.getEntity();
                        mPigeonString = EntityUtils.toString(entity2);
                        entity2.writeTo(System.out);
                        // do something useful with the response body
                        System.out.println();
                        // and ensure it is fully consumed
                        EntityUtils.consume(entity2);
                    } finally {
                        httpPost.releaseConnection();
                    }
                } catch (ClientProtocolException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!mPigeonString.isEmpty()) {
            parsePigeonResponse();
        }
    }
    
    private static void parsePigeonResponse() {
        String[] split = mPigeonString.split("\n");
        for (String s : split) {
            if (s.contains(mPigeonImageIdentifier)) {
                try {
                    String path = mPigeonPath + s.substring(s.indexOf("img src =")+9, s.indexOf("alt =")-2);
                    mPigeonURI = new URI(path);
                    launchPage(mPigeonURI);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }
    
    private static void parseGraphVizResponse() {
        String[] split = mGraphVizString.split("\n");
        for (String s : split) {
            if (s.contains(mGraphVizImageIdentifier)) {
                try {
                    String path = mGraphVizPath + s.substring(s.indexOf("img src =")+9, s.indexOf("alt =")-1);
                    mGraphVizURI = new URI(path);
                    launchPage(mGraphVizURI);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }

    private static void launchPage(URI pURI) {
        try {
            java.awt.Desktop.getDesktop().browse(pURI);
        } catch (IOException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static URI getmGraphVizURI() {
        return mGraphVizURI;
    }

    public static URI getmPigeonURI() {
        return mPigeonURI;
    }
    
    public static void setDotText(String text) {
        mGraphVizDotText = text;
    }
    
    /**
     * Aspect ratio ([0.0, 2.0])
     * Using major, minor, major, major
     * @param aratio 
     */
    public static void setWebGraphVizAspectRatioString(String aratio) {
        mGraphVizAspectRatioString = aratio;
    }
    
    public static void setPigeonText(String text) {
        mPigeonText = text;
    }
    
    /**
     * Background color:
     * If you assign #xyzwuv to the bgcolor variable in weyekin, then you can 
     * control the background color on pigeon images. 
     * xyzwuv should each be a digit between 0-9 or a letter between a-f or A-F
     * @param bgcolor 
     */
    public static void setPigeonBackgroundColorString(String bgcolor) {
        mPigeonBackgroundColorHexString = bgcolor;
    }
    
    @Deprecated
    public static void postMyCorrectVision() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://128.197.164.27/weyekin/weyekin1.php");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("gviz", "digraph { a -> b }"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try {
                HttpResponse response2 = httpclient.execute(httpPost);
                try {
                    //System.out.println(response2.getStatusLine());
                    HttpEntity entity2 = response2.getEntity();
                    entity2.writeTo(System.out);
                    // do something useful with the response body
                    System.out.println();
                    // and ensure it is fully consumed
                    EntityUtils.consume(entity2);
                } finally {
                    httpPost.releaseConnection();
                }
            } catch (ClientProtocolException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


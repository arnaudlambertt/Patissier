package MODEL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Requests
{

    public static void upload(File file)
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost("http://93.3.238.99/upload.php");
        FileBody filebody = new FileBody(file);

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("fileToUpload", filebody)
                .build();

        httppost.setEntity(reqEntity);

        try (CloseableHttpResponse response = httpclient.execute(httppost))
        {
            //System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
//            if (resEntity != null)
//                System.out.println("Response content a la ligne : \n" + EntityUtils.toString(resEntity));
            EntityUtils.consume(resEntity);
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public static ArrayList<String> scandir()
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://93.3.238.99/scandir.php");
        try (CloseableHttpResponse response = httpclient.execute(httpget))
        {
            //System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            
            ArrayList<String> filenames = new ArrayList<>();
            if (resEntity != null)
            {
                Scanner scanner = new Scanner(EntityUtils.toString(resEntity));
                while(scanner.hasNext())
                    filenames.add(scanner.nextLine());
            }
            EntityUtils.consume(resEntity);
            return filenames;
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}

package akshatmittal61.client;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class HttpClientService {
    
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    public HttpClientService() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }
    
    public String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet);
    }
    
    public <T> T get(String url, Class<T> responseType) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        String responseBody = executeRequest(httpGet);
        return objectMapper.readValue(responseBody, responseType);
    }
    
    public <T> T get(String url, TypeReference<T> typeReference) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        String responseBody = executeRequest(httpGet);
        return objectMapper.readValue(responseBody, typeReference);
    }
    
    public String post(String url, String jsonBody) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(jsonBody));
        httpPost.setHeader("Content-Type", "application/json");
        return executeRequest(httpPost);
    }
    
    public <T> T post(String url, String jsonBody, Class<T> responseType) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(jsonBody));
        httpPost.setHeader("Content-Type", "application/json");
        String responseBody = executeRequest(httpPost);
        return objectMapper.readValue(responseBody, responseType);
    }
    
    public <T> T post(String url, String jsonBody, TypeReference<T> typeReference) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(jsonBody));
        httpPost.setHeader("Content-Type", "application/json");
        String responseBody = executeRequest(httpPost);
        return objectMapper.readValue(responseBody, typeReference);
    }
    
    public String put(String url, String jsonBody) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(jsonBody));
        httpPut.setHeader("Content-Type", "application/json");
        return executeRequest(httpPut);
    }
    
    public <T> T put(String url, String jsonBody, Class<T> responseType) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(jsonBody));
        httpPut.setHeader("Content-Type", "application/json");
        String responseBody = executeRequest(httpPut);
        return objectMapper.readValue(responseBody, responseType);
    }
    
    public <T> T put(String url, String jsonBody, TypeReference<T> typeReference) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(jsonBody));
        httpPut.setHeader("Content-Type", "application/json");
        String responseBody = executeRequest(httpPut);
        return objectMapper.readValue(responseBody, typeReference);
    }
    
    public String delete(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        return executeRequest(httpDelete);
    }
    
    private String executeRequest(HttpRequestBase request) throws IOException {
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            
            if (statusCode >= 200 && statusCode < 300) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new IOException("HTTP request failed with status: " + statusCode);
            }
        } finally {
            request.releaseConnection();
        }
    }
    
    public void close() throws IOException {
        httpClient.close();
    }
}

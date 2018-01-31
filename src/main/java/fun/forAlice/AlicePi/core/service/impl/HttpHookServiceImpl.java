package fun.forAlice.AlicePi.core.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class HttpHookServiceImpl {
	private Logger logger = LoggerFactory.getLogger(HttpHookServiceImpl.class);
	
	CloseableHttpClient  httpClient;
	
	@PostConstruct
	public void init()  {
		httpClient =  HttpClients.createDefault();
		


	}
	
	public String postHook(String url, String json) throws ClientProtocolException, IOException{
		HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json);

		entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
		httpPost.setEntity(
				entity
				);

		CloseableHttpResponse  response = httpClient.execute(httpPost);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}
		String output;
		BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

		StringBuffer res = new StringBuffer();
		while ((output = br.readLine()) != null) {
			res.append(output);
		}

		response.close();
		return res.toString();
	}
}

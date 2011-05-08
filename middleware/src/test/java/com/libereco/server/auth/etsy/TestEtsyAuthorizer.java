/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.auth.etsy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import junit.framework.TestCase;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.libereco.server.listings.etsy.create.CreateListingResponse;
import com.libereco.server.listings.etsy.image.upload.UploadListingImageResponse;

/**
 * @author Aleksandar
 * 
 */
public class TestEtsyAuthorizer extends TestCase {

	private static final String SANDBOX_API_KEY = "a9vtvmrj8ypqhaevuch4d9rs";

	private static final String SANDBOX_SHARED_SECRET = "b3neUpXsXnWe";

	// private static final String SANDBOX_API_KEY = "yms7ywfz5rk42a7w9wxmaxxb";
	//
	// private static final String SANDBOX_SHARED_SECRET = "9gRQkGfYJ7FV";

	// private static final String PROTECTED_RESOURCE_URL =
	// "http://api.twitter.com/1/account/verify_credentials.xml";

	// @Test
	// public void testScribe() throws Exception {
	//
	// OAuthService service = new ServiceBuilder()
	// .provider(EtsySandboxScribeOauthApi.class)
	// .apiKey(SANDBOX_API_KEY).apiSecret(SANDBOX_SHARED_SECRET)
	// .build();
	//
	// Scanner in = new Scanner(System.in);
	//
	// System.out.println("=== Etsy OAuth Workflow ===");
	// System.out.println();
	//
	// // Obtain the Request Token
	// System.out.println("Fetching the Request Token...");
	// Token requestToken = service.getRequestToken();
	// System.out.println("Got the Request Token : " + requestToken);
	// System.out.println();
	//
	// System.out.println("Now go and authorize Scribe here:");
	// String verificationUrl = service.getAuthorizationUrl(requestToken);
	// System.out.println(verificationUrl);
	// System.out.println("And paste the verifier here");
	// System.out.print(">>");
	// Verifier verifier = new Verifier(in.nextLine());
	// System.out.println();
	//
	// // Trade the Request Token and Verfier for the Access Token
	// System.out.println("Trading the Request Token for an Access Token...");
	// Token accessToken = service.getAccessToken(requestToken, verifier);
	// System.out.println("Got the Access Token!");
	// System.out.println("(if your curious it looks like this: "
	// + accessToken + " )");
	// System.out.println();
	//
	// // Now let's go and ask for a protected resource!
	// System.out.println("Now we're going to access a protected resource...");
	// // TODO: Update to point to a
	// String protectedResourceUrl =
	// "http://api.twitter.com/1/account/verify_credentials.xml";
	// OAuthRequest request = new OAuthRequest(Verb.GET, protectedResourceUrl);
	// service.signRequest(accessToken, request);
	// Response response = request.send();
	// System.out.println("Got it! Lets see what we found...");
	// System.out.println();
	// System.out.println(response.getBody());
	//
	// System.out.println();
	// System.out
	// .println("Thats it man! Go and build something awesome with Scribe! :)");
	//
	// }

	// @Test
	// public void testSignpost_1_2_1() throws Exception {
	//
	// // OAuthConsumer consumer = new DefaultOAuthConsumer(SANDBOX_API_KEY,
	// // SANDBOX_SHARED_SECRET, SignatureMethod.HMAC_SHA1);
	//
	// OAuthConsumer consumer = new DefaultOAuthConsumer(SANDBOX_API_KEY,
	// SANDBOX_SHARED_SECRET);
	//
	// // OAuthProvider provider = new DefaultOAuthProvider(consumer,
	// // "http://openapi.etsy.com/v2/oauth/request_token",
	// // "http://openapi.etsy.com/v2/oauth/access_token",
	// // "http://www.etsy.com/oauth/signin");
	//
	// OAuthProvider provider = new DefaultOAuthProvider(
	// "http://openapi.etsy.com/v2/oauth/request_token",
	// "http://openapi.etsy.com/v2/oauth/access_token",
	// "http://www.etsy.com/oauth/signin");
	//
	// // OAuthProvider provider = new DefaultOAuthProvider(
	// // "http://openapi.etsy.com/v2/sandbox/oauth/request_token",
	// // "http://openapi.etsy.com/v2/sandbox/oauth/access_token",
	// // "http://www.etsy.com/oauth/signin");
	//
	// System.out.println("Fetching request token from Etsy...");
	//
	// // we do not support callbacks, thus pass OOB
	// //String authUrl = provider.retrieveRequestToken(OAuth.OUT_OF_BAND);
	// String authUrl = provider.retrieveRequestToken(consumer,
	// OAuth.OUT_OF_BAND);
	//
	// System.out.println("Request token: " + consumer.getToken());
	// System.out.println("Token secret: " + consumer.getTokenSecret());
	//
	// System.out.println("Now visit:\n" + authUrl
	// + "\n... and grant this app authorization");
	// System.out
	// .println("Enter the PIN code and hit ENTER when you're done:");
	//
	// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// String pin = br.readLine();
	//
	// System.out.println("Fetching access token from Etsy...");
	//
	// //provider.retrieveAccessToken(pin);
	// provider.retrieveAccessToken(consumer, pin);
	//
	// System.out.println("Access token: " + consumer.getToken());
	// System.out.println("Token secret: " + consumer.getTokenSecret());
	//
	// URL url = new URL("http://openapi.etsy.com/v2/private/users/__SELF__");
	// HttpURLConnection request = (HttpURLConnection) url.openConnection();
	//
	// consumer.sign(request);
	//
	// System.out.println("Sending request to Etsy...");
	// request.connect();
	//
	// System.out.println("Response: " + request.getResponseCode() + " "
	// + request.getResponseMessage());
	//
	// System.out.println("Payload:");
	// InputStream stream = request.getInputStream();
	// String stringbuff = "";
	// byte[] buffer = new byte[4096];
	//
	// while (stream.read(buffer) > 0) {
	// for (byte b : buffer) {
	// stringbuff += (char) b;
	// }
	// }
	//
	// System.out.print(stringbuff);
	// stream.close();
	// }
	//

	@Test
	public void testSignpost_1_2_1_CommonsHttp() throws Exception {

		// OAuthConsumer consumer = new DefaultOAuthConsumer(SANDBOX_API_KEY,
		// SANDBOX_SHARED_SECRET, SignatureMethod.HMAC_SHA1);

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(SANDBOX_API_KEY,
				SANDBOX_SHARED_SECRET);

		// OAuthProvider provider = new DefaultOAuthProvider(consumer,
		// "http://openapi.etsy.com/v2/oauth/request_token",
		// "http://openapi.etsy.com/v2/oauth/access_token",
		// "http://www.etsy.com/oauth/signin");

		OAuthProvider provider = new CommonsHttpOAuthProvider(
				"http://openapi.etsy.com/v2/sandbox/oauth/request_token",
				"http://openapi.etsy.com/v2/sandbox/oauth/access_token",
				"http://www.etsy.com/oauth/signin");

		// OAuthProvider provider = new DefaultOAuthProvider(
		// "http://openapi.etsy.com/v2/sandbox/oauth/request_token",
		// "http://openapi.etsy.com/v2/sandbox/oauth/access_token",
		// "http://www.etsy.com/oauth/signin");

		System.out.println("Fetching request token from Etsy...");

		// we do not support callbacks, thus pass OOB
		// String authUrl = provider.retrieveRequestToken(OAuth.OUT_OF_BAND);

		String authUrl = provider.retrieveRequestToken(consumer,
				OAuth.OUT_OF_BAND);
		// String authUrl = provider.retrieveRequestToken(consumer,
		// "http://example.com");

		System.out.println("Request token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());

		System.out.println("Now visit:\n" + authUrl
				+ "\n... and grant this app authorization");
		System.out
				.println("Enter the PIN code and hit ENTER when you're done:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pin = br.readLine();

		System.out.println("Fetching access token from Etsy...");

		// provider.setOAuth10a(false);

		// provider.retrieveAccessToken(pin);
		provider.retrieveAccessToken(consumer, pin);

		System.out.println("Access token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());

		// URL url = new
		// URL("http://openapi.etsy.com/v2/private/users/__SELF__");
		// HttpURLConnection request = (HttpURLConnection) url.openConnection();
		//
		// consumer.sign(request);
		//
		// System.out.println("Sending request to Etsy...");
		// request.connect();
		//
		// System.out.println("Response: " + request.getResponseCode() + " "
		// + request.getResponseMessage());
		//
		// System.out.println("Payload:");
		// InputStream stream = request.getInputStream();
		// String stringbuff = "";
		// byte[] buffer = new byte[4096];
		//
		// while (stream.read(buffer) > 0) {
		// for (byte b : buffer) {
		// stringbuff += (char) b;
		// }
		// }
		//
		// System.out.print(stringbuff);
		// stream.close();

		// Test sending a message to itself
		HttpPost request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/users/__SELF__");

		StringEntity body = new StringEntity("city=hamburg&label="
				+ URLEncoder.encode("Send via Signpost!", "UTF-8"));

		body.setContentType("application/x-www-form-urlencoded");
		request.setEntity(body);
		consumer.sign(request);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		printResponse(response);

		// Get shipping templates
		// HttpGet getRequest = new
		// HttpGet("http://openapi.etsy.com/v2/sandbox/public/users/13210389/shipping/templates");
		HttpGet getRequest = new HttpGet(
				"http://openapi.etsy.com/v2/sandbox/private/users/13210389/shipping/templates");

		// HttpGet getRequest = new HttpGet(
		// "http://openapi.etsy.com/v2/sandbox/public/users/14451731");

		// body.setContentType("application/x-www-form-urlencoded");
		// request.setEntity(body);
		consumer.sign(getRequest);

		System.out
				.println("------------------- Sending get shipping templates request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(getRequest);

		System.out.println("Response, status ["
				+ response.getStatusLine().getStatusCode()
				+ "], reason phrase ["
				+ response.getStatusLine().getReasonPhrase() + "]");

		printResponse(response);

		// Get all shipping templates
		// HttpGet getRequest = new HttpGet(
		// "http://openapi.etsy.com/v2/sandbox/public/users/13210389/shipping/templates");

		getRequest = new HttpGet(
				"http://openapi.etsy.com/v2/sandbox/private/users/13210389");

		// HttpGet getRequest = new HttpGet(
		// "http://openapi.etsy.com/v2/sandbox/public/users/14451731");

		// body.setContentType("application/x-www-form-urlencoded");
		// request.setEntity(body);
		consumer.sign(getRequest);

		System.out.println("Sending get user request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(getRequest);

		System.out.println("Response, status ["
				+ response.getStatusLine().getStatusCode()
				+ "], reason phrase ["
				+ response.getStatusLine().getReasonPhrase() + "]");

		printResponse(response);

		// Create shipping template
		request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/shipping/templates");

		// body = new StringEntity(
		// "title=myTemplate&origin_country_id=209&primary_cost=2&secondary_cost=1");

		body = new StringEntity(
				"title:myTemplate&origin_country_id=209&primary_cost=2&secondary_cost=1");

		body.setContentType("application/x-www-form-urlencoded");
		request.setEntity(body);
		consumer.sign(request);

		System.out
				.println("------------------- Sending create shipping template request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(request);

		printResponse(response);

		// Create payment template
		request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/payments/templates/");
		body = new StringEntity(
				"allow_check=true&allow_mo=true&state=new+york&zip=10111&allow_other=true&allow_paypal=true&city=any&country_id=76&first_line=any&name=any&paypal_email=any@example.com&second_line=any");

		body.setContentType("application/x-www-form-urlencoded");
		request.setEntity(body);
		consumer.sign(request);

		System.out
				.println("------------------- Sending create payment template request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(request);

		printResponse(response);

		// Send listing request
		request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/listings/");
		body = new StringEntity(
				"quantity=1&title=libEtsyTest&description=libEtsyDesc&price=2&tags=accessories&shipping_template_id=7071909");

		body.setContentType("application/x-www-form-urlencoded");
		request.setEntity(body);
		consumer.sign(request);

		System.out
				.println("------------------- Sending create listing request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(request);

		printResponse(response, false);

		GsonBuilder gsonBuilder = new GsonBuilder();

		// gsonBuilder.registerTypeAdapter(EtsyCategory.class,
		// new EtsyCategoryDeserializer());
		//
		// gsonBuilder.registerTypeAdapter(EtsyCategoryResponse.class,
		// new EtsyCategoryResponseDeserializer());

		// gson = new GsonBuilder().setPrettyPrinting().create();

		Gson gson = gsonBuilder.create();
		String responseJson = EntityUtils.toString(response.getEntity());

		System.out.println("Resonse JSON: " + responseJson);
		CreateListingResponse createListingResponse = gson.fromJson(responseJson,
				CreateListingResponse.class);

		System.out.println("Create listing response: " + createListingResponse);

		// String listingId = "73184451";
		// String listingId = "73184457";
		String listingId = createListingResponse.getResults().get(0)
				.getListingId().toString();

		// Upload listing image
		request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/listings/"
						+ listingId + "/images");

		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);

		// For File parameters
		entity.addPart("image", new FileBody(new File(
				"C:\\temp\\testThumbnail2.jpg"), "multipart/form-data"));

		// For usual String parameters
		entity.addPart("rank",
				new StringBody("1", "text/plain", Charset.forName("UTF-8")));

		request.setEntity(entity);

		// FileBody imageFileBody = new FileBody(new
		// File("C:\\temp\\testThumbnail2.jpg"));
		// StringBody comment = new StringBody("Etsy listing image");
		//
		// MultipartEntity multipartEntity = new MultipartEntity();
		// multipartEntity.addPart("bin", imageFileBody);
		// multipartEntity.addPart("comment", comment);
		//
		// request.setEntity(multipartEntity);

		// body = new StringEntity(
		// "quantity=1&title=libEtsyTest&description=libEtsyDesc&price=2&tags=accessories&shipping_template_id=7071909");
		//
		// //body.setContentType("application/x-www-form-urlencoded");
		// body.setContentType("multipart/form-data");
		// request.setEntity(body);

		consumer.sign(request);

		// listingId = "73184458";

		System.out
				.println("------------------- Sending upload listing image request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(request);

		printResponse(response, false);

		gson = gsonBuilder.create();
		responseJson = EntityUtils.toString(response.getEntity());

		System.out.println("Resonse JSON: " + responseJson);
		UploadListingImageResponse uploadImageResponse = gson.fromJson(responseJson,
				UploadListingImageResponse.class);

		System.out.println("Upload listing image response: "
				+ uploadImageResponse);

		request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/listings/"
						+ listingId + "/images");

		entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		// For File parameters
		entity.addPart("image", new FileBody(new File(
				"C:\\temp\\testThumbnail2.jpg")));

		// For usual String parameters
		entity.addPart("rank", new StringBody("1"));

		request.setEntity(entity);

		// FileBody imageFileBody = new FileBody(new
		// File("C:\\temp\\testThumbnail2.jpg"));
		// StringBody comment = new StringBody("Etsy listing image");
		//
		// MultipartEntity multipartEntity = new MultipartEntity();
		// multipartEntity.addPart("bin", imageFileBody);
		// multipartEntity.addPart("comment", comment);
		//
		// request.setEntity(multipartEntity);

		// body = new StringEntity(
		// "quantity=1&title=libEtsyTest&description=libEtsyDesc&price=2&tags=accessories&shipping_template_id=7071909");
		//
		// //body.setContentType("application/x-www-form-urlencoded");
		// body.setContentType("multipart/form-data");
		// request.setEntity(body);
		consumer.sign(request);

		System.out
				.println("------------------- Sending alternate upload listing image request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(request);

		printResponse(response, false);

		gson = gsonBuilder.create();
		responseJson = EntityUtils.toString(response.getEntity());

		System.out.println("Resonse JSON: " + responseJson);
		uploadImageResponse = gson.fromJson(responseJson,
				UploadListingImageResponse.class);

		System.out.println("Upload listing image response: "
				+ uploadImageResponse);

	}

	private void printResponse(HttpResponse response) throws ParseException,
			IOException {

		System.out.println("Response, status ["
				+ response.getStatusLine().getStatusCode()
				+ "], reason phrase ["
				+ response.getStatusLine().getReasonPhrase() + "]");

		printHeaders(response);
		printResponseEntity(response);
	}

	private void printResponse(HttpResponse response,
			boolean printResponseEntity) throws ParseException, IOException {

		System.out.println("Response, status ["
				+ response.getStatusLine().getStatusCode()
				+ "], reason phrase ["
				+ response.getStatusLine().getReasonPhrase() + "]");

		printHeaders(response);
		if (printResponseEntity) {
			printResponseEntity(response);
		}
	}

	private void printResponseEntity(HttpResponse response)
			throws ParseException, IOException {
		HttpEntity entity = response.getEntity();
		String charSet = EntityUtils.getContentCharSet(entity);
		System.out.println("Char set [" + charSet
				+ "]\r\n, entity content type [" + entity.getContentType()
				+ "]\r\n, entity as string [" + EntityUtils.toString(entity)
				+ "]");
	}

	@Test
	public void testSignpostCommonsHttp() throws Exception {

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(SANDBOX_API_KEY,
				SANDBOX_SHARED_SECRET);

		OAuthProvider provider = new CommonsHttpOAuthProvider(
				"http://openapi.etsy.com/v2/sandbox/oauth/request_token",
				"http://openapi.etsy.com/v2/sandbox/oauth/access_token",
				"http://www.etsy.com/oauth/signin");

		System.out.println("Fetching request token from Etsy...");

		String authUrl = provider.retrieveRequestToken(consumer,
				OAuth.OUT_OF_BAND);

		System.out.println("Request token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());

		System.out.println("Now visit:\n" + authUrl
				+ "\n... and grant this app authorization");
		System.out
				.println("Enter the PIN code and hit ENTER when you're done:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pin = br.readLine();

		System.out.println("Fetching access token from Etsy...");
		provider.retrieveAccessToken(consumer, pin);

		System.out.println("Access token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());

		// Create shipping template
		HttpPost request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/shipping/templates");

		StringEntity body = new StringEntity(
				"title=myTemplate&origin_country_id=79&primary_cost=2&secondary_cost=1");

		body.setContentType("application/x-www-form-urlencoded");
		request.setEntity(body);
		consumer.sign(request);

		System.out.println("Sending update request to etsy...");

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		System.out.println("Response, status ["
				+ response.getStatusLine().getStatusCode()
				+ "], reason phrase ["
				+ response.getStatusLine().getReasonPhrase() + "]");

		printHeaders(response);

		// Send listing request
		request = new HttpPost(
				"http://openapi.etsy.com/v2/sandbox/private/listings/");
		body = new StringEntity(
				"quantity=1&title=any&description=any&price=2&tags=accessories&shipping_template_id=1");

		body.setContentType("application/x-www-form-urlencoded");
		request.setEntity(body);
		consumer.sign(request);

		System.out.println("Sending update request to etsy...");

		httpClient = new DefaultHttpClient();
		response = httpClient.execute(request);

		System.out.println("Response, status ["
				+ response.getStatusLine().getStatusCode()
				+ "], reason phrase ["
				+ response.getStatusLine().getReasonPhrase() + "]");

		printHeaders(response);
	}

	private void printHeaders(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		if (headers != null) {
			for (Header header : headers) {
				System.out.println("Header [" + header.getName() + "]: "
						+ header.getValue());

			}
		}
	}

	@Test
	public void testCreateListingResponseParser() {
		String responseJson = "{\"count\":1,\"results\":[{\"listing_id\":73184473,\"state\":\"active\",\"user_id\":13210389,\"title\":\"libEtsyTest\",\"description\":\"libEtsyDesc\",\"creation_tsz\":1304645317.27,\"ending_tsz\":1315195200,\"original_creation_tsz\":1304645317.27,\"last_modified_tsz\":1304645317.3,\"price\":\"2\",\"currency_code\":\"CAD\",\"quantity\":\"1\",\"tags\":[\"accessories\"],\"materials\":[],\"shop_section_id\":null,\"featured_rank\":null,\"state_tsz\":1304645317.3,\"hue\":352,\"saturation\":5,\"brightness\":62,\"is_black_and_white\":false,\"shop_id\":6296921,\"url\":{},\"views\":32,\"num_favorers\":0,\"ShippingInfo\":[{\"shipping_info_id\":400807630,\"origin_country_id\":79,\"destination_country_id\":null,\"currency_code\":\"CAD\",\"primary_cost\":\"7.00\",\"secondary_cost\":\"5.00\",\"listing_id\":73184473,\"region_id\":null,\"origin_country_name\":\"Canada\",\"destination_country_name\":\"Everywhere Else\"}]}],\"params\":{\"quantity\":\"1\",\"title\":\"libEtsyTest\",\"description\":\"libEtsyDesc\",\"price\":\"2\",\"materials\":null,\"tags\":\"accessories\",\"shipping_template_id\":\"7071909\",\"shop_section_id\":null,\"image\":null},\"type\":\"Listing\"}";

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		CreateListingResponse createListingResponse = gson.fromJson(responseJson,
				CreateListingResponse.class);

		System.out.println("Create listing response: " + createListingResponse);

	}
	// @Test
	// public void testSignpost_1_1() throws Exception {
	//
	// OAuthConsumer consumer = new DefaultOAuthConsumer(SANDBOX_API_KEY,
	// SANDBOX_SHARED_SECRET, SignatureMethod.HMAC_SHA1);
	//
	// OAuthProvider provider = new DefaultOAuthProvider(consumer,
	// "http://openapi.etsy.com/v2/oauth/request_token",
	// "http://openapi.etsy.com/v2/oauth/access_token",
	// "http://www.etsy.com/oauth/signin");
	//
	// System.out.println("Fetching request token from Etsy...");
	//
	// // we do not support callbacks, thus pass OOB
	// String authUrl = provider.retrieveRequestToken(OAuth.OUT_OF_BAND);
	//
	// System.out.println("Request token: " + consumer.getToken());
	// System.out.println("Token secret: " + consumer.getTokenSecret());
	//
	// System.out.println("Now visit:\n" + authUrl
	// + "\n... and grant this app authorization");
	// System.out
	// .println("Enter the PIN code and hit ENTER when you're done:");
	//
	// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// String pin = br.readLine();
	//
	// System.out.println("Fetching access token from Etsy...");
	//
	// provider.retrieveAccessToken(pin);
	//
	// System.out.println("Access token: " + consumer.getToken());
	// System.out.println("Token secret: " + consumer.getTokenSecret());
	//
	// URL url = new URL("http://openapi.etsy.com/v2/private/users/__SELF__");
	// HttpURLConnection request = (HttpURLConnection) url.openConnection();
	//
	// consumer.sign(request);
	//
	// System.out.println("Sending request to Etsy...");
	// request.connect();
	//
	// System.out.println("Response: " + request.getResponseCode() + " "
	// + request.getResponseMessage());
	//
	// System.out.println("Payload:");
	// InputStream stream = request.getInputStream();
	// String stringbuff = "";
	// byte[] buffer = new byte[4096];
	//
	// while (stream.read(buffer) > 0) {
	// for (byte b : buffer) {
	// stringbuff += (char) b;
	// }
	// }
	//
	// System.out.print(stringbuff);
	// stream.close();
	// }
}
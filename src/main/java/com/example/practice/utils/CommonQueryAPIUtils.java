package com.example.practice.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CommonQueryAPIUtils {

	public static Map<String, Object> apiServiceMulti(List<String> apiServiceNameList,
			List<List<Map<String, Object>>> repomethodList) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		try {
			final_data.put("status", true);
			final_data.put("scode", "01");
			final_data.put("sdesc", "Multiple lists found");

			String apiServiceName = "";

			for (int i = 0; i < repomethodList.size(); i++) {

				apiServiceName = apiServiceNameList.get(i);

				if (repomethodList.get(i).size() > 0) {
					final_data.put(apiServiceName + "_status", true);
					final_data.put(apiServiceName, repomethodList.get(i));
				} else {
					final_data.put(apiServiceName + "_status", false);
					final_data.put(apiServiceName, "No data found");
				}
			}
		} catch (Exception e) {
			final_data = new LinkedHashMap<String, Object>();
			final_data.put("status", false);
			final_data.put("scode", "02");
			final_data.put("sdesc", "Internal server issue");
		}
		return final_data;

		// ************************************ Sample Controller
		// *****************************
		/*
		 * @GetMapping("/t") Map<String, Object> getMethod(@RequestParam Integer
		 * reqParam) { List<List<Map<String, Object>>> mulDataList =
		 * List.of(repo.get1(reqParam),repo.get2(reqParam)); List<String> mulNamesList =
		 * (List.of("get1Name","get2Name")); return
		 * commonQueryAPIUtils.apiServiceMulti(mulNamesList, mulDataList); }
		 */
	}

	public static Map<String, Object> apiServiceDelete(Integer deleteQueryfromRepo) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		try {
			if (deleteQueryfromRepo > 0) {
				final_data.put("status", true);
				final_data.put("scode", "01");
				final_data.put("sdesc", "Successfully Deleted");
			} else {
				final_data.put("status", false);
				final_data.put("scode", "03");
				final_data.put("sdesc", "No records found to delete");
			}
		} catch (Exception e) {

			final_data.put("status", false);
			final_data.put("scode", "02");
			final_data.put("sdesc", "Deletion Failed Due To: Internal Server Issue");
		}
		return final_data;
	}

	public static ResponseEntity<?> apiServiceUpdate(Integer updateQueryfromRepo) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		try {
			if (updateQueryfromRepo > 0) {
				final_data.put("status", true);
				final_data.put("scode", "01");
				final_data.put("sdesc", "Successfully Updated");
			} else {
				final_data.put("status", false);
				final_data.put("scode", "03");
				final_data.put("sdesc", "No records found to update");
			}
		} catch (Exception e) {

			final_data.put("status", false);
			final_data.put("scode", "02");
			final_data.put("sdesc", "Updation Failed Due To: Internal Server Issue");
		}
		return ResponseEntity.ok().body(final_data);
	}

//	public static String validationService(List<Object> values, List<String> names) {
//		String message = "";
//		try {
//			for (int i = 0; i < values.size(); i++) {
//				if (Objects.isNull(values.get(i)) || "".equals(values.get(i)) || values.get(i).toString().isBlank()) {
//					message += names.get(i) + " is required. ";
//				}
//			}
//		} catch (Exception e) {
//			
//			message = "Internal Server Issue";
//		}
//		return message;
//	}

	public static String validationServiceWithIndex(List<Object> values, List<String> names, Integer index) {
		String message = "";
		try {
			for (int i = 0; i < values.size(); i++) {
				if (Objects.isNull(values.get(i)) || "".equals(values.get(i)) || values.get(i).toString().isBlank()) {
					message += names.get(i) + " is required at index " + index + ". ";
				}
			}
		} catch (Exception e) {

			message = "Internal Server Issue";
		}
		return message;
	}

	public static ResponseEntity<Map<String, Object>> successResponse() {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		final_data.put("status", true);
		final_data.put("scode", "01");
		final_data.put("sdesc", "Request successfully submitted.");

		return ResponseEntity.ok().body(final_data);
	}

	public static ResponseEntity<Map<String, Object>> failureResponse(String errorMessage) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();

		final_data.put("status", false);

		if ("02".equals(errorMessage) || errorMessage.isBlank()) {
			final_data.put("scode", "02");
			final_data.put("sdesc", "Failed Due To: Internal Server Issue");
		} else {
			final_data.put("scode", "03");
			final_data.put("sdesc", "Failed Due To: " + errorMessage);
		}

		return ResponseEntity.ok().body(final_data);
	}

	public static ResponseEntity<?> unAuthorisedResponse() {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();

		final_data.put("status", false);
		final_data.put("scode", "401");
		final_data.put("sdesc", "Unauthorized request");

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(final_data);
	}

//	public static ResponseEntity<Map<String, Object>> manualResponse(String code, String message) {
//		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
//		if ("01".equals(code)) {
//			final_data.put("status", true);
//		} else {
//			final_data.put("status", false);
//		}
//		final_data.put("scode", code);
//		final_data.put("sdesc", message);
//
//		return ResponseEntity.ok().body(final_data);
//	}

	public static ResponseEntity<Map<String, Object>> catchResponse(Exception ex) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		final_data.put("status", false);
		final_data.put("scode", "02");
		final_data.put("sdesc", "Failed Due To: Internal Server Issue");
		exceptionLog(ex);
		return ResponseEntity.ok().body(final_data);
	}

	public static Map<String, Object> exceptionLog(Exception ex) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> ex_list = new ArrayList<Map<String, Object>>();

		String currentPackage = CommonQueryAPIUtils.class.getPackageName().trim();
		String basePackage = currentPackage.substring(0, currentPackage.indexOf('.', currentPackage.indexOf('.') + 1));

//		final_data.put("user", );
//		final_data.put("timestamp", );
//		final_data.put("ip", );
		final_data.put("base_package", basePackage);
		final_data.put("exception", ex.getClass().getName());
		final_data.put("message", ex.getMessage());

		System.out.println(":::Package::: " + basePackage + " :::ExClass::: " + ex.getClass().getName()
				+ " :::message::: " + ex.getMessage());

		StackTraceElement[] stackTrace = ex.getStackTrace();
		for (StackTraceElement element : stackTrace) {

			if (element.getClassName().contains(basePackage)) {

				Map<String, Object> inner_data = new LinkedHashMap<String, Object>();

				inner_data.put("exception_class", element.getClassName());
				inner_data.put("method", element.getMethodName());
				inner_data.put("line", element.getLineNumber());
				ex_list.add(inner_data);

				System.out.println("=> :::ExClass::: " + element.getClassName() + " :::Method::: "
						+ element.getMethodName() + " :::Line Number::: " + element.getLineNumber());
			}
		}

		final_data.put("ex_list", ex_list);
		return final_data;
	}

	public static String validatePojoProMax(ObjectNode objectNode) {
		String errMsg = "";
		StringBuilder errMsgBuilder = new StringBuilder();
		objectNode.fieldNames().forEachRemaining(key -> {
			JsonNode value = objectNode.get(key);
			if (value == null || value.isNull() || value.isTextual() && value.textValue().isEmpty()) {
				errMsgBuilder.append(key).append(" is required. ");
			}
		});
		errMsg = errMsgBuilder.toString();
		return errMsg;
	}

	public static Map<String, Object> uploadFileToAWSS3Bucket(byte[] fileByteArray, String fileNameWithExtension,
			String apiUrl) {

		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();

		ByteArrayResource fileResource = new ByteArrayResource(fileByteArray) {
			@Override
			public String getFilename() {
				return fileNameWithExtension;
			}
		};

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> payload = new LinkedMultiValueMap<>();
		payload.add("file", fileResource);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("File uploaded successfully! Path is " + responseEntity.getBody());
			responseMap.put("status", true);
			responseMap.put("path", responseEntity.getBody());
		} else {
			System.out.println("Failed to upload File. Response: " + responseEntity.getBody());
			responseMap.put("status", false);
		}

		return responseMap;
	}

	public static BufferedImage createImageWithText(String text) {
		int width = 140;
		int height = 75;

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bufferedImage.createGraphics();

		// Fill the background
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);

		// Draw the text
		graphics.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.BOLD, 30);
		graphics.setFont(font);
		graphics.drawString(text, 10, 50);

		// Dispose the graphics object to release resources
		graphics.dispose();

		return bufferedImage;
	}

	public static String randomString(int length) {
		SecureRandom rnd = new SecureRandom();
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String randomNumber(int length) {
		SecureRandom rnd = new SecureRandom();
		String AB = "0123456789";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	////////////////////////////////////// raji

	public static ResponseEntity<?> manualResponse(String code, String message) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		if ("01".equals(code)) {
			final_data.put("status", true);
		} else {
			final_data.put("status", false);
		}
		final_data.put("code", code);
		final_data.put("message", message);

		return ResponseEntity.ok().body(final_data);
	}

	public static ResponseEntity<Map<String, Object>> sResponse(String message) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		final_data.put("status", true);
		final_data.put("code", "01");
		final_data.put("message", message);

		return ResponseEntity.ok().body(final_data);
	}

	public static ResponseEntity<Map<String, Object>> fDynamicResponse(String message) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		final_data.put("status", false);
		final_data.put("code", "02");
		final_data.put("message", message);

		return ResponseEntity.ok().body(final_data);
	}

	public static ResponseEntity<?> fStaticResponse(String errorMessage) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();

		final_data.put("status", false);

		if ("02".equals(errorMessage) || errorMessage.isEmpty()) {
			final_data.put("code", "02");
			final_data.put("message", "Submission Failed Due To: Internal Server Issue");
		} else {
			final_data.put("code", "03");
			final_data.put("message", "Submission Failed Due To: " + errorMessage);
		}

		return ResponseEntity.ok().body(final_data);
	}

	public static ResponseEntity<Map<String, Object>> fCatchResponse(Exception ex) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		LocalDateTime logTime = LocalDateTime.now();
		String logId = logTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
		final_data.put("status", false);
		final_data.put("code", "02");
		final_data.put("message", "Failed Due To: Internal Server Issue. Time: " + logTime + ". Log Id: " + logId);
		System.out.println("====> ERROR :::: LOG ID: " + logId + " :::: LOG TIME: " + logTime + " :::: ERROR <====");
		exceptionLog(ex);
		return ResponseEntity.ok().body(final_data);
	}

	public static Map<String, Object> apiService(String apiServiceName, List<Map<String, Object>> repomethod) {
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		try {
			if (repomethod.size() > 0) {
				final_data.put("status", true);
				final_data.put("code", "01");
				final_data.put("message", "data found");
				final_data.put(apiServiceName, repomethod);
				final_data.put("data_count", repomethod.size());
			} else {
				final_data.put("status", false);
				final_data.put("code", "03");
				final_data.put("message", "No data found");
				final_data.put(apiServiceName, "No data found");
				final_data.put("data_count", 0);
			}
		} catch (Exception e) {

			final_data.put("status", false);
			final_data.put("code", "02");
			final_data.put("message", "Internal server issue");
			final_data.put(apiServiceName, "No data found");
			final_data.put("data_count", 0);
		}
		return final_data;
	}

	public static String validationService(List<Object> values, List<String> names) {
		String message = "";
		try {
			for (int i = 0; i < values.size(); i++) {
				if (Objects.isNull(values.get(i)) || "".equals(values.get(i))) {
					message += names.get(i) + " is required. ";
				}
			}
		} catch (Exception e) {

			message = "Internal Server Issue";
		}
		return message;
	}

}

/**
 * 
 */
package com.digilytics.javaAssignment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digilytics.javaAssignment.service.UsersService;
import com.digilytics.javaAssignment.vo.UserVo;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

/**
 * @author hemant
 *
 */

@RestController
public class UserController {

	@Autowired
	private UsersService userService;

	List<UserVo> userMessage = new ArrayList<>();

	@PostMapping("/register")
	public ResponseEntity<String> userCsvUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws IOException {
		// validate file
		// send CSV file convert into java bean
		List<UserVo> userVotemp = importCsvToBean(file);

		if (userVotemp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file is empty");
		}

		// send userVo bean to service
		userMessage = userService.registerUsersByCSV_File(userVotemp);
		// for success row sum
		Integer successCount = userMessage.stream().mapToInt(userVo -> userVo.getSuccessCount()).sum();

		// for faild row sum
		Integer faildCount = userMessage.stream().mapToInt(userVo -> userVo.getFaildCount()).sum();

		if (userMessage.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("<h3>Save successfully</h3>/br>" + "<h3>Total number of  row:</h3>"
							+ (successCount + faildCount) + "</br> " + " <h3>Number of pass row:</h3>" + successCount
							+ "</br> " + " <h3>Number of faild row:</h3>" + faildCount + "</br> ");
		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Some user are not saved</br>" + "<h3>Total number of  row:</h3>"
							+ (successCount + faildCount) + "</br> " + " <h3>Number of pass row:</h3>" + successCount
							+ "</br> " + " <h3>Number of faild row:</h3>" + faildCount + "</br> "
							+ " <a href='/com.digilytics.javaAssignment/error'>click to download error file</a>");

		}
	}

	/**
	 * 
	 * @param response    download file in browser
	 * @param userMessage
	 * @throws Exception
	 */
	@GetMapping("/error")
	public void exportCSV(HttpServletResponse response) throws Exception {

		// set file name and content type
		String filename = "error.csv";

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

		// create a csv writer
		StatefulBeanToCsv<UserVo> writer = new StatefulBeanToCsvBuilder<UserVo>(response.getWriter())

				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)

				.withOrderedResults(false).build();
		// remove blank entry
		List<UserVo> csvFormat = userMessage.stream().filter(userVo -> (StringUtils.isNotBlank(userVo.getEmailId())))
				.collect(Collectors.toList());

		// write all users to csv file
		writer.write(csvFormat);

	}

	/**
	 * 
	 * @param file
	 * @return list of userVo
	 */
	private List<UserVo> importCsvToBean(MultipartFile file) {
		List<UserVo> userVO = new ArrayList<>();
		// parse CSV file to create a list of `User` objects
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			// create Csv bean reader
			@SuppressWarnings("unchecked")
			CsvToBean<UserVo> csvToBean = new CsvToBeanBuilder(reader).withType(UserVo.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			// convert CsvToBean object to list of users
			userVO = csvToBean.parse();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return userVO;
	}

	// getter or setter
	public List<UserVo> getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(List<UserVo> userMessage) {
		this.userMessage = userMessage;
	}

}

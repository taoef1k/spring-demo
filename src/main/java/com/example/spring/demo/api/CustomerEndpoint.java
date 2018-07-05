package com.example.spring.demo.api;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.demo.model.Customer;
import com.example.spring.demo.service.CustomerService;
import com.example.spring.demo.util.CustomerUtil;

@RestController
@RequestMapping("/api/customer")
public class CustomerEndpoint {

	Logger log= LoggerFactory.getLogger(getClass());

	@Autowired
    CustomerService customerService;

	@GetMapping(path="/all")
	public List<Customer> getAllCustomer() {
	    return customerService.getAllCustomer();
	}

	@GetMapping(path="/get/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) {
	    return customerService.getCustomer(id);
	}

	// Delete customer
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		Customer customer= customerService.getCustomer(id);

		if(CustomerUtil.deleteFile(customer.getPhoto())) {
			customerService.delete(id);
		}

	    return ResponseEntity.ok().build();
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@ModelAttribute Customer customer) {

		if (!customer.getFile().isEmpty()) {
            //return new ResponseEntity("please select a file!", HttpStatus.OK);
						//Save file
						String filename= null;
						try {
							filename= CustomerUtil.saveFile(customer.getFile());
						} catch (IOException e) {
							log.error("failed upload file!");
							e.printStackTrace();
							return new ResponseEntity("failed upload file!", HttpStatus.OK);
						}

						customer.setPhoto(filename);
		}

		customerService.save(customer);

        return new ResponseEntity("Successfully save", new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

    	Customer customer= customerService.getCustomer(id);

        byte[] bytes = StreamUtils.copyToByteArray(CustomerUtil.getStreamFile(customer.getPhoto()));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}

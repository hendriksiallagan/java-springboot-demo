package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.id.json.JSONObject;

import com.bgw.translator.MessageTranslator;



@RestController
@SpringBootApplication
@RequestMapping("/iso")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	String configStr = "{\"f41\":{\"format\":\"%-8s\", \"variable\":\"card_acceptor_terminal\", \"options\":\"\", \"field_length\":\"8\", \"type\":\"STRING\"},\"f63\":{\"format\":\"%-32s%-30s%-50s%-18s\", \"variable\":\"locket_code, locket_name, locket_address, locket_phone\", \"options\":\"\", \"field_length\":\"130\", \"type\":\"LLLVAR\"},\"f32\":{\"format\":\"%-11s\", \"variable\":\"acq_institution_code\", \"options\":\"\", \"field_length\":\"11\", \"type\":\"LLVAR\"},\"f42\":{\"format\":\"%15s\", \"variable\":\"acceptor_identification_code\", \"options\":\"\", \"field_length\":\"15\", \"type\":\"STRING\"},\"f121\":{\"format\":\"%-32s\", \"variable\":\"payment_reference\", \"options\":\"\", \"field_length\":\"32\", \"type\":\"LLLVAR\"},\"f12\":{\"format\":\"%-6s\", \"variable\":\"local_time\", \"options\":\"\", \"field_length\":\"6\", \"type\":\"STRING\"},\"f120\":{\"format\":\"%-20s\", \"variable\":\"product_code\", \"options\":\"\", \"field_length\":\"20\", \"type\":\"LLLVAR\"},\"f11\":{\"format\":\"%06d\", \"variable\":\"stan\", \"options\":\"\", \"field_length\":\"6\", \"type\":\"NUMERIC\"},\"f33\":{\"format\":\"%-11s\", \"variable\":\"fwd_institution_code\", \"options\":\"\", \"field_length\":\"11\", \"type\":\"LLVAR\"},\"f13\":{\"format\":\"%-4s\", \"variable\":\"local_date\", \"options\":\"\", \"field_length\":\"4\", \"type\":\"STRING\"},\"f49\":{\"format\":\"%03d\", \"variable\":\"transaction_currency_code\", \"options\":\"\", \"field_length\":\"3\", \"type\":\"NUMERIC\"},\"f15\":{\"format\":\"%-4s\", \"variable\":\"settlement_date\", \"options\":\"\", \"field_length\":\"4\", \"type\":\"STRING\"},\"f37\":{\"format\":\"%012d\", \"variable\":\"reference_number\", \"options\":\"\", \"field_length\":\"12\", \"type\":\"NUMERIC\"},\"f48\":{\"format\":\"%11s%12s%01d\", \"variable\":\"meter_id, customer_id, id_selector\", \"options\":\"\", \"field_length\":\"24\", \"type\":\"LLLVAR\"},\"f2\":{\"format\":\"%-19s\", \"variable\":\"pan\", \"options\":\"\", \"field_length\":\"19\", \"type\":\"LLVAR\"},\"f18\":{\"format\":\"%04d\", \"variable\":\"merchant_type\", \"options\":\"\", \"field_length\":\"4\", \"type\":\"NUMERIC\"},\"f3\":{\"format\":\"%06d\", \"variable\":\"processing_code\", \"options\":\"\", \"field_length\":\"6\", \"type\":\"NUMERIC\"},\"f4\":{\"format\":\"%012d\", \"variable\":\"amount\", \"options\":\"\", \"field_length\":\"12\", \"type\":\"NUMERIC\"},\"f7\":{\"format\":\"%-10s\", \"variable\":\"transmission_date_time\", \"options\":\"\", \"field_length\":\"10\", \"type\":\"STRING\"},\"f127\":{\"format\":\"%-20s%-32s\", \"variable\":\"username, password\", \"options\":\"\", \"field_length\":\"52\", \"type\":\"LLLVAR\"}}";
		

	// @RequestMapping("/")  
	@PostMapping("/json")
	public String home(@RequestBody DemoForm demoForm) {  
		MessageTranslator mt = new MessageTranslator();
		String iso = "0200F23A400188C180060000000000000182196048200000002731   300000000000020000092513425200007213425209250926602111597        1112345      000000000072DEVALT0120090010080000014514987654321149999999911030E8597E3B2F1646505FDD6E210000090MUP210ZBE957561167FCD8506326E4AHAMDANIE LESTALUHUANI    R1  00000090000000090000000011653600505151106123            0600000000000000000000000000130                                ALTO                          Jalan Anggrek Neli Murni                          02199999          02000500050001         03214987654321                     052tester1             tester1                         ";
		JSONObject config = new JSONObject();
		JSONObject json = new JSONObject();

		config = new JSONObject(configStr);
		json = mt.parseISO8583(iso, config); 

		return json.toString();
	}

	// @RequestMapping("/xml") 
	@PostMapping("/xml") 
	public String home2(@RequestBody DemoForm demoForm) {  
		MessageTranslator mt = new MessageTranslator();
		String iso = demoForm.getIso8583();
		String xml = "";
		JSONObject config = new JSONObject();
		JSONObject json = new JSONObject();

		config = new JSONObject(configStr);
		json = mt.parseISO8583(iso, config);
		xml = mt.buildXML(json, "data");


		return xml;
	}
 
}

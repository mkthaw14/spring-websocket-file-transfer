package com.example.spring_boot_file_transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringBootFileTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFileTransferApplication.class, args);
		openBrowser("http://localhost:8080/");
	}


	public static void openBrowser(String url)  {
		if(Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}

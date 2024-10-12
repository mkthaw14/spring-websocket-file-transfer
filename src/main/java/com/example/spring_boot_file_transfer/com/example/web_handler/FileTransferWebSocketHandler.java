package com.example.spring_boot_file_transfer.com.example.web_handler;

import com.example.spring_boot_file_transfer.com.example.request.FileMetaData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class FileTransferWebSocketHandler extends BinaryWebSocketHandler {

    public FileTransferWebSocketHandler() {
        System.out.println("Hello websocket handler");
    }

    private long fileSize = 0;
    private long currentFileSize = 0;
    private List<byte[]> byteArrList = new ArrayList<>();

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        System.out.println("Binary data received");
        byte[] combinedDataArr = message.getPayload().array();
        byte[] metaDataArr = new byte[74];
        for (int i = 0; i < metaDataArr.length; i++) {
            metaDataArr[i] = combinedDataArr[i];
        }

        byte[] dataArr = new byte[combinedDataArr.length - metaDataArr.length];
        for (int i = 0; i < dataArr.length; i++) {
            dataArr[i] = combinedDataArr[i + metaDataArr.length];
        }

        ObjectMapper objectMapper = new ObjectMapper();
        FileMetaData metaDataJson = objectMapper.readValue(metaDataArr, FileMetaData.class);
        fileSize = metaDataJson.getFileSize();
        System.out.println(metaDataJson);
        System.out.println(dataArr);

        currentFileSize += metaDataJson.getChunkSize();

        byteArrList.add(dataArr);
        if(currentFileSize >= fileSize) {
            String filePath = System.getProperty("user.home") + "\\Music\\picture.png";
            try(FileOutputStream fos = new FileOutputStream(filePath)) {
                System.out.println("Booom!");
                System.out.println("Total file size: " + currentFileSize);
                System.out.println("Original file size: " + fileSize);
                for(byte[] chunk : byteArrList) {
                    fos.write(chunk);
                }

                byteArrList.clear();
                currentFileSize = 0;
            }
            catch(Exception e) {
                e.printStackTrace();
                byteArrList.clear();
                currentFileSize = 0;
            }
        }

    }

    private void fileRead() {
        try(FileInputStream fis = new FileInputStream("")) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while((bytesRead = fis.read(buffer)) != -1) {
                processBuffer(buffer, bytesRead);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void processBuffer(byte[] buffer, int bytesRead) {
        for(int i = 0; i < bytesRead; i++) {
            System.out.println(buffer[i] + "");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("Hello client is sending " + message.getPayload());
    }
}

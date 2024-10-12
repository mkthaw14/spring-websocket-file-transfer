package com.example.spring_boot_file_transfer.com.example.request;


public class FileMetaData {
    private String fileName;
    private Long chunkIndex;
    private Long chunkSize;
    private Long fileSize;

    public FileMetaData() {}
    public FileMetaData(String fileName, Long chunkIndex, Long chunkSize, Long fileSize) {
        this.fileName = fileName;
        this.chunkIndex = chunkIndex;
        this.chunkSize = chunkSize;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getChunkIndex() {
        return chunkIndex;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setChunkIndex(Long chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public void setChunkSize(Long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileMetaData{" +
                "fileName='" + fileName + '\'' +
                ", chunkIndex='" + chunkIndex + '\'' +
                ", chunkSize='" + chunkSize + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}

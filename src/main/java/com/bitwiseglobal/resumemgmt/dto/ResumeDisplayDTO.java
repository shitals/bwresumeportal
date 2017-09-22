package com.bitwiseglobal.resumemgmt.dto;

public class ResumeDisplayDTO {
	String resumeName;
	String uploadedBy;
	String uploadedTime;
	String uploadLink;
	
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(String uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	public String getUploadLink() {
		return uploadLink;
	}
	public void setUploadLink(String uploadLink) {
		this.uploadLink = uploadLink;
	}
}

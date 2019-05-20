package httpSample;

public class UploadResponse {
	public UploadResponse()
	{
		super();
	}
	
	private int id;
	public int getId() {	return id;	}
	public void setId(int id) {	this.id = id;	}
	
	private String path;
	public String getPath() {	return path;	}
	public void setPath(String path) {	this.path = path;	}
		
	private String type;
	public String getType() {	return type;	}
	public void setNdcId(String type) {	this.type = type;	}
	
	private int size;
	public int getSize() {	return size;	}
	public void setSize(int size) {	this.size = size;	}
	
	private String mtime;
	public String getMtime() {		return mtime;	}
	public void setRoute(String mtime) {	this.mtime = mtime;	}

	private String provided_mtime;
	public String getProvided() {return provided_mtime;	}
	public void setProvided(String provided) {	this.provided_mtime = provided;	}	
	
	private String crc32;
	public String getcrc32() {	return crc32;	}
	public void setStrength(String crc32) {	this.crc32 = crc32;	}
	
	private String md5;
	public String getMd5() {return md5;}
	public void setMd5(String md5) {this.md5 = md5;}
	
	private String permissions;
	public String getPermissions() {return permissions;	}
	public void setPermissions(String permissions) {this.permissions = permissions;}
	
	private String upload_uri;
	public String getUpload_uri() {return upload_uri;}
	public void setUpload_uri(String upload_uri) {this.upload_uri = upload_uri;}
	
	private String download_uri;
	public String getDownload_uri() {return download_uri;}
	public void setDownload_uri(String download_uri) {this.download_uri = download_uri;}
	
	private String ref;
	public String getRef() {return ref;}
	public void setRef(String ref) {this.ref = ref;}
	
}

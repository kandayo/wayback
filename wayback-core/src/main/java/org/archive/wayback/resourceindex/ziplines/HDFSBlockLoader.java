package org.archive.wayback.resourceindex.ziplines;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSBlockLoader implements BlockLoader {
	FileSystem fs = null;
	String defaultFSURI = null;
	public HDFSBlockLoader(String defaultFSURI) {
		this.defaultFSURI = defaultFSURI;
	}
	public void init() throws IOException, URISyntaxException {
		if(defaultFSURI == null) {
			Configuration c = new Configuration();
//			String defaultURI = "hdfs://hadoop-name.example.org/";
//			c.set("fs.default.name",defaultURI);
//			fs = FileSystem.get(new URI(defaultURI),c);		
			fs = FileSystem.get(c);
		} else {
			Configuration c = new Configuration();
			c.set("fs.default.name",defaultFSURI);
			fs = FileSystem.get(new URI(defaultFSURI),c);
		}
	}

	public byte[] getBlock(String url, long offset, int length)
			throws IOException {
		Path path = new Path(url);
		FSDataInputStream s = fs.open(path);
		byte buffer[] = new byte[length];
		s.readFully(offset, buffer);
		return buffer;
	}

	/**
	 * @return the defaultFSURI
	 */
	public String getDefaultFSURI() {
		return defaultFSURI;
	}

	/**
	 * @param defaultFSURI the defaultFSURI to set
	 */
	public void setDefaultFSURI(String defaultFSURI) {
		this.defaultFSURI = defaultFSURI;
		Configuration c = new Configuration();
		c.set("fs.default.name",defaultFSURI);
		try {
			fs = FileSystem.get(new URI(defaultFSURI),c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
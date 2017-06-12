package com.huaao.sunejwapi.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
	public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            // gzip.write(data);
            
            int count;
            byte buf[] = new byte[1024];
            while((count = bis.read(buf, 0, 1024)) != -1){
                gzip.write(buf, 0, count);
            }
            
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return b;
    }
    
	public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((num = gzip.read(buf, 0, buf.length)) != -1){
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return b;
    }
	 private static final String UTF_8 = "UTF-8";

	    /**
	     * @param data
	     * @return
	     */
	    public static final byte[] compress(String data) throws IOException {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        GZIPOutputStream gzipOutputtStream = new GZIPOutputStream(out);
	        try {
	            gzipOutputtStream.write(data.getBytes(UTF_8));
	        } finally {
	            closeQuietly(gzipOutputtStream);
	        }
	        return out.toByteArray();
	    }

	    /**
	     * @param data
	     * @return
	     */
	    public static final byte[] compress(byte[] data) throws IOException {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        GZIPOutputStream gzipOutputtStream = new GZIPOutputStream(out);
	        try {
	            gzipOutputtStream.write(data);
	        } finally {
	            closeQuietly(gzipOutputtStream);
	        }
	        return out.toByteArray();
	    }

	    /**
	     * @param data
	     * @return
	     */
	    public static final byte[] decompress(byte[] data) throws IOException {
	        ByteArrayOutputStream buffer = null;
	        GZIPInputStream gizpInputStream = null;
	        try {
	            buffer = new ByteArrayOutputStream();
	            gizpInputStream = new GZIPInputStream(new ByteArrayInputStream(data));
	            int n = -1;
	            byte[] _buffer = new byte[1024 * 12];
	            while (-1 != (n = gizpInputStream.read(_buffer))) {
	                buffer.write(_buffer, 0, n);
	            }
	            return buffer.toByteArray();
	        } finally {
	            closeQuietly(gizpInputStream);
	            closeQuietly(buffer);
	        }
	    }

	    /**
	     * @param in
	     * @return
	     * @throws IOException
	     */
	    public static final byte[] decompress(InputStream in) throws IOException {
	        ByteArrayOutputStream buffer = null;
	        GZIPInputStream gizpInputStream = null;
	        try {
	            buffer = new ByteArrayOutputStream();
	            gizpInputStream = new GZIPInputStream(in);
	            int n = -1;
	            byte[] _buffer = new byte[1024 * 12];
	            while (-1 != (n = gizpInputStream.read(_buffer))) {
	                buffer.write(_buffer, 0, n);
	            }
	            return buffer.toByteArray();
	        } finally {
	            closeQuietly(gizpInputStream);
	            closeQuietly(buffer);
	        }
	    }

	    private static void closeQuietly(Closeable closeable) {
	        try {
	            if (closeable != null) {
	                closeable.close();
	            }
	        } catch (IOException ioe) {
	            // ignore
	        }
	    }
}

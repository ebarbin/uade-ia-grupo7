package ar.edu.uade.ia.integrations.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;

import org.jboss.logging.Logger;

public abstract class AbstractQueueListener {

	private static Logger LOGGER = Logger.getLogger(AbstractQueueListener.class);
	
	protected static SimpleDateFormat DateFormatter = new SimpleDateFormat("yyyyMMdd");
	
	protected String getProviderCode(String code) throws Exception {
		return code.split("_")[1] + code.split("_")[2];
	}
	
	protected byte[] downloadImage(String urlStr) throws Exception {
		try {
			URL url = new URL(urlStr);
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf)))
				out.write(buf, 0, n);
			out.close();
			in.close();
			
			return out.toByteArray();//Base64.getDecoder().decode(out.toByteArray());
		} catch (Exception e) {
			AbstractQueueListener.LOGGER.error("Error al descargar la imagen: " + e.getMessage(), e);
			return null;
		}
	}
}

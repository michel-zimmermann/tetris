package tetris.params;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.CodeSource;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import tetris.init.Inicializavel;

public class Params implements Inicializavel {

	public static final String PRINCIPAL_LOG = "log.boolean";
	
	public static final String PRINCIPAL_PRINT = "print.boolean";
	public static final String PRINCIPAL_PRINT_PATH = "print.path.string";
	
	public static final String PRINCIPAL_DELAY = "delay.long";
	
	private static final Map<String, Object> PARAMS = new HashMap<>();
	
	@Override
	public void iniciar() {
		PARAMS.clear();
		
		ResourceBundle bundle = tentarCarregarDeFora();
		if (bundle == null) {
			// nao conseguiu carregar, pega o padrao
			bundle = ResourceBundle.getBundle("config");
		}
		
		armazenarParametros(bundle);
	}
	
	private ResourceBundle tentarCarregarDeFora() {
		try {
			CodeSource codeSource = getClass().getProtectionDomain().getCodeSource();
			File jarFile = new File(codeSource.getLocation().toURI().getPath());
			String path = jarFile.getParentFile().getPath() + File.separator + "config.properties";

			File file = new File(path);
			if (file.exists()) {
				return fromFile(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}           
		return null;
	}
	
	private static ResourceBundle fromFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        try {
            return new PropertyResourceBundle(fis);
        } finally {
            fis.close();
        }
    }

	private void armazenarParametros(ResourceBundle bundle) {
		Enumeration<String> keys = bundle.getKeys();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			if (key.endsWith("area")) {
				List<Integer> list = Arrays.asList(bundle.getString(key).split(",")).stream()
										.map(v -> Integer.valueOf(v))
										.collect(Collectors.toList());
				PARAMS.put(key, new Rectangle(list.get(0), list.get(1), list.get(2), list.get(3)));
			} else if (key.endsWith("long")) {
				PARAMS.put(key, Long.valueOf(bundle.getString(key)));
			} else if (key.endsWith("int")) {
				PARAMS.put(key, Integer.valueOf(bundle.getString(key)));
			} else if (key.endsWith("boolean")) {
				PARAMS.put(key, Boolean.valueOf(bundle.getString(key)));
			} else if (key.endsWith("listastr")) {
				PARAMS.put(key, Arrays.asList(bundle.getString(key).split(",")));
			} else if (key.endsWith("listahex")) {
				List<String> coresHex = Arrays.asList(bundle.getString(key).split(","));
				PARAMS.put(key, coresHex.stream().map(hex -> Color.decode("#" + hex).getRGB()).collect(Collectors.toList()));
			} else {
				PARAMS.put(key, bundle.getString(key));
			}
		}
	}
	
	public static String getString(String chave) {
		return (String) PARAMS.get(chave);
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getListaString(String chave) {
		return (List<String>) PARAMS.get(chave);
	}
	
	public static int getInt(String chave) {
		return (Integer) PARAMS.get(chave);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Integer> getListaInt(String chave) {
		return (List<Integer>) PARAMS.get(chave);
	}
	
	public static long getLong(String chave) {
		return (Long) PARAMS.get(chave);
	}
	
	public static Rectangle getArea(String chave) {
		return (Rectangle) PARAMS.get(chave);
	}
	
	public static boolean getBoolean(String chave) {
		return (Boolean) PARAMS.get(chave);
	}
}

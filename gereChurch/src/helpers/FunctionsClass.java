package helpers;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;

import views.HomePage;

public class FunctionsClass {
	public static String ddArray(String[] arrayToDebug) {
		return Arrays.toString(arrayToDebug);
	}
	
	public static String arrayListDataToString(ArrayList arrayData) {
		String listString = String.join(", ", arrayData);
		return listString;
	}
	
	public static Integer parseToInteger(String text) {
		try {
			return Integer.parseInt(text);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String parseBooleanToString(Boolean value) {
		String result = Boolean.toString(value);
		return result == "true" ? "1" : "0"; 
	}
	
	public static String parseArraytoObject(String[] array) {
		return Arrays.toString(array);
	}
	
	public static Boolean parseStringToBoolean(String value) {
		return value.equals("1") ? true : false;
	}
	
	public static String parseDateToString(Date value) {
		return value.toString();
	}
	
	public static Date parseStringToDate(String value) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("Y-m-d", Locale.ENGLISH);
		Date date = formatter.parse(value);
		return date;
	}
	
	public static String maskValue(String value) {
		if(value.equals(null) || value.equals("null")) {
			return "";
		}
		return value;
	}
	
	public static String parseMonetary(String value) {
		if(value == null || value == "null")
			return "R$00.00";
		if(value.indexOf(".00") == -1) {
			value = value.replace(".0", ".00");
		}
		value = "R$" + value;
		return value;
	}
	
	public static Integer returnPosicionOfArray(String key, String[] values) {
		Integer position = 0;
		for(String value : values) {
			if(value == key) {
				return position;
			}
			position++;
		}
		return null;
	}
	
	public static Boolean isInteger(String value) {
		if(value.matches("-?(0|[1-9]\\d*)"))
			return true;
		return false;
	}
	
	public static String dataAtual(String formato) {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		Date date = new Date();
		return formatter.format(date);
	}
	
	public static String formatarDataString(String data) {
		String[] dataArray = data.split("-");
		String dataFormatada = "";
		for(Integer i = (dataArray.length - 1); i >= 0; i--) {
			if(i.equals(0)) {
				dataFormatada += dataArray[i];
			}else {
				dataFormatada += dataArray[i] + '-';
			}
		}
		return dataFormatada;
	}
	
	public static String formatarDataUTC(String data) {
		try {
			String[] array = data.split("-");
			return array[0] + "-" + array[1];
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String apenasMesEAno(String data) {
		String[] dataArray = data.split("-");
		String valor = dataArray[1] + '-' + dataArray[2];
		return valor;
	}
	
	public static String stripMonetary(String valor) {
		String valorNovo = valor.replace("R$", "");
		valorNovo = valorNovo.replace(",", ".");
		return valorNovo.trim();
	}
	
	public static String sumOfStrings(String valor1, String valor2) {
		Integer resultado = Integer.parseInt(valor1) + Integer.parseInt(valor2);
		return resultado.toString();
	}
	
	public static String parseDateToBR(String data) {
		String[] array = data.split("-");
		return array[1] + "-" + array[0];
	}
	
	public static String parseENdateToBR(String date) {
		String[] dateArray = date.split("-");
		return dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];
	}
	
	public static String formatPTBR(String data) {
		String[] array = data.split("-");
		return array[2] + "-" + array[1] + "-" + array[0];
	}
	
	public static Boolean imageExist(String fileName) {
		File imageCheck = new File("src/images/" + fileName);
		if(imageCheck.exists()) 
		    return true;
		else 
		    return false;
	}
	
	public static ImageIcon createIconFromImg(String imageName, Integer height, Integer width) {
		String path = "src/images/" + imageName;
		ImageIcon resizedIcon = new ImageIcon(path); 
	    Image img = resizedIcon.getImage() ;  
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH ) ;  
		resizedIcon = new ImageIcon(newimg);
		return resizedIcon;
	}
}

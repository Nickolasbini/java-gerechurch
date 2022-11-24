package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import helpers.FunctionsClass;

public class ModeloBase {
	public static String   caminhoParaArquivo = "src/datafiles/";
	public static String   nomeArquivoObj;
	public static String[] atributos;
	public static String   dadosObj;
	private static Object objetoAtual;
	
	public static String getNomeArquivoObj() {
		return nomeArquivoObj;
	}
	public void setNomeArquivoObj(String nomeArquivoObj) {
		this.nomeArquivoObj = nomeArquivoObj;
	}
	
	public static String[] getAtributos() {
		return atributos;
	}
	public void setAtributos(String[] atributos) {
		this.atributos = atributos;
	}
	
	public static String getDadosObj() {
		return dadosObj;
	}
	public void setDadosObj(String dadosObj) {
		this.dadosObj = dadosObj;
	}
	
	public static Object getObjetoAtual() {
		return objetoAtual;
	}
	public void setObjetoAtual(Object objetoAtual) {
		this.objetoAtual = objetoAtual;
	}
	
	/* Add/Create/Update sent data
	*  @param String - the value to save with all object data as string, delimited with a comma ","
	*/
	public static Boolean salvarObj(String data, String fileName) {
		return salvarArquivo(fileName, data);
	}
	
	/* Removes a record
	*  @param String - the id to remove
	*/
	public static Boolean remover(String dataId) {
		return deletar(dataId, getNomeArquivoObj());
	}
	
	/* Returns an String[] with all current objects data as array
	*/
	public static ArrayList<String> buscarTodos() {
		String fileName = getNomeArquivoObj();
		ArrayList<String> result = buscarDadosDoArquivoComoArrayList(fileName);
		if(result == null)
			return null;
		return result;
	}
	
	public static ArrayList<String[]> buscarTodosComoArrayList() {
		String fileName = getNomeArquivoObj();
		ArrayList<String> result = buscarDadosDoArquivoComoArrayList(fileName);
		if(result == null)
			return null;
		ArrayList<String[]> resultado = new ArrayList<String[]>(result.size());
		for(String valor : result) {
			String[] dadosArray = valor.split(",");
			resultado.add(dadosArray);
		}
		return resultado;
	}
	
	/* Returns an String[] with current obj related data as array
	*  @param String - the id to get
	*/
	public static String[] buscarPorId(String id) {
		String[] resultToReturn;
		String result = buscarDadosArquivoPorId(getNomeArquivoObj(), id);
		if(result == null)
			return null;
		resultToReturn = result.split(",");
		return resultToReturn;
	}
	
	/* Returns an ArrayList<String[]> with current obj related data as array
	*  @param String  - the attribute name
	*  @param String  - the value to search
	*  @param Boolean - if value must be exact or not
	*/
	public static ArrayList<String[]> buscar(String attribute, String valueToFind, Boolean exactValue) {
		Integer numericPosition = 0;
		for(String attributeName : getAtributos()) {
			if(attributeName.equals(attribute))
				break;
			numericPosition = numericPosition + 1;	
		}
		return encontrar(getNomeArquivoObj(), numericPosition, valueToFind, exactValue);
	}
	
	/* Returns all txt file data as an array list
	*  @param String  - the file name
	*/
	public static ArrayList<String> buscarDadosDoArquivoComoArrayList(String fileName) {
		ArrayList<String> listOfData = new ArrayList<String>();
		try {
	        File myObj = new File(caminhoParaArquivo + fileName + ".txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          data = data.replace(";", "");
	          listOfData.add(data);
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	        return null;
        }
		return listOfData;
	}
	
	/* Returns all txt file data as string
	*  @param String  - the file name
	*/
	public static String buscarDadosArquivoComoString(String fileName) {
		String fileData = "";
		try {
	        File myObj = new File(caminhoParaArquivo + fileName + ".txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          fileData += data + "\r\n";
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	        return null;
        }
		return fileData;
	}
	
	/* Creates new file with sent data or add new data to file
	*  @param String - the file name
	*  @param String - the data to store
	*/
	public static Boolean salvarArquivo(String fileName, String fileData) {
		try {
	        String valueToAdd = fileData + ";\r\n";
	        if(fileExists(fileName)) {
	        	String myId = pegarIdDoDado(fileData);
	        	if(FunctionsClass.parseToInteger(myId) != null) {
	        		// search by id to update
	        		String foundData = buscarDadosArquivoPorId(fileName, myId);
	        		if(foundData == null)
	        			return false;
	        		valueToAdd = mesclarDados(myId, fileData, fileName);
	        	}else {
	        		// add new with next id
	        		String currentId = pegarProximoIdParaArquivo(fileName);
	        		fileData = fileData.replace("id", currentId);
	        		valueToAdd = buscarDadosArquivoComoString(fileName) + fileData + ";\r\n";
	        	}
	        }else {
	        	// create file with new record
	        	valueToAdd = fileData.replace("id", "1");
	        }
	        FileWriter myWriter = new FileWriter(caminhoParaArquivo + fileName + ".txt");
	        myWriter.write(valueToAdd);
	        myWriter.close();
	        return true;
	    } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/* Returns whether file exists or not
	*  @param String  - the file name
	*/
	public static Boolean fileExists(String fileName) {
		Boolean result = false;
		File myObj = new File(caminhoParaArquivo + fileName + ".txt");
		if(myObj.exists())
			result = true;
		return result;
	}
	
	/* Returns the last record from a txt file
	*  @param String  - the file name
	*/
	public static String buscarUltimoRegistroInserido(String fileName) {
		ArrayList<String> allFileData = buscarDadosDoArquivoComoArrayList(fileName);
		Integer size = allFileData.size();
		String lastValueFromList = allFileData.get(size - 1);
		return lastValueFromList;
	}
	
	/* Returns next id to be used at txt file
	*  @param String  - the file name
	*/
	public static String pegarProximoIdParaArquivo(String fileName) {
		ArrayList<String> allFileData = buscarDadosDoArquivoComoArrayList(fileName);
		Integer size                  = allFileData.size();
		if(size == 0)
			return Integer.toString(1);
		String  lastValueFromList     = allFileData.get(size - 1);
		String  lastId 			      = pegarIdDoDado(lastValueFromList);
		Integer nextId 				  = Integer.parseInt(lastId) + 1;
		return Integer.toString(nextId);
	}
	
	/* Return the first position (the id) of sent data
	*  @param String  - the data to extract the first position (the id)
	*/
	public static String pegarIdDoDado(String data) {
		String[] dataArray = data.split(",");
		return dataArray[0].trim();
	}
	
	/* Returns an arrayList containing Arrays inside or null if no correspondence is found
	*  @param String - the file name
	*  @param String - the id of the data from file to return
	*/
	public static String buscarDadosArquivoPorId(String fileName, String dataId) {
		ArrayList<String> allData = buscarDadosDoArquivoComoArrayList(fileName);
		for (String data : allData) {
			String idOfData = pegarIdDoDado(data);
			if(FunctionsClass.parseToInteger(idOfData) == FunctionsClass.parseToInteger(dataId)) {
				return data;
			}
	    }
		return null;
	}
	
	/* Updates txt file data correspondent to sent id
	*  @param String - the id of the data from file to update
	*  @param String - the new data to put on found id
	*  @param String - the file name to get/change data from
	*/
	public static String mesclarDados(String dataId, String newData, String fileName) {
		ArrayList<String> allData = buscarDadosDoArquivoComoArrayList(fileName);
		String updatedData = "";
		for (String data : allData) {
			String idOfData = pegarIdDoDado(data);
			if(FunctionsClass.parseToInteger(idOfData) == FunctionsClass.parseToInteger(dataId)) {
				data = newData;
			}
			updatedData += data + ";\r\n";
	    }
		return updatedData;
	}
	
	/* Deletes a record from the txt file source
	*  @param String - the id to get
	*  @param String - the file name to get data from
	*/
	public static Boolean deletar(String dataId, String fileName) {
		String newFileValue = "";
		ArrayList<String> allData = buscarDadosDoArquivoComoArrayList(fileName);
		Boolean reWriteFileData = false;
		for (String data : allData) {
			String idOfData = pegarIdDoDado(data);
			if(FunctionsClass.parseToInteger(idOfData) != FunctionsClass.parseToInteger(dataId)) {
				newFileValue += data + ";\r\n";
			}else {
				reWriteFileData = true;
			}
	    }
		if(reWriteFileData == false)
			return true;
		try {
			FileWriter myWriter = new FileWriter(caminhoParaArquivo + fileName + ".txt");
	        myWriter.write(newFileValue);
	        myWriter.close();
	        return true;
		} catch (IOException e) {
			System.out.println("An error occurred.");
	        e.printStackTrace();
	        return false;
		}
	}
	
	/* Returns an arrayList containing Arrays inside or null if no correspondence is found
	*  @param String  - the file name
	*  @param Integer - the position of the value
	*  @param String  - the value to check
	*  @param Boolean - for exact match
	*/
	public static ArrayList<String[]> encontrar(String fileName, Integer position, String valueToFind, Boolean exactValue) {
		ArrayList<String> allData = buscarDadosDoArquivoComoArrayList(fileName);
		ArrayList<String[]> valueToReturn = new ArrayList<>();
		for (String data : allData) {
			String[] dataArray = data.split(",");
			try {
				String value = dataArray[position];
				if(exactValue == true) {
					if(value.equals(valueToFind))
						valueToReturn.add(dataArray);
				}else {
					if(value.indexOf(valueToFind) > -1) {
						valueToReturn.add(dataArray);
					}
				}
			} catch (Exception e) {
				break;
			}
		}
		return valueToReturn;
	}
	
	public static ArrayList<String[]> filtrarDados(ArrayList<String[]> valores, Integer posicao, String valorEsperado, Boolean valorExato)
	{
		ArrayList<String[]> resultado = new ArrayList<String[]>();
		if(valores.size() < 1)
			return resultado;
		for(String[] data : valores) {
			try {
				String expectedData = data[posicao];
				if(valorExato == true) {
					if(expectedData.equals(valorEsperado)) {
						resultado.add(data);
					}
				}else {
					if(expectedData.indexOf(valorEsperado) > -1) {
						resultado.add(data);
					}
				}
			} catch (Exception e) {
				break;
			}
		}
		return resultado;
	}
}

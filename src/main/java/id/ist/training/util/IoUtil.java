package id.ist.training.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import id.ist.training.model.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IoUtil {
	
	private static final String KEY_APP_NAME = "TASK_APP";
	private static final String DEFAULT_DIR = "D:\\Work\\IST Training\\Project\\FileIO";
	private static final String FILE_NAME = "boarding_detail_object_v4.text";
	private static final String FULL_PATH = System.getProperty(KEY_APP_NAME, DEFAULT_DIR);
	private static final File FILE_OBJ = new File(FULL_PATH, FILE_NAME);
	

	private IoUtil(){
	}
	
	public static void ensureFile() {
		if(!FILE_OBJ.exists()) {
			try {
				FILE_OBJ.createNewFile();
				log.info("created new file");
			} catch (IOException e) {
				log.error("Error Creating database with name "+FILE_NAME);
			}
		}
	}
	
	public static List<Task> readData() {		
		List<Task> tasks = new ArrayList<>();
			
		if(FILE_OBJ.length()==0) {
			return tasks;
		}
				
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_OBJ))){
			@SuppressWarnings("unchecked")
			List<Task> tempList = (List<Task>) ois.readObject();
			tasks.addAll(tempList);		
		} catch (IOException e) {
			log.error("Error retrive data from file database because of I/O, detail : \n"+e.toString());
		} catch (ClassNotFoundException e) {
			log.error("Error retrive data from file database because class object not same or same serilizaber, detail : \n"+e.toString());
		}
		
		return tasks;
	}
	
	public static <T> void writeData(List<T> listT) {
		ensureFile();
				
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_OBJ))){
			oos.writeObject(listT);
		} catch (IOException e) {
			log.error("Failed to write updated Task List to File, detail : \n", e.toString());
		}
	}

}

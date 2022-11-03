package id.ist.training.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import id.ist.training.model.Task;
import id.ist.training.util.IoUtil;
import lombok.Data;

@Repository
@Data
public class TaskRepository {

	private List<Task> tasks;

	public TaskRepository() {
		this.tasks = new ArrayList<>();
	}

	public void create(Task task) {
		tasks.add(task);
		IoUtil.writeData(tasks);
	}

	@PostConstruct
	public List<Task> getDataFromFile() {
		tasks.addAll(IoUtil.readData());
		return tasks;
	}

	public Optional<Task> read(String id) {
		return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
	}

	public List<Task> readAll() {
		return tasks;
	}

	public void update(Task oriTask, Task updatedTask, String id) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(updatedTask, oriTask);
		oriTask.setId(id);
		IoUtil.writeData(tasks);
	}

	public void delete(Task task) {
		tasks.remove(task);
		IoUtil.writeData(tasks);
	}

	public void deleteAll() {
		tasks.clear();
		IoUtil.writeData(tasks);
	}

}

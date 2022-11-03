package id.ist.training.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ist.training.exception.TaskNotFoundException;
import id.ist.training.model.Task;
import id.ist.training.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public boolean isNotEmpty() {
		return !taskRepository.getTasks().isEmpty();
	}

	public void create(Task task) {
		taskRepository.create(task);
	}

	public Task createDummy() {
		log.info("Creating new dummy task");
		Task newTask = new Task();
		newTask.setOnBoardingType(Task.OnBoardingType.COMPANY_POLICY_MEETING);
		newTask.setTimeStart(LocalTime.of(8, 30));
		newTask.setRoom("Room 1");
		newTask.setInformation("Waiting for starting onboarding new employee");
		return newTask;
	}

	public Task read(String id) {
		return taskRepository.read(id).orElseThrow(TaskNotFoundException::new);
	}

	public List<Task> readAll() {
		return taskRepository.readAll();
	}
	
	public void update(Task updatedTask, String index) throws IllegalAccessException, InvocationTargetException {
		Task oriTask = taskRepository.read(index).orElseThrow(TaskNotFoundException::new);
		taskRepository.update(oriTask, updatedTask, index);
	}

	public void delete(String id) {
		Task task = read(id);
		taskRepository.delete(task);
	}

	public void deleteAll() {
		taskRepository.deleteAll();
	}

}

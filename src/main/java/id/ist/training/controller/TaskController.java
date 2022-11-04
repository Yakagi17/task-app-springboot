package id.ist.training.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import id.ist.training.dto.TaskDto;
import id.ist.training.exception.TaskNotFoundException;
import id.ist.training.model.Task;
import id.ist.training.service.TaskService;
import id.ist.training.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("/tasks")
@Slf4j
public class TaskController {

	private static final String DUMMY_TASK = "/dummy";
	private static final String GET_TASK = "/{id}";
	private static final String DELETE_TASK = "/{id}";
	private static final String UPDATE_TASK = "/{id}";

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	private void addLocalModuleOm() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	@GetMapping(path = DUMMY_TASK)
	public ResponseEntity<Task> getDummyTask() {
		log.info("Get a dummy task");
		return new ResponseEntity<>(taskService.createDummy(), HttpStatus.OK);
	}

	@GetMapping(path = GET_TASK)
	public ResponseEntity<Task> getTask(@PathVariable("id") String taskId) {
		log.info("Start get Task. ID=" + taskId);
		try {
			Task task = taskService.read(taskId);
			return new ResponseEntity<>(task, HttpStatus.OK);
		} catch (TaskNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping()
	public ResponseEntity<List<Task>> getAllTask() {
		log.info("Start get All Tasks.");
		return new ResponseEntity<>(taskService.readAll(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Object> createTask(@Valid @RequestBody TaskDto taskDto) {
		log.info("Start create Task.");
		Task newTask = new Task();
		ObjectUtils.copyProperties(newTask, taskDto);
		taskService.create(newTask);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@PutMapping(value = TaskController.UPDATE_TASK)
	public ResponseEntity<Object> updatePutTask(@PathVariable("id") String taskId, @Valid @RequestBody TaskDto updatedTaskDto) {
		log.info("Start updating with put method to Employee, index : " + taskId);
		try {
			Task tempTask = new Task();
			ObjectUtils.copyProperties(tempTask, updatedTaskDto);
			taskService.update(tempTask, taskId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalAccessException | InvocationTargetException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}

	@PatchMapping(path = TaskController.UPDATE_TASK, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePatchTask(@PathVariable("id") String taskId, @RequestBody JsonPatch patch) {
		log.info("Start updating with patch method to Employee, index : " + taskId);
		try {
			Task tempTask = taskService.read(taskId);
			TaskDto tempTaskDto = new TaskDto();
			ObjectUtils.copyProperties(tempTaskDto, tempTask);
			JsonNode patched = patch.apply(objectMapper.convertValue(tempTaskDto, JsonNode.class));
			TaskDto taskDtoPatched = objectMapper.treeToValue(patched, TaskDto.class);
			Task taskPatched = new Task();
			ObjectUtils.copyProperties(taskPatched, taskDtoPatched);
			taskService.update(taskPatched, taskId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (TaskNotFoundException e) {
        	 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (JsonPatchException | JsonProcessingException | IllegalAccessException | InvocationTargetException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}

	@DeleteMapping(path = TaskController.DELETE_TASK)
	public ResponseEntity<Object> deleteTask(@PathVariable("id") String taskId) {
		log.info("Start delete Employee, index : " + taskId);
		try {
			taskService.delete(taskId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(TaskNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping()
	public ResponseEntity<Object> deleteAllTask() {
		log.info("Start delete All Employee");
		taskService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

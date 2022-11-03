package id.ist.training.dto;

import java.io.Serializable;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import id.ist.training.model.Task.OnBoardingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto implements Serializable {

	private static final long serialVersionUID = -2719319357895023261L;

	@NotNull(message = "On Boarding Type is mandatory")
	private OnBoardingType onBoardingType;

	@NotNull(message = "Task time start is mandatory")
	private LocalTime timeStart;

	@NotBlank(message = "Room name is mandatory")
	private String room;

	@NotBlank(message = "Task Information is mandatory")
	private String information;

}

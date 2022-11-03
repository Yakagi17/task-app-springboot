package id.ist.training.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

	private static final long serialVersionUID = -244425828985012051L;

	private String id = UUID.randomUUID().toString();

//	@NotNull(message = "On Boarding Type is mandatory")
	private OnBoardingType onBoardingType;

	@NotNull(message = "Task time start is mandatory")
	private LocalTime timeStart;

	@NotBlank(message = "Room name is mandatory")
	private String room;

	@NotBlank(message = "Task Information is mandatory")
	private String information;

	public enum OnBoardingType {
		COMPANY_POLICY_MEETING, TEAM_AND_COWORKER_INTROCUTION, GET_WORK_ACCOUNT, JOINNING_GROUP
	}

}

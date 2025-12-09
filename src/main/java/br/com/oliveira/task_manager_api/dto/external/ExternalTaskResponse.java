package br.com.oliveira.task_manager_api.dto.external;

import lombok.Data;
import java.util.List;



@Data
public class ExternalTaskResponse {
   
    private List<ExternalTaskDTO> todos; 
}
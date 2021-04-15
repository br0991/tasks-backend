package br.ce.wcaquino.taskbackend.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.controller.TaskController;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Mock
	private TaskRepo taskService;

	@InjectMocks
	private TaskController controller;

	private Task todo;

	@Rule
	public ErrorCollector erro = new ErrorCollector();

	@Before
	public void setup() {

		todo = new Task();
		controller = new TaskController();
		MockitoAnnotations.initMocks(this);
	}

	@Test()
	public void naoDeveSalvarTarefaSemDescricao() {

		try {

			todo.setTask(null);
			controller.save(todo);
			fail("Deveria validar a exception !");

		} catch (ValidationException e) {

			erro.checkThat(e.getMessage(), is("Fill the task description"));
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {

		try {

			todo.setTask("Descrição");
			todo.setDueDate(null);
			controller.save(todo);
			fail("Deveria validar a exception !");
		} catch (ValidationException e) {

			erro.checkThat(e.getMessage(), is("Fill the due date"));
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {

		try {

			todo.setTask("Descrição");
			todo.setDueDate(LocalDate.of(2005, 01, 01));
			controller.save(todo);
			fail("Deveria validar a exception !");
		} catch (ValidationException e) {

			erro.checkThat(e.getMessage(), is("Due date must not be in past"));
		}
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {

		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.now());		
		controller.save(todo);
		Mockito.verify(taskService).save(todo);
	}
}

package br.ce.wcaquino.taskbackend.utils;

import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class DateUtilsTest {

	@Rule
	public ErrorCollector compara = new ErrorCollector();
	
	@Test
	public void deveRetornarTrueParaDatasFuturas() {

		LocalDate date = LocalDate.of(2030, 01, 01);
		boolean retorno = DateUtils.isEqualOrFutureDate(date);
		compara.checkThat(retorno, is(true));
		
	}
}

package study.test.learningtest.template;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalcSumTest {
	Calculator calculator;
	String numFilePath;
	
	@Before
	public void setUp(){
		this.calculator = new Calculator();
		this.numFilePath =  "C:\\dev\\numbers.txt";
	}
	@Test
	public void sumOfNumbers() throws IOException{
		int sum = calculator.calcSum(this.numFilePath);
		assertThat(sum, is(10));
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException{
		int multiply = calculator.calcMultiply(this.numFilePath);
		assertThat(multiply, is(24));
	}
	
	@Test
	public void concatenateStrings() throws IOException{
		assertThat(calculator.concatenate(this.numFilePath), is("1234"));
	}
}

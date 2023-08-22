package com.javamonks.common;

public class SimpleExecutor implements Executor {

	public void execute(Execuatable executable) {
		executable.run();
	}

}

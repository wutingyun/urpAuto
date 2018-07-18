package com.apache.urp.aop;

import org.fluentlenium.core.FluentPage;

import com.apache.urp.operation.IOperation;
import com.apache.urp.operation.OperationImpl;

public class OperationAopPage extends FluentPage {
	protected IOperation operation;

	public OperationAopPage() {
		operation = new OperationImpl();
	}

	public IOperation getOperation() {
		return operation;
	}

	public void setOperation(IOperation operation) {
		this.operation = operation;
	}

}

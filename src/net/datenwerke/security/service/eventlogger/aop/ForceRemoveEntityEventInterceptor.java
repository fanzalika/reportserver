package net.datenwerke.security.service.eventlogger.aop;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.AfterForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

public class ForceRemoveEntityEventInterceptor implements MethodInterceptor {

	@Inject
	private EventBus eventBus;
	
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		if(1 != method.getArguments().length)
			throw new IllegalArgumentException("Excepted exactly one argument");
		
		Object entity = method.getArguments()[0];

		eventBus.fireEvent(new ForceRemoveEntityEvent(entity));
		
		Object returnValue = method.proceed();
		
		eventBus.fireEvent(new AfterForceRemoveEntityEvent(entity));
		
		return returnValue;
	}
}
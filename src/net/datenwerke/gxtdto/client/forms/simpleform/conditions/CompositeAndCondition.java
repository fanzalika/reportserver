package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class CompositeAndCondition implements SimpleFormCondition {

	private final SimpleFormCondition[] conditions;
	
	public CompositeAndCondition(SimpleFormCondition... conditions){
		this.conditions = conditions;
	}
	
	public boolean isMet(Widget formField,
			FormFieldProviderHook responsibleHook, SimpleForm form) {
		if(null == conditions)
			return false;
		
		for(SimpleFormCondition condition : conditions)
			if(! condition.isMet(formField, responsibleHook, form))
				return false;

		return true;
	}

}
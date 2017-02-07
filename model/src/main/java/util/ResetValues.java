package util;

import java.util.List;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class ResetValues implements ActionListener {

	public void processAction(ActionEvent action) throws AbortProcessingException{
		FacesContext context=FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = context.getViewRoot();
		List<UIComponent> children = viewRoot.getChildren();
		reset(children);
	}
	

	private void reset(List<UIComponent> children){
		for(UIComponent component:children){
			if(component.getChildCount()>0){
				reset(component.getChildren());
			}else{
				if(component instanceof EditableValueHolder){
					EditableValueHolder input=(EditableValueHolder) component;
					input.resetValue();
				}
			}
			
		}
	}


}

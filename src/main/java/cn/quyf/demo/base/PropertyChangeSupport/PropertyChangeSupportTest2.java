package cn.quyf.demo.base.PropertyChangeSupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 类说明 
 *
 * @author Van
 * @date 2017-2-21
 */
public class PropertyChangeSupportTest2 {

	private String name = "wxq";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		PropertyChangeSupportTest t = new PropertyChangeSupportTest();
		
		PropertyChangeSupport change = new PropertyChangeSupport( t );
		change.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("change "+evt.getPropertyName());
			}
		});
		String st = t.getName();
		t.setName( "quyf" );
		change.firePropertyChange("name", st, t.getName());
	}
}


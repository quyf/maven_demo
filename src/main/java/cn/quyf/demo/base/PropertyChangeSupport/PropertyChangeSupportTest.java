package cn.quyf.demo.base.PropertyChangeSupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 事件源
 * @author quyf
 *
 */
public class PropertyChangeSupportTest {
 
	private String name = "hello world";
	
	private PropertyChangeSupport listener = new PropertyChangeSupport( this );

	public void addListener( PropertyChangeListener l ){
		listener.addPropertyChangeListener(l);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		listener.firePropertyChange(null, null, getName());
	}
	public static void main(String[] args) {
		PropertyChangeSupportTest  t1 = new PropertyChangeSupportTest ();
		Listener listener = new Listener();
		t1.addListener( listener );
		t1.setName( "quyf" );
		
	}
	
}
class Listener implements PropertyChangeListener{

	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println( "我看见了");
		System.out.println( evt.getPropertyName());
		System.out.println( evt.getOldValue());
		System.out.println( evt.getNewValue());
	}
	
}

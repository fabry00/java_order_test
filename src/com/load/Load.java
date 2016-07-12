package com.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.domains.BulkyItem;
import com.domains.IOrderItem;
import com.domains.LightItem;
import com.domains.StandardItem;

/**
 * http://www.javaworld.com/article/2078042/java-app-dev/domain-driven-design-with-java-ee-6.html
 * Immutable Load Class
 * This means that once a Load as been created it cannot be changed.
 * To change the Load you have to create a new Load and dismiss the old one
 * 
 * Immutable specifications
 * - Don't provide "setter" methods  methods that modify fields or objects referred to by fields.
 * - Make all fields final and private.
 * - Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. 
 *   A more sophisticated approach is to make the constructor private and construct instances in factory methods.
 * - If the instance fields include references to mutable objects, don't allow those objects to be changed:
 *     Don't provide methods that modify the mutable objects.
 *     Don't share references to the mutable objects. Never store references to external, mutable objects passed 
 *       to the constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.
 */
public final class Load implements ILoad{

	private final Set<IOrderItem> m_items;
	private final String id;
	
	private Load(Set<IOrderItem> items) {
		id = UUID.randomUUID().toString();
		m_items = Collections.unmodifiableSet(new HashSet<IOrderItem>(items));
	}
	
	public Set<IOrderItem> getItems() {
		return Collections.unmodifiableSet(m_items);
	}
	
	public int getShippingCost() {
		int total =  0;
		for(IOrderItem item : m_items){
			total += item.getShippingCost();
		}
		return total;
	}
	
	public String getID(){
		return id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	public IOrderItem lightest() {
		if(m_items == null || m_items.size() == 0) {
            return null;
		}
		List<IOrderItem> list = new ArrayList<IOrderItem>(m_items);		
		Collections.sort(list, new WheightComparator());		
		return list.iterator().next();
	}
	
	public IOrderItem haviest() {	
		if(m_items == null || m_items.size() == 0) {
            return null;
		}
		List<IOrderItem> list = new ArrayList<IOrderItem>(m_items);		
		Collections.sort(list, new WheightComparator());
		Collections.reverse(list);
		return list.iterator().next();
	}
	
	/**
	 * As this is an Immutable class this method
	 * generate a new Load without the dropped Item
	 * @param item
	 * @return
	 */
	public Load drop(IOrderItem item) {
		Set<IOrderItem> newItemsList = new HashSet<IOrderItem>(m_items);
		newItemsList.remove(item);
		return new Load(newItemsList);
	}
	
	/**
	 * Weight comparator
	 *
	 */
	private static class WheightComparator implements Comparator<IOrderItem>{

		@Override
		public int compare(IOrderItem o1, IOrderItem o2) {
			return o1.getShippingCost() - o2.getShippingCost();
		}
	}
	
	/**
	 * Load Builder
	 */
	public static class Builder {
		
		private final Set<IOrderItem> m_items = new HashSet<IOrderItem>();
		
		
		public Builder() {
			
		}
		
		/**
		 * Build a new Load cloning an existent one
		 * @param loadToClone
		 */
		public Builder(Load loadToClone) {
			m_items.addAll(loadToClone.getItems());
		}
		
		public Builder withStandardItem(int weight, String name) {
			m_items.add(new StandardItem(weight, name));
			return this;
		}
		
		public Builder withLightItem(int weight, String name) {
			m_items.add(new LightItem(weight, name));
			return this;
		}
		
		public Builder withBulkyItem(int weight, String name) {
			m_items.add(new BulkyItem(weight, name));
			return this;
		}
		
		public Load build() {			
			if(m_items.size() == 0) {
				throw new IllegalStateException("The load is empty"); 
			}		
			return new Load(m_items);
		}
	}
	
	
}

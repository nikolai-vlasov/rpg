package attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite pattern to store all the characters attributes: basics, weapons, spells, etc. Allows calculate their
 * final amount.
 */
public class CompositeAttribute implements Attribute {

	private List<Attribute> components = new ArrayList<>();

	public void addComponent(Attribute component) {
		components.add(component);
	}

	public void removeComponent(Attribute component) {
		components.remove(component);
	}

	@Override
	public int calculate() {
		int total = 0;
		for (Attribute component : components) {
			total = total + component.calculate();
		}
		return total;
	}
}

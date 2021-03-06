package character;

import experience.Level;

/**
 * Warrior. Primary skill is strength.
 */
public class Warrior extends Person {
	public Warrior(String name) {
		this.name = name;
		this.health = 100;
		this.healthMax = 100;
		this.experience = 0;
		this.strength = 10;
		this.agility = 3;
		this.stamina = 4;
	}

	@Override
	public void useClassPower() {
		this.strength *= 2;
	}

	@Override
	public void levelUp(Level level) {
		super.levelUp(level);
		strength = (int) (strength * 1.25);
	}
}

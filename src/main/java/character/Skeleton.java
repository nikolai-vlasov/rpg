package character;

public class Skeleton extends Person {
	public Skeleton() {
		this.name = "Skeleton";
		this.health = 40;
		this.experience = 100;
		this.strength = 5;
		this.agility = 1;
		this.stamina = 1;
	}

	@Override
	public void useClassPower() {
		System.out.println("Joooooohn Ceeena!");
	}
}
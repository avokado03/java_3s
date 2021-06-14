
public class Employee {
	private int Id;
	private String Name;
	private String Position;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String string) {
		Name = string;
	}
	
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	
	@Override
	public String toString() {
		return String.format("Id = %s, имя - %s, должность - %s", this.Id, this.Name, this.Position);
	}
}

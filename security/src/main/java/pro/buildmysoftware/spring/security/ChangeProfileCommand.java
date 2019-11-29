package pro.buildmysoftware.spring.security;

import java.util.Objects;

public class ChangeProfileCommand {

	private String id;
	private String newName;

	@Override
	public String toString() {
		return "ChangeProfileCommand{" + "id='" + id + '\'' + ", " +
			"newName='" + newName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ChangeProfileCommand that = (ChangeProfileCommand) o;
		return Objects.equals(id, that.id) && Objects
			.equals(newName, that.newName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, newName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}
}

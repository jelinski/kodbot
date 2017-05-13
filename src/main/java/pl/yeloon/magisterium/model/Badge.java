package pl.yeloon.magisterium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "badge")
public class Badge extends BaseEntity {

	@Size(max = 50)
	@Column(name = "polish_description", nullable = false)
	String polishDescription;

	@Size(max = 50)
	@Column(name = "english_description", nullable = false)
	String englishDescription;

	// TODO - poki co nazwa odpowiedniego pliku w resources
	@Column(name = "image_url", nullable = false)
	String imageUrl;

	public String getPolishDescription() {
		return polishDescription;
	}

	public void setPolishDescription(String polishDescription) {
		this.polishDescription = polishDescription;
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Badge) {
			return this.getId() == ((Badge) obj).getId();
		}
		return false;
	}

}

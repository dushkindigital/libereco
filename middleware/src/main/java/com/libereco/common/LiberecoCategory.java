/**
 * 
 */
package com.libereco.common;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author rrached
 *
 * According to the Libereco Technical Specification, pp. 9
 * A Category is simply an ordered list of CategoryNodes that describe a node
 * in the marketplace taxonomy.
 * 
 * Enums requires sinlge-arg String constructor to deal with the (less than) amazing
 * limitations of the JPA {@link javax.persistence.Enumerated} annotation!
 * Alternately make sure to use {@link Enumerated} with value {@link EnumType} set to STRING.
 */
public enum LiberecoCategory {
	CAT_BOOKS("CAT_BOOKS"),
	CAT_MOVIES_MUSIC_GAME("CAT_MOVIES_MUSIC_GAME"),
	CAT_COMPUTER_OFFICE("CAT_COMPUTER_OFFICE"),
	CAT_ELECTRONICS("CAT_ELECTRONICS"),
	CAT_HOME_GARDEN_PETS("CAT_HOME_GARDEN_PETS"),
	CAT_GROCERY_HEALTH_BEAUTY("CAT_GROCERY_HEALTH_BEAUTY"),
	CAT_TOYS_KIDS_BABY("CAT_TOYS_KIDS_BABY"),
	CAT_CLOTHING_SHOES_JEWELLERY("CAT_CLOTHING_SHOES_JEWELLERY"),
	CAT_SPORTS_OUTDOOR("CAT_SPORTS_OUTDOOR");
	
	private String category;
	
	LiberecoCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return category;
	}
}

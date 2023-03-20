package org.example.object;

import org.example.type.DrinkName;
import org.example.type.EssenceType;
import org.example.type.LiquidType;
import org.example.type.ToppingType;

public class Drink {
	DrinkName drinkName;
	EssenceType essenceType;
	LiquidType liquidType;
	ToppingType toppingType;
	int price;

	/**
	 * 생성자.
	 *
	 * @param drinkName : 음류 이름
	 * @param essenceType : 원료 타입
	 * @param liquidType : 액체 타입
	 * @param toppingType : 토핑 타입
	 * @param price : 가격
	 */
	public Drink(DrinkName drinkName, EssenceType essenceType, LiquidType liquidType,
				 ToppingType toppingType, int price) {
		this.drinkName = drinkName;
		this.essenceType = essenceType;
		this.liquidType = liquidType;
		this.toppingType = toppingType;
		this.price = price;
	}

	public DrinkName getDrinkName() {
		return drinkName;
	}

	public EssenceType getEssenceType() {
		return essenceType;
	}

	public LiquidType getLiquidType() {
		return liquidType;
	}

	public ToppingType getToppingType() {
		return toppingType;
	}

	public int getPrice() {
		return price;
	}
}

package com.neptum.BankAccountApi.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tb_type_card")
public class CardType 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public CardType() { }
	
	public CardType(
		final String type)
	{
		this.type = Type.valueOf(type);
	}

	public Integer getId() { return id; }

	public Type getType() { return type; }
	
	public void setId(final Integer id) { this.id = id; }
}
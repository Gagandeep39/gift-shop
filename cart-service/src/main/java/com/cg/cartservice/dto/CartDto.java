package com.cg.cartservice.dto;

import java.util.List;

import com.cg.cartservice.entities.UserDetails;

public class CartDto {
	
	//private int userId;

	 

    private List<ItemDto> itemDtoList;



public CartDto() {}    
    
	public CartDto( List<ItemDto> itemDtoList) {
		super();

		this.itemDtoList = itemDtoList;
	}



	






	public List<ItemDto> getItemDtoList() {
		return itemDtoList;
	}



	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}

 

}

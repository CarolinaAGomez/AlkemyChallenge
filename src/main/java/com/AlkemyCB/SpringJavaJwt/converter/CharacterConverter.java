package com.AlkemyCB.SpringJavaJwt.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.AlkemyCB.SpringJavaJwt.entity.Characters;
import com.AlkemyCB.SpringJavaJwt.model.CharacterModel;



@Component
public class CharacterConverter {
	
	public CharacterModel entityToModel(Characters  character) {
		return new CharacterModel(character.getImage(),character.getName());	
	}
	
	public Characters modelToEntity(CharacterModel  characterModel) {
		return new Characters(characterModel.getImagen(),characterModel.getNombre());	
	}
	
	
	

}

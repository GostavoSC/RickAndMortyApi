package com.gustavosc.rickandmortyapi.data.repository.dto

import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.model.Info
import java.util.ArrayList

class ListOfCharactersDto (
    var info: Info,
    var results: ArrayList<Character>
)
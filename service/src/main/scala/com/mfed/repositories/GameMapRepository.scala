package com.mfed.repositories

import com.mfed.model.GameMap
import org.springframework.data.mongodb.repository.MongoRepository
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 01:22.
  * mfedorowiat@gmail.com
  */
trait GameMapRepository extends MongoRepository[GameMap, String]{
}



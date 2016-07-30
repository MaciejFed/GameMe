package com.mfed.repositories



import com.mfed.model.GameMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.stereotype.Repository

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 15:22.
  * mfedorowiat@gmail.com
  */
@Repository
class GameMapRepositoryCustom {

  @Autowired
  private val mongoTemplate: MongoTemplate = null

  def findByLevelNumber(levelNumber: Integer): GameMap = {
    val query = new Query(Criteria.where("levelNumber").is(levelNumber))

    mongoTemplate.findOne(query, classOf[GameMap])
  }
}

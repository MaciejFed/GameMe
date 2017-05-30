package com.mfed.repositories



import com.mfed.model.Level
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
class LevelRepositoryCustom {

  @Autowired
  private val mongoTemplate: MongoTemplate = null

  def findByLevelName(levelNumber: String): Level = {
    val query = new Query(Criteria.where("name").is(levelNumber))

    mongoTemplate.findOne(query, classOf[Level])
  }
}

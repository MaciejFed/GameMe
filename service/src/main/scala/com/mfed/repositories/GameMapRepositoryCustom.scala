package com.mfed.repositories

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

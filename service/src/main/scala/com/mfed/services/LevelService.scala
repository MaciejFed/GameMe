package com.mfed.services

import com.mfed.model.Level
import com.mfed.repositories.LevelRepositoryCustom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 21:00.
  * mfedorowiat@gmail.com
  */
@Service
class LevelService{

  @Autowired
  val levelRepository: LevelRepositoryCustom = null

  def getLevel(levelNumber: Int): Level = {
    levelRepository.findByLevelNumber(levelNumber)
  }
}

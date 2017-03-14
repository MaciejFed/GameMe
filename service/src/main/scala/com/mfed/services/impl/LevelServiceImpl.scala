package com.mfed.services.impl

import com.mfed.model.Level
import com.mfed.repositories.LevelRepositoryCustom
import com.mfed.services.LevelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 21:00.
  * mfedorowiat@gmail.com
  */
@Service
class LevelServiceImpl extends LevelService{

  @Autowired
  val levelRepository: LevelRepositoryCustom = null

  override def getLevel(levelNumber: Int): Level = {
    levelRepository.findByLevelNumber(levelNumber)
  }
}

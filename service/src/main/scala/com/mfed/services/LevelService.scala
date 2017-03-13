package com.mfed.services

import com.mfed.model.Level

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 21:00.
  * mfedorowiat@gmail.com
  */
trait LevelService {
  def getLevel(levelNumber: Int): Level
}

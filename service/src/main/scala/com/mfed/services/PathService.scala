package com.mfed.services

/**
  * Created by Maciej Fedorowiat 
  * on 21/08/2016 20:18.
  * mfedorowiat@gmail.com
  */
trait PathService {
  def validatePathInLevel(levelNumber: Int, startPoint: (Int, Int), path: List[String]): List[String]
}

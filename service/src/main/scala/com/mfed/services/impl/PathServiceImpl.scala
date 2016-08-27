package com.mfed.services.impl

import com.mfed.api.exceptions.WrongDirectionException
import com.mfed.model.{GameMap, Obstacle}
import com.mfed.repositories.GameMapRepositoryCustom
import com.mfed.services.PathService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 21/08/2016 20:24.
  * mfedorowiat@gmail.com
  */
@Service
class PathServiceImpl extends PathService{

  @Autowired
  private val gameMapRepositoryCustom: GameMapRepositoryCustom = null

  override def validatePathInLevel(levelNumber: Int, startPoint: (Int, Int), path: List[String]): List[String] = {
    val gameMap = gameMapRepositoryCustom.findByLevelNumber(levelNumber)
    var point = startPoint

    val index = path.map(x => asd(x)).map(p => {
      point = add(point, p)
      if(isAnObstacle(point, gameMap))
        (-1, -1)
      else
        point
    }).indexOf((-1, -1))

    if (index != -1) path.slice(0, index) else path
  }
  def asd(direction: String): (String, Int, Int) = {
    direction.toLowerCase match {
      case "left" => ("left", -1, 0)
      case "right" => ("right", 1, 0)
      case "up" => ("up", 0, -1)
      case "down" => ("down", 0, 1)
      case _ => throw WrongDirectionException(s"not valid direction")
    }
  }

  def add(point: (Int, Int), direction: (String, Int, Int)) = (point._1 + direction._2, point._2 + direction._3)

  def isAnObstacle(point: (Int, Int), gameMap: GameMap) : Boolean= {
    gameMap.obstacles.contains(Obstacle(point._1, point._2))
  }


}

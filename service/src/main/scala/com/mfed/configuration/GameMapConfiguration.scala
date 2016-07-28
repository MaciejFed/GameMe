package com.mfed.configuration

import com.mongodb.{Mongo, MongoClient, MongoCredential, ServerAddress}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

import scala.collection.JavaConversions._
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 02:16.
  * mfedorowiat@gmail.com
  */
@Configuration
@EnableMongoRepositories(basePackages = Array("com.mfed.repositories"))
@ComponentScan(Array("com.mfed")) class GameMapConfiguration extends AbstractMongoConfiguration {
  private val mongoPort: Integer = 27017
  private val mongoHost: String = "localhost"
  private val mongoDBName: String = "GameMe"
  private val user: String = "root"
  private val password: String = "root"

  @Bean
  @throws[Exception]
  def mongo: Mongo = {
    val mongoCredential: MongoCredential = MongoCredential.createCredential(user, mongoDBName, password.toCharArray)
    val addresses: List[ServerAddress] = List(new ServerAddress(mongoHost + ":" + mongoPort))
    val mongoCredentials: List[MongoCredential] = List(mongoCredential)

    new MongoClient(addresses, mongoCredentials)
  }

  protected def getDatabaseName: String = mongoDBName

  override protected def getMappingBasePackage: String = "com.fedo.model"
}

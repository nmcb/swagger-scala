package org.zalando.swagger

import java.io.File

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Parser {
  val factory = new com.fasterxml.jackson.dataformat.yaml.YAMLFactory()
  def parse(file: File): Swagger = {
    val mapper = new ObjectMapper(factory)
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(file, classOf[Swagger])
  }
}

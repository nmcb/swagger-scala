package org.zalando.swagger

import java.io.File

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule

trait Parser {
  def parse(file: File): Swagger
}

private[swagger] abstract class SwaggerParser extends Parser {
  def factory: JsonFactory
  def parse(file: File): Swagger = {
    val mapper = new ObjectMapper(factory)
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(file, classOf[Swagger])
  }
}

object YamlParser extends SwaggerParser {
  val factory = new YAMLFactory()
}

object JsonParser extends SwaggerParser {
  val factory = new JsonFactory()
}

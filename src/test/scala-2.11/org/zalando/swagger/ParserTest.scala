package org.zalando.swagger

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParserTest extends FlatSpec with Matchers {

  val fixtures = Seq(
    new File("src/test/resources/uber.api.json"),
    new File("src/test/resources/uber.api.yaml"),
    new File("src/test/resources/minimal.api.yaml"),
    new File("src/test/resources/minimal.api.json"),
    new File("src/test/resources/heroku.petstore.api.yaml"),
    new File("src/test/resources/heroku.petstore.api.json")
  )

  fixtures.filter(_.getName.endsWith(".json")).foreach { file =>
    "The json swagger parser" should s"read the ${file.getName} specification" in {
      JsonParser.parse(file)
    }
  }

  fixtures.filter(_.getName.endsWith(".yaml")).foreach { file =>
    "The yaml swagger parser" should s"read the ${file.getName} specification" in {
      YamlParser.parse(file)
    }
  }
}

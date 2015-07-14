package org.zalando.swagger

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParserTest extends FlatSpec with Matchers {

  val fixtures = Seq(
    new File("src/test/resources/uber.api.json"),
    new File("src/test/resources/uber.api.yaml")
  )

  "The json swagger parser" should "read swagger json specifications" in {
    fixtures.filter(_.getName.endsWith(".json")).map(JsonParser.parse)
  }

  "The yaml swagger parser" should "read swagger yaml specifications" in {
    fixtures.filter(_.getName.endsWith(".yaml")).map(YamlParser.parse)
  }
}

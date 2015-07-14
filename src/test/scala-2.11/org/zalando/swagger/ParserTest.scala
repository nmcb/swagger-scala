package org.zalando.swagger

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParserTest extends FlatSpec with Matchers {
  
  val fixture = new File("src/test/resources/uber.api.yaml")

  "The swagger parser" should "read the uber specification" in {
    val swagger = Parser.parse(fixture) 
  }
}

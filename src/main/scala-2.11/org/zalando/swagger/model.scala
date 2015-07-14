package org.zalando.swagger

import com.fasterxml.jackson.annotation.JsonProperty

sealed trait Spec
case class Swagger(swagger: String, info: Info, host: String, schemes: List[String], basePath: String, consumes: List[String], produces: List[String], paths: Map[String, Path], definitions: Map[String,Definition]) extends Spec
case class Info(title: String, description: String, version: String) extends Spec
case class Path(get: Operation, parameters: List[Parameter]) extends Spec
case class Operation(parameters: List[Parameter], tags: List[String], description: String, summary: String, operationId: String, extensions: Map[String, String], responses: Map[String, Response]) extends Spec
case class Parameter(name: String, @JsonProperty("type") kind: String, format: String, description: String, access: String, in: String, required: Boolean) extends Spec
case class Response(description: String, schema: Schema, headers: Map[String, Header]) extends Spec
case class Schema(typename: String, @JsonProperty("type") kind: String, @JsonProperty("$ref") ref: String, format: String, description: String, required: List[String], items: Items) extends Spec
case class Header(typename: String, format: String, description: String, items: Items) extends Spec
case class Items(typename: String, format: String, items: Items, @JsonProperty("$ref") ref: String) extends Spec
case class Definition(properties: Map[String, Property]) extends Spec
case class Property(@JsonProperty("type") kind: String, description: String, format: String, items: Items) extends Spec
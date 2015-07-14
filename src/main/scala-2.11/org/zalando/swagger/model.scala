package org.zalando.swagger

import com.fasterxml.jackson.annotation.JsonProperty

sealed trait API

case class Swagger(
  swagger: String,
  info: Info,
  host: String,
  schemes: List[String],
  basePath: String,
  consumes: List[String],
  produces: List[String],
  paths: Map[String, Path],
  definitions: Map[String, Definition]
) extends API

case class Info(
  title: String, 
  description: String, 
  version: String
) extends API

case class Path(
  get: Operation,
  parameters: List[Parameter]
) extends API

case class Operation(
  parameters: List[Parameter], 
  tags: List[String], 
  description: String, 
  summary: String, 
  operationId: String, 
  extensions: Map[String, String], 
  responses: Map[String, Response]
) extends API

case class Parameter(
  name: String, 
  @JsonProperty("type") kind: String, 
  format: String, 
  description: String, 
  access: String, 
  in: String, 
  required: Boolean
) extends API

case class Response(
  description: String, 
  schema: Schema, 
  headers: Map[String, Header]
) extends API

case class Schema(
  typename: String, 
  @JsonProperty("type") kind: String, 
  @JsonProperty("$ref") ref: String, 
  format: String, 
  description: String, 
  required: List[String], 
  items: Items
) extends API

case class Header(
  typename: String, 
  format: String, 
  description: String, 
  items: Items
) extends API

case class Items(
  typename: String, 
  format: String, 
  items: Items, 
  @JsonProperty("$ref") ref: String
) extends API

case class Definition(
  properties: Map[String, Property]
) extends API

case class Property(
  @JsonProperty("type") kind: String,
  description: String,
  format: String, 
  items: Items
) extends API
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
  security: List[Map[String,List[String]]],
  definitions: Map[String, Definition],
  securityDefinitions: Map[String, SecurityDefinition]
) extends API

case class Info(
  title: String, 
  description: String, 
  version: String,
  termsOfService: String,
  contact: Contact,
  license: License
) extends API

case class Contact(
  name: String,
  email: String,
  url: String
  ) extends API

case class License(
  name: String,
  url: String
) extends API

case class Path(
  get: Operation,
  post: Operation,
  put: Operation,
  delete: Operation,
  parameters: List[Parameter]
) extends API

case class Operation(
  parameters: List[Parameter], 
  tags: List[String], 
  description: String, 
  summary: String, 
  operationId: String, 
  extensions: Map[String, String], 
  responses: Map[String, Response],
  produces: List[String],
  consumes: List[String],
  security: List[Map[String,List[String]]]
) extends API

case class Parameter(
  name: String,
  @JsonProperty("type") kind: String,
  @JsonProperty("$ref") ref: String,
  schema: Schema,
  format: String, 
  description: String, 
  access: String, 
  in: String, 
  required: Boolean,
  default: String,
  minimum: String,
  maximum: String,
  items: Items,
  collectionFormat: String
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
  title: String,
  format: String, 
  description: String, 
  required: List[String], 
  items: Items,
  properties: Map[String,Property],
  enum: List[String]
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
  @JsonProperty("$ref") ref: String,
  @JsonProperty("type") kind: String
) extends API

case class Definition(
  properties: Map[String, Property],
  required: List[String]
) extends API

case class Property(
  @JsonProperty("type") kind: String,
  @JsonProperty("$ref") ref: String,
  description: String,
  format: String, 
  items: Items,
  example: String,
  properties: Map[String,Property]
) extends API

case class SecurityDefinition(
  @JsonProperty("type") kind: String,
  name: String,
  description: String,
  in: String,
  authorizationUrl: String,
  flow: String,
  scopes: Map[String,String],
  tokenUrl: String
) extends API
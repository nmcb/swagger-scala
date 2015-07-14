package org.zalando.swagger

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration

sealed trait API

case class Swagger(
  swagger: String,
  info: Info,
  host: String,
  basePath: String,
  @JsonScalaEnumeration(classOf[SchemeType]) schemes: List[Scheme.Value],
  consumes: List[String],
  produces: List[String],
  paths: Map[String, Path],
  definitions: Map[String, Definition],
  parameters: Map[String, Parameter],
  responses: Map[String, Response],
  securityDefinitions: Map[String, SecurityDefinition],
  security: List[Map[String,List[String]]],
  tags: List[Tag]
) extends API

private[swagger] class SchemeType extends TypeReference[Scheme.type]
case object Scheme extends Enumeration {
  type Scheme = Value
  val HTTP = Value("http")
  val HTTPS = Value("https")
  val WS = Value("ws")
  val WSS = Value("wss")
}

case class Info(
  title: String, 
  description: String,
  termsOfService: String,
  contact: Contact,
  license: License,
  version: String
) extends API

case class Contact(
  name: String,
  url: String,
  email: String
) extends API

case class License(
  name: String,
  url: String
) extends API

case class Path(
  @JsonProperty("$ref") ref: String,
  get: Operation,
  put: Operation,
  post: Operation,
  delete: Operation,
  options: Operation,
  head: Operation,
  patch: Operation,
  parameters: List[Parameter]
) extends API

case class Operation(
  tags: List[String],
  summary: String,
  description: String,
  externalDocs: ExternalDocumentation,
  operationId: String,
  consumes: List[String],
  produces: List[String],
  parameters: List[Parameter], // TODO should be ParameterOrReference
  responses: Map[String, Response], // TODO should be OperationResponses
  @JsonScalaEnumeration(classOf[SchemeType]) schemes: List[Scheme.Value],
  deprecated: Boolean,
  security: List[Map[String,List[String]]]
) extends API

trait ParameterOrReference extends API

case class Parameter(
  name: String,
  in: String,
  description: String,
  required: Boolean,
  // if in is "body"
  schema: Schema,
  // if in is any other value than body 
  @JsonProperty("type") kind: String,
  format: String,
  allowEmptValue: Boolean,
  items: Items,
  collectionFormat: String,
  default: String,
  maximum: String,
  exclusiveMaximum: Boolean,
  minimum: String,
  exclusiveMinimum: Boolean,
  maxLength: Int,
  minLength: Int,
  pattern: String,
  maxItems: Int,
  minItems: Int,
  uniqueItems: Boolean,
  enum: List[String],
  multipleOf: Int
) extends ParameterOrReference

case class Reference(
  @JsonProperty("$ref") ref: String
) extends ParameterOrReference

case class Response(
  description: String, 
  schema: Schema, 
  headers: Map[String, Header],
  examples: Map[String, Any]
) extends API

case class Schema(
  discriminator: String,
  readOnly: Boolean,
  xml: Xml,
  externalDocs: ExternalDocumentation,
  example: Any,
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
  description: String,
  @JsonProperty("type") kind: String, // TODO should be enum
  format: String, 
  items: Items,
  collectionFormat: String,
  default: String,
  maximum: Int,
  exclusiveMaximum: Boolean,
  minimum: Int,
  exclusiveMinimum: Boolean,
  maxLength: Int,
  minLength: Int,
  pattern: String,
  maxItems: Int,
  minItems: Int,
  uniqueItems: Boolean,
  enum: List[String],
  multipleOf: Int
) extends API

case class Items(
  @JsonProperty("type") kind: String, // TODO should be enum
  format: String, 
  items: Items,
  @JsonProperty("$ref") ref: String,
  collectionFormat: String,
  default: String,
  maximum: Int,
  exclusiveMaximum: Int,
  minimum: Int,
  exclusiveMinimum: Int,
  maxLength: Int,
  minLength: Int,
  pattern: String,
  maxItems: Int,
  minItems: Int,
  uniqueItems: Boolean,
  enum: List[String],
  multipleOf: Int
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
  description: String,
  name: String,
  in: String,
  flow: String,
  authorizationUrl: String,
  tokenUrl: String,
  scopes: Map[String,String]
) extends API

case class Tag(
  name: String,
  description: String,
  externalDocs: ExternalDocumentation
) extends API

case class ExternalDocumentation(
  description: String,
  url: String
) extends API

case class Xml(
  name: String,
  namespace: String,
  prefix: String,
  attribute: Boolean,
  wrapped: Boolean
) extends API
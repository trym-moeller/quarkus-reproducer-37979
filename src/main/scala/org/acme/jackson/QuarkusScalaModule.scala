package org.acme.jackson

import com.fasterxml.jackson.module.scala._
import com.fasterxml.jackson.module.scala.deser._
import com.fasterxml.jackson.module.scala.introspect._

class QuarkusScalaModule
    extends JacksonModule
      with IteratorModule
      with EnumerationModule
      with OptionModule
      with SeqModule
      with IterableModule
      with TupleModule
      with MapModule
      with SetModule
      with ScalaNumberDeserializersModule
      with ScalaObjectDeserializerModule
      with ScalaAnnotationIntrospectorModule
      with EitherModule
      with SymbolModule {
    override def getModuleName: String = "QuarkusScalaModule"
  }

object QuarkusScalaModule

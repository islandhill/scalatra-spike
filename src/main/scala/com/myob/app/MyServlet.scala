package com.myob.app

import com.myob.service.{Flower, FlowerService}
import org.scalatra._

import org.json4s.{DefaultFormats, Formats}

import org.scalatra.json._


class MyServlet extends ScalatraspikeStack with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal

  before() {
    println("before...")
    contentType = formats("json")
  }

  after() {
    println("after...")
  }

  get("/notFound") {
    notFound {
      <h1>Not found, Dude</h1>
    }
  }

  get("/") {
    <html>
      <body>
        <h1>Hello, world!!</h1>
        Say
        <a href="hello-scalate">hello to Scalate!!</a>
        .
      </body>
    </html>
  }

  get("/guess/:id") {
    println("in get")
    val id: String = params.getOrElse("id", halt(400))
    println(s"id: $id")
  }

  post("/guess/:id") {
    println("in post")
    params("id") match {
      case "10" => "got me!"
      case _ => "whatever"
    }

  }

  get("/flowers") {
    FlowerService.all
  }

  post("/flowers") {
    println("receiving json...")
    val flower: Flower = parsedBody.extract[Flower]
    println(flower.name + ": " + flower.variety)
  }

}

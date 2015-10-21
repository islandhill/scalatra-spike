package com.myob.service

case class Flower(name: String, variety: String)

object FlowerService {

  var all = List(
    Flower("Yellow", "Tulip"),
    Flower("Red", "Rose")
  )

}

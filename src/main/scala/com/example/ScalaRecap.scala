package com.example

object ScalaRecap extends App{

  val aCondition: Boolean = false
  def myFunction(x: Int) =  {
    if(x > 4) 100 else 200
  }

  val someRespone = myFunction(400)
  println(someRespone)

  trait Carnivore {
    //abstrach method
    def eat(food: String) : Unit
  }

  class Animal extends Carnivore {
    override def eat(food: String): Unit = {
      println(s"Im eating $food")
    }
  }
  object Carnivore

  val animal = new Animal()
  animal.eat("Apples")

  //partial functions
  val partialFunction: PartialFunction[Int,Int] = {
    case 1 => 42
    case 2 => 65
    case _ => 999
  }

  val partialResult = partialFunction(12312312)
  println(s"Partial function result $partialResult")

  //Type aliases
  type AkkaReceive = PartialFunction[Any,Unit]
  def receive: AkkaReceive = {
    case 1 => println("Something")
    case _ => println("Other")
  }
  println(s"Testing type aliases ${receive(1)}")

  //Implicits!
  implicit val adasdadasda = 6000
  def setTimeout(f: () => Unit)(implicit timeoutx: Int) = {
    println(s"The value of the implicit timeout $timeoutx")
  }
  setTimeout(()=> println("timeout"))

  //conversions
  //1) implicit methods
  case class Person(name: String) {
    def greet: String = s"Hi, my name is $name"
  }

  implicit def fromStringToPerson(name: String) = Person(name)
  "Peter".greet
  // fromStringToPerson("Person").greet

  // 2) implicit classes
  implicit class Dog(name: String) {
    def bark = println("Bark!")
  }
  "Lassier".bark
  //new Dog("Lassie").bark

  //implicit organizations
  //local scope
  implicit val numberOrdering: Ordering[Int] = Ordering.fromLessThan(_ >_ )
  println(s"User custom ordering ${List(1,5,8,9).sorted}")

  //imported scope

  // companion objects of the types involved in the call
  object Person {
    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.name.compareTo(b.name)<  0)
  }

  val personList = List(Person("Xanadu2"),Person("Bob"), Person("Xanadu"),Person("Petrovsky") , Person("John"), Person("Xiomara"), Person("Zendaya"), Person("Anastassia"))
  println(s"Testing implicit sorting from companion object ${personList.sorted}")


}

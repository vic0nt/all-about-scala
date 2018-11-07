import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


val eventualA = Future(3)
val eventualB = Future(5)
val eventualC = Future(7)

// flatMap version. Returns Future[Int]
val aB = eventualA flatMap { aValue => eventualB map (_ * aValue) } // Future[Int]
val aBC = aB flatMap { aBValue => eventualC map (_ * aBValue) } // Future[Int]
Await.result(aB, 1 second) //res0: Int = 15
Await.result(aBC, 1 second) //res1: Int = 105

//map version. Returns Future[Future[Int]]
val aBMapVersion = eventualA map { aValue => eventualB map (_ * aValue) } // Future[Future[Int]]
val rez = Await.result(aBMapVersion, 1 second) // Future[Int]
//wait for a result of result
Await.result(rez, 1 second) //res2: Int = 15
package ch3

/**
  * Created by vicont on 29.05.2016.
  */
object Ex4 extends App {
  print(List.dropWhile(List(1,2,3,4,5))(x => x < 3))
}

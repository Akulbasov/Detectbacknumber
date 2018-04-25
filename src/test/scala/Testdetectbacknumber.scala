package test.tinkoff.detectbacknumber
import org.scalatest.FlatSpec

class Testdetectbacknumber extends FlatSpec{

  it should "return failure when key int with string" in {
    val exampleData = Map(
      "1" -> "2016.09.11",
      "2g" -> "2016.09.12",
      "3g" -> "2016.09.13",
      "4g" -> "2016.09.14",
      "7" -> "2016.09.15",
      "8" -> "2016.09.16",
      "9" -> "2016.09.17"
    )
    assert(Detectbacknumber.mapStr2Date(exampleData).isFailure)
  }

  it should "return failure with default value day, month " +
    "when format empty" in {
    val exampleData = Map(
      "1" -> "2016.09.11",
      "6" -> "2016.09.08",
      "7" -> "2016.09.15",
      "8" -> "2016.09.16",
      "9" -> "2016.09.17"
    )
    assert(Detectbacknumber.mapStr2Date(exampleData,"").isFailure)
  }

  it should "return success with default value day, month " +
    "when format not full" in {
    val exampleData = Map(
      "1" -> "2016.09.11",
      "6" -> "2016.09.08",
      "7" -> "2016.09.15",
      "8" -> "2016.09.16",
      "9" -> "2016.09.17"
    )
    assert(Detectbacknumber.mapStr2Date(exampleData,"yyyy").isSuccess)
  }


  it should "return failure when date is not correct" in {
    val exampleData = Map(
      "1" -> "201.09.11",
      "6" -> "2016.09sasdas.08",
      "7" -> "2016.09.15",
      "8" -> "2016.09.16",
      "9" -> "2016.09.17"
    )
      assert(Detectbacknumber.mapStr2Date(exampleData).isFailure)
  }

  it should "return Iterable (6, 5)" in {
    val exampleData = Map(
      "1" -> "2016.09.11",
      "2" -> "2016.09.12",
      "3" -> "2016.09.13",
      "4" -> "2016.09.14",
      "5" -> "2016.09.09",
      "6" -> "2016.09.08",
      "7" -> "2016.09.15",
      "8" -> "2016.09.16",
      "9" -> "2016.09.17"
    )

    assert(
      Detectbacknumber.getBackNumberField(
        Detectbacknumber.mapStr2Date(exampleData).get
      ).equals(Set(5,6))
    )
  }

  it should "return Iterable ()" in {
    val exampleData = Map(
      "1" -> "2016.09.11",
      "2" -> "2016.09.12",
      "3" -> "2016.09.13",
      "4" -> "2016.09.14",
      "7" -> "2016.09.15",
      "8" -> "2016.09.16",
      "9" -> "2016.09.17"
    )

    assert(
      Detectbacknumber.getBackNumberField(
        Detectbacknumber.mapStr2Date(exampleData).get
      ).equals(Set())
    )
  }

  it should "return Iterable (2,5)" in {

    val exampleData = Map(
      "1" -> "2016.09.11",
      "2" -> "2016.09.09",
      "5" -> "2016.09.09",
      "7" -> "2016.09.17"
    )

    assert(
      Detectbacknumber.getBackNumberField(
        Detectbacknumber.mapStr2Date(exampleData).get
      ).equals(Set(2,5))
    )
  }

  it should "return Iterable () with map \"1\" -> \"2016.09.11\"" in {
    val exampleData = Map(
      "1" -> "2016.09.11"
    )
    assert(
      Detectbacknumber.getBackNumberField(
        Detectbacknumber.mapStr2Date(exampleData).get
      ).equals(Set())
    )

  }



}

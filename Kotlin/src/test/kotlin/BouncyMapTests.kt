import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTests: FunSpec({

    context("Checking for movement possibility") {
        val map: IWorldMap = BouncyMap(5, 5)
        val animal = Animal(Vector2d(-1, -1), map)
        map.canMoveTo(animal.position) shouldBe false
    }
    context("Return object at position") {
        test("No animals") {
            val map: IWorldMap = BouncyMap(5, 5)
            map.objectAt(Vector2d(0, 0)) shouldBe null
        }
        test("Single animal") {
            val map: IWorldMap = BouncyMap(5, 5)
            val animal = Animal(map)
            map.place(animal)
            map.objectAt(animal.position) shouldBe animal
        }
    }
    context("Place animals") {
        test("Two animals at the same position") {
            val map: IWorldMap = BouncyMap(5, 5)
            val animal1 = Animal(map)
            val animal2 = Animal(map)
            map.place(animal1)
            map.place(animal2)
            animal1.position shouldNotBe animal2.position
        }
        test("Animal substitution and updating position") {
            val map: IWorldMap = BouncyMap(1, 1)
            val animal1 = Animal(Vector2d(0, 0), map)
            val animal2 = Animal(Vector2d(0, 0), map)
            map.place(animal1)
            map.place(animal2)
            map.objectAt(animal1.position) shouldBe animal2
        }
        test("Animal out of bounds") {
            shouldThrow<IllegalArgumentException> {
                val map: IWorldMap = BouncyMap(1, 1)
                val animal = Animal(map)
                map.place(animal)
            }
        }
    }
    context("Getting an entity's position") {
        test("Animal's position") {
            val map: IWorldMap = BouncyMap(5, 5)
            val animal = Animal(map)
            map.place(animal)
            map.getPosition(animal) shouldBe Vector2d(2, 2)
        }
    }

})
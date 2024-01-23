import kotlin.random.Random

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class BouncyMapTests : DescribeSpec({

    describe("BouncyMap canMoveTo function") {
        val bouncyMap = BouncyMap(5, 5)

        it("should return true for valid positions within the map") {

            bouncyMap.canMoveTo(Vector2d(2, 2)).shouldBeTrue()
            bouncyMap.canMoveTo(Vector2d(4, 3)).shouldBeTrue()
            bouncyMap.canMoveTo(Vector2d(1, 3)).shouldBeTrue()
            bouncyMap.canMoveTo(Vector2d(4, 2)).shouldBeTrue()
        }

        it("should return true for map corners") {

            bouncyMap.canMoveTo(Vector2d(0, 0)).shouldBeTrue()
            bouncyMap.canMoveTo(Vector2d(4, 0)).shouldBeTrue()
            bouncyMap.canMoveTo(Vector2d(4, 4)).shouldBeTrue()
            bouncyMap.canMoveTo(Vector2d(0, 4)).shouldBeTrue()
        }

        it("should return false for positions outside the map boundaries") {
            val bouncyMap = BouncyMap(4, 9)

            bouncyMap.canMoveTo(Vector2d(6, 2)).shouldBeFalse()
            bouncyMap.canMoveTo(Vector2d(1, 10)).shouldBeFalse()
            bouncyMap.canMoveTo(Vector2d(6, 18)).shouldBeFalse()
            bouncyMap.canMoveTo(Vector2d(0, 9)).shouldBeFalse()
        }

        it("should return false for positions with negative coordinates") {
            bouncyMap.canMoveTo(Vector2d(-1, 2)).shouldBeFalse()
            bouncyMap.canMoveTo(Vector2d(2, -3)).shouldBeFalse()
            bouncyMap.canMoveTo(Vector2d(6, -2)).shouldBeFalse()
            bouncyMap.canMoveTo(Vector2d(-1, -1)).shouldBeFalse()
        }
    }

    describe("BouncyMap place function") {

        it("should place animal on map for valid position") {
            val bouncyMap = BouncyMap(3, 4)

            listOf(Vector2d(2, 3), Vector2d(1, 0), Vector2d(1, 1), Vector2d(0, 2)).forEach {
                bouncyMap.place(Animal(it))
                bouncyMap.isOccupied(it).shouldBeTrue()
            }
        }

        it("should place animal next to occupied place") {
            val bouncyMap = BouncyMap(3, 3)

            val position = Vector2d(1, 1)
            val positionsNeighbours = listOf(Vector2d(1, 2), Vector2d(2, 1), Vector2d(1, 0), Vector2d(0, 1))

            repeat(2) { bouncyMap.place(Animal(position)) }

            val occupiedCount = positionsNeighbours.count { bouncyMap.isOccupied(it) }
            occupiedCount.shouldBe(1)

            repeat(3) { bouncyMap.place(Animal(position)) }

            val allOccupied = positionsNeighbours.all { bouncyMap.isOccupied(it) }
            allOccupied.shouldBe(true)
        }

        it("should bounce animal on place if neither space nor it's neighbours are free") {
            val bouncyMap = BouncyMap(2, 3)

            val position = Vector2d(0, 2)
            repeat(6) { bouncyMap.place(Animal(position)) }

            val cnt = (0..1).sumOf { x ->
                (0..2).count { y -> bouncyMap.isOccupied(Vector2d(x, y)) }
            }
            cnt.shouldBe(6)
        }

        it("should throw IllegalArgumentException when animal tries to be placed outside of boundaries") {
            val bouncyMap = BouncyMap(2, 1)

            val positions = listOf(Vector2d(-1, -1), Vector2d(2, 0), Vector2d(-1, 0), Vector2d(-90, 67)
                , Vector2d(1, 1), Vector2d(9, 7))

            positions.forEach { shouldThrow<IllegalArgumentException> { bouncyMap.place(Animal(it)) } }
        }
    }

    describe("BouncyMap move function") {

        it("should only rotate animals") {
            val bouncyMap = BouncyMap(4, 2)
            val position = Vector2d(2, 0)
            val animal = Animal(position)
            bouncyMap.place(animal)

            (0..100).forEach { _ ->
                bouncyMap.move(animal, if (Random.nextBoolean()) MoveDirection.LEFT else MoveDirection.RIGHT)
                bouncyMap.isOccupied(position).shouldBeTrue()
            }
        }

        it("should make animal go forward/backward") {
            val height = 20
            val bouncyMap1 = BouncyMap(1, height)
            val animal1 = Animal(Vector2d(0, 0))
            bouncyMap1.place(animal1)

            (1..<height).forEach { i ->
                bouncyMap1.move(animal1, MoveDirection.FORWARD)
                bouncyMap1.isOccupied(Vector2d(0, i)).shouldBeTrue()
            }

            (1..height).forEach { i ->
                bouncyMap1.isOccupied(Vector2d(0, height - i)).shouldBeTrue()
                bouncyMap1.move(animal1, MoveDirection.BACK)
            }


            val bouncyMap2 = BouncyMap(100, 100)
            val animal2 = Animal(Vector2d(50, 50))
            bouncyMap2.place(animal2)

            bouncyMap2.move(animal2, MoveDirection.LEFT)
            bouncyMap2.move(animal2, MoveDirection.FORWARD)
            bouncyMap2.isOccupied(Vector2d(49, 50)).shouldBeTrue()
            (0..3).forEach { i ->
                bouncyMap2.move(animal2, MoveDirection.BACK)
                bouncyMap2.isOccupied(Vector2d(50 + i, 50)).shouldBeTrue()
            }

            repeat(2) { bouncyMap2.move(animal2, MoveDirection.RIGHT) }
            bouncyMap2.move(animal2, MoveDirection.FORWARD)
            bouncyMap2.isOccupied(Vector2d(54, 50)).shouldBeTrue()
            bouncyMap2.move(animal2, MoveDirection.BACK)
            bouncyMap2.isOccupied(Vector2d(53, 50)).shouldBeTrue()
            bouncyMap2.move(animal2, MoveDirection.FORWARD)
            bouncyMap2.isOccupied(Vector2d(54, 50)).shouldBeTrue()

            repeat(3) { bouncyMap2.move(animal2, MoveDirection.LEFT) }
            (1..7).forEach { i ->
                bouncyMap2.move(animal2, MoveDirection.FORWARD)
                bouncyMap2.isOccupied(Vector2d(54, 50 - i)).shouldBeTrue()
            }
        }

        it("should keep animal stuck on border") {
            val bouncyMap = BouncyMap(3, 5)
            val position = Vector2d(2, 2)
            val animal = Animal(position)
            bouncyMap.place(animal)

            bouncyMap.move(animal, MoveDirection.RIGHT)
            (0..4).forEach { _ ->
                bouncyMap.move(animal, MoveDirection.FORWARD)
                bouncyMap.isOccupied(position).shouldBeTrue()
            }

            (1..10).forEach { i ->
                bouncyMap.move(animal, MoveDirection.BACK)
                bouncyMap.isOccupied(Vector2d((2 - i).coerceAtLeast(0), 2)).shouldBeTrue()
            }

            bouncyMap.move(animal, MoveDirection.LEFT)
            (1..10).forEach { i ->
                bouncyMap.move(animal, MoveDirection.FORWARD)
                bouncyMap.isOccupied(Vector2d(0, (2 + i).coerceAtMost(4))).shouldBeTrue()
            }

            (1..10).forEach { i ->
                bouncyMap.move(animal, MoveDirection.BACK)
                print(animal.getOrientation())
                print(animal.getPosition())
                print(i)
                bouncyMap.isOccupied(Vector2d(0, (4 - i).coerceAtLeast(0))).shouldBeTrue()
            }
        }
    }
})
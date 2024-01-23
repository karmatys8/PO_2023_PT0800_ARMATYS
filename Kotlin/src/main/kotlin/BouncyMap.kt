import java.lang.RuntimeException
import java.util.*
import kotlin.random.Random;

class BouncyMap(private val width: Int, private val height: Int) : WorldMap<Animal, Vector2d> {
    private val animalsMap: MutableMap<Vector2d, Animal> = mutableMapOf()
    private val possibleMoves = listOf(MapDirection.NORTH.toUnitVector(), MapDirection.EAST.toUnitVector(),
        MapDirection.SOUTH.toUnitVector(), MapDirection.WEST.toUnitVector())

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0..<width && position.y in 0..<height
    }

    override fun place(animal: Animal) {
        val animalPosition = animal.getPosition()
        if (!canMoveTo(animalPosition)) {
            throw IllegalArgumentException("Invalid position for placing an animal")
        }

        if (animalsMap.containsKey(animalPosition)) {
            val position = handleCollision(animal)
            animalsMap[position] = animal
        } else {
            animalsMap[animalPosition] = animal
        }
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPosition = animal.getPosition()
        animal.move(direction, this)
        if (animal.getPosition() != oldPosition) {
            animalsMap.remove(oldPosition)
            place(animal)
        }
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animalsMap.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Optional<Animal> {
        return Optional.ofNullable(animalsMap[position])
    }

    override fun getOrderedAnimals(): Collection<Animal> {
        return animalsMap.values.toList()
    }

    private fun handleCollision(animal: Animal): Vector2d {
        val position = animal.getPosition()
        val shuffledMoves = possibleMoves.shuffled()

        for (move in shuffledMoves) {
            val newPosition = position + move
            if (canMoveTo(newPosition) && !animalsMap.containsKey(newPosition)) {
                return newPosition
            }
        }

        // if no neighbouring tiles are available we begin bouncing (generating position)
        var newPosition = animalsMap.randomFreePosition(Vector2d(width, height))
        if (newPosition == null) {
            newPosition = animalsMap.randomPosition()
        }
        if (newPosition == null) {
            throw RuntimeException("Animal got null as placement")
        }

        return newPosition
    }
}
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
            handleCollision(animal)
        } else {
            animalsMap[animalPosition] = animal
        }
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val animalPosition = animal.getPosition()
        val newPosition = when (direction) {
            MoveDirection.RIGHT -> animalPosition + Vector2d(1, 0)
            MoveDirection.LEFT -> animalPosition + Vector2d(-1, 0)
            MoveDirection.FORWARD -> animalPosition + animal.getOrientation().toUnitVector()
            MoveDirection.BACK -> animalPosition - animal.getOrientation().toUnitVector()
        }

        if (canMoveTo(newPosition)) {
            animalsMap.remove(animalPosition)
            animal.setPosition(newPosition)
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

    private fun handleCollision(animal: Animal) {
        val newPosition = findFreePosition(animal.getPosition())
        animal.setPosition(newPosition)
        place(animal)
    }

    private fun findFreePosition(position: Vector2d): Vector2d {
        val shuffledMoves = possibleMoves.shuffled()

        for (move in shuffledMoves) {
            val newPosition = position + move
            if (canMoveTo(newPosition) && !animalsMap.containsKey(newPosition)) {
                return newPosition
            }
        }

        // If all adjacent positions are occupied, return a random position within the map
        return Vector2d(Random.nextInt(width), Random.nextInt(height))
    }
}
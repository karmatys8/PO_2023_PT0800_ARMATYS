import java.util.*

interface WorldMap<Animal, Vector2d> : MoveValidator<Vector2d> {
    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     * @throws IllegalArgumentException if the position is already occupied.
     */
    @Throws(IllegalArgumentException::class)
    fun place(animal: Animal)

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    fun move(animal: Animal, direction: MoveDirection)

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    fun isOccupied(position: Vector2d): Boolean

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    fun objectAt(position: Vector2d): Optional<Animal>

    fun getOrderedAnimals(): Collection<Animal>
}
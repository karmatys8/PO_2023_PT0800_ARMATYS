data class Animal(private val initialPosition: Vector2d = Vector2d(2, 2)) {
    private var orientation: MapDirection = MapDirection.NORTH
    private var position: Vector2d = initialPosition

    override fun toString(): String {
        return orientation.toString()
    }

    fun isAt(position: Vector2d): Boolean {
        return this.position == position
    }

    fun move(direction: MoveDirection, validator: MoveValidator<Vector2d>) {
        when (direction) {
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.FORWARD -> {
                val newPosition = position + orientation.toUnitVector()
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
            MoveDirection.BACK -> {
                val newPosition = position - orientation.toUnitVector()
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
        }
    }

    fun getOrientation(): MapDirection {
        return orientation
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Animal) return false

        return orientation == other.orientation && position == other.position
    }

    override fun hashCode(): Int {
        return listOf(orientation, position).hashCode()
    }

    fun getPosition(): Vector2d {
        return position
    }

    fun setPosition(newPosition: Vector2d) {
        position = newPosition
    }
}